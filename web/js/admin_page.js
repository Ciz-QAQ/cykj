function searchInfo() {

    let path=$("#path").val();
    let cNumber=$("#carNumber").val();
    console.log("cNumber:"+cNumber);
    msg = encodeURI(encodeURI(cNumber));

    $.ajax({
        url : path+"/carInfoServlet?methodName=getCarInfo",
        async : true,
        type : "GET",
        data : "cNumber=" +msg,
        dataType : "json",
        success : function(msg) {
            if (msg) {
                alert("搜素成功!");
            } else {
                alert("查无数据！");
            }
        },
        error : function() {
            alert("操作错误！");
        }
    });

}

function goToAdmin() {

    let path=$("#path").val();
    location.href=path+"/jsp/adminLogin.jsp"

}