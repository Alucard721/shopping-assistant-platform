var vm = new Vue({
  el: "#myModal",
  data: {
    sellCount:"",
    reviewCount:"",
    shopdsrMs:"",
    shopdsrFw:"",
    shopdsrWl:""

  },
  created: function () {
    getEvaluateModule();
  },
  methods:{
    saveEvaluate:function () {
      $.ajax({
        url: "http://localhost:8801/management/saveEvaluate",
        type: "post",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data:JSON.stringify({
            sellCount:this.sellCount,
            reviewCount:this.reviewCount,
            shopdsrMs:this.shopdsrMs,
            shopdsrFw:this.shopdsrFw,
            shopdsrWl:this.shopdsrWl
        })
      }).success(function (res) {
        alert(res.returnMessage);
        window.location.reload();
      })
    }
  },

  startEvaluate: function() {
    var messageElement = document.getElementById('success-message');
    if (messageElement) {
      messageElement.innerText = '评分成功';
      messageElement.style.display = 'block'; // 确保元素是可见的
    }
    $.ajax({
      url: "http://localhost:8601/recommend/evaluate",
      type: "get",
      dataType: "json",
    }).always(function() {
      // 无论AJAX请求成功或失败，都会执行这里的代码
      alert('评分请求已发送。');
      window.location.reload();
    });
  }
  // startEvaluate:function (){
  //   var messageElement = document.getElementById('success-message');
  //   if (messageElement) {
  //     messageElement.innerText = '评分成功';
  //     messageElement.style.display = 'block'; // 确保元素是可见的
  //   }
  //   $.ajax({
  //     url: "http://localhost:8601/recommend/evaluate",
  //     type: "get",
  //     dataType: "json",
  //   }).done(function (res) {
  //     alert(res);
  //     window.location.reload();
  //   });
  // }

})

function getEvaluateModule() {
  $.ajax({
    url: "http://localhost:8801/management/findEvaluateModule",
    type: "post",
    contentType: "application/json;charset=UTF-8",
    dataType: "json",
  }).success(function (res) {
    console.log(res)
    // 初始化echarts图表
    var myChart = echarts.init(document.getElementById("charts"));
    // 修改响应对象的name属性，添加权重信息
    res.name = res.name + ' ' + res.weight;
    // 遍历响应对象的children属性
    echarts.util.each(res.children, function (datum, index) {
      datum.name = datum.name + ' ' + datum.weight;
      // 遍历每个子对象的children属性
      echarts.util.each(datum.children, function (child, index1) {
        // 修改每个孙对象的name属性，添加权重信息
        child.name = child.name + ' ' + child.weight;
        // 根据索引值，将孙对象的权重赋值给不同的变量
        if(index==0){
          switch(index1){
            case 0: vm.sellCount = child.weight;break;
            case 1: vm.reviewCount = child.weight;break;
            case 2: vm.collectCount = child.weight;break;
          }
        }
        if(index==1){
          switch(index1){
            case 0: vm.shopdsrMs = child.weight;break;
            case 1: vm.shopdsrFw = child.weight;break;
            case 2: vm.shopdsrWl = child.weight;break;
          }
        }

        echarts.util.each(child.children, function (child1, index) {
          child1.name = child1.name + ' ' + child1.weight;
        });
      });

    });

    myChart.setOption(option = {
      tooltip: {
        trigger: 'item',
        triggerOn: 'mousemove'
      },
      series: [
        {
          type: 'tree',

          data: [res],

          top: '1%',
          left: '20%',
          bottom: '1%',
          right: '20%',

          symbolSize: 7,

          label: {
            normal: {
              position: 'left',
              verticalAlign: 'middle',
              align: 'right',
              fontSize: 14
            }
          },

          leaves: {
            label: {
              normal: {
                position: 'right',
                verticalAlign: 'middle',
                align: 'left'
              }
            }
          },

          expandAndCollapse: true,
          animationDuration: 550,
          animationDurationUpdate: 750
        }
      ]
    });
  })


}

