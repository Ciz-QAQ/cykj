var stateInfo=0;

function show() {
    var bodyElement=document.getElementById("body");
    bodyElement.style.display="block";

}

function changeImg() {
    $("#vCodeImg")[0].src=$("#path").val()+"/verifyCodeServlet?time="+new Date().getTime();
    // $("#vCodeImg")[1].src=$("#path").val()+"/verifyCodeServlet?time="+new Date().getTime();
}

function checkPhone(){
    var phone = document.getElementById('phone').value;
    if(!(/^1[3456789]\d{9}$/.test(phone))){
        $("#spanInfo").html("手机号码有误，请重填");
        $("#register").prop("disabled","disabled");
        $("#phoneSpan").html("×");
        return false;
    }else{
        $("#spanInfo").html("");
        $("#register").prop("disabled",false);
        $("#phoneSpan").html("√");
    }
}

function checkAge() {
    var age=document.getElementById("age").value;
    // alert(age)
    if (age<1||age>120){
        $("#spanInfo").html("年龄不合法");
        $("#register").prop("disabled","disabled");
        $("#ageSpan").html("×");
    }else{
        $("#spanInfo").html("");
        $("#register").prop("disabled",false);
        $("#ageSpan").html("√");
    }
}

function registerMethod() {
    let spanElement=$(".spanClass");

    for (let i=0;i<spanElement.length;i++){
        if (spanElement[i].innerHTML=="×"){
            alert("注册信息不符合要求！");
            return false;
        }
    }

    var account=$("#account").val();
    var name=$("#uName").val();
    var psd=$("#psd").val();
    var sex;
    if ($("input:radio[id='sexMan']:checked").val()==null){
        sex="女";
    }else{
        sex="男";
    }
    var age=$("#age").val();
    var phone=$("#phone").val();
    var address=$("#address").val();
    var vCode=$("#vCodeUser").val();
    var path=$("#path").val();


    var user={"userAccount":account,"userPsd":psd,"userName":name,"userSex":sex,"userAge":age,"userPhone":phone,"userAddress":address};
    user=JSON.stringify(user);
    alert(user);

    $.ajax({
        url:path+"/userServlet?methodName=register",
        async:true,
        type:"post",
        data:"user="+user+"&vCodeUser="+vCode,
        dataType:"text",
        success:function (msg) {
            alert(msg);
            if  ( msg == "success"){
                alert("注册成功！")
                location.href = path+"/jsp/login.jsp";
            }else{
                alert("注册失败！")
                changeImg();
            }
        },
        error:function () {
            alert("网络繁忙！");
        }
    })

    alert("注册");
}

function login() {
    alert("login")
    var account=$("#account").val();
    var psd=$("#psd").val();
    var vCode=$("#vCodeUser").val();
    var path=$("#path").val();
//取值
    var user={"userAccount":account,"userPsd":psd};
    user=JSON.stringify(user);
    console.log(user);
//转json
    $.ajax({
        url:path+"/loginServlet?methodName=login",
        async:true,
        type:"post",
        data:"user="+user+"&vCodeUser="+vCode,
        dataType:"text",
        success:function (msg) {
            // alert(msg);
            if  ( msg == "success"){
                alert("登入成功！")
                location.href = path+"/jsp/main_page.jsp";
            }else{
                changeImg();
            }
        },
        error:function () {
            alert("网络繁忙！");
        },
    })
}

function adminLogin() {
    alert("adminLogin")
    let account=$("#account").val();
    let psd=$("#psd").val();
    let vCode=$("#vCodeUser").val();
    let path=$("#path").val();
//取值
    let user={"cAccount":account,"cPsd":psd};
    user=JSON.stringify(user);
    console.log(user);
//转json
    $.ajax({
        url:path+"/adminServlet?methodName=login",
        async:true,
        type:"post",
        data:"user="+user+"&vCodeUser="+vCode,
        dataType:"text",
        success:function (msg) {
            // alert(msg);
            if  ( msg == "success"){
                location.href = path+"/menuServlet?methodName=findMenu";
            }else{
                changeImg();
            }
        },
        error:function () {
            alert("网络繁忙！");
        },
    })
}

function toUserLogin() {
    var path=$("#path").val();
    location.href = path+"/jsp/main_page.jsp";

}

function toAdminLogin() {
    var path=$("#path").val();
    location.href = path+"/jsp/adminLogin.jsp";
}

function checkAccount() {
    let uPattern = /^[a-zA-z]\w{3,10}$/;
    let userAccount=$("#account").val();
    let path=$("#path").val();
    if (userAccount==""||userAccount==null){
        alert("账号不能为空!")
        $("#accountSpan").html("×");
    }else if (!(uPattern.test(userAccount))){
        alert("账号必须为4到16位（字母，数字，下划线，减号）");
        $("#accountSpan").html("×");
        return false;
    }else{

        $.ajax({
            url:path+"/userServlet?methodName=findUserAccount",
            async:true,
            type:"post",
            data:"userAccount="+userAccount,
            dataType:"text",
            success:function (msg) {
                alert(msg);
                if  ( msg == "usable"){
                    $("#accountSpan").html("√");
                }else{
                    $("#accountSpan").html("×");
                    alert("账号已存在！");
                }
            },
            error:function () {
                alert("网络繁忙！");
            },
        })
    }
}

function checkName() {
    let pattern = /^[a-zA-Z_]\w{3,10}$/g;
    let userName=$("#uName").val();

    if (userName==""||userName==null){
        alert("用户名不能为空!");
        $("#nameSpan").html("×");
    } else if (!(pattern.test(userName))){
        alert("数字不能开头,长度在4-10位之间！");
        $("#nameSpan").html("×");
    }else {
        $("#nameSpan").html("√");
    }
}

function checkPsd() {

    let pattern=/^[\w@#*]{1,6}$/;
    let psd=$("#psd").val();

    if (psd==""||psd==null){
        alert("密码不能为空!");
        $("#psdSpan").html("×");
    } else if (!(pattern.test(psd))){
        alert("请输入1-6位的英文、数字、下划线、@、#、*");
        $("#psdSpan").html("×");
    }else {
        $("#psdSpan").html("√");
    }
}

function psdCheck() {
    let psd=$("#psd").val();
    let psdCheck=$("#psdCheck").val();
    if (psdCheck==""||psdCheck==null){
        alert("密码不能为空!");
        $("#psdCheckSpan").html("×");
    }else if (psdCheck!=psd){
        alert("两次输入密码不一致！")
        $("#psdCheckSpan").html("×");
    }else{
        $("#psdCheckSpan").html("√");
    }
}

function checkAddress() {

    let address=$("#address").val();
    let pattern= /.+?(省|市|自治区|自治州|县|区)/g;

    if (address==""||address==null){
        alert("地址不能为空!");
        $("#addressSpan").html("×");
    } else if (!(pattern.test(address))){
        alert("地址需包含省市区县信息！");
        $("#addressSpan").html("×");
    }else {
        $("#addressSpan").html("√");
    }

}