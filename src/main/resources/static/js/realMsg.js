var pageNo = 1;
function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
};
$(".more a").on("click",function () {
    var html = "";
        $.ajax({
           url:"/getMoreMsg",
           type:"GET",
           data:{pageNo:pageNo},
           dateType:"json",
           success:function (result) {
                $.each(result,function (index,message) {
                    html = html+"<div class=\"realMsg\">";
                    html = html+"<div class=\"time\">";
                    html = html+"<font size=\"4\" color=\"#666666\">"+getMyDate(message.time)+"</font>";
                    html = html+"</div>";
                    html = html+"<div class=\"msgBox\">";
                    html = html+"<div class=\"message\">";
                    html = html+"<h3><strong>"+message.title+"</strong></h3>";
                    if(message.content != null){
                        html = html+"<span>"+message.content+"</span>";
                    }
                    //html = html+"<span th:if=\"${message.content != null}\" th:text=\"${message.content}\"></span>";
                    html = html+"<br><br>";
                    if(message.source != null){
                        html = html+"<font>"+message.source+"</font>";
                    }
                    //html = html+"<font th:if=\"${message.source != null}\" th:text=\"${message.source}\"></font>";
                    if(message.url != null){
                        html = html+"<a href='"+message.url+"'><p>查看详细内容</p></a>";
                    }
                    //html = html+"<a th:if=\"${message.url != null}\" th:href=\"${message.url}\"><p>查看详细内容</p></a>";
                    html = html+"</div></div></div>";
                });
                $(".realMsgList").append(html);
           } 
        });
        pageNo++;
});