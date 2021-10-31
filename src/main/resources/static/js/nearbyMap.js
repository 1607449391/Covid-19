var map = new BMapGL.Map("container");
var point = new BMapGL.Point(116.404, 39.915);
var currentLat = 39.915;
var currentLng = 116.404;
var geoc = new BMapGL.Geocoder();   //地址解析对象
var seacherKeywords = '';  //创建标志位，判断点击的按钮
map.centerAndZoom(point, 15);
map.enableScrollWheelZoom();    //启用滑轮缩放




//获取当前位置信息
var currentMakerIcon = new BMapGL.Icon("../img/poi-marker-blue.png", new BMapGL.Size(30, 35));
var geolocation = new BMapGL.Geolocation();
geolocation.getCurrentPosition(function(r){
    if(this.getStatus() == BMAP_STATUS_SUCCESS){
        currentLng = r.point.lng;
        currentLat = r.point.lat;
        //console.log(getInfo);
        var mk = new BMapGL.Marker(r.point,{
            icon:currentMakerIcon
        });
        map.addOverlay(mk);
        map.panTo(r.point);
    }

},{enableHighAccuracy: true});


var pt;
var mak;
var myIcon = new BMapGL.Icon("../img/poi-marker-red.png", new BMapGL.Size(30, 35));
$.each(jws,function (index,value) {

    pt = new BMapGL.Point(value.lng, value.lat);
    mak = new BMapGL.Marker(pt,{
        icon:myIcon
    });
    map.addOverlay(mak);
});


map.addEventListener('click', function (e) {
    currentLng = e.latlng.lng;
    currentLat = e.latlng.lat;

    if(seacherKeywords != ''){
        seacherStrFun(seacherKeywords);
    }
});
$("#seacherBtn").on("click",function () {
    var position = $("#seacher").val();
    geoc.getPoint(position, function(pot){
        if(pot){

            currentLat = pot.lat;
            currentLng = pot.lng;
            map.centerAndZoom(pot, 16);
            map.addOverlay(new BMapGL.Marker(pot, {icon:currentMakerIcon}));
            if(seacherKeywords != ''){
                seacherStrFun(seacherKeywords);
            }
        }else{
            alert('您选择的地址没有解析到结果！');
        }
    });

});
$("#desiHospital").on("click",function () {
    seacherKeywords = '医院';
    seacherStrFun(seacherKeywords);
});
$("#feverClinic").on("click",function () {
    seacherKeywords = '发热门诊';
    seacherStrFun(seacherKeywords);
});
$("#nucleicTest").on("click",function () {
    seacherKeywords = '核酸检测';
    seacherStrFun(seacherKeywords);
});

function seacherStrFun(keywords) {
    var mPoint = new BMapGL.Point(currentLng, currentLat);
    map.enableScrollWheelZoom();
    map.centerAndZoom(mPoint,14);

    map.clearOverlays();
    var mk = new BMapGL.Marker(new BMapGL.Point(currentLng,currentLat),{
        icon:currentMakerIcon
    });
    map.addOverlay(mk);
    var circle = new BMapGL.Circle(mPoint,3000,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.1, strokeOpacity: 0.3});
    map.addOverlay(circle);
    var options = {
        renderOptions: {
            map: map,
            autoViewport: false,
            selectFirstResult:false,
            panel: "tipBox"
        },
        onSearchComplete: function(results){
            // 判断状态是否正确

            if (local.getStatus() == BMAP_STATUS_SUCCESS){
                var s = results.getCurrentNumPois();
                var windowInfo = "";
                if(s == 0){
                    windowInfo = "在您附近三公里没有搜索到"+seacherKeywords;
                }else {
                    windowInfo = "在您附近三公里总共搜索到了"+s+"处"+seacherKeywords;
                }
                var opts = {
                    width : 200,     // 信息窗口宽度
                    height: 100,     // 信息窗口高度
                    title : "周边详情"  // 信息窗口标题

                };
                var infoWindow = new BMapGL.InfoWindow(windowInfo, opts);  // 创建信息窗口对象
                map.openInfoWindow(infoWindow,new BMapGL.Point(currentLng,currentLat));

            }else {
                var infoWindow = new BMapGL.InfoWindow("在您附近三公里没有搜索到"+seacherKeywords, opts);  // 创建信息窗口对象
                map.openInfoWindow(infoWindow,new BMapGL.Point(currentLng,currentLat));
            }
        }
    };
    var local =  new BMapGL.LocalSearch(map,options);
        local.searchNearby(keywords,mPoint,3000);
}

