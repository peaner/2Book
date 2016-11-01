/**
 * Created by Clam on 2016/9/28.
 * 为你我受冷风吹 啊啊啊~
 */
$(function(){
    //点击“已有账号，登陆”。跳转到登陆界面
    $("#loginFormRegister").click = loginFormRegister();

    //输入提示、聚焦失焦事件调用
    tipsForInput();
    //取消表单的聚焦事件
    focusForInput();
    forLogin();
});

function loginFormRegister(){
    var loginInner = "<input type='text' name='input_txt' id='username' placeholder='请输入账号' class='input inputWidth inputHeight' id=''><br> "+
        "<input type='text' name='input_txt' id='password' class='input inputHeight inputWidth' id='' placeholder='请输入密码'><br>  "+
        "<input type='text' name='input_txt' id='testCode' placeholder='输入验证码' class='input inputHeight inputNumber' id=''> <input type='button' class='inputHeight inputBtn inputMessage' id='' value='刷新验证码'><br>" +
        "<input type='button' class='input inputHeight inputWidth inputBtn' id='' value='登陆'> <br>" +
        "<span class='smallInfo'>还没有账号?"+
            "<a href='register.html' id='loginFormRegister'>注册</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
        "</span>";
    $("#loginFormRegister").click(function(){
        //console.log("to login!");
        $(".register").empty().append("登陆");
        $(".enroll").empty().append(loginInner);
        $("#wrapper").removeClass("wrapper").addClass("login_wrapper");
    });
}