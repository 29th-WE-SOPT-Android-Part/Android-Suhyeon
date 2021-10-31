# Week 3

- **실행화면**

  | SignInActivity & SignUpActivity                              | ProfileFragment & HomeFragment                               |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | ![29week2_detail](https://user-images.githubusercontent.com/70002218/139585927-10961789-d1d3-404c-80fc-9e9978377a39.gif) | ![week3](https://user-images.githubusercontent.com/70002218/139585992-b4646fc2-710e-42a8-8b49-b392408d2613.gif) |



## 🐌 LEVEL1

#### *🍁 SignInActivity, SignUpActivity UI*

- **`selector_user_input.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/rectangle_fill_light_gray_border_gray_5" android:state_focused="false" />
    <item android:drawable="@drawable/rectangle_border_pink_5" android:state_focused="true" />
</selector>
```

- **`selector_user_input.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/rectangle_fill_light_gray_border_gray_5" android:state_focused="false" />
    <item android:drawable="@drawable/rectangle_border_pink_5" android:state_focused="true" />
</selector>
```



#### *🍁 BottomNavigation, ViewPager2*

- **`menu_bottom.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_profile"
        android:icon="@drawable/ic_person_24"
        android:title="@string/profile"/>

    <item
        android:id="@+id/menu_home"
        android:icon="@drawable/ic_home_24"
        android:title="@string/home"/>

    <item
        android:id="@+id/menu_camera"
        android:icon="@drawable/ic_camera_24"
        android:title="@string/camera"/>
</menu>
```

- **`selector_bottom_navi.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/light_pink" android:state_checked="true"/>
    <item android:color="@color/dark_gray"/>
</selector>
```

- **`activity_main.xml`에 BottomNavigation과 ViewPager2 추가**

```xml
<androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".ui.view.user.SignInActivity">

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/vp_main"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toTopOf="@id/bnv_main"/>

        <com.google.android.material.bottomnavigation.BottomNavigationView
            android:id="@+id/bnv_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:menu="@menu/menu_bottom"
            app:itemIconTint="@color/selector_bottom_navi"
            app:itemRippleColor="@color/dark_gray"
            app:itemTextColor="@color/selector_bottom_navi"
            app:layout_constraintBottom_toBottomOf="parent"/>

    </androidx.constraintlayout.widget.ConstraintLayout>
```

- **MainViewPagerAdapter**

```kotlin
class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    val fragments  = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
```

- **MainActivity에서 ViewPager2와 BottomNavigation 연동**

```kotlin
private fun initMainPagerAdapter() {
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), ImageFragment())

        mainViewPagerAdapter = MainViewPagerAdapter(this)
        mainViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpMain.adapter = mainViewPagerAdapter
    }

    private fun initBottomNavigation() {
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) { // 추상클래스
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_profile -> {
                    binding.vpMain.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpMain.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}
```



#### *🍁 ProfileFragment*

- **`fragment_profile.xml`의 button **
  - AppCompatButton을 이용하여 background에 selector 적용
  - textColor에 selector이용하여 색 변경

```xml
<androidx.appcompat.widget.AppCompatButton
            android:id="@+id/btn_follower"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="11dp"
            android:layout_marginTop="11dp"
            android:background="@drawable/selector_profile_btn"
            android:fontFamily="@font/noto_sans_kr_regular"
            android:paddingHorizontal="45dp"
            android:paddingVertical="13dp"
            android:text="@string/follower"
            android:textColor="@color/selector_profile_btn"
            android:textSize="14sp"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/cl_profile" />
```

- **ProfileFragment에서 select 상태 변경**

```kotlin
private fun initTransactionEvent() {
        val followerFragment = FollowerListFragment()
        val repositoryFragment = RepositoryListFragment()

        childFragmentManager.beginTransaction().add(R.id.container_rec, followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            with(binding) {
                btnFollower.isSelected = true
                btnRepo.isSelected = false
            }
            childFragmentManager.beginTransaction().replace(R.id.container_rec, followerFragment)
                .commit()
        }

        binding.btnRepo.setOnClickListener {
            with(binding) {
                btnFollower.isSelected = false
                btnRepo.isSelected = true
            }
            childFragmentManager.beginTransaction()
                .replace(R.id.container_rec, repositoryFragment).commit()
        }
    }

    private fun initProfile() {
        with(binding) {
            btnFollower.isSelected = true
            imgUrl = "https://cdn.pixabay.com/photo/2020/10/21/19/43/jack-o-lanterns-5674148_960_720.jpg"
        }
    }
```



#### *🍁 HomeFragment*

- **`fragment_home.xml`에 TabLayout, ViewPager2 추가 **

```xml
<com.google.android.material.tabs.TabLayout
            android:id="@+id/tl_follow"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabIndicatorColor="@color/sopt_pink"
            app:tabIndicatorHeight="3dp"
            android:textSize="16sp"
            android:layout_marginTop="23dp"
            app:tabTextColor="@color/mild_gray"
            app:tabSelectedTextColor="@color/sopt_pink"
            app:layout_constraintTop_toBottomOf="@id/tv_github">

        </com.google.android.material.tabs.TabLayout>

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/vp_follow"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintTop_toBottomOf="@id/tl_follow"
            app:layout_constraintBottom_toBottomOf="parent"/>
```

- **FollowTabPagerAdapter**
  - Fragment 내의 ViewPager2이므로 fragment를 넘김

```kotlin
class FollowTabPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
```

- **HomeFragment에서 ViewPager2와 TabLayout 연동**

```kotlin
private fun initFollowTabPagerAdapter() {
        val fragmentList = listOf(FollowerTabFragment(), FollowingTabFragment())

        followTabPagerAdapter = FollowTabPagerAdapter(this)
        followTabPagerAdapter.fragments.addAll(fragmentList)

        binding.vpFollow.adapter = followTabPagerAdapter
    }

    private fun initFollowTab() {
        val tabLabel = listOf("팔로워", "팔로잉")

        TabLayoutMediator(binding.tlFollow, binding.vpFollow) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
```



## 🐌 LEVEL2

#### 🍁 *ViewPager2 중첩 스크롤 문제 해결하기*

준비중....🐌



#### 🍁 *Glide 이용해 List에 다른 이미지 넣기*

- **`build.gradle`**

```kotlin
dependencies {
    //Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
}
```

- **BindingAdapter**

```kotlin
object BindingAdapter {
    @JvmStatic
    @BindingAdapter("circleImgUrl")
    fun setRemoteCircleImage(image: ImageView, url: String?) {
        Glide.with(image.context).load(url).circleCrop().into(image)
    }
}
```

- **ImageView**

```xml
<ImageView
                android:id="@+id/iv_profile"
                android:layout_width="0dp"
                android:layout_height="0dp"
                android:layout_marginStart="21dp"
                app:circleImgUrl="img link"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintDimensionRatio="1:1"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
```



## 🐌 LEVEL3

#### 🍁 *DataBinding*

- **`item_follower`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="userInfoData"
            type="org.sopt.sopthub.data.FollowerData" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/cl_follower"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingVertical="23dp"
            app:layout_constraintTop_toTopOf="parent">

            <ImageView
                android:id="@+id/iv_profile"
                android:layout_width="0dp"
                android:layout_height="0dp"
                android:layout_marginStart="21dp"
                app:circleImgUrl="@{userInfoData.imgUrl}"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintDimensionRatio="1:1"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />

            <TextView
                android:id="@+id/tv_name"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="22dp"
                android:layout_marginTop="1dp"
                android:fontFamily="@font/noto_sans_kr_bold"
                android:text="@{userInfoData.name}"
                android:textColor="@color/sopt_black"
                android:textSize="16sp"
                app:layout_constraintStart_toEndOf="@id/iv_profile"
                app:layout_constraintTop_toTopOf="@id/iv_profile"
                tools:text="@string/name" />

            <TextView
                android:id="@+id/tv_introduce"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="5dp"
                android:fontFamily="@font/noto_sans_kr_regular"
                android:text="@{userInfoData.introduce}"
                android:textColor="@color/sopt_black"
                android:textSize="14sp"
                app:layout_constraintStart_toStartOf="@id/tv_name"
                app:layout_constraintTop_toBottomOf="@id/tv_name"
                tools:text="@string/my_story" />

        </androidx.constraintlayout.widget.ConstraintLayout>

        <View
            android:id="@+id/line_bottom_divider"
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:background="@color/line_gray"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toBottomOf="@id/cl_follower" />

    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
```

- **FollowerAdapter**

```kotlin
class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    ItemTouchHelperListener {
    val userList = mutableListOf<FollowerData>()
    ...

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.userInfoData = data
      		...
        }
    }
```



#### 🍁 *ImageFragment*

준비중....🐌



## 🐌 배운 내용

- color값도 selector로 변경이 가능하다는 것을 알았다.
- BottomNavigation
- BindingAdapter

