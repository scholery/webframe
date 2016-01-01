/*
 * 去掉开头与结尾的空格
 */
function trim(str) {
	return str.replace(/(^(\s|　)*)|((\s|　)*$)/g, '');
}

/*
 * 校验字符串是否为空
 */
function isEmpty(str) {
	if (!str || null == str || undefined == str || "undefined" == str || trim(str) == "") {
		return true;
	}
	return false;
}

/*
 * 校验字符串是否为空
 */
function isUndefined(str) {
	if (!str || null == str || undefined == str || "undefined" == str) {
		return true;
	}
	return false;
}

/*
 * 校验字符串是否为空
 */
function isInteger(str) {
	if (!isEmpty(str) &&  str.indexOf(".") < 0 && !isNaN(str) && !isNaN(parseInt(str))) {
		return true;
	}
	return false;
}

/*
 * 检查是否选中
 */
function isSelect(arr,errorArea) {
	if (arr.length == 0) {
		$("#"+errorArea).html("请选择需要操作的数据");
		return false;
	}
	return true;
}

/*
 * 提示
 */
function showConfirm(message){
	return confirm(message);
}

/*
 * 警告
 */
function showMessage(message){
	showMessage(message,null);
}

/*
 * 警告
 */
function showMessage(message,type){
	alert(message);
}

/**
 * 文本区限长
 */
$(function() {
    $('textarea[maxlength]').keyup(function() {
    	var maxlength = $(this).attr('maxlength');
    	var value = $(this).val();
    	var length = value.length;
    	if (maxlength < length) {
    		$(this).val(value.substring(0, maxlength));
    	}
    });
});