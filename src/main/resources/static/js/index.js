var chinaAddMapData = new Array();
var chinaTotalMapData = new Array();
var provinceData;
var nowconfirmTotalCity = new Array();
var nowconfirmTotalData = new Array();
var confirmAddCity = new Array();
var confirmAddData = new Array();

var chinaMap;

/*导航栏吸顶js*/
$(window).scroll(function(){
	var $top = $("html,body").scrollTop();
	if($top > 500){
		$("#nav").addClass("fixedTop");
	}else{
		$("#nav").removeClass("fixedTop");
	}
	
});
/*获取当前时间*/
function getYearMonthDate() {
    var myDate = new Date;
    var year = myDate.getFullYear(); //获取当前年
    var mon = myDate.getMonth(); //获取上个月
	if(mon < 10){
		mon = '0'+mon;
	}
    var date = myDate.getDate(); //获取当前日
	if(date < 10){
		date = '0'+date;
	}
    return year+mon+date+'';
}
/*国内疫情地图*/
(function(){
	var mycharts = echarts.init(document.getElementById("chinaMap"));
	chinaMap = mycharts;
	mycharts.showLoading();
	option = {
	    tooltip: {
	        triggerOn: "click",
	        formatter: function(e, t, n) {
	            return .5 == e.value ? e.name + "：有疑似病例" : e.seriesName + "<br />" + e.name + "：" + e.value
	        }
	    },
	    visualMap: {
	        min: 0,
	        max: 1000,
	        left: 26,
	        bottom: '10%',
	        showLabel: !0,
	        text: ["高", "低"],
	        pieces: [{
	            gt: 100,
	            label: "> 100 人",
	            color: "#7f1100"
	        }, {
	            gte: 10,
	            lte: 100,
	            label: "10 - 100 人",
	            color: "#ff5428"
	        }, {
	            gte: 1,
	            lt: 10,
	            label: "1 - 9 人",
	            color: "#ff8c71"
	        }, {
	            gt: 0,
	            lt: 1,
	            label: "疑似",
	            color: "#ffd768"
	        }, {
	            value: 0,
	            color: "#ffffff"
	        }],
	        show: !0
	    },
	    geo: {
	        map: "china",
	        roam: "scale",
	        scaleLimit: {
	            min: 1,
	            max: 2
	        },
	        zoom: 1.23,
	        top: '10%',
	        label: {
	            normal: {
	                show: !0,
	                fontSize: "14",
	                color: "rgba(0,0,0,0.7)"
	            }
	        },
	        itemStyle: {
	            normal: {
	                //shadowBlur: 50,
	                //shadowColor: 'rgba(0, 0, 0, 0.2)',
	                borderColor: "rgba(0, 0, 0, 0.2)"
	            },
	            emphasis: {
	                areaColor: "#f2d5ad",
	                shadowOffsetX: 0,
	                shadowOffsetY: 0,
	                borderWidth: 0
	            }
	        }
	    },
	    series: [{
	        name: "确诊病例",
	        type: "map",
	        geoIndex: 0,

	    }]
	};

	$.ajax({
		type:"GET",
		url:"/getChinaMap",
		dataType:"json",
		success:function (result) {
			var midLength = result.length/2;
			$.each(result,function (index, value) {
				if(index<midLength){
					chinaAddMapData[index] = value;
				}else {
					chinaTotalMapData[index-midLength] = value;
				}
            });
			mycharts.hideLoading();
			mycharts.setOption({
                series: [{
                    data:chinaAddMapData
                }]
			})
        }
	});

	mycharts.setOption(option);

	window.addEventListener('resize', function () {
	  mycharts.resize();
	})
})();
/*每日新增趋势图*/
(function(){
	var dayAddLine = echarts.init(document.getElementById("everyDayAdd"));
	dayAddLine.showLoading();
	option = {
	    title: {
	        text: '每日新增趋势图',
			left:"center"
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
			right:'right',
			top:'top',
			orient:'vertical',

	    },
	    grid: {
			top:'10%',
	        left: '3%',
	        right: '10%',
	        bottom: '10%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,

	    },
	    yAxis: {
			
	        type: 'value',
			axisLine:{
				show:true
			}
	    },
        dataZoom: [{
            startValue: getYearMonthDate()
        }, {
            type: 'inside'
        }],
	};

	$.ajax({
		url:"/getAddLineData",
		type:"GET",
		dataType:"json",
		success:function (result) {
			dayAddLine.hideLoading();
			dayAddLine.setOption({
                legend: {
                    data: ['新增确诊', '新增境外输入', '新增无症状感染', '新增死亡', '新增治愈']
                },
                xAxis: {
                    data: result.date
                },
                series: [
                    {
                        name: '新增确诊',
                        type: 'line',
                        stack: 'confirm',
                        data: result.confirmList
                    },
                    {
                        name: '新增境外输入',
                        type: 'line',
                        stack: 'importCase',
                        data: result.importList
                    },
                    {
                        name: '新增无症状感染',
                        type: 'line',
                        stack: 'noFect',
                        data: result.nofectList
                    },
                    {
                        name: '新增死亡',
                        type: 'line',
                        stack: 'dead',
                        data: result.deadList
                    },
                    {
                        name: '新增治愈',
                        type: 'line',
                        stack: 'heal',
                        data: result.healList
                    }
                ]
			})
        }
	});
	dayAddLine.setOption(option);
	window.addEventListener('resize', function () {
	  dayAddLine.resize()
	})
})();
/*国内累计趋势图*/
(function(){
	var dayTotalLine = echarts.init(document.getElementById("dataTotal"));
	dayTotalLine.showLoading();
	option = {
	    title: {
	        text: '国内累计趋势图',
			left:"center"
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
			right:'right',
			top:'top',
			orient:'vertical',

	    },
	    grid: {
			top:'10%',
	        left: '3%',
	        right: '10%',
	        bottom: '10%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,

	    },
	    yAxis: {
			
	        type: 'value',
			axisLine:{
				show:true
			}
	    },
		dataZoom: [{
            startValue: getYearMonthDate()
        }, {
            type: 'inside'
        }],
	};

    $.ajax({
        url:"/getTotalLineData",
        type:"GET",
        dataType:"json",
        success:function (result) {
            dayTotalLine.hideLoading();
            dayTotalLine.setOption({
                legend: {
                    data: ['确诊总数', '境外输入总数', '无症状总数', '死亡总数', '治愈总数']
                },
                xAxis: {
                    data: result.date
                },
                series: [
                    {
                        name: '确诊总数',
                        type: 'line',
                        stack: 'confirm',
                        data: result.confirmList
                    },
                    {
                        name: '境外输入总数',
                        type: 'line',
                        stack: 'importCase',
                        data: result.importList
                    },
                    {
                        name: '无症状总数',
                        type: 'line',
                        stack: 'noFect',
                        data: result.nofectList
                    },
                    {
                        name: '死亡总数',
                        type: 'line',
                        stack: 'dead',
                        data: result.deadList
                    },
                    {
                        name: '治愈总数',
                        type: 'line',
                        stack: 'heal',
                        data: result.healList
                    }
                ]
            })
        }
    });

	dayTotalLine.setOption(option);
	window.addEventListener('resize', function () {
        dayTotalLine.resize();
	})
})();
/*获取现存数据*/
(function () {
    $.ajax({
        url:'/getProvinceData',
        type:'GET',
        dataType:'json',
        success:function (result) {
            provinceData = result;
            var nowConfirmFlag = 0;
            var confirmAddFlag = 0;
            $.each(result,function (index,value) {
                if(value.nowConfirm > 0){
                    nowconfirmTotalCity[nowConfirmFlag] = value.provinceName;
                    nowconfirmTotalData[nowConfirmFlag] = value.nowConfirm;
                    nowConfirmFlag = nowConfirmFlag + 1;
                }
                if(value.addConfirm > 0){
                    confirmAddCity[confirmAddFlag] = value.provinceName;
                    confirmAddData[confirmAddFlag] = value.addConfirm;
                    confirmAddFlag = confirmAddFlag + 1;
                }
            });
            nowConfirmTotal();
            confirmAdd();

        }
    });
})();
/*现存确诊柱形图*/
function nowConfirmTotal(){
    var confirmTotalBar = echarts.init(document.getElementById("NowCofTotalBar"));
    confirmTotalBar.showLoading();
    var option = {
        color: ['#3398DB'],
        title:{
            left:'40%',
            text:'现存确诊分布'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: nowconfirmTotalCity,
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel:{
                    interval: 0,
                    rotate:45
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLine:{
                    show:true
                }
            }
        ],
        series: [
            {
                name: '现存确诊',
                type: 'bar',
                barWidth: '60%',
                data: nowconfirmTotalData
            }
        ]
    };
    confirmTotalBar.hideLoading();
    confirmTotalBar.setOption(option);
};
/*新增确诊柱形图*/
function confirmAdd(){
    var confirmAddBar = echarts.init(document.getElementById("CofirmAddBar"));
    option = {
        color: ['#3398DB'],
        title: {
            left:'30%',
            text: '新增确诊分布'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },

        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            show:false,
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: confirmAddCity
        },
        series: [
            {
                name: '2011年',
                type: 'bar',
                data: confirmAddData
            }
        ]
    };
    confirmAddBar.setOption(option);
};



