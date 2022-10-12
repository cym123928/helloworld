<template>
    <el-row class="tac">
        <el-col>
            <el-container style="height: 650px; border: 1px solid #eee">
                <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu :default-active="$router.path"
             router
             @open="handleOpen"
             @close="handleClose"
             style="width: 200px;min-height:calc(100vh - 50px)"
    >
        <el-menu-item index="/recommendForPatient">
            <template slot="title"><i class="el-icon-menu"></i>精选推荐</template>
        </el-menu-item>
        <el-menu-item index="/personalInformation">
            <template slot="title"><i class="el-icon-menu"></i>个人信息</template>
        </el-menu-item>
        <el-sub-menu>
            <el-menu-item index="/questionForPatient">
                <template slot="title"><i class="el-icon-menu"></i>问题查询</template>
            </el-menu-item>
        </el-sub-menu>
        <el-menu-item index="/speechPatient">
            <template slot="title"><i class="el-icon-menu"></i>讲座预约</template>
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
  name: "patientMain",
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
