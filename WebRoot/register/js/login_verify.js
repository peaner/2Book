/**
 * Created by Clam on 2016/10/27.
 * 登陆表单的验证和登陆功能的实现
 * 嘿呀！好困呀~
 *2016.10.29 上传到gitHub了
 */


function forLogin() {
    //获取登录页面的表单
    var loginUsername = $("#username");
    var loginPassword = $("#password");
    var testCode = $("#testCode");

    //登陆页面登陆功能的实现
    loginUsername.blur(function () {
        if(this.value == ""){
            loginPassword.addClass("warning");
            console.log("用户密码不能为空！");
        }

        /*验证用户是否正确*/
        var url = "";
        $.ajax({
            type:get,
            url:url,
            dataType: 'json',
            data:{
                username:loginUsername.value
            },
            success:function (data) {
                /*提示：
                 * 1.用户名可用
                 * 2.用户名不可用
                 * 3.用户名格式不正确
                 * */
            },
            error:function () {
                alert("用户名验证失败！")
            }
        })
    });
    loginPassword.blur(function () {
        if(this.value == ""){
            loginPassword.addClass("warning");
            console.log("用户密码不能为空！");
        }

        /*验证用户密码是否正确*/
        var url = "";
        $.ajax({
            type:get,
            url:url,
            dataType: 'json',
            data:{
                uPwd:loginPassword.value
            },
            success:function (data) {
                /*提示：
                 * 1.用户名可用
                 * 2.用户名不可用
                 * 3.用户名格式不正确
                 * */
            },
            error:function () {
                alert("用户名验证失败！")
            }
        })
    });


    //获取图片验证码
    getTestCode();
}

function getTestCode() {
    /*为图片验证码添加点击事件
    * 当点击图片的时候从后台获取图片验证码
    * 或者点击图片旁边的 Link 标签，获取并刷新验证码图片
    */
}


