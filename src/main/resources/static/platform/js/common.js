function formatDate(date) {
    var date = new Date(date);
    var year = date.getFullYear();
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);
    var h = ("0" + date.getHours()).slice(-2);
    var m = ("0" + date.getMinutes()).slice(-2);
    var s = ("0" + date.getSeconds()).slice(-2);
    return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
}

/**
 * 序列化表单域的值,拼接为JSON
 * **/
function serializeObject(form) {
    var o = {};
    $.each(form.serializeArray(), function (index) {

        if (o[this['name']]) {

            o[this['name']] = o[this['name']] + "," + this['value'];

        } else {

            o[this['name']] = this['value'];

        }

    });

    return o;
}

/** 回填表单域内的值**/
function writeObjectValue(jsonObject) {
    // console.log(jsonObject);
    for (var key in jsonObject) {
        if (typeof (jsonObject[key]) == 'string') {
            if (typeof $("#" + key) != "undefined") {
                if ($("#" + key).length > 0) {
                    if (typeof ($("#" + key).textbox("options")) != 'undefined') {
                        $("#" + key).textbox("setValue", jsonObject[key]);
                    } else if ($("#" + key).combobox("options")) {
                        $("#" + key).combobox("setValue", jsonObject[key]);
                    }
                }

            }
        }
    }
}

/*
* 获取系统生成的编码
* @param type  编码名称 ; callback 请求成功时执行的函数
* 调用方式如下：
* getSeqCode("warehouseCode",function(data){$("#warehouseCodeId").textbox("setValue",data)});
* */
function getSeqCode(type, callback) {
    $.ajax({
        url: baseUrl + "/common/getSeqCode",
        type: "GET",
        data: {//data就是参数，是json格式
            type: type
        },
        async: false,//这里表示同步
        dataType: 'json',
        cache: false,
        success: function (args) {
            //请求成功返回后执行的动作
            callback(args.obj);
        },
        error: function (args) {
            //请求失败后执行的动作
            callback("");
        }
    });
}

/**
 * 获取公司列表
 * @param type 公司类型 (1 省公司,2市公司,3支撑中心)
 * * 调用方式如下：
 getCompanyList(1 ,function(data){
  if(data.success){
      $("#id").combobox("loaddata",data);
      }
    });
 * @data实例{success:true,msg:"",obj:[{companyCode:001,companyName:省公司,type:1},{companyCode:002,companyName:福州市公司,type:2}]}
 * */
function getCompanyList(type, callback) {
    $.ajax({
        url: baseUrl + "/common/getCompanyList",
        type: "POST",
        data: {//data就是参数，是json格式
            type: type
        },
        async: true,//这里表示异步
        dataType: 'json',
        cache: false,
        success: function (args) {
            //请求成功返回后执行的动作
            callback(args);
        },
        error: function (args) {
            //请求失败后执行的动作
            callback({success: false, msg: "操作失败"});
        }

    });
}


/**解析URL地址取得传入的参数***/
function getUrlParameter(name) {
    var url = location.href;
    var i = url.indexOf('?');
    if (i == -1) return;
    var queryStr = url.substr(i + 1);
    var arr1 = queryStr.split('&');
    var arr2 = new Object();
    for (i in arr1) {
        var ta = arr1[i].split('=');
        arr2[ta[0]] = ta[1];
    }
    return arr2[name];
}

//生成不重复的唯一ID  uuid
function S4() {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}

function guid() {
    return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
}

function getJsonLength(json) {
    var jsonLength = 0;
    for (var obj in json) {
        if (json[obj] != null || json[obj] != "" || typeof (json[obj]) != 'undefined') {
            jsonLength++;
        }
    }
    return jsonLength;
}

function toDecimal(x) {
    var val = Number(x)
    if (!isNaN(parseFloat(val))) {
        val = val.toFixed(2);
    }
    return val;
}

function accAdd(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length
    } catch (e) {
        r1 = 0
    }
    try {
        r2 = arg2.toString().split(".")[1].length
    } catch (e) {
        r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2))
    return (arg1 * m + arg2 * m) / m
}

//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg) {
    return accAdd(arg, this);
}

function find(obj, key) {
    if (obj == null) return false;
    if (!(typeof obj === 'object')) return false;
    if (key in obj) return true;
    for (var k in obj) {
        if (find(obj[k], key)) return true;
    }
    return false;
};

//用户缓存key常量
const USER_COOKIE_KEY = "user";

// function loginSetCache(data) {
//     const user = {
//         userId: data.id,
//         phone: data.phone,
//         loginName: data.loginName,
//         userName: data.userName,
//         userCode: data.userCode,
//         sex: data.sex
//     }
//     docCookies.setItem(USER_COOKIE_KEY, JSON.stringify(user), '', '/');
//     window.sessionStorage.setItem(USER_COOKIE_KEY, JSON.stringify(user));
// }

function getCacheInfoByKey(key) {
    return window.localStorage.getItem(key);
}

function setCacheInfo(key, val) {
    return window.localStorage.setItem(key, val);
}

function logoutRemoveCache() {
    // docCookies.removeItem(USER_COOKIE_KEY, '/');
    window.localStorage.removeItem(USER_COOKIE_KEY);
}