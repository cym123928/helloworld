<template>
  <div class="bc">
  <div class="login">
    <el-form :model="user" status-icon :rules="rules" ref="user" label-width="100px" >
      <el-form-item label="请选择身份:">
        <el-radio v-model="user.identity" label="1" >用户</el-radio>
        <el-radio v-model="user.identity" label="2" >商家</el-radio>
        <el-radio v-model="user.identity" label="3" >外卖员</el-radio>
      </el-form-item>
        <el-form  v-model="user" status-icon :rules="rules" ref="user" label-width="100px">
            <el-form-item label="用户名：" prop="name">
                <el-input placeholder="请输入用户名" v-model="user.userName" clearable></el-input>
            </el-form-item>
            <el-form-item label="手机号：" prop="phoneNumber">
                <el-input placeholder="请输入手机号" v-model="user.phoneNumber" clearable></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password">
                <el-input type="password" placeholder="请输入密码" v-model="user.password" show-password autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码：" prop="confirmPassword">
                <el-input type="password" placeholder="再输入密码" v-model="user.confirmPassword" show-password autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
      <el-button type="primary" style="background-color:#F49506" @click="$router.push('/')">返回</el-button>
      <el-button type="primary" style="background-color:#F49506" class="el-button" @click="register">注册</el-button>
    </el-form>
</div>
  </div>
</template>
<script>
    import request from "../utils/request";

    const Search = require("@element-plus/icons/lib/Search");
  export default {
    name: 'uu.vue',
    data(){
      var validatePass = (rule, value, callback) =>{
        if (value === ''){
          callback(new Error('请输入密码'));
        }
        else{
          if (this.user.confirmPassword !== ''){
            this.$refs.user.validateField('confirmPassword');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === ''){
          callback(new Error('请再次输入密码'));
        } else if (value !== this.user.password) {
          callback(new Error('两次密码输入不一致！'));
        }
        else{
          callback();
        }
      };
      return{
        user:{
        userName:'',    //输入数据
        phoneNumber:'',
        password:'',
        confirmPassword:'',
        identity:''
      },
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('注册成功!');
          } else {
            console.log('注册失败，请重新注册!!');
            return false;
          }
        });
      },
        register() {
            console.log("进来注册了！")
            // if (this.user.identity === "1"){
            request.get('/api/user/sign', {
                params: {
                    name: this.user.userName,
                    phone: this.user.phoneNumber,
                    password: this.user.password,
                }
            }).then(res => {   //得到后台的反馈结果
                console.log("传过去了！")
                // localStorage.setItem("user",JSON.stringify(this.user));
                this.$router.push('/');
                console.log(res)
            })

            if (this.user.identity === "3") {
                request.get('/api/deliver/add', {
                    params: {
                        phone: this.user.phoneNumber,
                        status: 0
                    }
                }).then(res => {
                    console.log("传过去了!!!！")
                    // sessionStorage.setItem("user",JSON.stringify(this.user));
                    this.$router.push('/');
                    console.log(res)
                })
            }
        }
    }
  }
</script>
<style>
  .el-button{
    background-color: deepskyblue;
  }
  .login{
      width:400px;
      height:500px;
      line-height: 20px;
      overflow: hidden;
      padding-top: 10px;
      margin: 215px 200px 20px 600px;

  }
  .bc{
      background: url("../assets/images/1.jpg") no-repeat;
      width:100%;
      height:100%;
      position:fixed;
      background-size:100% 100%;
  }

</style>
