<template>
    <div style="margin: 50px 10px 10px;">
        <el-input  v-model="search" style="width: 30%;" clearable placeholder="请输入关键字" />
        <el-button type="primary" @click="search1" style="margin-left: 5px;background-color:#F49506">查询</el-button>
    </div>
    <div style="padding:10px;margin-left: 150px">
        <!--stripe是斑马纹,sortable是排序-->
        <el-table :data="tableData" stripe border style="width: 100%">
           <!-- <el-table-column prop="number" label="餐厅号" sortable width="200">
                <template #default="scope">
                    {{scope.row.number}}
                </template>
            </el-table-column>-->
            <el-table-column prop="name" label="餐馆名称"  width="200">
                <template #default="scope">
                    {{scope.row.name}}
                </template>
            </el-table-column>
            <el-table-column prop="boss" label="商家联系方式" width="200">
                <template #default="scope">
                    {{scope.row.boss}}
                </template>
            </el-table-column>
            <el-table-column prop="city" label="餐馆地址" width="250">
                <template #default="scope">
                    {{scope.row.city}}
                </template>
            </el-table-column>
            <el-table-column   width="250">
                <template #default="scope">
                    <el-button type="primary" style="background-color:#F49506" size="mini" @click="change(scope.row)">详情</el-button>
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
                search:''
            }
        },
        created(){
            this.load();
        },
        methods:{
            load(){
                request.get('/api/restaurant/all',{
                    params:{
                        currentPage:this.currentPage,
                        pageSize:this.pageSize
                    }
                }).then(res=>{
                    this.tableData = res.data.item
                    this.total = res.data.total
                })
            },
            change(e){
                console.log(JSON.stringify(e))
                console.log(e.number)
                sessionStorage.setItem("numberD",e.number.toString())
                this.$router.push('/unReceiveDetail')
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


