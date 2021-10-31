# Week 2

- **실행화면**

  | Drag & Swipe                                                 | DetailActivity                                               |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | ![29week2_swipe](https://user-images.githubusercontent.com/70002218/139295073-66d15086-6013-44ab-a053-29b5a2e8068c.gif) | ![29week2_detail](https://user-images.githubusercontent.com/70002218/139295011-d557ffe7-64fc-4ac4-ba15-f253fc788e93.gif) |



## 🔥 LEVEL1

#### *🚩 Fragment Transaction*

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



## 🔥 LEVEL2

#### *🚩 DetailActivity*

- **FollowerAdapter**

```kotlin
class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    ItemTouchHelperListener {
    val userList = mutableListOf<FollowerData>()

    // 고차함수
    private var followerItemClickListener: ((String, String, String) -> Unit)? = null

    fun setFollowerItemClickListener(listener: (String, String, String) -> Unit) {
        followerItemClickListener = listener
    }

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.userInfoData = data
            binding.clFollower.setOnClickListener {
                followerItemClickListener?.invoke(data.imgUrl, data.name, data.introduce)
            }
        }
    }

}
```

- **FollowerFragment**

```kotlin
class FollowerFragment : BindingFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        ...
        followerAdapter.setFollowerItemClickListener { imgUrl, name, introduce ->
            navigateDetail(imgUrl, name, introduce)
        }
    }

    private fun navigateDetail(imgUrl: String, name: String, introduce: String) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        val bundle = Bundle().apply {
            putString("imgUrl", imgUrl)
            putString("name", name)
            putString("introduce", introduce)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }
```

- **DetailActivity**

```kotlin
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFollowerInfo()
    }

    private fun initFollowerInfo() {
        val bundle = intent.extras
        bundle?.let{
            val name = it.getString("name") ?: ""
            val introduce = it.getString("introduce") ?: ""
            binding.userInfoData = FollowerData("imgUrl", name, introduce)
        }
    }
}
```



#### *🚩* *ItemDecoration*

- **FollowerItemDecoration**

```kotlin
class FollowerItemDecoration(
    private val dividerHeight: Int,
    private val padding: Int,
    private val color: Int = Color.TRANSPARENT
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    //item 사이의 간격
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.apply {
            top = padding
            bottom = padding
            left = padding
            right = padding
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        paint.color = color

        parent.forEach { view ->

            val dividerTop = view.bottom + padding //dividerTop 위치를 padding 만큼 내림
            val dividerBottom = dividerTop + dividerHeight

            c.drawRect(
                view.left.toFloat(),
                dividerTop.toFloat(),
                view.right.toFloat(),
                dividerBottom.toFloat(),
                paint
            )
        }
    }
}
```

- **FollowerFragment 내 FollowerAdapter에 연결**

```kotlin
private fun initFollowerItemDecoration() {
        binding.rvFollower.addItemDecoration(
            FollowerItemDecoration(
                dividerHeight = 5.dp, padding = 5.dp,
                color = ContextCompat.getColor(requireContext(), R.color.sopt_pink)
            )
        )
    }
```

- **PixelRatio**

```kotlin
class PixelRatio() {
    private val displayMetrics
        get() = Resources.getSystem().displayMetrics

    fun dpToPx(dp: Int) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), displayMetrics).toInt()

}

val Number.dp: Int
    get() = PixelRatio().dpToPx(this.toInt())
```



#### *🚩* *RecyclerView Item 이동, 삭제*

- **ItemTouchHelperListener**

```kotlin
interface ItemTouchHelperListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemSwipe(position: Int)
}
```

- **ItemTouchHelperCallback**

```kotlin
class ItemTouchHelperCallback(val listener: ItemTouchHelperListener) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isLongPressDragEnabled(): Boolean = true

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwipe(viewHolder.adapterPosition)
    }
}
```

- **FollowerAdapter**

```kotlin
class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    ItemTouchHelperListener {
        
   ...

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val item = userList[fromPosition]
        userList.removeAt(fromPosition)
        userList.add(toPosition, item)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemSwipe(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
    }

}
```

- **FollowerFragment 내 FollowerAdapter에 연결**

```kotlin
private fun initItemTouchHelper() {
        ItemTouchHelper(ItemTouchHelperCallback(followerAdapter)).attachToRecyclerView(binding.rvFollower)
    }
```



## 🔥 LEVEL3

#### *🚩 보일러 플레이트 코드 개선*

- **BindingActivity**

```kotlin
abstract class BindingActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }
}
```

- **BindingFragment**

```kotlin
abstract class BindingFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    private var _binding: T? = null
    protected val binding: T get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```




- 배운 내용


  - RecyclerView의 layoutManager에서 grid를 사용해보았다.

