<template>
    <!--<el-page-header @back="goBack" content="商家详情"
                    style="margin-top: 30px;margin-left: 60px"
    >
    </el-page-header>-->
    <el-form label-position="left">
        <el-form-item style="margin-left: 60px">
            <el-input  v-model="search" style="width: 400px;margin-left: -25px;height:400px;margin-top: 60px" clearable placeholder="请输入关键字" />
            <el-button type="primary" @click="search1" style="margin-left: 5px;margin-top: 0px;background-color:#F49506">查询</el-button>
            <el-button type="primary" @click="forSure" style="margin-left: 50px;background-color:#F49506">我的订单</el-button>
        </el-form-item>
    </el-form>
    <el-table
            :data="tableData"
            style="width: 1050px;margin-left: 250px;margin-top: -25%"
    >

        <el-table-column
                fixed
                prop="name"
                label="菜品名称"
                width="300"
        >
        </el-table-column>
        <el-table-column
                fixed
                label="菜品价格"
                prop="price"
                width="250"
        >
        </el-table-column>
        <el-table-column
                fixed
                label="操作"
                width="100px"
                prop="value"
        >
            <template #default="scope">
                <el-switch
                        v-model="scope.row.value"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        @change="switchChange(scope.row)"
                >
                </el-switch>
            </template>
        </el-table-column>
    </el-table>
    <div style="margin: 10px 10px">
        <el-pagination
                v-model:currentPage="currentPage"
                :page-sizes="[10,20,30]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
        >
        </el-pagination>
    </div>

    <div style="margin: 30px 250px">
        <el-dialog title="已选食物" v-model="dialogVisible" width="50%">
            <el-table
                    :data="tableData1"
                    style="width: 100%"
            >

                <el-table-column
                        fixed
                        prop="name"
                        label="菜品名称"
                        width="200"
                >
                    <template #default="scope">
                        <span>{{scope.row.foodname}}</span>
                    </template>
                </el-table-column>
              <!--  <el-table-column
                        fixed
                        label="菜品价格"
                        prop="price"
                        width="250"
                >
                    <template #default="scope">
                        <span>{{scope.row.price}}</span>
                    </template>
                </el-table-column>-->
            </el-table>
            <div style="margin: 10px 10px">
                <el-pagination
                        v-model:currentPage="currentPage1"
                        :page-sizes="[5,10,20]"
                        :page-size="pageSize1"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="total1"
                        @size-change="handleSizeChange1"
                        @current-change="handleCurrentChange1"
                >
                </el-pagination>
            </div>
            <div>
             请填写收货地址：
            <el-input style="width:200px" v-model="address"></el-input>
            </div>
            <div>  </div>
            <div>总金额：</div>
            <div> {{totalPrice}} </div>
            <span style="margin: 0 80px">
   <!-- <el-button @click="dialogVisible = false">确 定</el-button>-->
                <el-button type="primary" style="background-color:#F49506" @click="submitOrder">提交订单</el-button>

            </span>
        </el-dialog>
    </div>
</template>
<script>
    import {Star} from "@element-plus/icons";
    import {ChatLineRound} from "@element-plus/icons";
    import {Edit} from "@element-plus/icons";
    import {Delete} from "@element-plus/icons";
    import request from "@/utils/request";
    import moment from "moment";

    export default{
        components:{
            Star,
            ChatLineRound,
            Edit,
            Delete,
        },
        data(){
            return {
                dialogVisible: false,
                tableData:[],
                tableData1:[],
                currentPage: 1,
                total:20,
                pageSize:10,
                currentPage1: 1,
                total1:10,
                pageSize1:5,
                food:{
                    name:'',
                    price:''
                },
                address:'',
                search:'',
                totalPrice:0.1,
                orderTime:moment().format("YYYY-MM-DD HH:mm:ss")
            }
        },
        created(){
            this.load();
            this.load1();
        },
        methods: {
            submitOrder(){         //提交订单
              request.get('/api/OL/add',{
                  params:{
                      status:0,
                      address:this.address,
                      orderTime:this.orderTime.toString(),
                      number: parseInt(sessionStorage.getItem("number1")),
                      phone: sessionStorage.getItem("phone1")
                  }
              }).then(res=>{
                  this.dialogVisible = false
                  console.log(this.address)
                  console.log(this.orderTime)
                  console.log("oladd")
              })
            },

            switchChange(e){
              if(e.value === true){
                  this.submit1(e);
              }
              else if(e.value === false){
                  this.submit11(e);
              }
            },

            load(){
                request.get('/api/food/findByRName',{
                    params:{
                        name: parseInt(sessionStorage.getItem("number1")),
                        pageSize: this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(res=>{
                    this.tableData = res.data.item
                    this.total = res.data.total
                })
            },
            forSure(){
              this.dialogVisible = true;
              this.load1();
              this.selectSum();
             // this.load();
            },
            load1(){
                request.get('/api/CR/findMy',{
                    params:{
                        number: parseInt(sessionStorage.getItem("number1")),
                        phone:sessionStorage.getItem("phone1"),
                        pageSize: this.pageSize1,
                        currentPage: this.currentPage1
                    }
                }).then(res=>{
                    this.tableData1 = res.data.item
                    this.total1 = res.data.total
                })
            },

            selectSum(){
              request.get('/api/OL/price',{
                  params:{}
              }).then(res=>{
                  this.totalPrice = res.data
                  console.log("data",this.totalPrice)
              })
            },

            search1(){
                request.get('/api/food/find',{
                    params:{
                        name:this.search,
                        number:parseInt(sessionStorage.getItem("number1")),
                        pageSize:this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(res=>{
                    this.tableData=res.data.item
                    this.total=res.data.total
                    console.log("执行搜索！！1！")
                })
            },
            goBack() {
                this.$router.back();
            },
            submit1(e){
                console.log(e.name)
                request.get('/api/CR/add',{
                    params: {
                        number: parseInt(sessionStorage.getItem("number1")),
                        foodname: e.name,
                        phone: sessionStorage.getItem("phone1")
                    }
                }).then(res=>{
                    console.log("添加食物submit1")
                })
            },
            submit11(e){
                console.log(e.name)
                request.get('/api/CR/delete',{
                    params: {
                        number: parseInt(sessionStorage.getItem("number1")),
                        foodname: e.name,
                        phone: sessionStorage.getItem("phone1")
                    }
                }).then(res=>{
                    console.log("删除食物submit11")
                })
            },

            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.load()

            },
            handleCurrentChange(currentPage) {
                this.currentPage = currentPage;
            },
            handleSizeChange1(pageSize1) {
                this.pageSize1 = pageSize1
                this.load()

            },
            handleCurrentChange1(currentPage1) {
                this.currentPage1 = currentPage1;
            }
        }
    }
</script>

<style scoped>
    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .time {
        font-size: 13px;
        color: #999;
    }
    .name{
        color:cornflowerblue;
        font-size: 14px;
    }
    .topic{
        color: cornflowerblue;
        font-size: 14px;
    }
    .wapper{
        width: 100%;
        height: auto;
        display: flex;
        justify-content: space-between;
        flex-direction: row;
        flex-wrap: wrap;
    }
    .wapper-content{
        width: 24%;
        margin-right: 10px;
        margin-bottom: 30px;
    }
</style>


