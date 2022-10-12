<template>
<div style="margin-top: 50px;margin-left: 260px">
    <el-collapse v-model="tableData" v-for="o in tableData" :key="o" @click="load">
        <div style="float: left">{{'问题'+o}}</div>
        <el-collapse-item>
            {{'kkkkkk'+o.content}}
            <el-space :size="size" :space="spacer" style="float: right">
            <el-button type="info" circle style="float: right">详情</el-button>
            <el-button type="success" circle style="float: right">更新</el-button>
                <el-popconfirm title="你确定要删除吗？">
                    <template #reference>
                        <el-button type="danger" circle style="float: right">删除</el-button>
                    </template>
                </el-popconfirm>
            </el-space>
        </el-collapse-item>
    </el-collapse>
</div>
</template>
<script>
    import {Delete} from "@element-plus/icons-vue";
    import {ElDivider} from "element-plus";
    import {h} from "vue";
    import request from "@/utils/request";
    export default {
        name: "rQuestion",
        components:{
            Delete,
        },
        data(){
         return{
             size:10,
             spacer: h(ElDivider, { direction: 'vertical' }),
             tableData:[],
             currentPage: 1,
             pageSize:5,
         }
        },
        created(){
          this.load();
        },
        methods:{
            load(){
                request.get('/api/restaurant/collect', {
                    params:{
                        pageSize:this.pageSize,
                        currentPage:this.currentPage,
                        phone: localStorage.getItem("phone")
                        }
                }).then(res=>{
                    this.tableData = res.data.item;
                })
            }
        }
    }
</script>

<style scoped>

</style>
