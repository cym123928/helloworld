<template>
  <el-form
    ref="doctor"
    :rules="rules"
    label-position="right"
    status-icon
    label-width="100px"
    size="medium"
    :model="doctor"
    >
    <el-form-item label="姓名" prop="name">
      <el-input v-model="doctor.name"></el-input>
    </el-form-item>
    <el-form-item label="年龄" prop="age">
      <el-input v-model.number="doctor.age"></el-input>
    </el-form-item>
    <el-form-item label="所属科室" prop="major">
        <el-input v-model="doctor.major"></el-input>
    </el-form-item>
      <el-form-item label="从业年限" prop="year">
          <el-input v-model="doctor.year"></el-input>
      </el-form-item>
    <el-form-item label="职位" prop="title">
      <el-select v-model="doctor.title" placeholder="请选择">
        <el-option
        v-for="item in options2"
        :key="item.value"
        :label="item.label"
        :value="item.value"
        >
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="手机号" prop="phoneNumber">
      <el-input v-model="doctor.phoneNumber"></el-input>
    </el-form-item>
    <el-form-item label="邮箱地址" prop="emailAddress">
      <el-input v-model="doctor.emailAddress">
        <template slot="append">.com</template>
      </el-input>
    </el-form-item>
    <el-button>保存</el-button>
    <el-button>提交</el-button>
  </el-form>
</template>

<script>

  export default {
    name: 'doctorInformation',
    data () {
        var d = JSON.parse(sessionStorage.getItem("doctor"));
      return {
          options2: [{
              value: '主任医师',
              label: '主任医师'
          },
              {
                  value: '副主任医师',
                  label: '副主任医师'
              },
              {
                  value: '主治医师',
                  label: '主治医师'
              },
              {
                  value: '住院医师',
                  label: '住院医师'
              }
          ],
          doctor: {
              name: '',
              age: '',
              major: '',
              title: d.title,
              phoneNumber: d.phoneNumber,
              emailAddress: '',
              year: d.year
          },
          rules: {
              name: [
                  {required: true, message: '姓名不能为空', trigger: 'blur'},
                  {min: 2, max: 4, message: '长度在2到4个字符', trigger: 'blur'}
              ],
              age: [
                  {required: true, message: '年龄不能为空', trigger: 'blur'},
                  {type: 'number', message: '年龄不是数字值', trigger: 'blur'}
              ],
              major: [
                  {type: 'array', required: true, message: '请选择所属科室', trigger: 'change'}
              ],
              title: [
                  {required: true, message: '请选择职位', trigger: 'blur'}
              ],
              phoneNumber: [
                  {required: true, message: '请输入手机号', trigger: 'blur'},
                  {pattern: /^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/, message: "请输入合法手机号", trigger: "blur"}
              ],
              emailAddress: [
                  {required: true, message: "请输入邮箱", trigger: "blur"},
                  {
                      pattern: /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g,
                      message: "请输入正确的邮箱",
                      trigger: "blur"
                  }
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
