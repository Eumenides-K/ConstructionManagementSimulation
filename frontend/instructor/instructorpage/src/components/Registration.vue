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
      <el-container direction="vertical" class="registration-container">
        <div class="registration">
        <el-header>
            <h1 class="header-title">Sign up to the portal</h1>
        </el-header>
      <el-main>
 
        <el-form :model="form" :rules="rules" ref="form" class="registration-form">
            <el-form-item label="Email" prop="email">
                <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item label="Password" prop="password">
                <el-input type="password" v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="Confirm Password" prop="confirmPassword">
                <el-input type="password" v-model="form.confirmPassword"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" :disabled="!formIsValid" @click="submitForm">Submit</el-button>
            </el-form-item>
        </el-form>

      </el-main>
    </div>
    </el-container>
</template>

<script>
import { affixProps } from 'element-plus'

export default {
  data() {
    return {
      form: {
        email: '',
        password: '',
        confirmPassword: ''
      },
      rules: {
        email: [
          { required: true, message: 'Email is required', trigger: 'blur' },
          { type: 'email', message: 'Please enter a valid email address', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'Password is required', trigger: 'blur' },
          { validator: this.validatePassword, trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: 'Password confirmation is required', trigger: 'blur' },
          { validator: this.validateConfirmPassword, trigger: 'blur' }
        ]
      },
      formIsValid: false
    }
  },
  methods: {
    validatePassword(rule, value, callback) {
      if (value === '') {
        callback(new Error('Please enter the password'))
      } else {
        if (this.form.confirmPassword !== '') {
          this.$refs.form.validateField('confirmPassword')
        }
        callback()
      }
    },
    validateConfirmPassword(rule, value, callback) {
      if (value !== this.form.password) {
        callback(new Error('The two passwords do not match'))
      } else {
        callback()
      }
    },
    submitForm() {
      // this.$refs.form.validate((valid) => {
      //   if (valid) {
      //     const data = {
      //       email: this.form.email,
      //       password: CryptoJS.SHA256(this.form.password).toString()
      //     };
      //     axios.post('/instructors', data)
      //     .then(response => {
      //       alert("You have successfully signed up!")
      //       this.$router.push('/');
      //     })
      //     .catch(error => {
      //       alert(error)
      //     });

      //   } else {
      //     console.log('form has errors')
      //   }
      // })
      const data = {
        email: this.form.email,
        password: this.form.password
      };
      this.$http.post('/instructors', data)
      .then(response => {
        alert("You have successfully signed up!")
        this.$router.push('/');
      })
      .catch(error => {
        alert(error)
      });
    }
  },
  watch: {
    form: {
      deep: true,
      handler() {
        this.$refs.form.validate((valid) => {
          this.formIsValid = valid
        })
      }
    }
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

.header-title {
    text-align: center;
    color: #333;
    font-family: 'Roboto', sans-serif;
}

.registration-container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 10%;
}

.registration {
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

</style>

