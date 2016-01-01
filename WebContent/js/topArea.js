function gotoSelect(obj){
	var href = $(obj).find("a").prop("href");
	if(isEmpty(href) == false){
		window.location.href=href;
	}
	return false;
}

function logout(){
	$.cookie('password',null,{path:"/frame"});
	$.cookie('remberMeFlag', null,{path:"/frame"});
	return true;
}

function changeUser(){
	var changeUserDialog = $('#changeUserDialog');
	changeUserDialog.find('input').prop("value", '');
	$("#userName",changeUserDialog).val($("#userName",$("#log")).text());
	$("#userError").html("");
	changeUserDialog.modal('show');

}

function saveUser(){
	var changeUserDialog = $('#changeUserDialog');
	var userName = $("#userName",changeUserDialog).val();
	var oldPWD = $("#oldPWD",changeUserDialog).val();
	var newPWD = $("#newPWD",changeUserDialog).val();
	var confirmPWD = $("#confirmPWD",changeUserDialog).val();
	if (isEmpty(userName)) {
		$("#userError").html("用户姓名不可以为空，请重新输入");
		return ;
	}
	if(isEmpty(oldPWD) == false || isEmpty(newPWD) == false || isEmpty(confirmPWD) == false) {
		if(isEmpty(oldPWD)){
			$("#userError").html("请输入旧密码");
			return ;
		}
		if(isEmpty(newPWD)){
			$("#userError").html("请输入新的密码");
			return ;
		}
		if(isEmpty(confirmPWD)){
			$("#userError").html("请输入确认密码，并保证与新密码一致");
			return ;
		}
		if(confirmPWD != newPWD){
			$("#userError").html("确认密码和新密码不一致，请重新输入");
			return ;
		}
		oldPWD = $.md5(oldPWD);
		newPWD = $.md5(newPWD);
	}
	$.ajax( {
        type : 'POST',  
        url : serverRoot+'/changeUser',     
        dataType : 'json',  
        
        data:{userName:userName,oldPWD:oldPWD,newPWD:newPWD},
        success : function(data,textStatus){
            if(data=='success'){
            	changeUserDialog.modal('hide');
            	$("#userName",$("#log")).text(userName);
            }else{
            	$("#userError").html("修改用户信息错误："+data);
            }
        },  
        error : function(data,textstatus,throwse){  
        	$("#userError").html("修改用户信息错误："+data);
        }  
    });
}


function contentResize(){
	var head = 68;
	var menu = 51;
	var footer = 32;
	var other = 15;
	//alert($("#menuArea"));
	if(!$("#menuArea") || $("#menuArea").html() == undefined){
		menu = 0;
	}
	var bodyMiniHead = $(window).height()- head- menu - footer - other;
	$("#middleArea").css("min-height",bodyMiniHead);
}

$(document).ready(function(){
	contentResize();
	$(window).resize(function (){
		contentResize();
	});
});