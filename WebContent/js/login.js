
function disableAndEncodePwd(){
	$("#encryptPass").val($.md5($("#password").val()));
	$("#userId").attr("readonly","readonly");
	$("#password").attr("readonly","readonly");
	$("#remberMeFlag").attr("readonly","readonly");
	$("#loginBt").attr("disable","disable");
}

function autoLogin(){
	$('#remberMeFlag').prop("checked",$.cookie("remberMeFlag") == "true");
	var userId = $.cookie("userId");
	var pwd = $.cookie("password");
	if(userId !=null){
		$("#userId").val(userId);
		$("#password").val(pwd);
	}
}

function remember(){
	var expireDays = 30;
	$.cookie('remberMeFlag', $("#remberMeFlag").prop("checked"),{expires:expireDays});
	if($('#remberMeFlag').prop("checked") == true){
		$.cookie('userId', $("#userId").val(), {expires: expireDays});
		$.cookie('password', $("#password").val(), {expires: expireDays});
	}else{
		$.cookie('userId',null);
		$.cookie('password',null);
	}
}

function validateForm(){
	$("#errorImg").hide();
	$("#error").hide();
	$("#error").html("");
	if(!$("#userId") || $("#userId").val() == ""){
		$("#errorImg").show();
		$("#error").show();
		$("#error").html("请输入用户名");
		return false;
	}
	if(!$("#password") || $("#password").val() == ""){
		$("#errorImg").show();
		$("#error").show();
		$("#error").html("请输入密码");
		return false;
	}
	/*if(!$("#rand") || $("#rand").val() == ""){
		$("#errorImg").show();
		$("#error").show();
		$("#error").html("请输入验证码");
		return false;
	}*/
	return true;
}

function login(){
	if(validateForm()){
		disableAndEncodePwd();
		remember();
		$("#loginForm").submit();
		return true;
	}else{
		return false;
	}
}

function reloadRand(obj){
	var img = document.getElementById("randImg");  
	img.src="createRandImageService?temp="+Math.random();
}


$(document).ready(function(){
	//自动显示登录信息
	//autoLogin();
});