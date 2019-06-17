//preview：是否显示预览。null/false:不显示，true:显示
    function printPage(preview)
    {
        try
        {
            var content=window.document.body.innerHTML;
            var oricontent=content;
            while(content.indexOf("{$printhide}")>=0) content=content.replace("{$printhide}","style='display:none'");
            if(content.indexOf("ID=\"PrintControl\"")<0) content=content+"<OBJECT ID=\"PrintControl\" WIDTH=0 HEIGHT=0 CLASSID=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></OBJECT>";
            window.document.body.innerHTML=content;
//PrintControl.ExecWB(7,1)打印预览，(1,1)打开，(4,1)另存为，(17,1)全选，(10,1)属性，(6,1)打印，(6,6)直接打印，(8,1)页面设置
            if(preview==null||preview==false) PrintControl.ExecWB(6,1);
            else PrintControl.ExecWB(7,1); //OLECMDID_PRINT=7; OLECMDEXECOPT_DONTPROMPTUSER=6/OLECMDEXECOPT_PROMPTUSER=1
            window.document.body.innerHTML=oricontent;
        }
        catch(ex){ alert("执行Javascript脚本出错。"); }
    }
function printConten(preview, html)
{
    try
    {
        var content=html;
        var oricontent=window.document.body.innerHTML;
        while(content.indexOf("{$printhide}")>=0) content=content.replace("{$printhide}","style='display:none'");
        if(content.indexOf("ID=\"PrintControl\"")<0) content=content+"<OBJECT ID=\"PrintControl\" WIDTH=0 HEIGHT=0 CLASSID=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></OBJECT>";
        window.document.body.innerHTML=content;
//PrintControl.ExecWB(7,1)打印预览，(1,1)打开，(4,1)另存为，(17,1)全选，(10,1)属性，(6,1)打印，(6,6)直接打印，(8,1)页面设置
        if(preview==null||preview==false) PrintControl.ExecWB(6,1);
        else PrintControl.ExecWB(7,1); //OLECMDID_PRINT=7; OLECMDEXECOPT_DONTPROMPTUSER=6/OLECMDEXECOPT_PROMPTUSER=1
        window.document.body.innerHTML=oricontent;
    }
    catch(ex){ alert("执行Javascript脚本出错。"); }
}