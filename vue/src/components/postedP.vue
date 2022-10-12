<template>
            <div style="margin: 50px 10px 10px;">
                <el-input  v-model="search" style="width: 30%;" clearable placeholder="请输入关键字" />
                <el-button type="primary" @click="search1" style="margin-left: 5px;background-color:#F49506">查询</el-button>
            </div>
            <div  style="padding:10px;margin-left: 120px">
                <el-table :data="tableData" stripe border style="width: 100%">
                    <!--<el-table-column prop="number" label="餐厅号"  width="100">
                        <template #default="scope">
                            <span>{{scope.row.number}}</span>
                        </template>
                    </el-table-column>-->
                    <el-table-column prop="name" label="餐厅名称" width="225">
                        <template #default="scope">
                            <span>{{scope.row.name}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="city" label="地址" width="500">
                        <template #default="scope">
                            <span>{{scope.row.city}}</span>
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
    import request from "@/utils/request";

    export default {
        name: "postedP",
        data(){
            return{
                tableData:[],
                pageSize:10,
                currentPage:1,
                username : sessionStorage.getItem("username1"),
                phone:sessionStorage.getItem("phone1"),
                search:'',
                total:20
            }
        },
        created(){
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
                request.get('/api/restaurant/all',{   //用户已发布的所有有效问题
                    params:{
                        pageSize:this.pageSize,
                        currentPage:this.currentPage,
                    }
                }).then(res=>{
                    this.tableData=res.data.item
                    this.total=res.data.total
                })
            },
            change(e){
                console.log(JSON.stringify(e))
                console.log(e.number)
                sessionStorage.setItem("number1",e.number.toString())
                this.$router.push('/collectP')
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

<style scoped>

</style>
