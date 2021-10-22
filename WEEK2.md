# Week 2

**LEVEL 1**

- **실행화면**

  ![29week2](https://user-images.githubusercontent.com/70002218/138418272-891c1509-09fd-4a0f-ad03-4ca229a04144.gif)


- **Fragment Transaction**

  ```kotlin
  private fun initTransactionEvent() {
          val followerFragment = FollowerFragment()
          val repositoryFragment = RepositoryFragment()
  
          supportFragmentManager.beginTransaction().add(R.id.container_rec, followerFragment).commit()
  
          binding.btnFollower.setOnClickListener {
              supportFragmentManager.beginTransaction().replace(R.id.container_rec, followerFragment).commit()
          }
  
          binding.btnRepo.setOnClickListener {
              supportFragmentManager.beginTransaction().replace(R.id.container_rec, repositoryFragment).commit()
          }
      }
  ```

- layoutManager는 linear와 grid를 이용함

- TextView 글자 수 제한

  ```kotlin
  android:ellipsize="end"
  android:maxLines="1"
  ```

- 배운 내용


  - RecyclerView의 layoutManager에서 grid를 사용해보았다.

