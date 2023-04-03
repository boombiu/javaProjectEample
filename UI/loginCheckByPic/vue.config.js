const name = process.env.VUE_APP_TITLE || '验证码登录' // 网页标题
module.exports = {
    configureWebpack: {
        name: name,
        resolve: {
        alias: {
            '@': resolve('src')
        }
        }
    }
}