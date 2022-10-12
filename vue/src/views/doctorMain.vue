<template>
  <el-row class="tac">
    <el-col>
      <el-container style="height: 650px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-active="$router.path"
                   router
                   @open="handleOpen"
                   @close="handleClose"
          >
            <el-menu-item index="recommendForDoctor">
              <template slot="title"><i class="el-icon-menu"></i>精选推荐</template>
            </el-menu-item>
            <el-menu-item index="doctorInformation">
              <template slot="title"><i class="el-icon-menu"></i>个人信息</template>
            </el-menu-item>
            <el-menu-item index="">
              <template slot="title"><i class="el-icon-menu"></i>问题解答</template>
            </el-menu-item>
            <el-menu-item index="">
              <template slot="title"><i class="el-icon-menu"></i>讲座管理</template>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: 'doctorMain',
    methods:{
      handleOpen(key,keyPath){
        console.log(key,keyPath);
      },
      handleClose(key,keyPath){
        console.log(key,keyPath);
      },
      goto(path){
        this.$router.replace(path);
      }
    }
  }
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  .el-aside {
    color: #333;
  }
</style>
