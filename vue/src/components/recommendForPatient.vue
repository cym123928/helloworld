<template>
  <div>
  <el-carousel :interval="4000" type="card" height="200px">
    <el-carousel-item v-for="item in 6" :key="item">
      <h3 class="medium">{{ item }}</h3>
    </el-carousel-item>
  </el-carousel>
    <span></span>
      <div @click="load()">问题热议</div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="近一月热议" name="first">
              <div style="padding: 10px">
                  <el-table :data="tableData" stripe  style="width: 100%">
                      <el-table-column prop="content" label="内容"  width="500"/>
                      <el-table-column prop="name" label="发布人" width="100" />
                      <el-table-column prop="time" label="时间" sortable width="200"  />
                      <el-table-column prop="clicks" label="点击量" sortable width="200">
                          <template #default="scope">
                              {{parseInt(scope.row.count)}}
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
          <el-tab-pane label="近一周热议" name="second">
              <div style="padding: 10px">
                  <el-table :data="tableData1" stripe style="width: 100%">
                      <el-table-column prop="content" label="内容"  width="400">
                          <template #default="scope">
                              {{scope.row.content}}
                          </template>
                      </el-table-column>
                      <el-table-column prop="name" label="发布人" width="100">
                          <template #default="scope">
                              {{scope.row.name}}
                          </template>
                      </el-table-column>
                      <el-table-column prop="time" label="时间" sortable width="200">
                          <template #default="scope">
                              {{scope.row.time}}
                          </template>
                      </el-table-column>
                      <el-table-column prop="clicks" label="点击量" sortable width="200">
                          <template #default="scope">
                              {{parseInt(scope.row.count)}}
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
          type:'week',
          type1:'month'
      }
    },
      created() {
        this.load();
      },
      methods: {
      handleChange(val){
        console.log(val);
      },
      load(){
          request.get('/api/question/top',{   //按周热议
              params:{
                  type: this.type,
                  number:5
              }
          }).then(res=>{
              this.tableData1=res.data
              console.log("hjhjkhk")
           //   this.total=res.data.total
          })
          request.get('/api/question/top',{    //按月热议
              params:{
                  type:this.type1,
                  number:5
              }
          }).then(res=>{
              this.tableData=res.data
              this.total=res.data.total
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
        }
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
