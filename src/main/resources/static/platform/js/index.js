$(function(){
	InitLeftMenu();
	tabClose();
	tabCloseEven();
	$('#w').window('close');
});

function openNav(id){
	var title;
	if(id==1){
		title = "公司管理";
	}
	if(id==2){
		title = "用户管理";
	}
	if(id==3){
		title = "区域管理";
	}
	var url = $("#nav_"+id).attr("rel");
	addTab(title,url);
}

//初始化左侧
function InitLeftMenu() {
    $.ajax({
        type: "post",
        dataType: "json",
        cache : 'false',
        url: baseHttpUrl+"/menuApi/getMenuTree",
        success: function(json){
            $("#divMenu").empty();
            var menulist = "";
			if(json != null && json.length > 0){
				for(var i in json) {
					var _m = json[i];
					menulist += '<div title="&nbsp;&nbsp;'+_m.text+'"  data-options="iconCls:\''+_m.iconCls+'\'" style="overflow:auto;">';
		        	menulist += '<ul>';
		        	if(_m.children!=null&&_m.children.length>0){
		        		var _children=_m.children;
						for(var j in _children){
							var _cm = _children[j];
							menulist += '<li><div><a ref="'+ _cm.id + '" href="#" rel="'+baseHttpUrl+_cm.attributes + '" ><span class="icon '+_cm.iconCls+'" >&nbsp;</span>' + _cm.text + '</a></div></li> ';
						}
		        	}
					menulist += '</ul></div>';
				}
			}
            $("#divMenu").append(menulist);
            $.parser.parse($('#divMenu'));
            $('#divMenu').accordion({ border:false,fit:true });
        	$('#divMenu li a').click(function(){
        		var tabTitleParent = $(this).parents(".panel-body").siblings(".panel-header").find(".panel-title").eq(1).text();
        		var tabTitle = $(this).text();
        		var title=tabTitleParent+"-"+tabTitle;
        		var url = $(this).attr("rel");
        		$('#divMenu li div').removeClass("selected");
        		$(this).parent().addClass("selected");
        		addTab(title,url);
        	}).hover(function(){
        		$(this).parent().addClass("hover");
        	},function(){
        		$(this).parent().removeClass("hover");
        	});
		},
        error:function(msg){
        	console.log(msg);
        }
    });
}

function addTab(subtitle,url){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			width:$('#mainPanle').width()-10,
			height:$('#mainPanle').height()-26
		});
	}else{
		$('#tabs').tabs('select',subtitle);
	}
	tabClose();
}

function createFrame(url){
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="border:0;width:100%;height:99.5%;"></iframe>';
	return s;
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

//绑定右键菜单事件
function tabCloseEven(){
	
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	});
	
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});	
	});
	
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!=currtab_title)
				$('#tabs').tabs('close',t);
		});	
	});
	
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	});
	
}

function tabClose(){
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children("span").text();
		$('#tabs').tabs('close',subtitle);
	});

	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
		
		var subtitle =$(this).children("span").text();
		$('#mm').data("currtab",subtitle);
		return false;
	});
}