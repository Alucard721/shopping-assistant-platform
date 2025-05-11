<template>
  <div>
    <!-- 主要内容区 -->
    <div style="background:#EEEEEE; margin-top: -20px" align="center">

      <div id="productNum" style="padding-top: 14px" class="row productNum" v-model="productNum" :value="productNum">
        优易购 > 购物搜索    共找到相关商品<b style="color: #a71d2a">{{productNum}}</b>件
      </div>

      <div id="filter"
           style="background-color:#fff; height: 90px; width: 971px;position: relative;z-index: 0;border-radius: 5px;margin-top: 5px"
           class="content">
        <div class="row" style="height: 30px;width: 971px">
          <span class="titlebodyhead" style="color:#333333 ;height: 35px;width: 971px;text-align: left;">筛选商品</span>
        </div>
<!--        <div class="row" style="height: 60px;width: 971px" v-model="keyword">-->
          <div class="row" style="height: 60px;width: 971px; margin-top: 20px;">
          <div class="T" style="margin-top:3px; text-align: left; color: #5A5A5A; font-family: Microsoft YaHei;font-size: 12px">
            <b>商城报价:</b>
          </div>
            <div class="divSXrightN">
              <ul>
                <li class="divSXblock">
                  <a href="#" class="f12sc">
                    <input style="margin-left: 30px; margin-top: 3px" class="mallfilter" type="checkbox" value="all" @click="mallFilter($event)">
                    <span class="fcount">(2478)</span>
                    <span style="margin-left: -1px; margin-top: 1px">全部</span>
                  </a>
                </li>
                <li class="divSXblock">
                  <a href="#" class="f12sc">

                    <img src="../assets/imgs/jd.png" width="16" height="16" align="absmiddle">京东商城
                    <span class="fcount">(2478)</span>
                    <input style="margin-left: 4px; margin-top: 2px" class="mallfilter" type="checkbox" value="2" @click="mallFilter($event)">
                  </a>
                </li>
                <li class="divSXblock">
                  <a href="#" class="f12sc">

                    <img src="../assets/imgs/tm.png" width="16" height="16" align="absmiddle">天猫商城
                    <span class="fcount">(113)</span>
                    <input style="margin-left: 4px; margin-top: 2px" class="mallfilter" type="checkbox" value="1" @click="mallFilter($event)">
                  </a>
                </li>
              </ul>
            </div>
        </div>
      </div>

      <div id="productContent"
           style="margin-top: 12px; background-color:#fff; height: 3785px; width: 971px;font-size: 12px;position: relative;z-index: 0;"
           class="content">
        <div style="width: 990px; margin-top: 6px; padding-bottom: 2px; overflow: hidden;">
          <div style="height: 60px;float: left; color: #5A5A5A " class="searchBarN" >
            <div class="btn-toolbar" role="toolbar" style="font-size: 12px">
              <div class="btn-group  btn-group-sm">
                <button type="button" class="btn btn-default tab1" @click="getProduct"
                        style="background-color: #5A5A5A;color: #ffffff;"> <span style="font-size: 12px">默认排序</span>
                </button>
                <button @click="getProductSortBySellCount" type="button" class="btn btn-default tab2">
                  <span style="font-size: 12px">销量</span></button>
                <button @click="getProductSortByLowerPrice" type="button" class="btn btn-default tab2"><span style="font-size: 12px">价格</span><i
                    class="glyphicon glyphicon-arrow-up btn-sm "></i></button>
                <button @click="getProductSortByUpperPrice" type="button" class="btn btn-default tab2"><span style="font-size: 12px">价格</span><i
                    class="glyphicon glyphicon-arrow-down btn-sm" style="size: 1px"></i></button>
              </div>
              <form style="margin-left: 20px;margin-top: -6px;">
                价格
                <input type="text" class="priceT" name="price1"/> -
                <input type="text" class="priceT" name="price2"/>
                <input type="image" class="btn" src="http://misc.manmanbuy.com/images/search/ok.gif"/>
              </form>
            </div>

          </div>
        </div>
