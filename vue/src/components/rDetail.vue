<template>
   <!-- <el-page-header @back="goBack" content="商家详情"
                    style="margin-top: 30px;margin-left: 60px"
    >
    </el-page-header>-->
    <el-form  label-position="left">
        <el-form-item style="margin-left: -50px">
        <el-input  v-model="search" style="width: 400px;margin-left: -25px;height:400px;margin-top: 60px" clearable placeholder="请输入关键字" />
        <el-button type="primary" @click="search1" style="margin-left: 5px;margin-top: 0px;background-color:#F49506">查询</el-button>
            <el-button type="primary" @click="dialogVisible=true" style="margin-left: 5px;margin-top: 0px;background-color:#F49506">添加菜品</el-button>
        </el-form-item>
        <div style="margin: 30px 250px">
            <el-dialog title="请填写添加菜品信息" v-model="dialogVisible" width="30%">
                <el-form :model="food" :rules="rules" ref="ruleForm" label-width="120px" action="post">
                    <el-form-item label="菜品名称" prop="name">
                        <el-input v-model="food.name" style="width: 100%" type="textarea" :rows="1"></el-input>
                    </el-form-item>
                    <el-form-item label="菜品价格" prop="price">
                        <el-input v-model="food.price" style="width: 100%" type="textarea" :rows="1"></el-input>
                    </el-form-item>
                </el-form>
                <span style="margin: 0 80px">
    <el-button type="primary" style="background-color:#F49506" @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" style="background-color:#F49506" @click="saveFood">确 定</el-button>
  </span>
            </el-dialog>
        </div>

    </el-form>
    <el-table
            :data="tableData"
            style="width: 1050px;margin-left: 250px;margin-top: -25%"
    >
        <el-table-column
                fixed
                prop="name"
                label="菜品名称"
                width="300"
        >
        </el-table-column>
        <el-table-column
                fixed
                label="菜品价格"
                prop="price"
                width="250"
        >
        </el-table-column>
        <el-table-column
                fixed
                label="操作"
                width="500"
        >
            <template #default="scope">
               <!-- <el-button @click="edit1(scope.row,scope)">编辑</el-button>
                <el-button @click="save1(scope.row)">保存</el-button>-->
                <el-button @click="delete1(scope.row)" type="danger">删除</el-button>

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
</template>
<script>
    import {Star} from "@element-plus/icons";
    import {ChatLineRound} from "@element-plus/icons";
    import {Edit} from "@element-plus/icons";
    import {Delete} from "@element-plus/icons";
    import request from "@/utils/request";

    export default{
        components:{
            Star,
            ChatLineRound,
            Edit,
            Delete,
        },
        data(){
            return {
                dialogVisible: false,
                tableData: [],    //详情总页的信息
                tableData1: [],   //我的回答里面的信息
                currentPage: 1,
                total:20,
                pageSize:10,
                form:{
                    name:'',
                    price:''
                },
                food:{
                   name:'',
                   price:''
                },
                search:''
            }
        },
        created(){
          this.load();
        },
        methods: {
            load(){
              request.get('/api/food/findByRName',{
                  params:{
                  name: parseInt(sessionStorage.getItem("number")),
                  pageSize: this.pageSize,
                  currentPage: this.currentPage
                  }
              }).then(res=>{
                  this.tableData = res.data.item
                  this.total = res.data.total
              })
            },
            search1(){
              request.get('/api/food/find',{
                  params:{
                      name:this.search,
                      number:parseInt(sessionStorage.getItem("number")),
                      pageSize:this.pageSize,
                      currentPage: this.currentPage
                  }
              }).then(res=>{
                  this.tableData=res.data.item
                  this.total=res.data.total
                  console.log("执行搜索！！1！")
              })
            },
          /*  edit1(row,index){
                row.iseditor = true;
            },
            save1(row){
              row.iseditor = false;
              request.get('/api/food/update',{
                  params:{
                      name: row.name,
                      number: row.number,
                      price: row.price
                  }
              }).then(res=>{
                  console.log("更新成功！")
              })
            },*/
            delete1(e){
                console.log("调用删除方法")
                request.get('/api/food/delete',{
                    params:{
                        number:e.number,
                        name:e.name
                    }
                }).then(res=>{
                    console.log("删除食物",e.name)
                    this.load()
                })
            },
            goBack() {
                this.$router.back();
            },
            saveFood(){
                request.get('/api/food/add',{
                    params: {
                        name: this.food.name,
                        price: Number(this.food.price),
                        number: parseInt(sessionStorage.getItem("number"))
                    }
                }).then(res=>{
                        console.log("添加食物")
                         this.load()
                         this.dialogVisible=false;
                })
            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.load()

            },
            handleCurrentChange(currentPage) {
                this.currentPage = currentPage;
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
