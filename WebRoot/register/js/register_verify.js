/**
 * Created by Clam on 2016/9/28.
 */



/*tipsForInput()
* 设置了失焦事件和注册按钮的点击事件
* */
function tipsForInput(){
    //获取注册页面的表单
    var registerUsername = $("#register_username");
    var registerPassword = $("#registerPassword");
    var registerPassword_again = $("#registerPassword_again");
    var telNumber = $("#tel_number");
    var telMessage = $("#tel_message");
    var btnRegister = $("#btnRegister");

    /*注册表单的输入验证*/
    registerUsername.blur(function () {

        /*验证用户名输入是否合法的正则表达式
        * ·由字母a～z(不区分大小写)、数字0～9、点、减号或下划线组成
          ·只能以字母开头，包含字符 数字 下划线，例如：beijing.2008
          ·用户名长度为4～18个字符
         */
        var patten = /^[a-zA-Z]\w{3,15}$/ig;
        if(this.value == ""){
            registerUsername.addClass("warning");
            registerUsername.placeholder = "用户名不能为空！";
            console.log("输入内容为空！");
            return false;
        }
        else if(!patten.test(this.value)){
            registerUsername.addClass("warning");
            alert("用户名格式有误！");
            return false;
        }
        /*验证用户名是否已经存在*/
        else {
            var url = "login/checkData";
            //alert(registerUsername.val());
            $.ajax({
                type:"get",
                url:url,//接口
                dataType: "json",
                data:{
                    usr:registerUsername.val()
                },
                success:function (data) {
                    /*提示：
                     * 1.用户名可用 return true;
                     * 2.用户名不可用 return false;
                     **/
                	alert(data.MESSAGE);
                	//alert("${data.MESSAGE}");
                	//alert('${MESSAGE}');
                },
                error:function () {
                    alert("用户名验证失败！")
                }
            })
        }
    }); //return true ;表示用户名设置正确且可用

    registerPassword.blur(function () {

        var base = /^(\w){6,20}$/;
        var val = this.value;
        if (val == "") { //判断是否为空
            registerPassword.addClass("warning");
            console.log("输入内容为空！");
            return false;
        }
        else if(base.exec(val)){//如果密码的格式正确，再判断密码的强度
            var standard = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})/;
            var strObj = val.match(standard);
            //var strength = val.match(standard).length;
            if( strObj == null || strObj.length <= 1 ){
                registerPassword.addClass("warning");
                registerPassword.value == null;
                registerPassword.attr('placeholder',"长度为6-20，至少一个小写字母，至少一个大写字母");
                alert("强度弱！");
            }
            else if(strObj.length > 1 && strObj.length <= 6){
                alert("强度中！");
            }
            else {
                alert("强度高！");
            }
            return true;
        }
        else{
            registerPassword.addClass("warning");
            alert("密码格式不正确！");
            return false;
        }
    });//密码格式正确即返回true  --> return true;

    registerPassword_again.blur(function () {
        if(this.value == ""){
            registerPassword_again.addClass("warning");
            console.log("输入内容为空！");
            return false;
        }
        /*验证重新输入的密码是否与上次输入否认密码相同*/
        else if(this.value != registerPassword.value){
            registerPassword_again.addClass("warning");
            alert("两次输入的密码不同");
            return false;
        }
        else{
            console.log("再次输入正确！");
            return true;
        }
    });//密码再次输入正确，则返回一个true

    telNumber.blur(function () {

        var reg = /^1[3|4|5|7|8][0-9]\d{4,8}$/i;//验证手机正则(输入前7位至11位)
        var val = this.value;
        if(!val.match(reg) || val == ""){
            telNumber.addClass("warning");
            console.log("电话号码输入格式有误！");
        }
        else {
            console.log("电话号码格式正确！");
            $.ajax({
                type:get,
                url:url,
                data:{
                    tel:val
                },
                dataType: 'json',
                success:function (data) {
                    console.log("电话号码可用！");
                    return true;
                },
                error:function () {
                    console.log("电话号码已注册过账号！")
                    return false;
                }
            })
        }
    });

    telMessage.blur(function () {
        if(this.value == ""){
            telMessage.addClass("warning");
            console.log("输入内容为空！");
        }
    });

    /* 点击按钮，发送短信验证码
    *
    * */


    //设置注册按钮的点击事件，判断是否点击按钮的时候表单的输入内容为空
    btnRegister.click(function () {
        console.log("点击事件");
        var input_txt = $("input");
        // console.log(input_txt);
        input_txt.each(function (i) {
            console.log(this.id);
            if(this.value == ""){
                input_txt.eq(i).addClass("warning");
                console.log("注册请求发送失败，输入内容为空！");
            }
            /*else{
                $.ajax({
                    type:"get",
                    url: url,
                    async:true,
                    data:{
                        usr:$()
                    }
                })
            }*/
        })


        var url = "login/regist";/*注册接口*/
        if("表单输入格式正确"){
            $.ajax({
                type:get,
                url:url,
                async:true,
                dataType:'json',
                data:{
                    usr:input_txt.eq(0).value,
                    pwd:input_txt.eq(1).value,
                    pwd_again:input_txt.eq(2).value,
                    tel:input_txt.eq(3).value,
                    msg:input_txt.eq(4).value
                },
                success:function (data) {
                    alert("注册成功，回到登陆界面！");
                },
                error:function () {
                    alert("注册失败！");
                }
            })
        }
    })
}


/*focusForInput()
* 聚焦事件
* 目标是：表单是聚焦的时候，取消表单失焦的样式
* Created by Clam
* 我想大言不惭卑微奢求来世再爱你--《不再见》 陈学冬
* */

function focusForInput(){

    //获取注册页面的表单
    var registerUsername = $("#register_username");
    var registerPassword = $("#registerPassword");
    var registerPassword_again = $("#registerPassword_again");
    var telNumber = $("#tel_number");
    var telMessage = $("#tel_message");
    var btnRegister = $("#btnRegister");
    //获取登录页面的表单
    var loginUsername = $("#username");
    var loginPassword = $("#password");
    var testCode = $("#testCode");

    //表单的聚焦事件
    registerUsername.focus(function () {
        if(this.value == ""){
            registerUsername.removeClass("warning");
            console.log("表单已聚焦");
        }
    });

    registerPassword.focus(function () {
        if(this.value == ""){
            registerPassword.removeClass("warning");
            console.log("表单已聚焦");
        }
    });

    registerPassword_again.focus(function () {
        if(this.value == ""){
            registerPassword_again.removeClass("warning");
            console.log("表单已聚焦");
        }
    });

    telNumber.focus(function () {
        if(this.value == ""){
            telNumber.removeClass("warning");
            console.log("表单已聚焦");
        }
    });

    telMessage.focus(function () {
        if(this.value == ""){
            telMessage.removeClass("warning");
            console.log("表单已聚焦");
        }
    });
}


