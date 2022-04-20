/**
 controller.js
 @author szm
 页面中基础组件的封装
 */
function getById(id) {
    return document.getElementById(id);
}

var Page = {
    /*注册原生JS事件(例如onClick,onChange)的回调方法等*/
    /*
    *用法Page.registerEvent(dom,"click",callbackName,[1,2]);
    * */
    registerEvent: function (obj, even, fun, arr) {
        if (obj)
            obj["on" + even] = function () {
                fun(arr);
            };
    },
    /***注册datagrid(无复选框)组件**/
    registerDataGrid: function (id, dataGridUrl, toolbarId, arr) {
        if (getById(id)) {
            //微调工具栏中文字标签的宽度和对齐方式、按照templates/inlib/inStorageOrders.html的写法
            PageUtil.resizeToolBarTd(toolbarId);
            return $(getById(id)).datagrid({
                method: 'post',
                url: baseHttpUrl + dataGridUrl,
                loadMsg: '数据加载中,请稍后...',
                queryParams: arr,
                singleSelect: true,
                fitColumns: false,
                nowrap: true,
                pageSize: 20,
                scrollbarSize: 0,
                fit: false,
                toolbar: '#' + toolbarId,
                pagination: true
            });
        }
    },
    /***注册datagrid无分页组件(不带复选框)**/
    registerDataGridNotPage: function (id, dataGridUrl, toolbarId, arr) {
        if (getById(id)) {
            //微调工具栏中文字标签的宽度和对齐方式、按照templates/inlib/inStorageOrders.html的写法
            PageUtil.resizeToolBarTd(toolbarId);
            return $(getById(id)).datagrid({
                method: 'post',
                url: baseHttpUrl + dataGridUrl,
                loadMsg: '数据加载中,请稍后...',
                queryParams: arr,
                singleSelect: true,
                fitColumns: false,
                nowrap: true,
                scrollbarSize: 0,
                fit: false,
                rownumbers: true,
                toolbar: '#' + toolbarId,
                pagination: false
            });
        }
    },
    /***注册datagrid无分页组件(带复选框)**/
    registerDataGridNotPageWithCheckbox: function (id, dataGridUrl, toolbarId, arr) {
        if (getById(id)) {
            //微调工具栏中文字标签的宽度和对齐方式、按照templates/inlib/inStorageOrders.html的写法
            PageUtil.resizeToolBarTd(toolbarId);
            return $(getById(id)).datagrid({
                method: 'post',
                url: baseHttpUrl + dataGridUrl,
                loadMsg: '数据加载中,请稍后...',
                queryParams: arr,
                singleSelect: false,
                fitColumns: false,
                nowrap: true,
                scrollbarSize: 0,
                fit: false,
                rownumbers: true,
                toolbar: '#' + toolbarId,
                pagination: false
            });
        }
    },
    /***注册datagrid(带复选框)组件**/
    registerDataGridWithCheckbox: function (id, dataGridUrl, toolbarId, arr) {
        if (getById(id)) {
            //微调工具栏中文字标签的宽度和对齐方式、按照templates/inlib/inStorageOrders.html的写法
            PageUtil.resizeToolBarTd(toolbarId);
            return $(getById(id)).datagrid({
                method: 'post',
                url: baseHttpUrl + dataGridUrl,
                loadMsg: '数据加载中,请稍后...',
                queryParams: arr,
                singleSelect: false,
                fitColumns: false,
                nowrap: true,
                pageSize: 20,
                scrollbarSize: 0,
                fit: false,
                toolbar: '#' + toolbarId,
                pagination: true
            });
        }
    },
    /***注册弹框（含取消和保存按钮）**/
    registerWindowDialog: function (dataGrid, divId, title, url, w, h) {
        var dialogDiv = $("<div id='" + divId + "' class='easyui-dialog'></div>");
        var buttonDiv = divId + "_button";
        var dialogButtons = "<div id=\"" + buttonDiv + "\">\n" +
            "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-cancel\" closeFlag=\"" + divId + "\">关闭</a>&nbsp;\n" +
            "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-save\" saveFlag=\"" + divId + "\">保存</a>&nbsp;\n" +
            "</div>";
        $("body").append(dialogDiv);
        dialogDiv.after(dialogButtons);
        $.parser.parse($("#" + buttonDiv));
        /**注册关闭按钮事件和提交事件**/
        this.closeWin(buttonDiv, divId);
        this.saveWin(dataGrid, buttonDiv, divId);
        $("#" + divId).dialog({
            content: "<iframe name='" + divId + "_iframe' id='" + divId + "_iframe'  src='" + baseHttpUrl + url + "' width='100%' height='98%' frameborder='0' scrolling='auto'></iframe>",
            title: title,
            width: w,
            height: h,
            modal: true,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            buttons: '#' + buttonDiv,
            onClose: function () {
                $(this).panel('destroy');
                $("#" + divId).remove();
                $("#" + divId + "_button").remove();
            }
        });
    },
    /***注册弹框（仅含关闭按钮）**/
    registerWindowDialogSingleBut: function (divId, title, url, w, h) {
        var dialogDiv = $("<div id='" + divId + "' class='easyui-dialog'></div>");
        var buttonDiv = divId + "_button";
        var dialogButtons = "<div id=\"" + buttonDiv + "\">\n" +
            "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-cancel\" closeFlag=\"" + divId + "\">关闭</a>&nbsp;\n" +
            "</div>";
        $("body").append(dialogDiv);
        dialogDiv.after(dialogButtons);
        $.parser.parse($("#" + buttonDiv));
        /**注册关闭按钮事件和提交事件**/
        this.closeWin(buttonDiv, divId);
        $("#" + divId).dialog({
            content: "<iframe name='" + divId + "_iframe' id='" + divId + "_iframe'  src='" + baseHttpUrl + url + "' width='100%' height='98%' frameborder='0' scrolling='auto'></iframe>",
            title: title,
            width: w,
            height: h,
            modal: true,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            buttons: '#' + buttonDiv,
            onClose: function () {
                $(this).panel('destroy');
                $("#" + divId).remove();
                $("#" + divId + "_button").remove();
            }
        });
    },
    /***注册图片弹框（仅含关闭按钮）**/
    registerImgWindowDialogSingleBut: function (parentDivId, divId, title, url, w, h) {
        var dialogDiv = $("<div id='" + divId + "' class='easyui-dialog'></div>");
        var buttonDiv = divId + "_button";
        var dialogButtons = "<div id=\"" + buttonDiv + "\">\n" +
            "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-cancel\" closeFlag=\"" + divId + "\">关闭</a>&nbsp;\n" +
            "</div>";
        $("#" + parentDivId + "_iframe").contents().find("body").append(dialogDiv);
        dialogDiv.after(dialogButtons);
        $.parser.parse($("#" + buttonDiv));
        /**注册关闭按钮事件和提交事件**/
        this.closeWin(buttonDiv, divId);
        // $("#"+parentDivId + "_iframe").contents().find("#" + divId)
        $("#" + divId).dialog({
            content: "<iframe name='" + divId + "_iframe' id='" + divId + "_iframe'  src='" + url + "' width='100%' height='98%' frameborder='0' scrolling='auto'></iframe>",
            title: title,
            width: w,
            height: h,
            modal: true,
            collapsible: false,
            minimizable: false,
            maximizable: false,
            buttons: '#' + buttonDiv,
            onClose: function () {
                $(this).panel('destroy');
                $("#" + divId).remove();
                $("#" + divId + "_button").remove();
            }
        });
    },
    /**注册关闭弹框事件**/
    closeWin: function (buttonDiv, divId) {
        this.registerEvent($("#" + buttonDiv).find("a[closeFlag]").eq(0)[0], "click", function () {
            $('#' + divId).dialog('close');
        }, divId);

    },
    closeDialog: function (divId) {
        $('#' + divId).dialog('close');
    },
    refreshDataGrid: function (dateGrid) {
        dateGrid.datagrid("reload");
    },
    /** *审核弹框（含通过和不通过按钮）含查看流程图* */
    registerActivitiDialog: function (divId, title, url, w, h, taskId, checkTaskUrl, businessCode, titleName, procInsId, cloudAddress) {
        var dialogDiv = $("<div id='" + divId
            + "' class='easyui-dialog'></div>");
        var buttonDiv = divId + "_button";
        var dialogButtons = "<div id=\""
            + buttonDiv
            + "\">\n"
            + "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-cancel\" denyFlag=\""
            + divId
            + "\">不通过</a>&nbsp;\n"
            + "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-save\" passFlag=\""
            + divId + "\">通过</a>&nbsp;\n"
            + "<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" iconCls=\"icon-save\" activitiImg=\""
            + divId + "\">查看流程图</a>&nbsp;\n" + "</div>";
        $("body").append(dialogDiv);
        dialogDiv.after(dialogButtons);
        $.parser.parse($("#" + buttonDiv));
        /** 注册关闭按钮事件和提交事件* */
        this.denyWin(buttonDiv, divId, taskId, checkTaskUrl, businessCode);
        this.passWin(buttonDiv, divId, taskId, checkTaskUrl, businessCode, titleName);
        this.activitiImg(buttonDiv, divId, procInsId, cloudAddress);
        $("#" + divId)
            .dialog(
                {
                    content: "<iframe name='"
                        + divId
                        + "_iframe' id='"
                        + divId
                        + "_iframe'  src='"
                        + url
                        + "' width='100%' height='98%' frameborder='0' scrolling='auto'></iframe>",
                    title: title,
                    width: w,
                    height: h,
                    modal: true,
                    collapsible: false,
                    minimizable: false,
                    maximizable: false,
                    buttons: '#' + buttonDiv,
                    onClose: function () {
                        $(this).panel('destroy');
                        $("#" + divId).remove();
                        $("#" + divId + "_button").remove();
                    }
                });
    },
    /** *注册弹框拒绝事件* */
    denyWin: function (buttonDiv, divId, taskId, checkTaskUrl, businessCode) {
        this
            .registerEvent(
                $("#" + buttonDiv).find("a[denyFlag]").eq(0)[0],
                "click",
                function () {
                    var obj = $.messager.prompt("审核意见", "请输入意见", function (remark) {
                    });
                    $(obj).parent().find(".window-body:first").find("input:first").on("blur", function () {
                        obj.remarkStr = this.value;
                    });
                    $(obj).parent().find(".l-btn-small").eq(0).on("click", function () {
                        //确定
                        var type = "0";
                        progressLoad();
                        jrequest.post(baseHttpUrl + checkTaskUrl, {
                            taskId: taskId,
                            businessCode: businessCode,
                            type: type,
                            remark: obj.remarkStr
                        }).then(res => {
                            progressClose();
                            if (res.data.result === 200) {
                                $(document.getElementById("myTaskTable")).find("a[taskId='" + taskId + "']").parent().remove();
                                $.messager.alert('提示', res.data.message, 'info');
                            }
                            $('#' + divId).dialog('close');
                        })
                        // $.post(baseHttpUrl + checkTaskUrl, {
                        //         taskId: taskId, businessCode: businessCode,
                        //         type: type, remark: obj.remarkStr
                        //     },
                        //     function (data) {
                        //         progressClose();
                        //         if (data.result == 200) {
                        //             $(document.getElementById("myTaskTable")).find("a[taskId='" + taskId + "']").parent().remove();
                        //             $.messager.alert('提示', data.message, 'info');
                        //         }
                        //         $('#' + divId).dialog('close');
                        //     }, 'JSON');
                    });
                    $(obj).parent().find(".l-btn-small").eq(1).on("click", function () {
                        //取消
                    });
                }, divId);
    },
    /** *注册弹框通过事件* */
    passWin: function (buttonDiv, divId, taskId, checkTaskUrl, businessCode, titleName) {
        this.registerEvent(
            $("#" + buttonDiv).find("a[passFlag]").eq(0)[0], "click",
            function () {
                var obj = $.messager.prompt("审核意见", "请输入意见", function (remark) {
                });
                $(obj).parent().find(".window-body:first").find("input:first").on("blur", function () {
                    obj.remarkStr = this.value;
                });
                $(obj).parent().find(".l-btn-small").eq(0).on("click", function () {
                    //确定
                    var type = "1";
                    progressLoad();
                    jrequest.post(baseHttpUrl + checkTaskUrl, {
                        taskId: taskId,
                        businessCode: businessCode,
                        type: type,
                        remark: obj.remarkStr,
                        titleName: titleName
                    }).then(res => {
                        progressClose();
                        if (res.data.result === 200) {
                            $(document.getElementById("myTaskTable")).find("a[taskId='" + taskId + "']").parent().remove();
                            $.messager.alert('提示', res.data.message, 'info');
                            $('#' + divId).dialog('close');
                        }
                        if (res.data.result === 500) {
                            $.messager.alert('提示', res.data.message, 'info');
                            return false;
                        }
                        if (res.data.result === 400) {
                            //这例为个人领料审核时使用，如有问题可群里询问，请勿私自删除
                            Page.passSecondWin('secondWin', divId, taskId, obj.remarkStr, checkTaskUrl, businessCode, titleName, '询问', '批复数量未修改，您是否通过?');
                        }
                    })
                    // $.post(baseHttpUrl + checkTaskUrl, {
                    //         taskId: taskId, businessCode: businessCode,
                    //         type: type, remark: obj.remarkStr, titleName: titleName
                    //     },
                    //     function (data) {
                    //         progressClose();
                    //         if (data.result === 200) {
                    //             $(document.getElementById("myTaskTable")).find("a[taskId='" + taskId + "']").parent().remove();
                    //             $.messager.alert('提示', data.message, 'info');
                    //             $('#' + divId).dialog('close');
                    //         }
                    //         if (data.result == 500) {
                    //             $.messager.alert('提示', data.message, 'info');
                    //             return false;
                    //         }
                    //         if (data.result == 400) {
                    //             //这例为个人领料审核时使用，如有问题可群里询问，请勿私自删除
                    //             Page.passSecondWin('secondWin', divId, taskId, obj.remarkStr, checkTaskUrl, businessCode, titleName, '询问', '批复数量未修改，您是否通过?');
                    //         }
                    //     }, 'JSON');
                });
                $(obj).parent().find(".l-btn-small").eq(1).on("click", function () {
                    //取消
                });
            }, divId);
    },
    activitiImg: function (buttonDiv, divId, procInsId, cloudAddress) {
        this.registerEvent(
            $("#" + buttonDiv).find("a[activitiImg]").eq(0)[0],
            "click",
            function () {
                Page.registerImgWindowDialogSingleBut(divId, "imgWin", "流程图", cloudAddress + "/v1/human/getActivitiProccessImage?pProcessInstanceId=" + procInsId, "75%", "80%");
            }, divId);
    },
    /***注册弹框保存事件**/
    saveWin: function (dataGrid, buttonDiv, divId) {
        this.registerEvent($("#" + buttonDiv).find("a[saveFlag]").eq(0)[0], "click", function () {
            window.frames[divId + "_iframe"].parentPanelDivId = divId;
            window.frames[divId + "_iframe"].parentPanelDataGrid = dataGrid;
            window.frames[divId + "_iframe"].saveWin();
        }, divId);
    },
    /****字段过长时用省略号...代替*****/
    moreInfoFormatter: function (value, row, index) {
        var str = "";
        var name = "";
        if (value) {
            return '<span style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;display:block;"' +
                ' title="' + value + '">' + value + '</span>';
        }
        return str;
    },
    /***注册普通不可输入combobox****/
    combobox: function (id, url, valueField, textField, afterLoadFun, afterSelectFun, afterChangeFun) {
        $('#' + id).combobox({
            url: url,
            lines: true,
            panelHeight: '200',//combobox高度统一用200px,避免后期被测试人员揪出来整改
            editable: false,
            valueField: valueField,
            textField: textField,
            onLoadSuccess: afterLoadFun,
            onSelect: afterSelectFun,
            onChange: afterChangeFun
        });
    },
    /***注册普通combobox(高度为auto)****/
    comboboxOfAutoHeight: function (id, url, valueField, textField, afterLoadFun, afterSelectFun, afterChangeFun) {
        $('#' + id).combobox({
            url: url,
            lines: true,
            panelHeight: 'auto',//combobox高度统一用200px,避免后期被测试人员揪出来整改
            editable: false,
            valueField: valueField,
            textField: textField,
            onLoadSuccess: afterLoadFun,
            onSelect: afterSelectFun,
            onChange: afterChangeFun
        });
    },
    /***注册普通可输入combobox****/
    comboboxWithEdit: function (id, url, valueField, textField, afterLoadFun, afterSelectFun, afterChangeFun) {
        $('#' + id).combobox({
            url: url,
            lines: true,
            panelHeight: '200',//combobox高度统一用200px,避免后期被测试人员揪出来整改
            editable: true,
            valueField: valueField,
            textField: textField,
            onLoadSuccess: afterLoadFun,
            onSelect: afterSelectFun,
            onChange: afterChangeFun
        });
    },
    /**
     *  注册普通messager.confirm
     *  dataGrid：dateGrid对象，url：请求url，title：标题，info：内容
     *  paramMap：需传参数对象，args：dateGrid带参时需填，status：二级弹窗刷新一级dataGrid需填
     * */
    messager: function (dateGrid, url, title, info, paramMap, args, status) {
        parent.$.messager.confirm(title, info, function (b) {
            if (b) {
                progressLoad();
                jrequest.post(baseHttpUrl + url, paramMap)
                    .then(res=>{
                        progressClose();
                        if (res.data.result === 200) {
                            parent.$.messager.alert('提示', res.data.message, 'info');
                            //这里主要是为了执行操作后刷新界面
                            dateGrid.datagrid("load", args);
                            if (status === "Y") {
                                parent.refreshGrid();
                            }
                        } else {
                            parent.$.messager.alert('提示', res.data.message, 'info');
                            //这里主要是为了执行操作后刷新界面
                            dateGrid.datagrid("load", args);
                            if (status === "Y") {
                                parent.refreshGrid();
                            }
                        }
                    });
                // $.post(baseHttpUrl + url, paramMap, function (date) {
                //     progressClose();
                //     if (date.result == 200) {
                //         parent.$.messager.alert('提示', date.message, 'info');
                //         //这里主要是为了执行操作后刷新界面
                //         dateGrid.datagrid("load", args);
                //         if (status == "Y") {
                //             parent.refreshGrid();
                //         }
                //     } else {
                //         parent.$.messager.alert('提示', date.message, 'info');
                //         //这里主要是为了执行操作后刷新界面
                //         dateGrid.datagrid("load", args);
                //         if (status == "Y") {
                //             parent.refreshGrid();
                //         }
                //     }
                // }, 'JSON')
            }
        });
    },
    /**注册父级菜单选择框**/
    registerComboboxSelect: function (comboboxId, url, code, name) {
        this.combobox(comboboxId, baseHttpUrl + url, code, name,
            function () {
                //此处执行加载完成后的事件
            },
            function (row) {
            },
            function (newVal, oldVal) {

            }
        )
    }
};


var PageUtil = {
    resizeToolBarTd: function (toolbarId) {
        var tdEvery = $("#" + toolbarId).find("td");
        for (var i in tdEvery) {
            if (typeof (tdEvery.eq(i)[0]) != 'undefined') {
                //如果td的下标是奇数，则认定为左侧的文字标签
                if ((tdEvery.eq(i).index() + 1) % 2 != 0) {
                    tdEvery.eq(i)[0].style.textAlign = 'right';
                    try {
                        var labelTitle = tdEvery.eq(i).find("label").html();
                        if (labelTitle.length > 0) {
                            //设置文字标签所在td的宽度
                            tdEvery.eq(i)[0].style.width = labelTitle.length + 2 + "em";
                        }
                    } catch (e) {
                        console.log(e);
                    }
                } else {
                    //如果td的下标是偶数，则认定为右侧的输入框
                    tdEvery.eq(i)[0].style.textAlign = 'left';
                }
            }
        }
    }
}

