<template>

    <div style="margin: 50px 10px 10px;">
        <el-input  v-model="search" style="width: 30%;" clearable placeholder="请输入关键字" />
        <el-button type="primary" @click="search1" style="margin-left: 5px;">查询</el-button>
    </div>

    <div style="padding:60px;margin-left: 2px">
        <!--stripe是斑马纹,sortable是排序-->
        <el-table :data="tableData" stripe border style="width: 100%">
            <el-table-column prop="name" label="餐馆名称"  width="100">
                <template #default="scope">
                    {{scope.row.name}}
                </template>
            </el-table-column>
            <el-table-column prop="city" label="餐馆地址" width="200">
                <template #default="scope">
                    {{scope.row.city}}
                </template>
            </el-table-column>
            <el-table-column prop="boss" label="商家联系方式" width="100">
                <template #default="scope">
                    {{scope.row.boss}}
                </template>
            </el-table-column>
            <el-table-column prop="price" label="金额" width="100"  />
            <el-table-column prop="orderTime" label="下单时间" width="150"  />
            <el-table-column prop="address" label="收货地址" width="100"  />
            <el-table-column   width="300">
                <template #default="scope">
                    <el-button type="primary" style="background-color:#F49506" size="mini" @click="orderDetail(scope.row);dialogVisible=true">详情</el-button>
                    <el-button type="primary" style="background-color:#F49506" size="mini" @click="confirmOrder(scope.row)">确认收货</el-button>
                    <el-button type="primary" style="background-color:#F49506" size="mini" @click="cancelOrder(scope.row)">取消订单</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin: 10px 10px">
            <el-pagination
                    v-model:currentPage="currentPage"
                    :page-sizes="[5,10,20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
            >
            </el-pagination>
        </div>
    </div>

    <div style="margin: 0px 50px">
        <el-dialog title="订单详情" v-model="dialogVisible" width="30%">
            <div>所点菜品</div>
            <el-table :data="tableData3">
                <el-table-column
                        style="width: 200px"
                        prop="foodname"
                >
                    <template #default="scope">
                        <span>{{scope.row.foodname}}</span>
                    </template>
                </el-table-column>
            </el-table>
            <span style="margin: 0 80px">
                    <el-button type="primary" style="background-color:#F49506" @click="dialogVisible=false">确 定</el-button>
                    </span>
        </el-dialog>
    </div>
</template>

<script>
    import request from "../utils/request";
    import moment from "moment";

    export default {
        name: "",
        data(){
            return{
                currentPage:1,
                pageSize:5,
                total:10,
                tableData: [],
                tableData3:[],
                dialogVisible:false,
                sendTime: moment().format("YYYY-MM-DD HH:mm:ss"),
                search:''
            }
        },
        created() {
            this.load();
        },
        methods:{
            handleSizeChange(pageSize)
            {
                this.pageSize=pageSize
                this.load()
            },
            handleCurrentChange(currentPage){
                this.currentPage=currentPage;
                this.load();
            },

            load(){
                request.get('/api/OL/myOrdered',{
                    params:{
                        pageSize: this.pageSize,
                        currentPage: this.currentPage,
                        status:1,
                        phone:sessionStorage.getItem("phone1")
                    }
                }).then(res=>{
                    console.log("/ol/myWillOrder")
                    this.tableData = res.data.item
                    this.total = res.data.total
                })
            },

            orderDetail(e){   //显示订单详情  食物，顾客的手机号等等
                request.get('/api/CR/findOD',{
                    params:{
                        orderNumber:e.orderNumber
                    }
                }).then(res=>{
                    this.tableData3 = res.data
                })
            },

            confirmOrder(e){   //确认收货
                request.get('/api/OL/confirm',{
                    params:{
                        orderNumber:e.orderNumber,
                        sendTime:this.sendTime.toString()
                    }
                }).then(res=>{
                    this.load();
                    console.log("ol/confirm")
                })
            },

            cancelOrder(e){   //取消收货
                request.get('/api/OL/cancel',{
                    params:{
                        orderNumber:e.orderNumber
                    }
                }).then(res=>{
                    this.load();
                    console.log("ol/cancel")
                })
            },

            search1(){
                request.get('/api/restaurant/searchR',{
                    params:{
                        search:this.search,
                        pageSize:this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(res=>{
                    this.tableData=res.data.item
                    this.total=res.data.total
                    console.log("执行！！1！")
                })
            },

        }
    }
</script>


