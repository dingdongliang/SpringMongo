var ShowMsg={
    title:'提示',
    content:'模拟qq弹出框消息提醒',
    width:'300px',
    height:'100px',
    setTitle:function(value){
        this.title=value;
    },
    setContent:function(value){
        this.content=value;
    },
    getTitle:function(){
        return this.title;
    },
    getContent:function(){
        return this.content;
    },
    show:function(){
        //弹窗div
        var _winPopDiv = document.createElement('div');
        _winPopDiv.id="_winPopDiv";
        _winPopDiv.style.cssText = 'width:300px; position:absolute; right:0; bottom:0; border:1px solid #666; margin:0; padding:1px; overflow:hidden; display:block;';
        //消息标题div
        var _titleDiv= document.createElement('div');
        _titleDiv.id="_titleDiv";
        _titleDiv.innerHTML=this.getTitle();
        _titleDiv.style.cssText = 'width:100%; height:22px; line-height:20px; background:#FFCC00; font-weight:bold; text-align:left; font-size:14px;';
        _winPopDiv.appendChild(_titleDiv);
        //关闭消息按钮span
        var _closeSpan= document.createElement('span');
        _closeSpan.id="_closeSpan";
        _closeSpan.innerHTML="X";
        _closeSpan.style.cssText = 'position:absolute; right:4px; top:-1px; color:#FFF; cursor:pointer;font-size:14px;';
        _titleDiv.appendChild(_closeSpan);
        //内容div
        var _conDiv= document.createElement('div');
        _conDiv.id="_conDiv";
        _conDiv.style.cssText = 'width:100%; height:110px; line-height:80px; font-weight:bold; font-size:12px; color:#FF0000; text-decoration:underline; text-align:center;background:rgb(243,243,243)';
        _conDiv.innerHTML=this.getContent();
        _winPopDiv.appendChild(_conDiv);
        document.body.appendChild(_winPopDiv);
        //关闭span绑定事件
        var closeDiv = document.getElementById("_closeSpan");
        if(closeDiv.addEventListener){
            closeDiv.addEventListener("click",function(e){
                if (window.event != undefined) {
                    window.event.cancelBubble = true;
                } else if (e.stopPropagation) {
                    e.stopPropagation();
                }
                document.getElementById('_winPopDiv').style.cssText="display:none;";
            },false);
        }else if(closeDiv.attachEvent){
            closeDiv.attachEvent("onclick",function(e){
                if (window.event != undefined) {
                    window.event.cancelBubble = true;
                } else if (e.stopPropagation) {
                    e.stopPropagation();
                }
                document.getElementById('_winPopDiv').style.cssText="display:none;";
            });
        }
    }
};
function getMsg() {
    var project;
    var userid;
    var script=document.createElement("script");
    script.type="text/javascript";
    script.src="http://127.0.0.1:8080/resources/js/jquery-1.7.1.min.js";
    document.getElementsByTagName('head')[0].appendChild(script);
    var params = document.getElementById('getinfo').getAttribute('data');
    var array = params.split("&");
    // 定义三个参数，msg是否返回信息，token认证，appid应用ID,userid是用户ID
    for (var j = 0; j < array.length; j++) {
        var finalObj = array[j].split("=");
        if (finalObj[0] == "project") {
            project = finalObj[1];
        }  else if (finalObj[0] == "userid") {
            userid = finalObj[1];
        }
    }
    // 获取浏览器信息
    function getBrowserInfo() {
        var agent = navigator.userAgent.toLowerCase();
        var regStr_ie = /ie [\d.]+;/gi;
        var regStr_ff = /firefox\/[\d.]+/gi
        var regStr_chrome = /chrome\/[\d.]+/gi;
        var regStr_saf = /safari\/[\d.]+/gi;
        // IE
        if (agent.indexOf("msie") > 0) {
            return agent.match(regStr_ie);
        }
        // firefox
        if (agent.indexOf("firefox") > 0) {
            return agent.match(regStr_ff);
        }
        // Chrome
        if (agent.indexOf("chrome") > 0) {
            return agent.match(regStr_chrome);
        }
        // Safari
        if (agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0) {
            return agent.match(regStr_saf);
        }
    }
    var browser = getBrowserInfo();
    var apiurl=	'http://localhost:8080/manage/user/save';
    alert(browser);
    // 循环获取用户状态
    window.setInterval(function() {
        $.ajax({
            type: "POST",
            url:"/user/save",
            data: {
                'Project':project,
                'ClientType':browser,
                'OtherData': '1001',
                'UserId':userid,
                'ClientId': '8edfdfa75c104c22aa0a6sdb9e6ef4da',
                'ClientVersion': '55.0',
                'KeyFlag': '0'
            },
            dataType: 'json',
            success: function (info) {
                if(info.code!=="OK"){
                    if(info.msgType=="info"){
                        ShowMsg.title="信息";
                    }else if(info.msgType=="warning"){
                        ShowMsg.title="警告";
                    }else{
                        ShowMsg.title="错误";
                    }
                    ShowMsg.content=info.msgContent;
                    ShowMsg.show();
                    if(info.msgType=="warning"||info.msgType=="error"){
                        document.getElementById('_closeSpan').style.cssText="display:none;";
                    }
                }
            },
            error:function(){
                alert("error");
            },error:function(){
                alert("error");
            }
        });
    }, 60000);
};
getMsg();
