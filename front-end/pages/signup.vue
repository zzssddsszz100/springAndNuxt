<template xmlns="">
  <div>
    <div class="page-title-area">
      <div class="container">
        <ul>
          <li>회원가입</li>
        </ul>
      </div>
    </div>

    <section class="signup-area ptb-60">
      <div class="container">
        <div class="signup-content">
          <div class="section-title">
            <h2>회원가입</h2>
          </div>

          <form @submit.prevent="submitForm" class="signup-form" novalidate>
            <div class="form-group">
              <label>아이디</label>
              <input type="text" class="form-control" placeholder="아이디를 입력해 주세요" v-model="form.username" id="username"
                     name="username">
              <div class="field-error" v-if="$v.form.username.$dirty">
                <div class="error" v-if="!$v.form.username.required">
                  필수로 입력하셔야 합니다.
                </div>
                <div class="error" v-if="!$v.form.username.alphaNum">
                  특수문자가 포함되지 않은 문자, 숫자만 가능합니다.
                </div>
                <div class="error" v-if="!$v.form.username.minLength">
                  최소한 4자이상 입력하셔야 합니다.
                </div>
                <div class="error" v-if="!$v.form.username.maxLength">
                  최대한 20자 이내로 입력하셔야 합니다.
                </div>
              </div>
            </div>

            <div class="form-group">
              <label>이메일</label>
              <input type="email" class="form-control" placeholder="이메일을 입력해주세요" v-model="form.emailAddress"
                     id="emailAddress" name="emailAddress">
              <div class="field-error" v-if="$v.form.emailAddress.$dirty">
                <div class="error" v-if="!$v.form.emailAddress.required">필수로 입력하셔야 합니다.</div>
                <div class="error" v-if="!$v.form.emailAddress.email">이메일 형식이 올바르지 않습니다.</div>
                <div class="error" v-if="!$v.form.emailAddress.maxLength">
                  최대한 100자 이내로 입력하셔야 합니다.
                </div>
              </div>
            </div>

            <div class="form-group">
              <label>비밀번호</label>
              <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요" v-model="form.password"
                     id="password" name="password">
              <div class="field-error" v-if="$v.form.password.$dirty">
                <div class="error" v-if="!$v.form.password.required">필수로 입력하셔야 합니다.</div>
                <div class="error" v-if="!$v.form.password.minLength">최소한 6자 이상 입력하셔야 합니다.</div>
                <div class="error" v-if="!$v.form.password.maxLength">
                  최대한 30자 이내로 입력하셔야 합니다.
                </div>
              </div>

            </div>


            <button type="submit" class="btn btn-primary">Signup</button>
          </form>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import {validationMixin} from 'vuelidate'
import {required, email, minLength, maxLength, alphaNum, alpha} from 'vuelidate/lib/validators';
import registrationService from "~/services/registration";

export default {
  mixins: [validationMixin],

  data() {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  validations: {
    form: {
      username: {
        required,
        minLength: minLength(4),
        maxLength: maxLength(20),
        alphaNum
      },
      emailAddress: {
        required,
        email,
        maxLength: maxLength(100),
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      }
    }
  },
  methods: {
    submitForm() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        return
      }
      registrationService.register(this.form).then(() => {
        this.$router.push({name: 'login'})
      }).catch((error) => {
        this.errorMessage = '회원가입에 실패했습니다.' + error.message;
      })
    }
  }
}
</script>
