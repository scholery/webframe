var rootUrl = 'admin/users/';
var _domainId = 2;
($(function() {
	loadGrid();
}));
var userGrid = $("#userGrid");
var dialog = $('#userEditDialog');
var userRoleDialog = $('#userRoleDialog');

function loadGrid(){
	userGrid.jqGrid({
        url: rootUrl + 'details',
		datatype: 'json',
        colNames: ['ID','用户名','登录账号','类型', '备注',''],
        colModel: [
			{ name: 'id', width: 30, hidden:true,key:true},
            { name: 'userName', width: 250, align: 'left'},
			{ name: 'userAccount', width: 150, align: 'left'},
            { name: 'userType', width: 90 ,align: 'left', hidden:true},
            { name: 'remarks', width: 250 ,align: 'left'},
            { name: 'status', width: 90, align: 'left', formatter:showOperation}
        ],
        cmTemplate: {sortable: false},
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#pager',
        gridview: true,
        shrinkToFit:false,
        rownumbers:true,
        multiselect:true,
        hoverrows: false,
        autoencode: true,
        ignoreCase: true,
        viewrecords: true,
        height: '100%',
        width: 900
    });
}

/**
 * operations
 */
function showOperation(cellvalue, options, rowObject){
	var div1 = $("<div>");
	var div = $("<div>").addClass("operation-cell");
	var temp = $("<img>").css("cursor","pointer")
						 .css("vertical-align"," middle")
						 .css("margin","-2px 2px 1px 0px");
	var edit = temp.clone();
	edit.attr("src",'images/edit.gif');
	edit.attr("alt",'edit');
	edit.attr("onclick","showEdit("+rowObject.id+",-1);");
	
	var del = temp.clone();
	del.attr("src",'images/del.png');
	del.attr("alt",'delete');
	del.attr("onclick","showDelete("+rowObject.id+");");
	div.append(edit);
	div.append(del);
	
	var role = temp.clone();
	role.attr("src",'images/user.png');
	role.attr("alt",'add role');
	role.attr("onclick","showRoleSelect("+rowObject.id+");");
	
	div.append(edit);
	div.append(role);
	div.append(del);
	
	div1.append(div);
	return div1.html();
}

function showEdit(id) {
	dialog.find('input').prop("value", '');
	dialog.find('textarea').prop("value", '');
	$("#showError",dialog).html("");
	$('#dialogTitle').html('修改用户');
	$.ajax({
        type : 'GET',  
        url : rootUrl+id,    
        dataType : 'json',
        data: {},
        success : function(data){
        	if(null == data){
        		$("#showError").html("获取数据失败");
        		return;
        	}
        	$('#id',dialog).val(data.id);
        	$('#userAccount',dialog).val(data.userAccount);
        	$('#userAccount',dialog).attr("readonly",true);
        	$('#userName',dialog).val(data.userName);
        	$('#remarks',dialog).val(data.remarks);
        	dialog.modal('show');
        },  
        error : function(data){
        	$("#showListError").html("获取数据失败");
        }
    });
}

function showDelete(id) {
	if(confirm("确认删除该用户？") == false){
		//delete
		return;
	}
	doDelete(id);
}

function deleteUsers(){
	var ids=userGrid.jqGrid('getGridParam','selarrrow');
	$("#showListError").html("");
	if(ids.length == 0){
		$("#showListError").html("请选择要删除的用户");
		return;
	}
	if(confirm("确认删除已选择的用户？") == false){
		//delete
		return;
	}

	doDelete(ids.join(","));
}

function doDelete(id){
	//delete
	$.ajax({
        type : 'DELETE',  
        url : rootUrl + id,    
        dataType : 'text',
        data: {},
        success : function(data){
        	userGrid.trigger('reloadGrid');
        },  
        error : function(data){
        	$("#showListError").html("删除失败");
        }
    });
}
/*
 * 显示添加
 */
function showAdd() {
	$('#dialogTitle').html('添加用户');
	dialog.find('input').prop("value", '');
	dialog.find('textarea').prop("value", '');
	$("#showError",dialog).html("");
	$('#userAccount',dialog).attr("readonly",false);
	dialog.modal('show');
}

/*
 * 判断用户是否存在
 */
