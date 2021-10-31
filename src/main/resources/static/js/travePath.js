$(".more").addClass("showFlag");
function displayInfo(result) {
    var html = "";
    html += "<div class='result'>";
    html += "<div class='location info'>";
    html += "<img src='../img/location.png' >";
    html += "<font size='3' color='#666666'>活动位置：</font>";
    html += "<font size='3' color=''><strong>" + result.province + result.city + result.county + "</strong></font>";
    html += "</div>";
    html += "<div class='date info'>";
    html += "<img src='../img/date.png' >";
    html += "<font size='3' color='#666666'> 发布时间：</font>";
    html += "<font size='3' color=''><strong>" + result.pubTime + "</strong></font>";
    html += "</div>";
    html += "<div class='patient-info info'>";
    html += "<img src='../img/people.png' >";
    html += "<font size='3' color='#666666'>  病患信息：</font>";
    html += "<font size='3' color=''>" + result.userName + "</font>";
    html += "</div>";
    html += "<div class='other-info info'>";
    html += "<img src='../img/info.png' >";
    html += "<font size='3' color='#666666'> 其他信息：</font>";
    html += "<font size='3' color=''>" + result.otherInfo + "</font>";
    html += "</div>";
    html += "<hr >";
    html += "<div class='track-title info'>";
    html += "<img src='../img/track.png' >";
    html += "<font size='3' color='#666666'> 行动轨迹：</font>";
    html += "</div>";
    html += "<div class='track-list info'>";
    $.each(JSON.parse(result.track), function (index, value) {
        html += "<div class='track'>";
        html += "<div class='day'>";
        html += "<span>" + value.time + "</span>";
        html += "</div>";
        html += "<div class='tk-info'>";
        html += "<font size='3' color='#666666'>" + value.action + "</font>";
        html += "</div>";
        html += "</div>";
    });
    html += "</div>";
    html += "<div class='source info'>";
    html += "<font size='' color='#666666'> 信息来源：</font>";
    html += "<font size='' color='#666666'>" + result.source + "</font>";
    html += "</div>";
    html += "</div>";


    return html;
}

(function () {
    var html = "";
    $.each(provinces, function (index, value) {
        html += "<option>" + value.name + "</option>"
    });
    $("#province").append(html);
})();

var start = 0;
var pageSize = 5;
(function () {
    $.ajax({
        url: "/getIndexData",
        type: "GET",
        data: {start: start, pageSize: pageSize},
        dataType: "json",
        success: function (result) {
            $.each(result, function (index, value) {
                var html = displayInfo(value);
                $(".resultBox").append(html);
                $(".more").removeClass("showFlag");
            });
        }
    });
})();


$(".seacherBtn").on("click", function () {
    var province = $("#province").val();
    var city = $("#city").val();
    var country = $("#country").val();
    start = 0;
    console.log(province + city + country);
    $.ajax({
        url: "/getSeacherData",
        type: "GET",
        data: {province: province, city: city, country: country, start: start, pageSize: pageSize},
        dataType: "json",
        success: function (result) {
            console.log(result);
            if (result=="0") {
                $(".resultBox").html("<h1>此地区没有查到数据</h1>");
                $(".more").addClass("showFlag");
            } else {
                $(".resultBox").html("");
                $.each(result, function (index, value) {
                    var html = displayInfo(value);
                    $(".resultBox").append(html);
                    $(".more").removeClass("showFlag");
                });
            }

        }

    });

});


var provinceCheckedNode;
var CityCheckedNode;
$("#province").change(function () {
    var selectIndex = this.selectedIndex;
    $("#country").html("<option>全部</option>");
    if (selectIndex > 0) {

        var html = "<option>全部</option>";
        provinceCheckedNode = provinces[selectIndex - 1];

        $.each(provinceCheckedNode.city, function (index, value) {
            html += "<option>" + value.name + "</option>";
        });
        $("#city").html(html);
    }
});

$("#city").change(function () {
    var citySelectIndex = this.selectedIndex;
    if (citySelectIndex > 0) {
        var html = "<option>全部</option>";
        CityCheckedNode = provinceCheckedNode.city[citySelectIndex - 1];
        $.each(CityCheckedNode.area, function (index, value) {
            html += "<option>" + value + "</option>";
        });
        $("#country").html(html);
    }
});

$(".more a").on("click",function () {
    var province = $("#province").val();
    var city = $("#city").val();
    var country = $("#country").val();
    start++;
    var startIndex = start*pageSize;
    console.log(province + city + country);
    $.ajax({
        url: "/getSeacherData",
        type: "GET",
        data: {province: province, city: city, country: country, start: startIndex, pageSize: pageSize},
        dataType: "json",
        success: function (result) {
            console.log(result);
            if (result=="0") {
                $(".resultBox").append("<h5>我是有底线的。</h5>");
                $(".more").addClass("showFlag");
            } else {

                $.each(result, function (index, value) {
                    var html = displayInfo(value);
                    $(".resultBox").append(html);
                });
            }

        }

    });
});


