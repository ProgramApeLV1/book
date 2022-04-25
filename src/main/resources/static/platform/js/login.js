// 判断时候在Iframe框架内,在则刷新父页面
//if (self != top) {
//    parent.location.reload(true);
//    if (!!(window.attachEvent && !window.opera)) {
//        document.execCommand("stop");
//    } else {
//        window.stop();
//    }
//}

$(function () {

    //顶部图片自动适应
    var de = document.documentElement;
    var bodyw = self.innerWidth || (de && de.clientWidth) || document.body.clientWidth;
    var bodyh = self.innerHeight || (de && de.clientHeight) || document.body.clientHeight;
    $(".login_bgpic").height(bodyh);
    $(".login_bgpic").width(bodyw);
    $(".login_content").css("top", (bodyh - 415) / 2);

    $(window).resize(function () {
        var de = document.documentElement;
        var bodyw = self.innerWidth || (de && de.clientWidth) || document.body.clientWidth;
        var bodyh = self.innerHeight || (de && de.clientHeight) || document.body.clientHeight;
        $(".login_bgpic").height(bodyh);
        $(".login_bgpic").width(bodyw);
        $(".login_content").css("top", (bodyh - 415) / 2);
    });

    //密码输入框输入时变为password类型
    $(".login_pwdtext1").focus(function () {
        $(".login_pwdtext2").show().focus();//使密码输入框获取焦点
        $(".login_pwdtext1").hide();//隐藏文本输入框
    });

    //密码输入框失去焦点后，若输入框中没有输入字符时，则显现文本输入框
    $(".login_pwdtext2").blur(function () {
        if ($(".login_pwdtext2").val() == '') {
            $(".login_pwdtext1").show();
            $(".login_pwdtext2").hide();
        }
    });

    $('.login_content').show();
});

if (window.top.location.href != location.href) {
    window.top.location.href = location.href;
}

function showMsg(_er) {
    if (_er == "1") {
        hintMsg("userNo", "温馨提示：密码输入有误！");
    } else if (_er == "2") {
        hintMsg("userNo", "温馨提示：验证码输入有误！");
    } else if (_er == "10") {
        hintMsg("userNo", "温馨提示：用户名或密码错误！");
    } else if (_er == "20") {
        hintMsg("userNo", "温馨提示：用户名或权限被冻结！");
    }
}

function fun_enter(e) {
    if (e.keyCode == 13) {
        submitForm();
    }
}


function trims(str) {
    return str.replace(/[ ]/g, "");
}

function checkform() {

    var user = trims($('#et_username').val());
    var pwd = trims($('#et_pwd').val());

    if (($.trim(user) == "" || user == '账   号') & pwd == "") {
        hintMsg("userNo", "温馨提示：请输入账号和密码！");
        return false;
    }

    if ($.trim(user) == "" || user == '账号') {
        $("#et_username").select();
        hintMsg("userNo", "温馨提示：请输入账号！");
        return false;
    }

    if (pwd == "") {
        $("#et_pwd").select();
        hintMsg("password", "温馨提示：请输入密码！");
        return false;
    }
    return true;
}

function submitForm() {
    if (checkform()) {
        loadFormSubmit();
    }
}

function loadFormSubmit() {
    let params = serializeObject($('#loginForm'));
    params.password = md5(params.password);
    // $.post("/loginCont/userLogin", params, function (data) {
    //     if (data.result === 200) {
    //         if (data.data) {
    //             loginSetCache(data.data);
    //             window.location.href = '/index';
    //         }
    //     } else {
    //         $('#msg-err').html(data.message);
    //     }
    // }, "JSON");
    jrequest.post(baseHttpUrl + "loginCont/userLogin", params).then(res => {
        if (res.data.result === 200) {
            if (res.data.data) {
                // loginSetCache(res.data.data);
                window.location.href = '/index';
            }
        } else {
            $('#msg-err').html(res.data.message);
        }
    });
}

function hintMsg(id, msg) {
    $("#" + id).focus();
    $('#msg-err').html(msg);
}
