<template>
    <el-tabs type="border-card" style="padding: 10px;margin-top: 50px;height: 700px;width:100%" v-model="activeName">
        <el-tab-pane label="已完成订单" name="first">

        <el-table
            :data="tableData"
            style="width:100%;padding:10px;margin-left: 60px"
    >
           <!-- <el-table-column
                    fixed
                    prop="phoneC"
                    label="用户联系方式"
                    width="150"
            >
                <template #default="scope">
                    {{scope.row.phoneC}}
                </template>
            </el-table-column>-->
        <el-table-column
                fixed
                prop="orderTime"
                label="下单时间"
                width="225"
        >
        </el-table-column>
            <el-table-column
                    fixed
                    prop="sendTime"
                    label="送达时间"
                    width="225"
            >
            </el-table-column>
            <el-table-column
                    fixed
                    prop="price"
                    label="金额"
                    width="100"
            />
            <el-table-column
                    fixed
                    prop="address"
                    label="配送地址"
                    width="400"
            />
         <!--   <el-table-column
                    fixed
                    prop="nameD"
                    label="外卖员"
                    width="100"
            />
            <el-table-column
                    fixed
                    prop="phoneD"
                    label="外卖员电话"
                    width="100"
                />-->
            <el-table-column
                    fixed
                    label="操作"
                    width="250"
            >
                <template #default="scope">
                    <el-button style="background-color:#F49506" type="primary" @click="orderDetail(scope.row);dialogVisible=true">订单详情</el-button>
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
    </el-tab-pane>
        <el-tab-pane label="配送中订单" name="second">
                <el-table
                        :data="tableData1"
                        style="width:100%;padding:10px;margin-left: 60px"
                >
                   <!-- <el-table-column
                            fixed
                            prop="orderNumber"
                            label="订单号"
                            width="200"
                    >

                    </el-table-column>-->
                  <!--  <el-table-column
                            fixed
                            prop="phoneC"
                            label="用户联系方式"
                            width="150"
                    />-->
                    <el-table-column
                            fixed
                            prop="orderTime"
                            label="下单时间"
                            width="225"
                    >
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="price"
                            label="金额"
                            width="150"
                    />
                    <el-table-column
                            fixed
                            prop="address"
                            label="配送地址"
                            width="450"
                    />
                  <!--  <el-table-column
                            fixed
                            prop="nameD"
                            label="外卖员"
                            width="100"
                    />
                    <el-table-column
                            fixed
                            prop="phoneD"
                            label="外卖员电话"
                            width="150"
                    />-->
                    <el-table-column
                            fixed
                            label="操作"
                            width="350"
                    >
                        <template #default="scope">
                            <el-button style="background-color:#F49506" type="primary" @click="orderDetail(scope.row);dialogVisible=true">订单详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>

            <div style="margin: 10px 10px">
                <el-pagination
                        v-model:currentPage="currentPage1"
                        :page-sizes="[10,20,30]"
                        :page-size="pageSize1"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="total1"
                        @size-change="handleSizeChange1"
                        @current-change="handleCurrentChange1"
                >
                </el-pagination>
            </div>
        </el-tab-pane>
        <el-tab-pane label="未配送订单" name="third">
           <!-- <div  style="padding:10px;margin-left: 60px">-->
                <el-table
                        :data="tableData2"
                        style="width:100%;padding:10px;margin-left: 60px"
                >
                  <!--  <el-table-column
                            fixed
                            prop="phoneC"
                            label="用户联系方式"
                            width="200"
                    />-->
                    <el-table-column
                            fixed
                            prop="orderTime"
                            label="下单时间"
                            width="250"
                    >
                    </el-table-column>
                    <el-table-column
                            fixed
                            prop="price"
                            label="金额"
                            width="150"
                    />
                    <el-table-column
                            fixed
                            prop="address"
                            label="配送地址"
                            width="300"
                    />
                    <el-table-column
                            fixed
                            label="操作"
                            width="250"
                    >
                        <template #default="scope">
                            <el-button style="background-color:#F49506" type="primary" @click="orderDetail(scope.row);dialogVisible=true">订单详情</el-button>
                        </template>
                    </el-table-column>

                </el-table>

            <div style="margin: 10px 10px">
                <el-pagination
                        v-model:currentPage="currentPage2"
                        :page-sizes="[10,20,30]"
                        :page-size="pageSize2"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="total2"
                        @size-change="handleSizeChange2"
                        @current-change="handleCurrentChange2"
                >
                </el-pagination>
            </div>




        </el-tab-pane>

        <div style="margin: 0px 50px">
            <el-dialog title="订单详情" v-model="dialogVisible" width="30%">
                <div>点的菜品</div>
                <el-table :data="tableData3">
                    <el-table-column
                            label="用户联系方式"
                            style="width: 200px"
                            prop="phone"
                    >
                        <template #default="scope">
                            <span>{{scope.row.phone}}</span>
                        </template>
                    </el-table-column>
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

    </el-tabs>
