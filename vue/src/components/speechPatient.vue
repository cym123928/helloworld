<template>
    <div style="margin: 30px 300px 30px;">
        <el-input  v-model="search" style="width: 60%;height: 20%" clearable placeholder="请输入关键字" />
        <el-button type="primary" @click="load" style="margin-left: 5px;">查询</el-button>
        <el-button type="primary" @click="add">添加</el-button>
    </div>
    <div style="padding: 30px">
        <div id="app" >
            <div v-for="(lec,i) in leclist" class="wapper">
                <div style="width:100%;display: flex;justify-content: flex-start;align-items: center;" v-if="i%3==0" >
                    <div class="wapper-content" v-for="(lec, i) in shuzu(leclist,i)">
                        <el-card :body-style="{ padding: '0px' }">
                            <a @click="yl(lec.url,lec.time)">
                                <img :src="image01" alt="" class="image">
                                <div>
                                    <div class="topic" style="display: table">
                                        <div class="time">{{lec.time}}</div>
                                        <div style="display: flex">
                                            <div style="margin-right: 10px;float: right;margin-bottom: 5px">
                                                主题： {{lec.topic}}
                                            </div>
                                            <div style="float: right;margin-bottom: 5px">
                                                发布人：{{lec.name}}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </el-card>
                    </div>
                </div>
            </div>
        </div>

        <div style="margin: 30px 250px">
            <el-pagination
                    v-model:currentPage="currentPage"
                    :page-sizes="[3,6,20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
            >
            </el-pagination>
            <el-dialog title="添加讲座" v-model="dialogVisible" width="30%">
                <tr>
                    <td>
                        讲座主题：
                    </td>
                    <td>
                        <el-input v-model="topic" style="width: 100%;margin-bottom: 8px" placeholder="请输入讲座主题"></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        讲座发布人：
                    </td>
                    <td>

                        <el-input v-model="name" style="width: 100%;margin-bottom: 8px" placeholder="请输入讲座发布人"></el-input>

                    </td>
                </tr>
                <tr>
                    <td>
                        讲座链接：
                    </td>
                    <td>
                        <el-input v-model="leclink" style="width: 100%;margin-bottom: 8px" placeholder="请输入讲座链接"></el-input>

                    </td>
                </tr>
                <tr>
                    <td>
                        讲座时间：
                    </td>
                    <td>

                        <el-date-picker
                                v-model="time"
                                type="datetime"
                                @change="getSTime(time)"
                                style="margin-bottom: 8px"
                                placeholder="选择日期时间">
                        </el-date-picker>
                    </td>
                </tr>
                <span style="margin: 0 80px">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save()">确 定</el-button>
  </span>
            </el-dialog>
        </div>

    </div>
</template>
<script>
    // @ is an alias to /src
    import request from "../utils/request";
    import moment from 'moment'

    import Vue from "vue";
    const Search = require("@element-plus/icons/lib/Search");

    export default {
        data() {
            return {
                dialogVisible: false,
                //image01:require('../assets/images/image04.png'),
                search:'',
                doctor:'李医生',
                currentPage:1,
                pageSize:6,
                total:10,
                leclist:[],
                time:'',
                topic:'',
                name:'',
                leclink:'',

                /*rules:{
                topic:[
                  {required: true, message: "主题不能为空", trigger: 'blur'}
                ]
                }*/
            }
        },
        created() {
            this.load();
        },
        methods: {
            getSTime(val) {
                this.time=moment(val).format("YYYY-MM-DD HH:mm:ss")
                //这个sTime是在data中声明的，也就是v-model绑定的值
            },
            /*check(){
              var s=this.topic;
              var t=s.toString();
              if(t.length==0){
                alert("不能为空！")
                return
              }
            },*/
            shuzu(items,i){
                let arryy = [];
                let oldarr = items;
                for(let y = i; y < oldarr.length; y++) {
                    if (arryy.length < 3) {
                        arryy.push(items[y]);
                    } else {
                        break;
                    }
                }
                return arryy;
            },
            yl(url,time){
                var remindTime=time;
                var str=remindTime.toString();
                var oldTime=new Date(str).getDate();
                if(oldTime>new Date().getDate()){
                    alert("讲座未开始，不能观看");
                    return;
                }else{
                    window.location.href = url;
                }
            },
            load() {
              /*  request.get('/api/lecture/finds', {
                    params: {
                        doctor:this.doctor,
                        search: this.search,
                        pageSize: this.pageSize,
                        currentPage: this.currentPage,
                    }
                }).then(res => {
                    this.leclist = res.data.item;
                    this.total = res.data.total
                })*/
            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.load()

            },
            handleCurrentChange(currentPage) {
                this.currentPage = currentPage;
                this.load();

            },
            add(){
                this.dialogVisible=true;
            },
            save(){
              /*  var s1=this.topic;
                var s2=this.name;
                var s3=this.leclink;
                var s4=this.time;
                var t1=s1.toString();
                var t2=s2.toString();
                var t3=s3.toString();
                var t4=s4.toString();
                if(t1.length==0||t2.length==0||t3.length==0||t4.length==0){
                    alert("不能为空！");
                    return
                }
                if(t3.valueOf("https://")==-1){
                    alert("讲座链接格式错误，请重新填写")
                    return
                }
                request.get('api/lecture/add', {
                    params:{
                        topic:this.topic,
                        name:this.name,
                        leclink:this.leclink,
                        time:this.time
                    }}

                ).then(res=>{
                    console.log(res)
                })*/
            }
        }

    }


</script>
<style>
    .image {
        width: 100%;
        display: block;
    }
    .time {
        font-size: 13px;
        color: #999;
    ; margin-top: 5px;
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
        width: 30%;
        margin-right: 30px;
        margin-bottom: 30px;
    }
</style>

