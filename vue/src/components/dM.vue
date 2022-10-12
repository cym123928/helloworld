<template>
    <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc; display: flex;position: absolute">
        <div style="width: 200px;padding-left: -20px;font-width: bold;color:#F49506">后台管理</div>
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
                <el-menu-item index="/question">
                    <el-icon><document /></el-icon>
                    <span>餐馆管理</span>
                </el-menu-item>
                <el-menu-item index="/doctorP">
                    <el-icon><document /></el-icon>
                    <span>查看订单</span>
                </el-menu-item>
                <el-menu-item index="/checkCommentR">
                    <el-icon><document /></el-icon>
                    <span>查看评价</span>
                </el-menu-item>
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
                    <el-form-item label="手机号" prop="phone">
                        <el-input v-model="user.phone"></el-input>
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
  <span class="el-dropdown-link">
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
        name: "dM",
        data(){
          return{
              dialogVisible:false,
              user:{
               name: sessionStorage.getItem("username"),
               phone: sessionStorage.getItem("phone")
              },
              rules: {
                  name: [
                      {required: true, message: '姓名不能为空', trigger: 'blur'},
                      {min: 2, max: 4, message: '长度在2到4个字符', trigger: 'blur'}
                  ],
                  phoneNumber: [
                      {required: true, message: '请输入手机号', trigger: 'blur'},
                      {pattern: /^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/, message: "请输入合法手机号", trigger: "blur"}
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
            }
        }
    }
</script>

<style scoped>

</style>
