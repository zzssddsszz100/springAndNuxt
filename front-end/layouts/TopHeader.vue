<template>
    <!-- Start Top Header Area -->
    <div class="top-header">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-7 col-md-6">
                    <ul class="top-header-nav">
                        <li><nuxt-link to="/login">로그인</nuxt-link></li>
                        <li><nuxt-link to="/signup">회원가입</nuxt-link></li>
                        <li><nuxt-link to="/products">모든상품</nuxt-link></li>
                        <li><nuxt-link to="/contact">문의하기</nuxt-link></li>
                    </ul>
                </div>

                <div class="col-lg-5 col-md-6">
                    <ul class="top-header-right-nav">
                        <li><nuxt-link to="/blog-one">블로그</nuxt-link></li>
                        <li><nuxt-link to="/cart">장바구니</nuxt-link></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- End Top Header Area -->
</template>

<script>
export default {
  name:'TopHeader'
}
</script>
