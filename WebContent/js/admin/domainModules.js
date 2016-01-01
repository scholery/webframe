var _domainId;
var roleId = -1;
var rootUrl = 'admin/domains/';
var tree;
var tab;
($(function() {
	$("#domainTabs").tabs({
		  activate: function( event, ui ) {
			  //console.log(ui);
			  //_domainId = ui.newTab[0].id;
			  //onTabChange(_domainId);
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
	tree = $("#moduleTree"+_domainId);
	loadTree();
	loadRoleSelect();
}
function loadTree(){
	if(isUndefined(_domainId)){
		return;
	}
	
	tree.jqGrid({
        url: rootUrl+_domainId+'/modules',
		datatype: 'json',
        colNames: ['权限','模块名称','模块code','parent', 'level', 'url', ''],
        colModel: [
			{ name: 'tId', width: 40, hidden:false,key:true,formatter:showSelect},
            { name: 'obj.moduleName', width: 250, align: 'left', formatter:showName},
			{ name: 'obj.moduleCode', width: 150, align: 'left', formatter:showCode},
            { name: 'parent', width: 90 ,align: 'left', hidden:true},
            { name: 'level', width: 180, align: 'left', hidden:true},
            { name: 'obj.moduleUrl', width: 250, align: 'left', formatter:showUrl},
            { name: 'obj.status', width: 180 ,align: 'left', formatter:showOperation}
        ],
        cmTemplate: {sortable: false},
        //pager: '#pager',
        shrinkToFit:false,
        //rownumbers:true,
        hoverrows: false,
        autoencode: true,
        ignoreCase: true,
        height: '100%',
        treeGrid:true,
        ExpandColumn:'obj.moduleName',
        ExpandColClick:true,
        treeGridModel:'adjacency',
        width:900,
        parent:null,
        jsonReader:{
			root:"rows",
			total:"total"
		},
		treeReader:{
			level_field:'level',
			parent_id_field:'parent',
			leaf_field:'leaf',
			expanded_field:'expand',
			nodeid:'tId'//,
			//loaded:"loaded"//,
			//icon_field:"obj.moduleIcon"
		},
		/*treeIcons:{
			minus:'',
			leaf:''
		},*/
		loadComplete:function(){
		},
		beforeRequest: function() {
			//var nodeid = $(this).getGridParam("postData")["nodeid"];
			//if(nodeid){
			//	var row = $(this).getRowData(nodeid);
			//    $(this).setGridParam({ postData: { level: row.level}});
			//}
		},
		onSelectRow:function(id,status){
		},
		gridComplete:function(){
			//setTimeout(expand,0);
			setPermissions();
		}
    });
}

function loadRoleSelect(){
	$.ajax({
        type : 'GET',  
        url : rootUrl+_domainId+'/roles',     
        dataType : 'json',
        success : function(data){
        	var select = $("#roleSelect",tab);
        	select.find("option").not(":first").remove();
        	for(var op in data){
            	select.append("<option value='"+data[op].id+"'>"+data[op].roleName+"</option>");  
        	}
        },  
        error : function(data){
        	$("#showListError",tab).html("加载用户模块失败");
        }
    });
}

function changeRole(){
	roleId = $("#roleSelect",tab).val();
	setPermissions();
}

/**
 * 展开节点
 * @return
 */
function expand(){
	var ids = tree.getDataIDs();
	var _item;
	for(var id in ids){
		_item = tree.getLocalRow(ids[id]);
		if(_item.leaf){
			continue;
		}
		tree.jqGrid("expandNode",_item);
		tree.jqGrid("expandRow",_item);
	}
}

function showSelect(cellvalue, options, rowObject){
	var div = $("<div>");
	var check = $("<input>").css("margin","3px");
	check.attr("type","checkbox");
	check.attr("id",rowObject.tId);
	check.attr("name","selectCheck");
	check.attr("onclick","selectModule("+rowObject.tId+");");
	div.append(check);
	return div.html();
}

function selectModule(id){
	//
	var _item = tree.getLocalRow(id);
	var node = tree.getNodeParent(_item);
	if(!isUndefined(node) && tree.find(':checkbox#'+node.tId).prop("checked") == false){
		tree.find(':checkbox#'+id).prop("checked",false);
		return;
	}
	var val = tree.find(':checkbox#'+id).prop("checked");
	//if(val == false){
	resetChildrenNodes(id,val);
	//}
}

function resetChildrenNodes(id,val){
	var _item = tree.getLocalRow(id);
	var nodes = tree.getNodeChildren(_item);
	if(nodes.length > 0){
		for(var node in nodes){
			tree.find(':checkbox#'+nodes[node].tId).prop("checked",val);
			//tree.find(':checkbox#'+nodes[node].tId).prop("disabled",!val);
			resetChildrenNodes(nodes[node].tId,val);
		}
	}
}

function showName(cellvalue, options, rowObject){
	if(rowObject.obj.moduleName != null){
		return rowObject.obj.moduleName;
	}
	if(rowObject.obj.actionName != null){
		return rowObject.obj.actionName;
	}
	return "-";
}

function showCode(cellvalue, options, rowObject){
	if(isEmpty(rowObject.obj.moduleCode) == false){
		return rowObject.obj.moduleCode;
	}
	if(isEmpty(rowObject.obj.actionCode) == false){
		return rowObject.obj.actionCode;
	}
	return "-";
}

function showUrl(cellvalue, options, rowObject){
	if(isEmpty(rowObject.obj.moduleUrl) == false){
		return rowObject.obj.moduleUrl;
	}
	return "-";
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
	edit.attr("onclick","showEdit("+rowObject.tId+",-1);");
	
	var add = temp.clone();
	add.attr("src",'images/add.png');
	add.attr("alt",'add');
	add.attr("onclick","showAdd("+rowObject.tId+");");
	
	var del = temp.clone();
	del.attr("src",'images/del.png');
	del.attr("alt",'delete');
	del.attr("onclick","showDelete("+rowObject.tId+");");
	div.append(edit);
	div.append(add);
	div.append(del);
	div1.append(div);
	return div1.html();
}

function showEdit(id) {
	var dialog = $('#moduleEditDialog');
	dialog.find('input').prop("value", '');
	dialog.find('textarea').prop("value", '');
	$("#showError",dialog).html("");
	$('#dialogTitle').html('修改模块');
	$.ajax({
        type : 'GET',  
        url : rootUrl+_domainId+'/modules/'+id,    
        dataType : 'json',
        data: {},
        success : function(data){
        	if(null == data){
        		$("#showError").html("获取数据失败");
        		return;
        	}
        	$('#id',dialog).val(data.id);
        	$('#parentModuleId',dialog).val(data.parentModuleId);
        	$('#moduleCode',dialog).val(data.moduleCode);
        	$('#moduleName',dialog).val(data.moduleName);
        	$('#moduleIcon',dialog).val(data.moduleIcon);
        	$('#moduleUrl',dialog).val(data.moduleUrl);
        	$('#index',dialog).val(data.sortIndex);
        	$('#remarks',dialog).val(data.remarks);
        	dialog.modal('show');
        },  
        error : function(data){
        	$("#showListError",tab).html("获取模块数据失败");
        }
    });
}

function showDelete(id) {
	var _item = tree.getRowData(id);
	_item = tree.getRowData(id);
	if(_item.leaf == "false"){
		if(confirm("该模块包含子模块或动作，是否删除？") == false){
			return;
		}
	}else{
		if(confirm("确认删除该模块或动作？") == false){
			//delete
			return;
		}
	}
	//delete
	$.ajax({
        type : 'DELETE',  
        url : rootUrl+_domainId+'/modules/'+id,    
        dataType : 'text',
        data: {},
        success : function(data){
        	//deleteChildrenNodes(id);
        	//tree.delRowData(id);
        	tree.trigger('reloadGrid');
        },  
        error : function(data){
        	$("#showListError",tab).html("删除失败");
        }
    });
}

function deleteChildrenNodes(id){
	var _item = tree.getLocalRow(id);
	var nodes = tree.getNodeChildren(_item);
	if(nodes.length > 0){
		for(var node in nodes){
			deleteChildrenNodes(nodes[node].tId);
			tree.delRowData(nodes[node].tId);
		}
	}
}

/*
 * 显示添加
 */
function showAdd(id) {
	$('#dialogTitle').html('添加模块');
	var dialog = $('#moduleEditDialog');
	dialog.find('input').prop("value", '');
	dialog.find('textarea').prop("value", '');
	$('#parentModuleId',dialog).val(id);
	$("#showError",dialog).html("");
	dialog.modal('show');
}

/*
 * 判断模块是否存在
 */
function isModuleExist(code) {
	var ret = false;
	$.ajax({
        type : 'GET',  
        url : rootUrl+_domainId+'/modules/isModuleExist',     
        dataType : 'json',
        async:false,
        data: {'moduleCode': code},
        success : function(data){
        	ret = data;
        },  
        error : function(data){
        	$("#showError",dialog).html("检查模块失败");
        }
    });
	return ret;
}

/*
 * 保存编辑内容
 */
function saveModule() {
	var dialog = $('#moduleEditDialog');
	var code =  $('#moduleCode',dialog).val();
	if (isEmpty(code)) {
		$("#showError").html("模块code不可以为空，请重新输入");
		$('#moduleCode',dialog).focus();
		return ;
	}
	
	if(!isInteger($('#id',dialog).val()) && isModuleExist(code) == true){
		$("#showError").html("模块code已经存在，请重新输入");
		$('#moduleCode',dialog).focus();
		return ;
	}
	
	var name = $('#moduleName',dialog).val();
	if (isEmpty(name)) {
		$("#showError").html("模块名称不可以为空，请重新输入");
		$('#moduleName',dialog).focus();
		return ;
	}
	var options = {
        type : 'POST',  
        url : rootUrl+_domainId+'/modules',
        data: {},
        success : function(data){
        	dialog.modal('hide');
        	//addChildrenNodes(data);
        	tree.trigger('reloadGrid');
        },  
        error : function(data){
        	$("#showError",dialog).html("保存失败");
        }
    };
	$("#moduleForm").ajaxSubmit(options);
}

function addChildrenNodes(data){
	var _item = tree.getLocalRow(data.parent);
	tree.jqGrid("expandNode",_item);
	tree.jqGrid("expandRow",_item);
	data.level = _item.level + 1;
	//var nodes = tree.getNodeChildren(_item);
	tree.addRowData(data.tId,data,"last",data.parent);
}

function savePermissions() {
	if(roleId <= 0){
		$("#showListError",tab).html("请选择角色");
		return;
	}
	$("#showListError",tab).html("");
	var moduleIds = "";
	var checks = $('input[name="selectCheck"]:checkbox:enabled:checked',tree);
	var length = checks.length;
	if(length > 0){
		for(var t =0; t < length; t++){
			moduleIds += $(checks[t]).attr("id");
			moduleIds += ",";
		}
	}
	$.ajax({
		type : 'POST',
		url : rootUrl + _domainId + '/permissions',
		dataType : 'text',
		data : {
			roleId : roleId,
			moduleIds: moduleIds
		},
		success : function(data) {
		},
		error : function(data) {
			$("#showListError",tab).html("保存用户模块失败");
		}
	});
	return false;
}

function setPermissions() {
	$('input[name="selectCheck"]:checkbox',tree).prop("checked",false);
	if(roleId <= 0){
		return;
	}
	$.ajax({
		type : 'GET',
		url : rootUrl + _domainId + '/permissions',
		dataType : 'json',
		data : {
			roleId : roleId
		},
		success : function(data) {
			if(null != data && data.length > 0){
				for(var t in data){
					//console.log(data[t].moduleId);
					tree.find(':checkbox#'+data[t].moduleId).prop("checked",true);
				}
			}
		},
		error : function(data) {
			$("#showListError",tab).html("加载用户模块失败");
		}
	});
	return false;
}

/*
 * 显示添加
 */
function addDomain(id) {
	$('#dialogTitle').html('添加域');
	var dialog = $('#moduleEditDialog');
	dialog.find('input').prop("value", '');
	dialog.find('textarea').prop("value", '');
	$('#parentModuleId',dialog).val(id);
	$("#showError",dialog).html("");
	dialog.modal('show');
}