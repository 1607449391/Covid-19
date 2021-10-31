var provinceName = $("#name").val();
var childrenStr = $("#children").val();
var children = childrenStr.replaceAll("ProvRealData","");
/*省份地图展示*/
(function(){

	var map = echarts.init(document.getElementById("provinceMap"));
	map.showLoading();
	option = {
	    tooltip: {
	        triggerOn: "click",
	        formatter: function(e, t, n) {
	            return .5 == e.value ? e.name + "：确诊总数" : e.seriesName + "<br />" + e.name + "：" + e.value
	        }
	    },
	    visualMap: {
	        min: 0,
	        max: 1000,
	        left: 26,
	        bottom: 40,
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
	        map: provinceName,
	        roam: true,
	        scaleLimit: {
	            min: 1,
	            max: 2
	        },
	        zoom: 1.23,
	        top: 120,
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
	        geoIndex: 0

	    }]
	};
	$.ajax({
		url:"/getProvMap",
		data:{provinceName:provinceName},
		type:'GET',
		dataType:'json',
		success:function (result) {
			console.log(result);
			map.hideLoading();
			map.setOption({
                series: [{
                	data:result
				}]
			});
        }
	});
	map.setOption(option);
})();

/*绘制省份折线图*/
(function(){
	var dateList = new Array();
	var confirmList = new Array();
	var deadList = new Array();
	var healList = new Array();
	var newConfirmList = new Array();

	var provLine = echarts.init(document.getElementById("provinceLine"));
	provLine.showLoading();
	option = {
	    title: {
			left:"center"
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
			right:'right',
			top:'top',
			orient:'vertical'
	    },
	    grid: {
			top:'10%',
	        left: '3%',
	        right: '10%',
	        bottom: '15%',
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
        dataZoom:[
			{

			},
			{
                type: 'inside'
			}
		]
	};
	$.ajax({
		url:'/getProvHistorydata',
		type:'GET',
		data:{provinceName:provinceName},
		dataType:'json',
		success:function (result) {
			$.each(result,function (index,val) {
				dateList[index] = val.date;
				confirmList[index] = val.confirm;
				deadList[index] = val.dead;
				healList[index] = val.heal;
				newConfirmList[index] = val.newConfirm;
            });
			provLine.hideLoading();
			provLine.setOption({
                title: {
                    text: provinceName+'疫情趋势图'
                },
                legend: {
                    data: ['累计确诊', '新增确诊', '治愈人数', '死亡人数']
				},
                xAxis: {
                    data: dateList
				},
                series: [
                    {
                        name: '累计确诊',
                        type: 'line',
                        stack: 'confirm',
                        data: confirmList
                    },
                    {
                        name: '新增确诊',
                        type: 'line',
                        stack: 'newConfirm',
                        data: newConfirmList
                    },
                    {
                        name: '治愈人数',
                        type: 'line',
                        stack: 'heal',
                        data: healList
                    },
                    {
                        name: '死亡人数',
                        type: 'line',
                        stack: 'dead',
                        data: deadList
                    }
                ]

			});


        }
	});


	provLine.setOption(option);
	
})();