</template>
    <script>
        import {Star} from "@element-plus/icons";
        import {ChatLineRound} from "@element-plus/icons";
        import request from "@/utils/request";

        export default {
            components:{
              Star,
                ChatLineRound,
            },
            data(){
              return{
                  activeName:'first',
                  tableData:[],
                  tableData1:[],
                  tableData2:[],
                  tableData3:[],
                  currentPage:1,
                  pageSize:10,
                  total:20,
                  currentPage1:1,
                  pageSize1:10,
                  total1:20,
                  currentPage2:1,
                  pageSize2:10,
                  total2:20,
                  dialogVisible:false
              }
            },
            created(){
              this.load();
              this.load1();
              this.load2();
            },
            methods: {
                goBack() {
                    this.$router.back();
                },


                orderDetail(e){   //显示订单详情  食物，顾客的手机号等等
                 request.get('/api/CR/findOD',{
                     params:{
                         orderNumber:e.orderNumber
                     }
                    }).then(res=>{
                        console.log("api/cr/findOD")
                         this.tableData3 = res.data
                 })

                },
                load(){
                    request.get('/api/OL/send1',{
                        params:{
                            status:2,
                            number:parseInt(sessionStorage.getItem("numberOR")),
                            pageSize: this.pageSize,
                            currentPage: this.currentPage
                        }
                    }).then(res=>{
                        this.tableData = res.data.item
                        this.total = res.data.total
                    })
                },
                load1(){
                    request.get('/api/OL/send1',{
                        params:{
                            status:1,
                            number:parseInt(sessionStorage.getItem("numberOR")),
                            pageSize: this.pageSize1,
                            currentPage: this.currentPage1
                        }
                    }).then(res=>{
                        this.tableData1 = res.data.item
                        this.total1 = res.data.total
                    })
                },

                load2(){
                    request.get('/api/OL/send1',{
                        params:{
                            status:0,
                            number:parseInt(sessionStorage.getItem("numberOR")),
                            pageSize: this.pageSize2,
                            currentPage: this.currentPage2
                        }
                    }).then(res=>{
                        this.tableData2 = res.data.item
                        this.total2 = res.data.total
                    })
                },

                handleSizeChange(pageSize)
                {
                    this.pageSize=pageSize
                    this.load()
                },
                handleCurrentChange(pageSize)
                {
                    this.currentPage=pageSize
                    this.load()
                },


                handleSizeChange1(pageSize)
                {
                    this.pageSize1=pageSize
                    this.load()
                },
                handleCurrentChange1(currentPage){
                    this.currentPage1=currentPage;
                    this.load();
                },


                handleSizeChange2(pageSize)
                {
                    this.pageSize2=pageSize
                    this.load()
                },
                handleCurrentChange2(currentPage){
                    this.currentPage2=currentPage;
                    this.load();
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
</style>