<!--        <template v-if="total==0">-->
<!--          <div>无结果</div>-->
<!--        </template>-->
        <div v-for="product in productContent" :key="product">
          <div>
          <div class="row bjlineSmall">
            <div class="pic" style="margin-left: 15px">
              <a target="_blank"><img v-bind:src="product.productImg"
                                      style=" transform: scale(0.7);margin-left: -131px;margin-top: -136px;"></a>
            </div>
            <div class="title">
              <div class="t" style="font-size: 14px; width: 420px">
                <a v-bind:href="product.productUrl"
                   v-html="product.productName" style="color: #5a5a5a">
                  <div>{{product.productName}}</div>
                </a>
              </div>
            </div>

            <div class="cost" style="margin-top: -6px">
              <a v-bind:href="product.productUrl" style="color: #CC0000">
                <span style="font-weight: bold">¥</span>
                <span style="font-size: 20px;font-weight: bold">{{product.spareField1}}</span>
              </a>
            </div>

            <div class="comment" style="margin-left: 14px">
              有<span style="color: #005AA0">{{product.reviewCount}}</span>人评论
            </div>
            <template v-if="product.spareField2=='2'">
              <div class="mall " style=" margin-left: 15px">
                <p class="m">
                  <img src="http://sf.manmanbuy.com/images/sitelogo/1.png" width="16" height="16" align="absmiddle"
                       alt="京东商城的报价">
                  <span style="color: #5a5a5a; margin-left: 3px">京东商城</span>
                </p>
                <span style="color: #5a5a5a; margin-top: -10px; display: block;">{{ product.shopName }}</span>
              </div>
            </template>
            <template v-if="product.spareField2=='1' || product.spareField2==null || product.spareField2==''">
              <div class="mall">
                <p class="m">
                  <img src="http://sf.manmanbuy.com/images/sitelogo/10.png" width="16" height="16" align="absmiddle"
                       alt="天猫商城的报价">
                </p>
                <span>天猫商城</span>
              </div>
            </template>
          </div>

        </div>

        </div>
        <!-- 分页 -->
        <div class="pagination" style="display: flex; justify-content: center;">
          <el-pagination
              background
              layout="prev, pager, next"
              :page-size="20"
              :total="productNum"
              @current-change="currentChange"
          ></el-pagination>
        </div>
        <!-- 分页END -->
      </div>




        <!--<template v-if="total!=0">-->
        <div class="row">
<!--          <div class="col-sm-10" style="padding-left: 100px" id="app" v-model="current,allpage,showItem">-->
          <div class="col-sm-10" style="padding-left: 100px" id="app">
            <page v-if="hackReset"></page>
          </div>
        </div>
        <!--</template>-->
      </div>
    </div>



    <!-- 主要内容区END -->

</template>

<script>

export default {
  name: 'Product',
  data: function () {
    return {
      productContent: [],
      productNum: 0,
      currentSort: 'default',
      search: "", // 搜索条件
      pageNum: 1, //当前页码
    }
  },
  mounted: function () {
    this.currentPage = 1; //初始化当前页码为1
    let _this = this;
    _this.getProduct();
    // _this.getProductSortByUpperPrice();
    // _this.getProductSortByLowerPrice();
    // _this.getProductSortBySellCount();

    // 如果路由传递了search，则为搜索，显示对应的分类商品
    if (this.$route.query.search != undefined) {
      this.search = this.$route.query.search;
    }
  },
  watch: {
    // 监听搜索条件，响应相应的商品
    search: function(val) {
      if (val != "") {
        this.getProduct(val);
      }
    },
    // 监听路由变化，更新路由传递了搜索条件
    $route: function(val) {
      if (val.path == "/goods") {
        if (val.query.search != undefined) {
          this.pageNum = 1;
          this.productNum = 0;
          this.search = val.query.search;
        }
      }
    }
  },
  methods: {
    // 返回顶部
    backtop() {
      const timer = setInterval(function() {
        const top = document.documentElement.scrollTop || document.body.scrollTop;
        const speed = Math.floor(-top / 5);
        document.documentElement.scrollTop = document.body.scrollTop =
            top + speed;

        if (top === 0) {
          clearInterval(timer);
        }
      }, 20);
    },
    // 页码变化调用currentChange方法
    currentChange(pageNum) {
      this.pageNum = pageNum;
      if (this.search != "") {
        this.getProduct();
      } else {
        this.getData();
      }
      this.backtop();
    },
    getProduct() {
      let _this = this;
      _this.currentSort = 'default';
      _this.$ajax
          .post('http://localhost:8401/search/search',{
            key: this.search,
            pageNum: this.pageNum,
            pageSize: 20,
            mallFilter: 2
          })
          .then(
          (response) => {
            _this.productContent = response.data.products;
            _this.productNum = response.data.total;
          }
      )
    },getProductSortByUpperPrice() {
      let _this = this;
      _this.currentSort = 'upperPrice';
      _this.$ajax
          .post('http://localhost:8401/search/search2',{
            key: this.search,
            pageNum: this.pageNum,
            pageSize: 20,
            mallFilter: 2
          })
          .then(
          (response) => {
            _this.productContent = response.data.products;
            _this.productNum = response.data.total;
          }
      )
    },getProductSortByLowerPrice () {
      let _this = this;
      _this.currentSort = 'lowerPrice';
      _this.$ajax
          .post('http://localhost:8401/search/search3',{
            key: this.search,
            pageNum: this.pageNum,
            pageSize: 20,
            mallFilter: 2
          })
          .then(
          (response) => {
            _this.productContent = response.data.products;
            _this.productNum = response.data.total;
          }
      )
    },getProductSortBySellCount () {
      let _this = this;
      _this.currentSort = 'sellCount';
      _this.$ajax
          .post('http://localhost:8401/search/search4',{
            key: this.search,
            pageNum: this.pageNum,
            pageSize: 20,
            mallFilter: 2
          })
          .then(
          (response) => {
            _this.productContent = response.data.products;
            _this.productNum = response.data.total;
          }
      )
    }

  }
};
</script>