/*获取确诊前几名的省份*/
(function () {
    var confirmTotalPie = echarts.init(document.getElementById("confirmTotalPie"));
    var healRatePie = echarts.init(document.getElementById("healRatePie"));
    var confrimDataPie=new Array();
    var healRateDataPie = new Array();
    confirmTotalPie.showLoading();
    healRatePie.showLoading();
    option = {
        title: {
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
        },
        series: [
            {

                type: 'pie',
                radius: '50%',
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    confirmTotalPie.setOption(option);
    healRatePie.setOption(option);
    $.ajax({
       url:'/getTop',
       type:'GET',
       dataType:'json',
       success:function (result) {
           var flag1 = 0;
           var flag2 = 0;
           $.each(result,function (index,pieData) {
              if(pieData.value1 != null){
                  confrimDataPie[flag1] = {
                      name:pieData.name,
                      value:pieData.value1
                  };
                  flag1++;
              }else {
                 healRateDataPie[flag2] = {
                     name:pieData.name,
                     value:pieData.value2
                 };
                 flag2++;
              }
           });
           confirmTotalPie.hideLoading();
           confirmTotalPie.setOption({
              title:{
                  text:'确诊总数TOP5'

              } ,
               series:{
                   name:'确诊总数',
                   data:confrimDataPie
               }
           });
           healRatePie.hideLoading();
           console.log(healRateDataPie);
           healRatePie.setOption({
               title:{
                   text:'治愈率TOP5',
                   subtext:'单位%'
               } ,
               series:{
                   name:'治愈率',
                   data:healRateDataPie
               }
           });

       }
    });

})();

/*省市列表下拉js*/
$(function(){
	$("#provinceData>ul li").on("click",function(){
		$(this).children(".city").slideToggle(500);
		$(this).children("span").children("img").toggleClass("open");
		
	});
});


$("#nowServer").on("click",function () {
	chinaMap.showLoading();
	chinaMap.setOption({
        visualMap: {
            min: 0,
            max: 1000,
            left: 26,
            bottom: '10%',
            showLabel: !0,
            text: ["高", "低"],
            pieces: [{
                gt: 100,
                label: "> 100 人",
                color: "#7f1100"
            }, {
                gte: 10,
                lte: 100,
                label: "10 - 100 人",
                color: "#ff5428"
            }, {
                gte: 1,
                lt: 10,
                label: "1 - 9 人",
                color: "#ff8c71"
            }, {
                gt: 0,
                lt: 1,
                label: "疑似",
                color: "#ffd768"
            }, {
                value: 0,
                color: "#ffffff"
            }],
            show: !0
        },
        series: [{
            data:chinaAddMapData
        }]
	});
	chinaMap.hideLoading();
    $("#nowServer a").addClass("active");
    $("#confirmTotal a").removeClass("active");
});

$("#confirmTotal").on("click",function () {
	chinaMap.showLoading();
	chinaMap.setOption({
		series: [{
			data:chinaTotalMapData
		}]
	});

	chinaMap.setOption({
        visualMap: {
            min: 0,
            max: 69000,
            left: 26,
            bottom: '10%',
            showLabel: !0,
            text: ["高", "低"],
            pieces: [{
                gt: 1000,
                label: "> 1000 人",
                color: "#7f1100"
            }, {
                gte: 500,
                lte: 1000,
                label: "500 - 1000 人",
                color: "#ff5428"
            }, {
                gte: 100,
                lt: 500,
                label: "100 - 500 人",
                color: "#ff8c71"
            }, {
                gt: 50,
                lt: 100,
                label: "50 - 100 人",
                color: "#ffd768"
            }, {
                gt: 0,
                lt: 50,
                label: "0 - 50 人",
                color: "#D8BFD8"
            }, {
                value: 0,
                color: "#ffffff"
            }],
            show: !0
        }
	});
	chinaMap.hideLoading();
    $("#nowServer a").removeClass("active");
    $("#confirmTotal a").addClass("active");
});