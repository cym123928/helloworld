<template>
  <div style="margin: 70px 20px 20px;">
    <el-input  v-model="search" style="width: 30%;" clearable placeholder="请输入关键字" />
    <el-button type="primary" @click="search1" style="margin-left: 5px;background-color:#F49506">查询</el-button>
      <el-button type="primary" @click="dialogVisible=true" style="margin-left: 90px;background-color:#F49506">加入餐厅</el-button>
  </div>
    <div style="margin: 30px 250px">
        <el-dialog title="请填写餐厅信息" v-model="dialogVisible" width="30%">

            <div>{{time}}</div>
            <el-form :model="form" :rules="rules" ref="ruleForm" label-width="120px" action="post">
                <el-form-item label="餐厅名称：" prop="name">
                    <el-input v-model="form.name" style="width: 100%" type="textarea" :rows="1"></el-input>
                </el-form-item>
                <el-form-item label="  地址：" prop="city">
                    <el-input v-model="form.city" style="width: 100%" type="textarea" :rows="1"></el-input>
                </el-form-item>
                <el-form-item label="规模大小：" prop="scale">
                    <el-input v-model="form.scale" style="width: 100%" type="textarea" :rows="1"></el-input>
                </el-form-item>
            </el-form>
            <span style="margin: 0 80px">
    <el-button type="primary" style="background-color:#F49506" @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" style="background-color:#F49506" @click="save">添 加</el-button>
  </span>
        </el-dialog>
    </div>

  <div style="padding:10px;margin-left: 120px">
    <!--stripe是斑马纹,sortable是排序-->
    <el-table :data="tableData" stripe border style="width: 100%">
        <!--<el-table-column prop="number" label="餐厅号" sortable width="200">
           <template #default="scope">
                {{scope.row.number}}
            </template>
        </el-table-column>-->
        <el-table-column prop="name" label="餐馆名称"  width="225"/>
      <el-table-column prop="city" label="地址" width="375" />
      <el-table-column prop="scale" label="规模大小" width="150"  />
      <el-table-column  label="操作" width="200">
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
    <!--</el-scrollbar>-->
</template>
<script>
// @ is an alias to /src
import request from "../utils/request";
import moment from 'moment';
const Search = require("@element-plus/icons/lib/Search");
export default {
  data() {

    return {
      scrollHeight:'0px',
      search:'',
      currentPage:1,
      pageSize:10,
      total:20,
      tableData: [],
        time: moment().format("YYYY-MM-DD HH:mm:ss"),   //获取当前时间
        form:{
          number:'',
          name:'',
          city:'',
          scale:''
        },
        dialogVisible: false,
        //count:0,   //点击量

    }
  },
    created() {
    this.load();
  },
  methods:{
    load(){
      request.get('/api/restaurant/collect',{   //拥有的所有餐厅
        params:{
          phone:sessionStorage.getItem("phone"),
          pageSize:this.pageSize,
          currentPage:this.currentPage,
        }
      }).then(res=>{
        this.tableData=res.data.item,
        this.total=res.data.total
      })
    },

      change(e){
        console.log(JSON.stringify(e))
          console.log(e.number)
          sessionStorage.setItem("number",e.number.toString())
          this.$router.push('/rDetail')
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



    handleSizeChange(pageSize)
    {
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(currentPage){
      this.currentPage=currentPage;
      this.load();
    },
      save(){
        var na = this.form.name.toString();
        var ci = this.form.city.toString();
        var sc = this.form.scale.toString();
        if (na.length === 0){
            alert("餐厅名称不能为空！");
            return;
        }
        request.get('/api/restaurant/add',{
            params:{
                name: na,
                boss:sessionStorage.getItem("phone"),
                city:ci,
                scale:sc
            }
        }).then(res=>{
            this.load()
            this.dialogVisible = false;   //点击添加后对话框消失
            console.log(res.data)
        })
      }
  }
}
</script>
<style>
</style>
