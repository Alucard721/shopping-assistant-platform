var PAGESIZE = 1000;



var vm = new Vue({
  el: "#content",
  data: {
    invalidProducts: ""
  },
  created: function () {
    getAllinvalidProducts(0, PAGESIZE);
  },
  methods: {
    modalFuZhi: function (e, invalidProduct,index) {

      console.log(e)
      console.log(invalidProduct)
      //vm1.invalidproduct = target.getAttribute("invalidproduct");
      vm1.invalidproduct = invalidProduct
      vm1.index = index
      //console.log(JSON.stringify(target.dataset.invalidproduct));
      //console.log(target.dataset.invalidproduct. productName);
      //console.log(target.getAttribute("invalidproduct"))
    },
    updatavalidateProduct: function () {
      $.ajax({
        url: "http://localhost:8801/management/updatavalidateProduct",
        type: "post",
        dataType: "json",
      }).success(function (res) {
        window.location.reload();
        alert(res.returnMessage)

      })
    },
    seen: function (messeage) {
      if (messeage == "") {
        return false;
      }
      return true;
    },
    deleteInvalid: function (id, index) {
      console.log(id)
      $.ajax({
        url: "http://localhost:8801/management/deleteInvalid",
        type: "post",
        dataType: "json",
        data: {
          id: id
        }
      }).success(function (res) {
        vm.invalidProducts[index].spareField3 = "";
        Vue.set(vm.invalidProducts, index, vm.invalidProducts[index])
        alert(res.returnMessage)

      })
    },

  }
})
var vm1 = new Vue({
  el: "#myModal",
  data: {
    invalidproduct: "",
    index:""
  },
  methods: {
    saveProduct: function (index) {
      console.log(JSON.stringify(this.invalidproduct))
      $.ajax({
        url: "http://localhost:8801/management/saveProduct",
        type: "post",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        data: JSON.stringify(this.invalidproduct)
      }).success(function (res) {
        /*        $.each(vm.invalidProducts, function (index, item) {
                  if (item.id == vm1.invalidproduct.id) {
                    vm.invalidProducts.slice(index, 1)
                  }
                })*/
        vm.invalidProducts[index].spareField3 = "";
        Vue.set(vm.invalidProducts, index, vm.invalidProducts[index])
        alert(res.returnMessage)

      })
    }
  }

})

function getAllinvalidProducts(pageNum, pageSize) {
  $.ajax({
    url: "http://localhost:8801/management/validateProduct",
    type: "post",
    dataType: "json",
    data: {
      pageNum: pageNum,
      pageSize: pageSize
    }
  }).success(function (res) {
    console.log(res.content);
    vm.invalidProducts = res.content;

  })

}