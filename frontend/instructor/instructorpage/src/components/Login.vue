<template>
    <vue-particles
        color="#555"
        :particleOpacity="0.7"
        :particlesNumber="150"
        shapeType="circle"
        :particleSize="4"
        linesColor="#555"
        :linesWidth="1"
        :lineLinked="true"
        :lineOpacity="0.4"
        :linesDistance="150"
        :moveSpeed="2"
        :hoverEffect="true"
        hoverMode="grab"
        :clickEffect="false"
        clickMode="push"
        class="particle"
      />
    <el-container direction="vertical" class="login-container">
        <div class="login">
        <el-header>
            <h1 class="header-title">Sign in to the portal</h1>
        </el-header>
      <el-main>
        <el-form class="login-form">
          <el-form-item>
            <el-input 
              v-model="email" 
              placeholder="Please input your email address"
              prefix-icon="User"
            />
          </el-form-item>
          <el-form-item>
            <el-input 
              v-model="password" 
              type="password" 
              placeholder="Please input password" 
              show-password 
              prefix-icon="Key"
            />
          </el-form-item>
          <el-form-item class="login-buttons">
            <el-button type="primary" round @click="signIn">Sign in</el-button>
            <el-button plain @click="signUp">Sign up</el-button>
            <el-link class="forgot-password-link" type="primary" @click="forgetPassword">forget your password?</el-link>
          </el-form-item>
        </el-form>
      </el-main>
    </div>
    </el-container>
</template>

<script>
    import { store } from '@/store.js'

    export default {
        data() {
            return {
                email: '',
                password: '',
                store
            }
        },
        methods: {
          signUp() {
                this.$router.push('/registration')
            },

            forgetPassword() {
              alert('Sorry, this feature is still being developed. Please contact the administrator.')
            },

            async signIn() {  
              try {
                const response = await this.$axios.post('/instructors/login', {email: this.email, password_hash: this.password})
                if (response.status === 200) {
                  this.store.isAuthenticated = true
                  this.store.signedEmail = this.email
                  this.$router.push('/')
                } else {
                  alert('Invalid email or password')
                } 
              } catch (error) {
                if (error.response && error.response.status === 400) {
                  alert('Invalid email or password')
                } else {
                  console.error('An error occurred:', error)
                  alert('An error occurred: ' + error)
                }
              }
            },
        }
    }
</script>
  
<style scoped>
.particle{
    position: absolute;
    top:0;
    width:100%;
    z-index: 1;
}
.login-container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 10%;
}

.header-title {
    text-align: center;
    color: #333;
    font-family: 'Roboto', sans-serif;
}

.login {
    background-color: #ffffff;
    border: 1px solid #848587;
    padding: 20px;
    max-width: 38.2%; 
    width: 100%;
    border-radius: 10px;
    box-shadow: 5px 5px 10px rgba(0,0,0,0.15); 
    position: relative;
    z-index: 2;
}
  
.login-buttons {
    text-align: center;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.forgot-password-link {
    margin-left: auto;
    font-weight: 500;
}
</style>
  