<style scoped>
@import "../assets/css/bootstrap.css";
@import "../assets/css/bootstrap-grid.css";
@import "../assets/css/display/result.css";

/* 顶部导航栏CSS */
.topbar {
  height: 40px;
  background-color: rgba(27, 30, 33, 0.83);
  margin-bottom: 20px;
}
.topbar .nav {
  width: 1500px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.topbar .nav ul {
  float: right;
  /*margin-left: 1120px;*/
}
.topbar .nav li {
  float: left;
  height: 40px;
  color: #b0b0b0;
  font-size: 14px;
  text-align: center;
  line-height: 40px;
  margin-left: 20px;
}
.topbar .nav .sep {
  color: #b0b0b0;
  font-size: 12px;
  margin: 0 5px;
}
.topbar .nav li .el-button {
  color: #b0b0b0;
}
.topbar .nav .el-button:hover {
  color: #fff;
}
.topbar .nav li a {
  color: #b0b0b0;
}
.topbar .nav a:hover {
  color: #fff;
}
.topbar .nav .shopCart {
  width: 120px;
  background: #424242;
}
.topbar .nav .shopCart:hover {
  background: #fff;
}
.topbar .nav .shopCart:hover a {
  color: #ff6700;
}
.topbar .nav .shopCart-full {
  width: 120px;
  background: #ff6700;
}
.topbar .nav .shopCart-full a {
  color: white;
}

.topbar .nav .right {
  display: flex;
  align-items: center;
  /*margin-left: 9000px;*/
  float: right;
}

.topbar .right .btn {
  font-size: 17px;
  color: white;
}
.topbar .right .btn:hover {
  color: #31c27c;
}

.logo {
  margin-top: -10px; /* 根据需要调整这个值 */
}
/* 顶部导航栏CSS END */

.divSXblock {
  display: flex;
  align-items: center; /* 垂直居中 */
}

.f12sc {
  display: flex;
  align-items: center; /* 垂直居中 */
}

.mallfilter {
  margin-right: 5px; /* 为复选框添加右边距 */
}
.goods {
  background-color: #f5f5f5;
}
/* 面包屑CSS */
.el-tabs--card .el-tabs__header {
  border-bottom: none;
}
.goods .breadcrumb {
  height: 50px;
  background-color: white;
}
.goods .breadcrumb .el-breadcrumb {
  width: 971px;
  line-height: 30px;
  font-size: 16px;
  margin: 0 auto;
}
/* 面包屑CSS END */

/* 分类标签CSS */
.goods .nav {
  background-color: white;
}
.goods .nav .product-nav {
  width: 971px;
  height: 40px;
  line-height: 40px;
  margin: 0 auto;
}
.nav .product-nav .title {
  width: 50px;
  font-size: 16px;
  font-weight: 700;
  float: left;
}
/* 分类标签CSS END */

/* 主要内容区CSS */

</style>
