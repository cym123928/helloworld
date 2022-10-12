<template>
    <el-form label-width="100px" size="medium" :model="patient" ref="patient" :rules="rules"
    style="margin-top: 50px"
    >
        <el-form-item label="姓名" prop="name">
            <el-input style="width: 300px" v-model="patient.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
            <el-input v-model.number="patient.age"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNumber">
            <el-input v-model="patient.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="邮箱地址" prop="emailAddress">
            <el-input v-model="patient.emailAddress">
                <template slot="append">.com</template>
            </el-input>
        </el-form-item>
        <el-button>保存</el-button>
        <el-button>提交</el-button>
    </el-form>
</template>
<script>
    export default {
        name: 'personalInformation',
        data(){
            return{
                patient:{
                    name: '',
                    age: '',
                    phoneNumber: '',
                    emailAddress: ''
                },
                rules: {
                    name: [
                        {required: true, message: '姓名不能为空', trigger: 'blur'},
                        {min: 2, max: 4, message: '长度在2到4个字符', trigger: 'blur'}
                    ],
                    age: [
                        {required: true, message: '年龄不能为空',trigger: 'blur'},
                        {type: 'number', message: '年龄不是数字值', trigger: 'blur'}
                    ],
                    phoneNumber: [
                        { required: true, message: '请输入手机号', trigger: 'blur'},
                        { pattern:/^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/, message: "请输入合法手机号", trigger: "blur" }
                    ],
                    emailAddress: [
                        { required: true, message: "请输入邮箱", trigger: "blur" },
                        { pattern:/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g, message: "请输入正确的邮箱", trigger: "blur"}
                    ]
                }
            }
        }
    }
</script>

<style scoped>
    .el-form-item{
        width: 400px;
        margin-left: 33%;
    }
</style>
