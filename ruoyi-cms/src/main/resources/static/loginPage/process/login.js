var cookieName=getCookie('admin_username');
var cookiePass=getCookie('admin_password');
var cookieRememberMe=getCookie('admin_rememberMe');

function getCookie(name){
    //获取cookie字符串
    var strCookie=document.cookie;
    //将多cookie切割为多个名/值对
    var arrCookie=strCookie.split("; ");
    var value="";
    //遍历cookie数组，处理每个cookie对
    for(var i=0;i<arrCookie.length;i++){
        var arr=arrCookie[i].split("=");
        if(name==arr[0]){
            value=arr[1];
            break;
        }
    }
    return value;
}
$(function() {
    if(cookieRememberMe=="1"){
        $("#rememberMe").attr("checked",true);
    }else{
        $("#rememberMe").attr("checked",false);
    }
    if($.common.isNotEmpty(cookieName)){
        $("#username").val(cookieName);
    }
    if($.common.isNotEmpty(cookiePass)){
        $("#password").val(cookiePass);
    }
    $('.imgcode').click(function() {
        var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
        $(".imgcode").attr("src", url);
    });
});


var login = {
    init: function() {
       // login.renderBg(),
        window.addEventListener("resize",
        function() {
          //  login.renderBg()
        });
        var fullName = document.getElementById("login_fullname"),
        fun = "login.login()",
        forget_input = document.getElementById("forget_input");
        null != fullName ? (fun = "login.signup()", document.getElementById("login_fullname").addEventListener("keyup",
        function(e) {
            13 == e.keyCode && eval(fun)
        })) : null != forget_input && (fun = "login.forget()"),
        null != document.getElementById("login_email") && (document.getElementById("login_email").addEventListener("keyup",
        function(e) {
            13 == e.keyCode && eval(fun)
        }), null != document.getElementById("login_password") && document.getElementById("login_password").addEventListener("keyup",
        function(e) {
            13 == e.keyCode && eval(fun)
        })),
        $(".somemore").off("click.more").on("click.more",
        function(a) {
            a.stopPropagation(),
            $(".morelogo-con").toggleClass("popover")
        }),
        $(document).off("click.more").on("click.more",
        function() {
            $(".morelogo-con").removeClass("popover")
        }),
        setTimeout(function() {
            $(".error-tip").html("")
        },
        3e3)
    },
    showTip: function(a, b) {
        b.innerHTML = a,
        setTimeout(function() {
            b.innerHTML = ""
        },
        3e3)
    },
    /* 登陆 */
    login: function() {
            // $.modal.loading($("#btnSubmit").data("loading"));
            var a = document,
            b = a.getElementById("username"),
            c = a.getElementById("password"),
            verycode;
            if(captchaEnabled){
             verycode= a.getElementById("validateCode");
            }
            var rememberMe = $("input[name='rememberMe']").is(':checked');
            if ("" == b.value.trim()) return b.focus(),
            void login.showTip("用户名/邮箱不能为空", b.nextElementSibling);
            if (!/^([\w]+)(.[\w]+)*@([\w-]+\.){1,5}([A-Za-z]){2,4}$/.test(b.value.trim())&& b.value.trim().indexOf("@")>0) return b.focus(),
            void login.showTip("邮箱格式不正确", b.nextElementSibling);
            if ("" == c.value.trim()) return c.focus(),
            void login.showTip("密码不能为空", c.parentNode.querySelector(".error-tip"));
            b.nextElementSibling.innerHTML = "",
            c.parentNode.querySelector(".error-tip").innerHTML = "";
            if (verycode!=null){
                if ("" == verycode.value.trim()) return verycode.focus(),
                    void login.showTip("验证码为空!请输入验证码!", verycode.nextElementSibling.nextElementSibling);
                verycode.parentNode.querySelector(".error-tip").innerHTML = "";
            }

            var d = document.getElementsByClassName("logo-dot")[0];
            d.style.display = "inline-block",
            a.getElementById("username").value = b.value.trim(),
            a.getElementById("password").value = c.value.trim();
            //document.getElementById("loginform").submit()
            $.ajax({
                type: "post",
                url: ctx + "login",
                xhrFields: {
                    withCredentials: true
                },
                data: {
                    "username": b.value,
                    "password": c.value,
                    "validateCode" : verycode!=null?verycode.value:"",
                    "rememberMe": rememberMe
                },
                success: function(r) {
                    d.style.display = "none";
                    if (r.code == 0) {
                        location.href = ctx + 'index';
                    } else {
                        $.modal.closeLoading();
                        $('.imgcode').click();
                        $(".code").val("");
                        $.modal.msg(r.msg);
                    }
                }
            });
    },
    forget: function() {
        var a = document.getElementById("login_email"),
        b = document.getElementById("error-tip");
        var ok_tip=document.getElementById("success-tip");
        if ("" == a.value.trim()) return a.focus(),
        void login.showTip("邮箱不能为空", b);
        if (!/^([\w]+)(.[\w]+)*@([\w-]+\.){1,5}([A-Za-z]){2,4}$/.test(a.value.trim())) return a.focus(),
        void login.showTip("邮箱格式不正确", b);
        var c = document.getElementsByClassName("logo-dot")[0];
        c.style.display = "inline-block",
        ok_tip.style.display = "none";
        b.innerHTML = "";
        $.ajax({
            url: "/reset",
            type: "post",
            data: {
                email: a.value.trim()
            },
            success: function(json) {
                json=JSON.parse(json);
                //alert(JSON.stringify(json));
                if(json.type=="success"){
                    document.getElementById("signin_btn").style.backgroundColor="#ababab";
                    document.getElementById("signin_btn").removeAttribute('onclick');
                    ok_tip.innerHTML = "已经发送重置密码邮件，有效时间60分钟，请查收";
                    ok_tip.style.display = "block",
                        c.style.display = "none";
                }else{
                    b .style.display = "block",
                    b .innerHTML = json.content;
                    a.focus();
                    c.style.display = "none";
                }
               /* "error" == b.type ? (a.nextElementSibling.innerHTML = json.content, a.focus()) : "success" == b.result && (a.nextElementSibling.innerHTML = "已经发送重置密码邮件，有效时间30分钟，请查收</a>"),
                a.nextElementSibling.style.display = "block",
                c.style.display = "none",
                */
            }
        }),
        login.weixin.loaded = !0
    },
    /*注册*/
    signup: function() {
            var a = document,
            b = a.getElementById("login_email"),
            c = a.getElementById("login_password"),
            d = a.getElementById("login_fullname");
            if ("" == b.value.trim()) return b.focus(),
            void login.showTip("邮箱不能为空", b.nextElementSibling);
            if (!/^([\w]+)(.[\w]+)*@([\w-]+\.){1,5}([A-Za-z]){2,4}$/.test(b.value.trim())) return b.focus(),
            void login.showTip("邮箱格式不正确", b.nextElementSibling);
            if ($.inArray($.trim(b.value), login.tempmails) >= 0) return b.focus(),
            void login.showTip("此类型邮箱暂不支持", b.nextElementSibling);
            if ("" == c.value.trim()) return c.focus(),
            void login.showTip("密码不能为空", c.nextElementSibling);
            if ("" == d.value.trim()) return d.focus(),
            void login.showTip("昵称不能为空", d.nextElementSibling);
            if (d.value.length > 15) return d.focus(),
            void login.showTip("昵称最大长度为15位", d.nextElementSibling);
            a.getElementById("login_email").value = b.value.trim(),
            a.getElementById("login_password").value = c.value.trim(),
            a.getElementById("login_fullname").value = d.value.trim(),
            b.nextElementSibling.innerHTML = "",
            c.nextElementSibling.innerHTML = "",
            d.nextElementSibling.innerHTML = "";
            var e = document.getElementsByClassName("logo-dot")[0];
            e.style.display = "inline-block",
            document.getElementById("signup_form").submit()
    },
    bind: function() {
        var a = $("#account"),
        b = $("#password"),
        c = "^.{6,24}$",
        d = document.getElementById("account"),
        e = document.getElementById("password");
        if ("" == $.trim(a.val())|| "" == $.trim(b.val())) {
            if ("" == $.trim(a.val())) {
                a.focus();
                var d = document.getElementById("account");
                return login.showTip("请输入账户", d.nextElementSibling),
                !1
            }
            if ("" == $.trim(b.val())) return b.focus(),
            login.showTip("请输入密码", e.nextElementSibling),
            !1
        } else {
            if ("" == $.trim(a.val()) || $.trim(a.val()).length<3 || "" == $.trim(b.val())) return a.focus(),
            login.showTip("账户格式不正确", d.nextElementSibling),
            !1;
            if (!$.trim(b.val()).match(c)) return b.focus(),
            login.showTip("密码格式错误", e.nextElementSibling),
            !1;
            a.val($.trim(a.val())),
            b.val($.trim(b.val())),
            $("#signin_bind_form").submit();//提交绑定用户表单
        }
    },
    renderBg: function() {
        var a = document.getElementById("bg-canvas"),
        b = document.documentElement.clientWidth,
        c = document.documentElement.clientHeight;
        a.width = b,
        a.height = c;
        var d = a.getContext("2d");
        d.strokeStyle = "#f6f6f6",
        d.lineWidth = 1,
        d.beginPath(),
        d.translate(.5, .5);
        for (var e = 20; e <= c; e += 20) d.moveTo(0, e),
        d.lineTo(b, e);
        for (var e = 20; e <= b; e += 20) d.moveTo(e, 0),
        d.lineTo(e, c);
        d.closePath(),
        d.stroke()
    },
    paltform: {
        weixin: function() {
            login.weixin.init()
        },
        login: function(a) {
            if ("google" == a) {
                var b = "524693152001.apps.googleusercontent.com",
                c = "https://accounts.google.com/o/oauth2/auth?redirect_uri=http://www.processon.com/google.jsp&response_type=token&client_id=" + b + "&scope=https://www.googleapis.com/auth/userinfo.profile+https://www.googleapis.com/auth/userinfo.email";
                window.open(c, "newwindow", "height=550, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no")
            } else if ("qq" == a) {
                var c = "https://graph.qq.com/oauth2.0/authorize?client_id=101496501&response_type=code&scope=all&redirect_uri=http://www.cmsyue.com/sns/callback/qq&state=admin";
                window.open(c, "newwindow", "height=550, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no")
            }  else if ("baidu" == a) {
                c = "https://openapi.baidu.com/oauth/2.0/authorize?response_type=code&client_id=ruoQHn4Avs6GRXarcsQ9Aa5i&redirect_uri=http://www.cmsyue.com/sns/callback/baidu&display=page&state=admin";
                window.open(c, "newwindow", "height=550, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no")
            }
            else if ("csdn" == a) {
                c = "http://api.csdn.net/oauth2/authorize?client_id=1100872&redirect_uri=http://www.cmsyue.com/sns/callback/csdn&response_type=code&state=admin";
                window.open(c, "newwindow", "height=550, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no")
            }else if ("oschina" == a) {
                c = "https://www.oschina.net/action/oauth2/authorize?response_type=code&client_id=vXAHX2MnjDsMjsWAERBh&redirect_uri=http://www.cmsyue.com/sns/callback/oschina&state=admin";
                window.open(c, "newwindow", "height=550, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no")
            }
            else if ("sina" == a) {
                var c = "https://api.weibo.com/oauth2/authorize?client_id=1185373104&response_type=code&redirect_uri=http://www.cmsyue.com/sns/callback/sina&state=admin";
                window.open(c, "newwindow", "height=550, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no")
            } else if ("mingdao" == a) {
                var d = "5967E9E0B4ADA1B9C23B1893ABAED0F",
                c = "https://api.mingdao.com/oauth2/authorize?app_key=" + d + "&response_type=code&redirect_uri=http://www.processon.com/login/mingdao&state=admin";
                window.open(c, "newwindow", "height=550, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no")
            }
        }
    },
    weixin: {
        loaded: !1,
        init: function() {
            var a = document.getElementById("weixin_dlg");
            if (a.style.display = "block", !login.weixin.loaded) {
                var b = {
                    js: ["https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"]
                };
                bigPipe.render(b,
                function() {
                  /*  var objWx = new WxLogin(
                        {
                            id : "ewm_container",
                            appid : "wx22db67a3c7637aef",
                            scope : "snsapi_login",
                            redirect_uri : encodeURIComponent("http://1968376h6j.iask.in/wxLogin"),
                            state : Math.ceil(Math.random() * 1000),
                            style : "black",
                            href : "data:text/css;base64,LmltcG93ZXJCb3ggLnFyY29kZSB7d2lkdGg6IDEwMHB4O30NCg0K"
                        });*/

                    setTimeout(function() {
                        new WxLogin({
                            id: "weixin_dlg",
                            appid: "wx22db67a3c7637aef",
                            scope: "snsapi_login",
                            redirect_uri:encodeURIComponent("http://www.cmsyue.com/sns/callback/wx"),
                            state: "admin",
                            style: "",
                            href: ""
                        });
                        document.addEventListener("mousedown",
                        function() {
                            a.style.display = "none"
                        }),
                        a.addEventListener("mousedown",
                        function(a) {
                            a.stopPropagation()
                        }),
                        login.weixin.loaded = !0
                    },
                    1e3)
                })
            }
        }
    },
    tempmails: ["mvrht.com", "34nm.com", "dingbone.com", "fudgerub.com", "lookugly.com", "smellfear.com", "tempinbox.com", "yopmail.com", "yopmail.fr", "yopmail.net", "cool.fr.nf", "jetable.fr.nf", "nospam.ze.tc", "nomail.xl.cx", "mega.zik.dj", "speed.1s.fr", "courriel.fr.nf", "moncourrier.fr.nf", "monemail.fr.nf", "monmail.fr.nf", "chacuo.net", "027168.com", "www.bccto.me", "mail.bccto.me", "sharklasers.com", "guerrillamail.info", "grr.la", "guerrillamail.biz", "guerrillamail.com", "guerrillamail.de", "guerrillamail.net", "guerrillamail.org", "guerrillamailblock.com", "pokemail.net", "spam4.me", "nowmymail.com", "mailcatch.com", "incognitomail.org", "cd.mintemail.com", "spamgourmet.com", "e4ward.com", "mailinator.com", "spamfree24.org", "mt2015.com", "mailnesia.com", "tempemail.net", "maildrop.cc"]
};
login.init();