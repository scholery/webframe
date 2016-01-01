var _domainId;
var rootUrl = 'admin/roles/';
var roleGrid;
var tab;
var dialog = $('#roleEditDialog');
($(function() {
	$("#domainTabs").tabs({
		  activate: function( event, ui ) {
			  //onTabChange(ui.newTab[0].id);
		  },
		  load: function( event, ui ) {
			  //console.log(ui.tab[0].id);
			  _domainId = ui.tab[0].id;
			  onTabChange();
		  }
	});
}));

function onTabChange(){
	tab =  $("#domainTab"+_domainId);
	roleGrid = $("#roleGrid"+_domainId);
	loadGrid();
}
function loadGrid(){
	if(isUndefined(_domainId)){
		return;
	}
	
	roleGrid.jqGrid({
        url: rootUrl + 'details',
		datatype: 'json',
        colNames: ['ID','角色名称','角色code','类型', '默认角色', '备注', ''],
        colModel: [
			{ name: 'id', width: 30, hidden:true,key:true},
            { name: 'roleName', width: 150, align: 'left'},
			{ name: 'roleCode', width: 150, align: 'left'},
            { name: 'roleType', width: 90 ,align: 'left', hidden:true},
            { name: 'defaultFlag', width: 180, align: 'left'},
            { name: 'remarks', width: 250 ,align: 'left'},
            { name: 'status', width: 90, align: 'left', formatter:showOperation}
        ],
        cmTemplate: {sortable: false},
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#pager'+_domainId,
        gridview: true,
        shrinkToFit:false,
        rownumbers:true,
        multiselect:true,
        hoverrows: false,
        autoencode: true,
        ignoreCase: true,
        viewrecords: true,
        height: '100%',
        width: 900,
		beforeRequest: function() {
			$(this).setGridParam({ postData: { domainId: _domainId}});
		}
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
	if(isEmpty(rowObject.defaultFlag)){
		var defaultRole = temp.clone();
		defaultRole.attr("src",'images/icoQuit.png');
		defaultRole.attr("alt",'defaultRole');
		defaultRole.attr("title",'defaultRole');
		defaultRole.attr("onclick","setDefault("+rowObject.id+");");
		div.append(defaultRole);
	}
	div.append(del);
	div1.append(div);
	return div1.html();
}

function showEdit(id) {
	$('#dialogTitle').html('修改角色');
	dialog.find('input').prop("value", '');
	dialog.find('textarea').prop("value", '');
	$("#showError",dialog).html("");
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
        	$('#domainId',dialog).val(data.domainId);
        	$('#roleCode',dialog).val(data.roleCode);
        	$('#roleName',dialog).val(data.roleName);
        	$('#remarks',dialog).val(data.remarks);
        	dialog.modal('show');
        },  
        error : function(data){
        	$("#showListError",tab).html("加载角色失败");
        }
    });
}

function showDelete(id) {
	if(confirm("确认删除该角色？") == false){
		//delete
		return;
	}
	doDelete(id);
}

function deleteRoles(){
	var ids=roleGrid.jqGrid('getGridParam','selarrrow');
	$("#showListError",tab).html("");
	if(ids.length == 0){
		$("#showListError",tab).html("请选择要删除的角色");
		return;
	}
	if(confirm("确认删除已选择的角色？") == false){
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
        	roleGrid.trigger('reloadGrid');
        	$("#showListError",tab).html("");
        },  
        error : function(data){
        	$("#showListError",tab).html("删除角色失败");
        }
    });
}

/*
 * 显示添加
 */
function showAdd() {
	$('#dialogTitle').html('添加角色');
	dialog.find('input').prop("value", '');
	dialog.find('textarea').prop("value", '');
	$('#domainId',dialog).val(_domainId);
	$("#showError",dialog).html("");
	dialog.modal('show');
}

/*
 * 判断角色是否存在
 */
function isRoleExist(code) {
	var ret = false;
	$.ajax({
        type : 'GET',  
        url : rootUrl + 'isRoleExist',     
        dataType : 'json',
        async:false,
        data: {'domainId': _domainId,'roleCode': code},
        success : function(data){
        	ret = data;
        },  
        error : function(data){
        	$("#showError",dialog).html("检查角色失败");
        }
    });
	return ret;
}

/*
 * 保存编辑内容
 */
function saveRole() {
	var code =  $('#roleCode',dialog).val();
	if (isEmpty(code)) {
		$("#showError",dialog).html("角色code不可以为空，请重新输入");
		$('#roleCode',dialog).focus();
		return ;
	}
	
	if(!isInteger($('#id',dialog).val()) && isRoleExist(code) == true){
		$("#showError").html("角色code已经存在，请重新输入");
		$('#roleCode',dialog).focus();
		return ;
	}
	
	var name = $('#roleName',dialog).val();
	if (isEmpty(name)) {
		$("#showError").html("角色名称不可以为空，请重新输入");
		$('#roleName',dialog).focus();
		return ;
	}
	var options = {
        type : 'POST',  
        url : rootUrl,
        data: {},
        success : function(data){
        	dialog.modal('hide');
        	//addChildrenNodes(data);
        	roleGrid.trigger('reloadGrid');
        },  
        error : function(data){
        	$("#showError",dialog).html("保存失败");
        }
    };
	$("#roleForm").ajaxSubmit(options);
}

/*
 * 判断角色是否存在
 */
function setDefault(id) {
	$.ajax({
        type : 'POST',  
        url : rootUrl + 'setDefault',     
        dataType : 'text',
        data: {'id': id},
        success : function(data){
        	roleGrid.trigger('reloadGrid');
        },  
        error : function(data){
        	$("#showListError",tab).html("设置默认角色失败");
        }
    });
}