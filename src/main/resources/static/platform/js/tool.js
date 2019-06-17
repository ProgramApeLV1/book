Tool = function() {

    this.excute = function(dataSource) {
        var excuteSql = $.trim($('#excuteSql').val());
        if (!excuteSql) {
            $.messager.alert('警告', '请输入要执行的语句!', 'error');
            return;
        } else if (excuteSql.substring(0, 6) != 'select') {
            $.messager.alert('警告', '您当前的用户组仅支持查询语句权限，请勿输入其他类型语句', 'error');
            return;
        }
        $.messager.confirm("操作提示", "您确定要执行操作吗？", function(data) {
            if (data) {
                $.ajax({
                    type : "post",
                    url : basePath+"/sql/query",
                    dataType : "html",
                    async : true,
                    data : {
                        sql : excuteSql,
                        dataSource : dataSource
                    },
                    success : function(html) {
                        $("#excuteDetail").empty();
                        if(html.indexOf("错误描述：系统内部错误") > 0 ){
                        	$("#excuteDetail").html("执行sql语句错误,请检查sql语句!");
                        } else {
                        	$("#excuteDetail").html(html);
                        }
                    },
                    error : function() {
                        $("#excuteDetail").html("执行sql语句错误,请检查sql语句!");
                    }
                });
            }
        });
    };

    this.clear = function(textareaId) {
        $('#' + textareaId).val('');
    };
    
    this.excuteTabSelect = function(title,index){
        $("#excuteDetail").html('暂时么有执行结果');
    };
};
var tool = new Tool();