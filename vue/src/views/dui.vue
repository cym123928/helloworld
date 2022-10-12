<template>
    <div>
        <el-button type="primary" @click="add">新增</el-button>
    </div>
    <div style="margin: 30px 300px 30px;">
        <el-input  v-model="search" style="width: 60%;height: 20%" clearable placeholder="请输入关键字" />
        <el-button type="primary" style="margin-left: 5px;">查询</el-button>
    </div>
    <div style="padding: 10px">
        <div id="app" >
            <div class="wapper">
                <div v-for="lec in leclist" class="wapper-content">
                    <div style="width: 30%">
                        <a>
                            <div>
                                <img :src="image01" alt="">
                            </div>
                            <div style="display: flex">
                                <el-col class="time">{{lec.time}}</el-col>
                                <el-col class="topic" style="margin-left: 75px;display: flex">{{lec.topic}}
                                    <div style="margin-left: 4px">{{lec.name}}</div></el-col>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <div style="margin: 30px 250px">
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
            <el-dialog title="提示" v-model="dialogVisible" width="30%">
                <el-form :model="form" :rules="rules" ref="ruleForm" label-width="120px" action="post">
                    <el-form-item label="主题" prop="topic">
                        <el-input v-model="form.topic" style="width: 100%"></el-input>
                    </el-form-item>
                    <el-form-item label="医生姓名" prop="name">
                        <el-input v-model="form.name" style="width: 100%"></el-input>
                    </el-form-item>
                    <el-form-item label="链接" prop="leclink">
                        <el-input v-model="form.leclink" style="width: 100%"></el-input>
                    </el-form-item>
                    <el-form-item label="活动时间" required prop="time">
                        <el-date-picker
                                v-model="form.time"
                                type="datetime"
                                placeholder="选择日期时间">
                        </el-date-picker>
                    </el-form-item>
                </el-form>
                <span style="margin: 0 80px">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary">确 定</el-button>
  </span>
            </el-dialog>
        </div>

    </div>
</template>
<script>
    const Search = require("@element-plus/icons/lib/Search");

    export default {
        data() {
            return {
                dialogVisible: false,
                search:'',
                currentPage:1,
                pageSize:5,
                total:10,
                leclist:[],
                form:{
                    time:'',
                    topic:'',
                    name:'',
                    leclink:''
                }
            }
        },
        created(){
            load()
        },
        methods: {
            load(){

            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.load()

            },
            handleCurrentChange(currentPage) {
                this.currentPage = currentPage;
                this.load()
            },
            add(){
                this.dialogVisible=true;
                this.form={}
            },
        }

    }


</script>
<style>
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
