<template>
    <div style="margin: 50px 10px 10px;">
        <el-input  v-model="search" style="width: 30%" clearable placeholder="请输入关键字" />
        <el-button type="primary"  @click="search1" style="margin-left: 5px;background-color:#F49506">查询</el-button>
    </div>
    <div  style="padding:10px;margin-left: 120px">
        <el-table :data="tableData" stripe border style="width: 100%">
           <!-- <el-table-column prop="number" label="餐厅号"  width="100">
                <template #default="scope">
                    <span>{{scope.row.number}}</span>
                </template>
            </el-table-column>-->
            <el-table-column prop="name" label="餐厅名称" width="200">
                <template #default="scope">
                    <span>{{scope.row.name}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="city" label="地址" width="500">
                <template #default="scope">
                    <span>{{scope.row.city}}</span>
                </template>
            </el-table-column>
            <el-table-column   width="100">
                <template #default="scope">
                    <el-button type="primary" style="background-color:#F49506" size="mini" @click="change(scope.row)">详情</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin: 30px 250px">
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
    // @ is an alias to /src
    import request from "../utils/request";
    import moment from 'moment'

    import Vue from "vue";
    const Search = require("@element-plus/icons/lib/Search");

    export default {
        data() {
            return {
                tableData:[],
                pageSize:10,
                currentPage:1,
                username : sessionStorage.getItem("username1"),
                phone:sessionStorage.getItem("phone1"),
                search:'',
                total:20,
            }
        },
        created() {
            this.load();
        },
        methods: {
            load() {
                request.get('/api/restaurant/collect',{   //该boss拥有的所有餐厅
                    params:{
                        pageSize:this.pageSize,
                        currentPage:this.currentPage,
                        phone:sessionStorage.getItem("phone")
                    }
                }).then(res=>{
                    this.tableData=res.data.item
                    this.total=res.data.total
                })
            },
            change(e){
            sessionStorage.setItem("numberOR",e.number.toString())
            this.$router.push('/details')
            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.load()

            },
            handleCurrentChange(currentPage) {
                this.currentPage = currentPage;
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
<style>
    .image {
        width: 100%;
        display: block;
    }
    .time {
        font-size: 13px;
        color: #999;
        margin-top: 5px;
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
        width: 30%;
        margin-right: 30px;
        margin-bottom: 30px;
    }
</style>

