<template>
    <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc; display: flex;position: absolute">
        <div style="width: 200px;padding-left: 30px;font-width: bold;color:#F49506">后台管理</div>
        <div>
            <el-menu
                    style="width: 200px;min-height:calc(100vh - 50px);margin-top: 50px;margin-left: -200px"
                    :default-active="$router.path"
                    router
                    @open="handleOpen"
                    @close="handleClose"
                    class="el-menu-vertical-demo"
                    background-color="#606266"
            >

                <el-menu-item index="/postedP">
                    <el-icon><location /></el-icon>
                    <span>开始点餐</span>
                </el-menu-item>
                <el-sub-menu index="1">
                    <template #title>
                        <el-icon><document/></el-icon>
                        <span>我的订单</span>
                    </template>
                    <el-menu-item index="/myOrdered">历史订单</el-menu-item>
                    <el-menu-item index="/myWillOrder">待收订单</el-menu-item>
                </el-sub-menu>

                <el-sub-menu index="2">
                    <template #title>
                        <el-icon><document/></el-icon>
                        <span>我的评价</span>
                    </template>
                    <el-menu-item index="/waitComment">评价订单</el-menu-item>
                    <el-menu-item index="/checkComment">查看评价</el-menu-item>
                </el-sub-menu>


            </el-menu>
        </div>
        <div style="flex: 1;">
            <router-view></router-view>
        </div>
        <div style="margin: 0px 50px">
            <el-dialog title="个人信息" v-model="dialogVisible" width="30%">
                <el-form size="medium" :model="user" ref="user" :rules="rules"
                         style="margin-top: 15px"
                >
                    <el-form-item label="姓名" prop="name">
                        <el-input v-model="user.name"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phoneNumber">
                        <el-input v-model="user.phoneNumber"></el-input>
                    </el-form-item>
                </el-form>
                <span style="margin: 0 80px">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="">确 定</el-button>
  </span>
            </el-dialog>
        </div>
        <div style="width: 100px;">
            <el-dropdown>
  <span class="el-dropdown-link" >
         {{user.name}}
     <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
                <template #dropdown>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click="dialogVisible=true">个人信息</el-dropdown-item>
                        <el-dropdown-item>退出系统</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>
<script>
    export default {
        name: "pM",
        data(){
          return{
              user:{
                name:  sessionStorage.getItem("username1") ,   //得到sessionStorage的值
                phoneNumber: sessionStorage.getItem("phone1")
              },
              dialogVisible: false,
              rules: {
                  name: [
                      {required: true, message: '姓名不能为空', trigger: 'blur'},
                      {min: 2, max: 4, message: '长度在2到4个字符', trigger: 'blur'}
                  ],
                  phoneNumber: [
                      { required: true, message: '请输入手机号', trigger: 'blur'},
                      { pattern:/^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/, message: "请输入合法手机号", trigger: "blur" }
                  ],
              }
          }
        },
        methods:{
            handleOpen(key,keyPath){
                console.log(key,keyPath);
            },
            handleClose(key,keyPath){
                console.log(key,keyPath);
            },
            goto(path){
                this.$router.replace(path);
            },

        }
    }
</script>

<style scoped>
    .el-form-item{
        width: 400px;
    }
</style>
