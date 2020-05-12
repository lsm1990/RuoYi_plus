/*
*
*  name: baseMotheds
*  author: markbro
*  date: 2019/04/17 10:30
*
*/
/* 切换主题 */
function switchBlogTheme() {
    $.modal.alertWarning("该功能移到了后台管理中!请到后台切换!");
    return;
    layer.open({
        type : 2,
        shadeClose : true,
        title : "切换主题",
        area : ["1428px", "470px"],
        content : [ctx + "blog/blogTheme?pageSize=3", 'no']
    })
}

$(function(){
    baseMotheds.init();
    initSwitch();
    // 切换模式
    $('#switchMode').on('click',function(){
        localStorage.setItem('switchMode','on');
        if(typeof(Storage)!=="undefined"){
            if($(this).hasClass('on')){
                $(this).removeClass('on');
                localStorage.setItem('switchMode','off');
            }else{
                $(this).addClass('on');
                localStorage.setItem('switchMode','on');
            }
            initSwitch();
        } else {
            // 抱歉! 不支持 web 存储。
            $(this).toggleClass('on');
            if($(this).hasClass('on')){
                $('#articleModeGrid').addClass('on');
            }else{
                $('#articleModeGrid').removeClass('on');
            }
        }
    });
});
 
var initSwitch = function(){
    if(typeof(Storage)!=="undefined"){
        var _type = localStorage.getItem('switchMode');
        if( _type != 'undefined'){
            if(_type == 'on'){
	            $('#switchMode, #articleModeGrid').addClass('on');
            }else{
	            $('#switchMode, #articleModeGrid').removeClass('on');
            }
        }
    } else {
        console.log('不支持localStorage');
    }
};

// baseMotheds
var baseMotheds = function(){
    var scrollHead = function(){
        var headGroup = $('#headGroup').height();
        var headTitleGroup = $('#headTitleGroup').height();
        var headFullTabs = $('#headFullTabs').height();
        var sroll = parseInt(headGroup+headTitleGroup);
        //var sroll = 155;
        $(window).bind("scroll resize", function() {
        	if ($(window).scrollTop() > sroll) {
        		$('#headFullTabs').addClass('index');
        		$('#channelGroup').css('padding-top',headFullTabs+'px');
        	}else{
        		$('#headFullTabs').removeClass('index');
        		$('#channelGroup').css('padding-top','0');
        	}
        });
    };
    var scrollTop = function(){
    	var offset = 300,
    		offset_opacity = 1200,
    		scroll_top_duration = 700,
    		$back_to_top = $('.backTop');
    	$(window).scroll(function(){
    		( $(this).scrollTop() > offset ) ? $back_to_top.addClass('backTopVisible') : $back_to_top.removeClass('backTopVisible cd-fade-out');
    		if( $(this).scrollTop() > offset_opacity ) { 
    			$back_to_top.addClass('cd-fade-out');
    		}
    	});
    	$back_to_top.on('click', function(event){
    		event.preventDefault();
    		$('body,html').animate({
    			scrollTop: 0 ,
    		 	}, scroll_top_duration
    		);
    	});
    };
    var pv=function(){
        var data={
            os:navigator.platform,
            //pageId:document.title,
            referrer:document.referer,
            url:window.location.href,
            timeZone:new Date().getTimezoneOffset()/60
        };
        $.ajax({
                type: 'POST',
                url: "/page/view",
                data: data,
                success: function(json){
                    console.log(json);
                }
            }
        );
    };
    return{
        init: function(){
            scrollHead();
            scrollTop();
            pv();
        }
    }
}();

