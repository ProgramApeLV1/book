/**
 * easyui 验证扩展
 * 
 * @returns
 */
$(function() {
	validate();
});

// 验证
function validate() {
	// 扩展验证
	$.extend($.fn.validatebox.defaults.rules, {
		// 验证下拉框是否为空
		selectIsNull : {
			validator : function(value, param) {
				// 下拉框值
				var v = $(param).combo("getValue");
				if (v == null || v == "") {
					return false;
				}
				return true;
			},
			message : "该选择项为必选项"
		},
		/* 手机号码验证 */
		mobile : {
			validator : function(value) {
				var reg = /^1[1-9]\d{9}$/;
				return reg.test(value);
			},
			message : '输入手机号码格式不准确.'
		},
		/* 邮箱验证 */
		email : {
			validator : function(value) {
				var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
				return reg.test(value);
			},
			message : '邮箱格式不准确.'
		},
		/* 日期大小比较 */
		endTime : {
			validator : function(value, param) {
				// 结束日起
				var endTime = new Date(value).getTime();
				endTime = new Date(endTime).getTime();

				// 开始日期
				var beginTime = $('#' + param[0]).datebox('getValue');
				beginTime = new Date(beginTime).getTime();

				if (endTime <= beginTime) {
					return false;
				}
				return true;
			},
			message : '结束日期不能比开始日期小'
		},
		// 验证最大数值
		maxNum : {
			validator : function(value, param) {
				// 最大数值
				var maxVal = param[0];
				var flag = true;
				if (value > maxVal) {
					$.fn.validatebox.defaults.rules.maxNum.message = "最大值为{"
							+ maxVal + "}";
					flag = false;
				}
				return flag;
			},
			message : ''
		},
		checkPwd : {
			validator : function(value, param) {
				var reg = /^[0-9a-zA-Z]+$/;
				return reg.test(value);
			},
			message : '密码只能由数字或字母组成'
		},
		pwdEqu : {
			validator : function(value, param) {
				var pwd = $(param[0]).textbox('getValue');
				return value == pwd;
			},
			message : '新密码和确认新密码不匹配'
		},
		timeLtThiTime : {
			// 验证选择时间是否小于当前时间
			validator : function(value, param) {
				// 当前时间
				var thiDate = new Date();
				// 年
				var y = thiDate.getFullYear();
				// 月
				var m = thiDate.getMonth() + 1;
				// 日
				var d = thiDate.getDate();
				// 时
				var h = thiDate.getHours();
				// 分
				var min = thiDate.getMinutes();
				// 秒
				var s = thiDate.getSeconds();
				if (m < 10) {
					m = "0" + m;
				}
				if (d < 10) {
					d = "0" + d;
				}
				if (h < 10) {
					h = "0" + h;
				}
				if (min < 10) {
					min = "0" + min;
				}
				if (s < 10) {
					s = "0" + s;
				}
				var dd = y + "" + m + "" + d + "" + h + "" + "" + min + "" + s;

				// 输入时间
				value = value.replace("-", "");
				value = value.replace("-", "");
				value = value.replace(" ", "");
				value = value.replace(":", "");
				value = value.replace(":", "");
				value = parseInt(value);
				dd = parseInt(dd);
				if (value <= dd) {
					return false;
				}
				return true;
			},
			message : '选择的时间不能小于当前时间'
		}
	});
}

/**
 * 下拉框验证必填
 * 
 * @param ids
 *            下拉框id,多个逗号隔开
 * @returns
 */
function checkSelectIsNull(ids) {
	if (ids != null && ids != "") {
		ids = ids.split(",");
		$.each(ids, function(index, val) {
			$("#" + val).combobox({
				validType : "selectIsNull[" + val + "]"
			});
		});
	}
}
