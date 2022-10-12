<template>
  <div class="bc">
  <div class="login">
    <el-form :model="User" status-icon :rules="rules" ref="User" label-width="100px">
      <el-form-item label="请选择身份:">
    <el-radio v-model="User.identity" label="1" >用户</el-radio>
    <el-radio v-model="User.identity" label="2" >商家</el-radio>
    <el-radio v-model="User.identity" label="3" >外卖员</el-radio>
      </el-form-item>
      <el-form-item label="用户名：" prop="username">
    <el-input placeholder="请输入用户名" v-model="User.username" clearable>
    </el-input>
      </el-form-item>
      <el-form-item label="手机号：" prop="phoneNumber">
    <el-input placeholder="请输入手机号" v-model="User.phoneNumber" clearable>
    </el-input>
      </el-form-item>
      <el-form-item label="密码：" prop="password">
    <el-input placeholder="请输入密码" v-model="User.password" show-password></el-input>
      </el-form-item>
  <el-button class="el-button" type="primary" style="background-color:#F49506" @click="login">登录</el-button>
  <el-button type="primary" style="background-color:#F49506" @click="$router.push('/SignIn')">注册</el-button>
    </el-form>
  </div>
  </div>
</template>
<script>

  import request from "@/utils/request";

  export default {
      name: 'Login.vue',
      data() {
          return {
              User: {},
          }
      },
      methods: {
          login() {
              if (this.User.identity === "1"){    //字符串  用户
              request.get('/api/user/login',{
                  params : {
                    name : this.User.username,
                    phone: this.User.phoneNumber,
                    password: this.User.password,
                  }
                  }
              ).then(res => {   //得到后台的反馈结果
                  alert(res.data.message)
                  if ((res.data.code !==2) && (res.data.code !==3)){
                      sessionStorage.setItem("username1",this.User.username);
                      sessionStorage.setItem("phone1",this.User.phoneNumber);
                      this.$router.push('/pM')
                  }
               //  var u = sessionStorage.getItem("user");
                //  u = JSON.parse(u);
                //  console.log(u.username)
                  console.log(res)
               //   console.log(u)
              })
          }
              else if (this.User.identity === "2"){   //商家
                  console.log("dianhua   ===",this.User.phoneNumber);
                  request.get('/api/user/login',{
                      params:{
                          name: this.User.username,
                          phone: this.User.phoneNumber,
                          password: this.User.password,
                      }
                  }).then(res=>{
                      alert(res.data.message)
                      if (res.data.code === 0){
                          sessionStorage.setItem("username",this.User.username);   //存储整个对象
                          sessionStorage.setItem("phone",this.User.phoneNumber);
                          console.log(this.User.phoneNumber);
                          this.$router.push('/dM')
                      }
                      console.log(res)
                  })
              }

              else if (this.User.identity === "3"){   //外卖员
                  request.get('/api/user/login',{
                      params:{
                          name: this.User.username,
                          phone: this.User.phoneNumber,
                          password: this.User.password,
                      }
                  }).then(res=>{
                      alert(res.data.message)
                      if (res.data.code === 0){
                          sessionStorage.setItem("username2",this.User.username);   //存储整个对象
                          sessionStorage.setItem("phone2",this.User.phoneNumber);
                          this.$router.push('/sellerM')
                      }
                      console.log(res)
                  })
              }
          }
      }
  }
</script>
<style>
.login{
  width:400px;
  height:500px;
  line-height: 20px;
    overflow: hidden;
  padding-top: 10px;
    margin: 215px 200px 20px 600px;
}
.el-button{
 background-color: deepskyblue;
}
.bc{
    background: url("../assets/images/1.jpg") no-repeat;
    width:100%;
    height:100%;
    position:fixed;
    background-size:100% 100%;
}
</style>
