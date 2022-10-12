<template>

    <div style="padding:160px;margin-left: -10px;" >
        <el-table
                :data="tableData"
                style="width:100%"
        >
            <!--  <el-table-column
                      fixed
                      prop="orderNumber"
                      label="订单号"
                      width="100"
              >
              </el-table-column>-->
            <el-table-column
                    fixed
                    prop="price"
                    label="价格"
                    width="100"
            >
            </el-table-column>
            <el-table-column
                    fixed
                    prop="orderTime"
                    label="下单时间"
                    width="250"
            >
            </el-table-column>
            <el-table-column
                    fixed
                    prop="address"
                    label="配送地址"
                    width="250"
            >
            </el-table-column>
            <el-table-column
                    fixed
                    label="操作"
                    width="100"
            >
                <template #default="scope">
                    <el-button style="background-color:#F49506" type="primary" @click="orderDetail(scope.row);dialogVisible=true">订单详情</el-button>
                </template>
            </el-table-column>
            <el-table-column
                    fixed
                    width="100"
            >
                <template #default="scope">
                    <el-button type="primary" style="background-color:#F49506" @click="updateDDD(scope.row)">取消接单</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
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

    <div style="margin: 0px 50px">
        <el-dialog title="订单详情" v-model="dialogVisible" width="30%">
            <el-table :data="tableData1">
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
                tableData:[],
                tableData1:[],
                currentPage1:1,
                pageSize1:5,
                total1:10,
                currentPage:1,
                pageSize:5,
                total:10,
                dialogVisible:false
            }
        },
        created() {
            this.load();
        },
        methods: {
            orderDetail(e) {   //显示订单详情  食物，顾客的手机号等等
                request.get('/api/CR/findOD', {
                    params: {
                        orderNumber: e.orderNumber
                    }
                }).then(res => {
                    this.tableData1 = res.data
                })

            },

            load(){
                request.get('/api/OL/send1',{
                    params:{
                        status:1,
                        number:parseInt(sessionStorage.getItem("numberD")),
                        pageSize: this.pageSize,
                        currentPage: this.currentPage
                    }
                }).then(res=>{
                    this.tableData = res.data.item
                    this.total = res.data.total
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
            updateDDD(e){
                request.get('/api/deliver/updateDDD',{
                    params:{
                        orderNumber:e.orderNumber,
                        phone:sessionStorage.getItem("phone2"),
                        status:0
                    }
                }).then(res=>{
                    this.load()
                    console.log("updateDDD")
                })
            },


        }


    }
</script>

<style scoped>

</style>
