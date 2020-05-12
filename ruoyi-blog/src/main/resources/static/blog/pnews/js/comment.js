$(function () {
    var pblogNickName=getCookie("pt-cms-username");
    var zblogQQ=getCookie("pt-cms-qq");
    var zblogEmail=getCookie("pt-cms-email");
    if(pblogNickName!=""){
        $("#user-name-content").show();
        $("#user-name").text(pblogNickName);
        $("#nickname").val(pblogNickName);
        $("#qq").val(zblogQQ);
        $("#email").val(zblogEmail);
    }else{
        $("#user-info").show();
    }
    var simplemde = new SimpleMDE({
        element: document.getElementById("comment-textarea"),
        toolbar: [],
        autoDownloadFontAwesome: false,
        placeholder: "你的评论可以一针见血",
        renderingConfig: {
            codeSyntaxHighlighting: true
        },
        tabSize: 4,
        status:false
    });
    $("#qq").blur(function(){
        var qq=$("#qq").val();
        var nickname=$("#nickname").val();
        if(nickname.length==0&&qq.length > 0){
            if(!isNaN(qq)){
                getQqInfo(qq,function (data) {
                    $("#nickname").val(data.nickname);
                });
            }else{
                layer.msg("qq格式不正确！")
            }
        }
    });
    function setCookie(cname,cvalue,exdays){
        var d = new Date();
        d.setTime(d.getTime()+(exdays*24*60*60*1000));
        var expires = "expires="+d.toGMTString();
        document.cookie = cname+"="+cvalue+"; "+expires+";path=/";
    }
    function getCookie (cname){
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i].trim();
            if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
        }
        return "";
    }
    function getQqInfo(qq,d) {
        $.ajax({  /* 使用ajax请求 */
            type: "get",  /* 请求方式为GET */
            url: "http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins="+qq,  /* 发送请求的地址 */
            dataType: "jsonp",   /* 返回JSONP格式 */
            scriptCharset: 'gbk',
            jsonp: "callback",    /* 重写回调函数名 */
            jsonpCallback:"portraitCallBack",  /* 指定回调函数名 */
            success: function(json){  /* 请求成功输出 */
                if(json[qq]==undefined||json[qq][6].trim()==""){
                    layer.msg("qq信息不存在！")
                    return;
                }
                var qqInfo={qq:"",nickname:"",avatar:""};
                for(var key in json){
                    qqInfo.qq=key;
                }
                qqInfo.nickname=json[qq][6];
                qqInfo.avatar=json[qq][0];
                if (typeof d == "function") {
                    d(qqInfo);
                }
            },
            error: function(){  /* 请求失败输出 */
                layer.msg('获取QQ信息失败');
            }
        });
    }
    function init(pageNum) {
        var url=ctx+"blog/comments";

        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {"pageNum": (pageNum==null? 1 : pageNum),"tid":tid,"type":comment_type},
            success: function(data) {

                if(data.code!=0&&data.code!='0'){
                    console.log(result.msg);
                }
                data=data.data;
                var commentOne="";
                if(data.total==0){
                    commentOne+='<div class="no-comment">暂无评论，快来占领宝座</div>';
                    $("#comment-ul").append(commentOne);
                }else{
                    $.each(data.rows,function (index,value) {
                        commentOne +=
                            '<li>'+
                            '	<div class="comment-body" id="comment-'+value.id+'">'+
                            '		<div class="comment-user-img">'+
                            '			<img src="'+(value.avatar||"/blog/img/user-default.png")+'" onerror="this.src=\'/blog/img/user-default.png\'" />'+
                            '		</div>'+
                            '		<div class="comment-info">'+
                            '			<div class="comment-top">'+
                            '				<span class="comment-nickname">'+
                            '					<a href="javascript:void(0)">'+value.userName+'</a>'+
                            '				</span>'+
                            '				<span class="comment-time">'+value.createTime+
                            '				</span>'+
                            '			</div>'+
                            '           <div class="comment-content">';
                        if(value.parent!=null){
                            commentOne +=
                                '<div class="comment-parent">'+
                                '				<div class="comment-parent-user"><a class="comment-link" data-link="comment-'+value.parent.id+'">@'+value.parent.userName+'</a></div>'+
                                '				<div class="comment-parent-content">'+value.parent.content+'</div>'+
                                '			</div> ';
                        }

                        commentOne +=value.content+
                            '			</div>'+
                            '			<div class="comment-footer">'+
                            '				<span class="reply mr-5" reply-id="'+value.id+'">回复</span>'+
                            '				<span class="cancel-reply mr-5" style="display: none;">取消回复</span>'+
                            '				<span class="comment-support pointer fa fa-thumbs-o-up" biz-id="'+value.id+'">'+value.upVote+'</span>'+
                            '			</div>'+
                            '		</div>'+
                            '	</div> '+
                            '</li>';
                    })
                    $("#comment-more").remove();
                    if(data.hasNextPage){
                        commentOne+='<div id="comment-more" data-page="'+data.nextPage+'" class="comment-more">加载更多</div>'
                    }
                    $("#comment-ul").append(commentOne);
                    /*加载更多*/
                    $("#comment-more").click(function () {
                        init($(this).attr("data-page"));
                    })
                    /*link至评论*/
                    $(".comment-link").click(function () {
                        var commentLinkId = $(this).attr("data-link");
                        $("html,body").animate({
                            scrollTop:$("#"+commentLinkId).offset().top-55},{duration: 300,easing: "swing"})
                    })

                    $(".reply").click(function () {
                        var replyId=$(this).attr("reply-id");
                        if($("#reply-comment-form").length>0){
                            $replyForm=$("#reply-comment-form");
                            $("#reply-comment-form").remove();
                            $(this).parent().after($replyForm);
                            $("#reply-comment-form").show();
                            $(".reply[style='display: none;']").next().hide();
                            $(".reply[style='display: none;']").show();
                            $("#replyId").val(replyId);
                        }else{
                            var replyForm =
                                '<form id="reply-comment-form" class="form-horizontal mt-10">'+
                                '   <input name="tid" type="hidden" value="'+tid+'"  />'+
                                '   <input name="type" type="hidden" value="'+comment_type+'"  />'+
                                '   <input id="replyId" name="pid" type="hidden" value="'+replyId+'"  />'+
                                '   <div class="form-group" style="display: '+(pblogNickName==""?"block":"none")+'">'+
                                '       <div class="col-sm-4">'+
                                '           <input id="reply-nickname" value="'+pblogNickName+'"  type="text" class="form-control" name="userName" placeholder="昵称(必填)" />'+
                                '       </div>'+
                                '       <div class="col-sm-4">'+
                                '           <input id="reply-qq" value="'+zblogQQ+'" type="text" class="form-control" name="qq" placeholder="QQ（可显示头像和昵称）" />'+
                                '       </div>'+
                                '       <div class="col-sm-4">'+
                                '           <input id="reply-email" value="'+zblogEmail+'" type="text" class="form-control" name="email" placeholder="邮箱" />'+
                                '       </div>'+
                                '   </div>'+
                                '   <div class="form-group">'+
                                '       <div class="col-xs-12">'+
                                '           <textarea name="content" id="reply-comment-textarea"></textarea>'+
                                '       </div>'+
                                '   </div>'+
                                '   <div>'+
                                '   <button id="submitReplyCommentBtn" type="button" class="btn btn-primary">发表评论</button>'+
                                '   </div>'+
                                '</form>'
                            $(this).parent().after(replyForm);
                            var replySimplemde = new SimpleMDE({
                                element: document.getElementById("reply-comment-textarea"),
                                toolbar: [],
                                autoDownloadFontAwesome: false,
                                placeholder: "说点什么好呢",
                                renderingConfig: {
                                    codeSyntaxHighlighting: true
                                },
                                tabSize: 4,
                                status:false
                            });

                            $("#reply-qq").blur(function(){
                                var qq=$("#reply-qq").val();
                                var nickname=$("#reply-nickname").val();
                                if(nickname.length==0&&qq.length > 0){
                                    if(!isNaN(qq)){
                                        getQqInfo(qq,function (data) {
                                            $("#reply-nickname").val(data.nickname);
                                        });
                                    }else{
                                        layer.msg("qq格式不正确！")
                                    }
                                }
                            });
                        }
                        $(this).hide();
                        $(this).next().show();
                        $("#submitReplyCommentBtn").on('click',function () {
                            if($("#reply-nickname").val()==""){
                                layer.msg("请输入昵称")
                                return;
                            }else if(replySimplemde.value()==""){
                                layer.msg("说点什么吧")
                                return;
                            }
                            $("#reply-comment-textarea").val(replySimplemde.markdown(replySimplemde.value()));

                            $.ajax({
                                url: ctx+"blog/comments/save",
                                type: "post",
                                dataType: "json",
                                data: $("#reply-comment-form").serialize(),
                                success: function(json) {
                                    if(getCookie("pt-cms-username")==""){
                                        setCookie("pt-cms-username",$("#reply-nickname").val(),30);
                                        setCookie("pt-cms-qq",$("#reply-qq").val(),30);
                                        setCookie("pt-cms-email",$("#reply-email").val(),30);
                                    }
                                    if (json.code != 0 && json.code != '0') {
                                        layer.msg(json.msg);
                                        return;
                                    }
                                    data = json.data;

                                    layer.msg(json.msg, {
                                        offset: '30%',
                                        time: 800
                                    }, function () {
                                        location.reload();
                                    });

                                }});
                        })
                    })
                    $(".cancel-reply").click(function () {
                        $("#reply-comment-form").hide();
                        $(this).hide();
                        $(this).prev().show();
                    })

                    $(".comment-support").click(function () {
                        $thisLove = $(this);
                        $.ajax({
                            url: ctx+"blog/comments/upVote",
                            type: "post",
                            dataType: "json",
                            data: {"commentId":$(this).attr("biz-id")},
                            success: function(json) {
                                if (json.code != 0 && json.code != '0') {
                                    return;
                                }
                                layer.msg(json.msg);
                                $thisLove.text(parseInt($thisLove.text())+1);
                            }});

                    })
                }


            }
        });
    }
    init();

    /*提交评论*/
    $("#submitCommentBtn").click(function () {
        if($("#nickname").val()==""){
            layer.msg("请输入昵称")
            return;
        }else if(simplemde.value()==""){
            layer.msg("说点什么吧")
            return;
        }
        $("#comment-textarea").val(simplemde.markdown(simplemde.value()));

        $.ajax({
            url: ctx+"blog/comments/save",
            type: "post",
            dataType: "json",
            data: $("#comment-form").serialize(),
            success: function(json) {
                if(getCookie("pt-cms-username")==""){
                    setCookie("pt-cms-username",$("#reply-nickname").val(),30);
                    setCookie("pt-cms-qq",$("#reply-qq").val(),30);
                    setCookie("pt-cms-email",$("#reply-email").val(),30);
                }
                if (json.code != 0 && json.code != '0') {
                    layer.msg(json.msg);
                    return;
                }
                data = json.data;

                layer.msg(json.msg, {
                    offset: '30%',
                    time: 800
                }, function () {
                    if(pblogNickName!=$("#nickname").val()||zblogQQ!=$("#qq").val()||zblogEmail!=$("#email").val()){
                        setCookie("pt-cms-username",$("#nickname").val(),30);
                        setCookie("pt-cms-qq",$("#qq").val(),30);
                        setCookie("pt-cms-email",$("#email").val(),30);
                    }
                    location.reload();
                });

            }});
    })
    /*点击用户名*/
    $("#user-name").click(function () {
        if($("#user-info").hasClass("user-show")){
            $("#user-info").slideUp();
            $("#user-info").removeClass("user-show");
        }else{
            $("#user-info").slideDown();
            $("#user-info").addClass("user-show");
        }

    })
})

