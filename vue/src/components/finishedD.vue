<template>
    <div style="padding:160px;margin-left: 0px">
        <!--stripe是斑马纹,sortable是排序-->
        <el-table :data="tableData" stripe border style="width: 100%">
          <!--  <el-table-column prop="orderNumber" label="订单号" sortable width="200">
                <template #default="scope">
                    {{scope.row.orderNumber}}
                </template>
            </el-table-column>
            <el-table-column prop="number" label="餐厅号" sortable width="200">
                <template #default="scope">
                    {{scope.row.number}}
                </template>
            </el-table-column>-->
            <el-table-column prop="name" label="餐馆名称"  width="100">
                <template #default="scope">
                    {{scope.row.name}}
                </template>
            </el-table-column>
            <el-table-column prop="boss" label="商家联系方式" width="100">
                <template #default="scope">
                    {{scope.row.boss}}
                </template>
            </el-table-column>
            <el-table-column prop="city" label="餐馆地址" width="200">
                <template #default="scope">
                    {{scope.row.city}}
                </template>
            </el-table-column>
            <el-table-column prop="price" label="金额" width="100"  />
            <el-table-column prop="orderTime" label="下单时间" width="150"  />
            <el-table-column prop="sendTime" label="送达时间" width="150"  />
            <el-table-column prop="address" label="收货地址" width="200"  />
            <el-table-column   width="100">
                <template #default="scope">
                    <el-button type="primary" style="background-color:#F49506" size="mini" @click="orderDetail(scope.row);dialogVisible=true">详情</el-button>
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
            <el-table :data="tableData3">
                <el-table-column
                        label="用户联系方式"
                        style="width: 200px"
                        prop="phone"
                />
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
                pageSize:5,
                total:10,
                tableData: [],
                tableData3:[],
                dialogVisible:false
            }
        },
        created() {
            this.load();
        },
        methods:{
            load(){
              request.get('/api/deliver/selectEd',{
                  params:{
                      phone:sessionStorage.getItem("phone2"),
                      pageSize:this.pageSize,
                      currentPage:this.currentPage
                  }
              }).then(res=>{
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

            handleSizeChange(pageSize)
            {
                this.pageSize=pageSize
                this.load()
            },
            handleCurrentChange(currentPage){
                this.currentPage=currentPage;
                this.load();
            },
        }
    }
</script>

