<template>
    <div class="login">
      <el-form ref="loginForm" :model="loginForm"  class="login-form">
        <h3 class="title">后台管理系统</h3>
        <el-form-item prop="userName">
          <el-input
            v-model="loginForm.userName"
            type="text"
            auto-complete="off"
            placeholder="账号"
          >
            <!-- <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" /> -->
          </el-input>
        </el-form-item>
        <el-form-item prop="userPassword">
          <el-input
            v-model="loginForm.userPassword"
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
        <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
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
import Cookies from "js-cookie";
export default {
    name:"Login",
    data(){
        return {
            loginForm: {
              userName: "zjw",
              userPassword: "123",
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
      handleLogin() {
      this.$refs.loginForm.validate(valid => {
        console.log("cookie")
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("userName", this.loginForm.userName, { expires: 30 });
            Cookies.set("userPassword", this.loginForm.userPassword, { expires: 30 });
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
            console.log(Cookies.get("userName"))
          } else {
            Cookies.remove("userName");
            Cookies.remove("userPassword");
            Cookies.remove('rememberMe');
          }
          console.log(this.loginForm)
          const loginData = {
            userName: "zjw",
            userPassword: "123",
            code: this.loginForm.code,
            uuid: this.loginForm.uuid
          }
          console.log("this.loginForm")
          console.log(this.loginForm)
          console.log("loginData")
          console.log(loginData)
          return request({
                url: '/login',
                data: loginData,
                headers: {
                isToken: false
                },
                method: 'post',
                timeout: 20000
            }).then(res=>{
              console.log(res)
              this.$router.push({ path: this.redirect || "/" }).catch(()=>{});
            })
        }
      });
    },
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
              console.log(res)
                this.codeUrl = "data:image/gif;base64," + res.data.image;
                this.loginForm.uuid = res.data.uuid;
            })
            
        },
        login(){
          return request({
                url: '/login',
                headers: {
                isToken: false
                },
                data: this.loginForm,
                method: 'post',
                timeout: 20000
            }).then(() => {
            this.$router.push({ path: this.redirect || "/" }).catch(()=>{});
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