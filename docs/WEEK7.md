# Week 7

- **실행화면**

  | Onboarding                                                   | AutoLogin                                                    |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | ![온보딩](https://user-images.githubusercontent.com/70002218/145555723-e4aa9ecc-65e7-4e31-bd5d-640ce14faadd.gif) | ![자동로그인](https://user-images.githubusercontent.com/70002218/145555758-7b2259bb-8919-433c-b199-0d4ee5fcc33c.gif) |



## 🐌 LEVEL1

#### *🍁 온보딩 화면*

- **`nav_on_board.xml` 생성**

- **SharedPreferences**

```kotlin
object SOPTHubSharedPreferences {
    private const val IS_ON_BOARDING = "IS_ON_BOARDING"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
    
    fun getIsOnBoarding(): Boolean {
        return preferences.getBoolean(IS_ON_BOARDING, false)
    }

    fun setIsOnBoarding(value: Boolean) {
        preferences.edit().putBoolean(IS_ON_BOARDING, value).apply()
    }
}
```

- **SOPTHubApplication**

```kotlin
class SOPTHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SOPTHubSharedPreferences.init(applicationContext)
    }
}
```

- **OnboardingActivity**

```kotlin
private fun isOnBoarding() {
    if (SOPTHubSharedPreferences.getIsOnBoarding()) {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
}
```

- **OnboardingFinishFragment**

```kotlin
private fun setBtnStartClick() {
    binding.btnStart.setOnClickListener {
        SOPTHubSharedPreferences.setIsOnBoarding(true)
        startActivity(Intent(requireContext(), SignInActivity::class.java))
        requireActivity().finish()
    }
}
```



#### **🍁 자동 로그인/ 자동 로그인 해제**

- **SharedPreferences**

```kotlin
object SOPTHubSharedPreferences {
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(): Boolean {
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(value: Boolean) {
        preferences.edit().putBoolean(AUTO_LOGIN, value).apply()
    }

    fun removeAutoLogin() {
        preferences.edit().remove(AUTO_LOGIN).apply()
    }
}
```

- **SignInActivity**

```kotlin
private fun initAutoLoginBtnClick() {
    binding.clAutoLogin.setOnClickListener {
        binding.ivCheck.isSelected = !binding.ivCheck.isSelected

        SOPTHubSharedPreferences.setAutoLogin(binding.ivCheck.isSelected)
    }
}

private fun isAutoLogin() {
    if(SOPTHubSharedPreferences.getAutoLogin()) {
        shortToast("자동로그인 되었습니다.")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
```

- **SettingActivity**

```kotlin
private fun initRemoveAutoLoginClick() {
    binding.tvRemoveAutoLogin.setOnClickListener {
        SOPTHubSharedPreferences.removeAutoLogin()
    }
}
```



#### **🍁 패키징**

```
📦 org.sopt.sopthub
 ┣ 📂 data
 ┃ ┣ 📂 local
 ┃ ┗ 📂 remote
 ┃ 	 ┣ 📂 api
 ┃ 	 ┗ 📂 model
 ┃   	┣ 📂 request
 ┃   	┗ 📂 response
 ┣ 📂 ui
 ┃ ┣ 📂 base
 ┃ ┗ 📂 view
 ┃   ┗ 📂 adapter
 ┗ 📂 util
```

- data : data 관련 파일
- ui : ui 관련 파일
- util : BindingAdapter, PixelRatio와 같은 파일을 담았다. 나중에 수정이 필요할 것 같다.





## 🐌 LEVEL2

#### **🍁 NavigationComponent BackStack 관리**

- **`nav_on_board.xml` 에서 popUpTo, popUpToInclusive**

```xml
<fragment
        android:id="@+id/onBoardMessageFragment"
        android:name="org.sopt.sopthub.ui.view.onboard.OnBoardMessageFragment"
        android:label="@string/second_fragment"
        tools:layout="@layout/fragment_on_board_message">
        <action
            android:id="@+id/action_onBoardMessageFragment_to_onBoardFinishFragment"
            app:destination="@id/onBoardFinishFragment"
            app:popUpTo="@id/onBoardWelcomeFragment"
            app:popUpToInclusive="false"/>
</fragment>
```



#### **🍁 NavigationComponent와 ToolBar 연동**

- **OnboardingActivity**

```kotlin
private fun initToolBar() {
    val navHostFragment =
    supportFragmentManager.findFragmentById(R.id.container_on_board) as NavHostFragment
    val navController = navHostFragment.navController
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    binding.toolbar.setupWithNavController(navController, appBarConfiguration)
}
```



## 🐌 LEVEL3

#### 🍁 



## 🐌 배운 내용

- NavigationComponent를 ToolBar와 연동해보면서 ToolBar에 대해 더 알게 되었다.

