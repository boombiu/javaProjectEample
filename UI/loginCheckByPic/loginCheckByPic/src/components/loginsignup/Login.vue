<template>
    <div class="login">
      <el-form ref="loginForm" :model="loginForm"  class="login-form">
        <h3 class="title">后台管理系统</h3>
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            type="text"
            auto-complete="off"
            placeholder="账号"
          >
            <!-- <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" /> -->
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            auto-complete="off"
            placeholder="密码"
            @keyup.enter.native="handleLogin"
          >
            <!-- <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" /> -->
          </el-input>
        </el-form-item>
        <el-form-item prop="code" v-if="captchaEnabled">
          <el-input
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="验证码"
            style="width: 63%"
            @keyup.enter.native="handleLogin"
          >
            <!-- <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" /> -->
          </el-input>
          <div class="login-code">
            <img :src="codeUrl" @click="getCode" class="login-code-img"/>
          </div>
          <div class="code-button">
            <button  @click="getCode">获取验证码</button>
          </div>
        </el-form-item>

        <el-form-item style="width:100%;">
          <el-button
            :loading="loading"
            size="medium"
            type="primary"
            style="width:100%;"
            @click.native.prevent="handleLogin"
          >
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
          <div style="float: right;" v-if="register">
            <router-link  :to="'/register'">立即注册</router-link>
          </div>
        </el-form-item>
      </el-form>
      </div>

    
  </template>

<script>
import request from '@/utils/request'
export default {
    name:"Login",
    data(){
        return {
            loginForm: {
                username: "admin",
                password: "admin123",
                code: "",
                uuid: ""
            },
            codeUrl: "",
            captchaEnabled: true,
            loading: false,
            register: true
        }
    },
    created() {
        this.getCode();  

    },
    methods:{
        getCode(){
            return request({
                url: '/captchaImage',
                headers: {
                isToken: false
                },
                method: 'get',
                timeout: 20000
            }).then(res=>{
              console.log(res)
                this.codeUrl = "data:image/gif;base64," + res.data.data.image;
                // this.codeUrl = res.data;
                this.loginForm.uuid = res.uuid;
            })
            
        }
    }
}
</script>
<style rel="stylesheet/scss" lang="css">
.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
}
</style>