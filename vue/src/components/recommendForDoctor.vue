<template>
    <div>
        <el-carousel :interval="4000" type="card" height="200px">
            <el-carousel-item v-for="item in 6" :key="item">
                <h3 class="medium">{{ item }}</h3>
            </el-carousel-item>
        </el-carousel>
        <span></span>
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="有价值的" name="first">
                <div style="padding: 10px">
                    <el-table :data="tableData" stripe  style="width: 100%">
                        <el-table-column prop="content" label="内容"  width="700"/>
                        <el-table-column prop="name" label="发布人" width="100" />
                        <el-table-column prop="time" label="时间" sortable width="200"  />
                        <el-table-column width="100">
                            <template #default>
                                <el-popconfirm title="你确定要删除吗？">
                                    <template #reference>
                                        <el-button type="danger" size="mini"  @click="">取消收藏</el-button>
                                    </template>
                                </el-popconfirm>
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
            </el-tab-pane>
            <el-tab-pane label="无价值的" name="second">
                <div style="padding: 10px">
                    <el-table :data="tableData1" stripe style="width: 100%">
                        <el-table-column prop="content" label="内容"  width="700"/>
                        <el-table-column prop="name" label="发布人" width="100" />
                        <el-table-column prop="time" label="时间" sortable width="200"  />
                        <el-table-column width="100">
                            <template #default>
                                <el-popconfirm title="你确定要删除吗？">
                                    <template #reference>
                                        <el-button type="danger" size="mini"  @click="">取消收藏</el-button>
                                    </template>
                                </el-popconfirm>
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
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
    import request from "@/utils/request";

    export default {
        name: 'recommendForPatient',
        data(){
            return {
                activeName: 'first',
                tableData:[],
                tableData1:[],
                search:'',
                currentPage:'',
                pageSize:'',
                total:10,
            }
        },
        methods: {
            handleChange(val){
                console.log(val);
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
            load(){
                request.get('/api/question/findValued',{   //所有有效的问题
                    params:{
                        pageSize:this.pageSize,
                        currentPage:this.currentPage,
                    }
                }).then(res=>{
                    this.tableData1=res.data.item
                    this.total=res.data.total
                })
            },
        }
    }
</script>
<style scoped>
    .el-carousel{
        margin-top: 50px;
    }
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
    }
    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }
    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

    .el-collapse-item{
        width: 400px;
    }
</style>
