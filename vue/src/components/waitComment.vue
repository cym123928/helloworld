<template>
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
            <el-table-column prop="price" label="金额" width="50"  />
            <el-table-column prop="orderTime" label="下单时间" width="150"  />
            <el-table-column prop="sendTime" label="收货时间" width="150"  />
            <el-table-column prop="address" label="收货地址" width="100"  />
            <el-table-column   width="200">
                <template #default="scope">
                    <el-button type="primary" size="mini" style="background-color:#F49506" @click="orderDetail(scope.row);dialogVisible=true">详情</el-button>
                    <el-button type="primary" style="background-color:#F49506" @click="dialogVisible1=true">评价</el-button>
                    <div style="margin: 0px 50px">
                        <el-dialog title="评价" v-model="dialogVisible1" width="30%">

                            <div class="block">
                                <span class="demonstration">评价餐厅</span>
                                <el-rate v-model="value1" @change="changeRate"></el-rate>
                            </div>
                            <el-input
                                    type="textarea"
                                    :rows="4"
                                    placeholder="请输入评价"
                                    v-model="resCom">
                            </el-input>


                            <div class="block">
                                <span class="demonstration">评价外卖员</span>
                                <el-rate v-model="value2" @change="changeRate1"></el-rate>
                            </div>
                            <el-input
                                    type="textarea"
                                    :rows="4"
                                    placeholder="请输入评价"
                                    v-model="delCom">
                            </el-input>

                            <span style="margin: 0 80px">
                    <el-button type="primary" style="background-color:#F49506" @click="insertComment(scope.row);dialogVisible1=false">确 定</el-button>
                    </span>
                        </el-dialog>
                    </div>
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
    </div>

    <div style="margin: 0px 50px">
        <el-dialog title="订单详情" v-model="dialogVisible" width="30%">
            <el-table :data="tableData3">
                <el-table-column
                        label="点的菜品"
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

    export default {
        name: "",
        data(){
            return{
                currentPage:1,
                pageSize:10,
                total:20,
                tableData: [],
                tableData3:[],
                dialogVisible:false,
                dialogVisible1:false,
                value1:0,
                resCom:'',
                value2:5,
                delCom:''
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
                        status:2,
                        phone:sessionStorage.getItem("phone1")
                    }
                }).then(res=>{
                    console.log("/ol/myordered")
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
            changeRate(value) {
                console.log(value);
            },
            changeRate1(value) {
                console.log(value);
            },
            insertComment(e){
                request.get('/api/CC/addC',{
                    params:{
                        orderNumber:e.orderNumber,
                        resCom: this.value1.toString(),
                        delCom: this.value2.toString(),
                        resCom1:this.resCom,
                        delCom1:this.delCom
                    }
                })
            },
        }
        }
</script>