function isUserExist(account) {
	var ret = false;
	$.ajax({
        type : 'GET',  
        url : rootUrl+'isUserExist',     
        dataType : 'json',
        async:false,
        data: {'userAccount': account},
        success : function(data){
        	ret = data;
        },  
        error : function(data){
        	$("#showError",dialog).html("检查用户失败");
        }
    });
	return ret;
}

/*
 * 保存编辑内容
 */
function saveUser() {
	var account =  $('#userAccount',dialog).val();
	$("#showError").html("");
	if (isEmpty(account)) {
		$("#showError").html("用户登录账号不可以为空，请重新输入");
		$('#userAccount',dialog).focus();
		return ;
	}
	
	if(!isInteger($('#id',dialog).val()) && isUserExist(account) == true){
		$("#showError").html("用户登录账号已经存在，请重新输入");
		$('#userAccount',dialog).focus();
		return ;
	}
	
	var name = $('#userName',dialog).val();
	if (isEmpty(name)) {
		$("#showError").html("用户名称不可以为空，请重新输入");
		$('#userName',dialog).focus();
		return ;
	}
	
	var password = $("#password",dialog).val();
	var confirmPWD = $("#confirmPWD",dialog).val();
	if(isEmpty(password) == false || isEmpty(confirmPWD) == false) {
		if(isEmpty(password)){
			$("#showError").html("请输入密码");
			$("#password",dialog).focus();
			return ;
		}
		if(isEmpty(confirmPWD)){
			$("#showError").html("请输入确认密码，并保证与新密码一致");
			$("#confirmPWD",dialog).focus();
			return ;
		}
		if(password != confirmPWD){
			$("#showError").html("确认密码和新密码不一致，请重新输入");
			return ;
		}
		password = $.md5(password);
		$("#password",dialog).val(password);
	}

	var options = {
        type : 'POST',  
        url : rootUrl,
        data: {},
        success : function(data){
        	dialog.modal('hide');
        	userGrid.trigger('reloadGrid');
        },  
        error : function(data){
        	$("#showError",dialog).html("保存失败");
        }
    };
	$("#userForm").ajaxSubmit(options);
}

function showRoleSelect(userId){
	$('input[name="roleSelect"]:checkbox',userRoleDialog).prop("checked",false);
	$.ajax({
        type : 'GET',  
        url : 'admin/domains/'+_domainId+'/roles',     
        dataType : 'json',
        success : function(data){
        	$("#userId",userRoleDialog).val(userId);
        	var div = $("<div>");
        	var span = $("<span>");
        	var checkBox =  $("<input type='checkbox' name='roleSelect'>");
        	var temp,temp1;
        	for(var op in data){
        		temp = span.clone();
        		temp1 = checkBox.clone();
        		temp1.attr('id',data[op].id);
        		temp.append(temp1);
        		temp.append(data[op].roleName);
        		div.append(temp);
        	}
        	$("#roles").html(div.html());
        	loadUserRole(userId);
        	userRoleDialog.modal('show');
        },  
        error : function(data){
        	$("#showListError").html("获取角色数据失败");
        }
    });
}

function saveUserRole(){
	var userId = $("#userId",userRoleDialog).val();
	var selected = $('input[name="roleSelect"]:checkbox:checked',userRoleDialog);
	var ids = "";
	var length = selected.length;
	if(length > 0){
		for(var t =0; t < length; t++){
			ids += $(selected[t]).attr("id")+",";
		}
	}
	$.ajax({
        type : 'POST',  
        url :  rootUrl + userId + '/roles',   
        dataType : 'text',
        data:{roleIds : ids},
        success : function(data){
        	userRoleDialog.modal('hide');
        },  
        error : function(data){
        	$("#showError",userRoleDialog).html("保存失败");
        }
    });
}

function loadUserRole(userId){
	$.ajax({
        type : 'GET',  
        url : rootUrl + userId + '/roles',     
        dataType : 'json',
        success : function(data){
        	for(var op in data){
        		$('input[id="'+data[op].roleId+'"]:checkbox',userRoleDialog).prop("checked",true);
        	}
        },  
        error : function(data){
        	$("#showListError").html("获取用户角色数据失败");
        }
    });
}