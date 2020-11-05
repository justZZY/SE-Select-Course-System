<template>
  <div>
    <!--flex弹性盒子模型，justify-content：主抽 -->
    <div style="display: flex;justify-content: center;margin-top: 150px">
      <el-card style="width: 400px">
        <div slot="header" class="clearfix">
          <span>Login</span>
        </div>
        <el-form :label-position="position" label-width="80px" :model="user">
          <el-form-item label="School ID">
            <el-input placeholder="Please Input Your ID" v-model="user.id"></el-input>
          </el-form-item>
          <el-form-item label="Password">
            <el-input placeholder="Please Input Your Password" v-model="user.pwd" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">Login</el-button>
            <el-button @click="onClear">Clear</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'

  export default {
    name: 'login',
    //单页面中不支持前面的data:{}方式
    data() {
      return {
        position: 'left',
        user: {
          id: '10001',
          pwd: '123456',
          //为了登录方便，可以直接在这里写好用户名和密码的值
        }
      }
    },
    methods: {
      onSubmit() {
        axios.post('http://127.0.0.1:8086/log/login', this.user).then(res => {
          console.log(res);
          this.$message(res.data.status);
          if (res.data.status === 'success') {
            this.$router.push({name: 'main_page', params: {
              userId: this.user.id,
              name: res.data.name
            }});
          }
        }).catch(err => {
          console.log(err)
        })
      },
      onClear() {
        this.user.id = '';
        this.user.pwd = ''
      }
    }
  }
</script>

