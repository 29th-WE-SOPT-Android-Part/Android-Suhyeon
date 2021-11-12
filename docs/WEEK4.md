# Week 4

- **실행화면**

  | SignIn                                                       | SignUp                                                       |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | ![image](https://user-images.githubusercontent.com/70002218/141466383-754ecfb3-1b9e-445d-8c80-ca2e5972d8b5.png) | ![image](https://user-images.githubusercontent.com/70002218/141466518-e23bed2d-e3bd-41be-988e-4bd1772acce2.png) |
  
  
  
  | SignInActivity & SignUpActivity                              |
  | ------------------------------------------------------------ |
  | ![week4](https://user-images.githubusercontent.com/70002218/141466884-7000fbbf-d2f4-44f1-902c-78da4ccae1e4.gif) |



## 🐌 LEVEL1

#### *🍁 SignInActivity, SignUpActivity 서버 연동*

- **UserService**

```kotlin
interface UserService {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun postSignIn(
        @Body body : ReqSignInData
    ) : Call<ResSignInData>

    @Headers("Content-Type: application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body body : ReqSignUpData
    ) : Call<ResSignUpData>
}
```

- **UserServiceCreator**

```kotlin
object UserServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService : UserService = retrofit.create(UserService::class.java)
}
```

- **Res, Req data 생성**
- **SignInActivity**

```kotlin
private fun initSignInBtnClick() {
    with(binding) {
        btnLogin.setOnClickListener {
            postSignIn(
                ReqSignInData(
                    binding.etId.text.toString(),
                    binding.etPw.text.toString()
                )
            )
        }
    }
}


private fun postSignIn(reqSignInData: ReqSignInData) {
    val call: Call<ResSignInData> = UserServiceCreator.userService.postSignIn(reqSignInData)

    call.enqueue(object : Callback<ResSignInData> {
        override fun onResponse(call: Call<ResSignInData>, response: Response<ResSignInData>) {
            if (response.isSuccessful) {
                val data = response.body()?.data

                shortToast("${data?.email}님 반갑습니다!")
                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            } else
                shortToast("로그인에 실패하셨습니다.")
            }

        override fun onFailure(call: Call<ResSignInData>, t: Throwable) {
            Log.e("NetworkTest", "error:$t")
        }
        })
}
```

- **SignUpActivity**

```kotlin
private fun initSignUpBtnClick() {
    with(binding) {
        btnSignUp.setOnClickListener {
            postSignUp(
                ReqSignUpData(
                    etId.text.toString(),
                    etName.text.toString(),
                    etPw.text.toString()
                )
            )
        }
    }
}

private fun postSignUp(reqSignUpData: ReqSignUpData) {
    val call: Call<ResSignUpData> = UserServiceCreator.userService.postSignUp(reqSignUpData)

    call.enqueue(object : Callback<ResSignUpData> {
        override fun onResponse(call: Call<ResSignUpData>, response: Response<ResSignUpData>) {
            if (response.isSuccessful) {
                finish()
            } else
                shortToast("중복 이메일 또는 입력되지 않은 정보가 있습니다.")
        }

        override fun onFailure(call: Call<ResSignUpData>, t: Throwable) {
            Log.e("NetworkTest", "error:$t")
        }
    })
}
```



## 🐌 LEVEL2

#### 🍁 



## 🐌 LEVEL3

#### 🍁 



## 🐌 배운 내용

- retrofit2를 사용해 봤다.

