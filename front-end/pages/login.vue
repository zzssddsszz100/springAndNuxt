<template>
  <div>
    <div class="page-title-area">
      <div class="container">
        <ul>
          <li>로그인</li>
        </ul>
      </div>
    </div>

    <section class="login-area ptb-60">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-md-12">
            <div class="login-content">
              <div class="section-title">
                <h2>로그인</h2>
              </div>

              <form @submit.prevent="submitForm" class="login-form" novalidate>
                <div class="form-group">
                  <label>이메일 또는 닉네임</label>
                  <input class="form-control" placeholder="이메일 또는 닉네임을 입력해주세요." v-model="form.email" id="email" name="email">
                  <div class="field-error" v-if="$v.form.eamil.$dirty">
                    <div class="error" v-if="!$v.form.email.required">필수로 입력하셔야 합니다.</div>
                  </div>
                </div>

                <div class="form-group">
                  <label>비밀번호</label>
                  <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요." v-model="form.password" id="password" name="password">
                  <div class="field-error" v-if="$v.form.password.$dirty">
                    <div class="error" v-if="!$v.form.password.required">필수로 입력하셔야 합니다.</div>
                  </div>
                </div>

                <button type="submit" class="btn btn-primary">로그인</button>

                <nuxt-link to="/" class="forgot-password">비밀번호를 잊으셨나요??</nuxt-link>
              </form>
            </div>
          </div>

          <div class="col-lg-6 col-md-12">
            <div class="new-customer-content">
              <div class="section-title">
                <h2>신규가입</h2>
              </div>
              <span>신규가입</span>
              <p>빠르게 회원가입 할 수 있습니다. 쇼핑을 시작하려면 가입하기를 클릭하세요.</p>
              <nuxt-link to="/signup" class="btn btn-light">가입하기</nuxt-link>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import {validationMixin} from 'vuelidate'
import {required} from 'vuelidate/lib/validators'
import authenticationService from "~/services/authentication";

export default {
  mixins: [validationMixin],

  data() {
    return {
      form: {
        email: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  validations: {
    form: {
      email: {
        required,
      },
      password: {
        required,
      }
    }
  },
  methods: {
    submitForm() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        return
      }
      authenticationService.login(this.form).then(() => {
        this.$router.push({name: 'gallery-one'})
      }).catch((error) => {
        this.errorMessage = '로그인에 실패했습니다.' + error.message;
      })
    }
  }
}
</script>
