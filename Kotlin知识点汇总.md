对象
  java写法  MainActivity.this
  Kotlin写法  this@MainActivity
  
类 
  java写法  MainAcitivty.this
  Kotlin写法  MainActivity::class.java
  
继承
  java写法  
  public class MainActivity extends AppCompatActivity{ }
  Kotlin写法(kotlin中被继承类必须被open关键字修饰)
  class MainActivity: AppCompatActivity{}
  
变量
  java写法  Intent intent = new Intent();
  Kotlin写法  var intent = Intent();
  
常量  
  java写法  final String text = "";
  Kotlin写法   val text = ""
  
静态常量  
  java写法  
  public class MainActivity extends AppCompatActivity {
    static final String text = "";
  }
  Kotlin写法  (需注意的是要把静态变量定义在类上方)
  const val text =""
  class MainActivity : AppCompatActivity(){}
  
定义方法
  java写法
  public void test(String msg){}  
  Kotlin写法(Unit 等同void)
  fun test(msg : String) : Unit{}
  fun test(msg : String){}
  
重载方法  
  java写法  
  public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       super.oncreate(savedInstanceState);
    }
  }
  Kotlin写法
  class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState : Bundle?){
      super.onCreate(savedInstanceState)
    }
  }
  
基本数据类型
  java写法  
  int i = 1;
  long l = 2;
  boolean b = true;
  float f = 0;
  double d = 0;
  char c = 'A';
  String s = "text";
  Kotlin写法
  var i = 1
  var l =2
  var b =true
  var f = 0F
  var d = 0.0
  var c = 'A'
  var s = "text"
  
比较类型  
  java写法
  if("" instanceof String){}  
  Kotlin写法
  if("" is String){}
  
转换符
  java写法  
  int number = 100;
  System.out.println(String.format("商品数量有%d", number));
  Kotlin写法
  var number = 100
  println("商品数量有${number}")
  // 换种简洁的写法
  var number = 100
  println("商品数量有$number")
  // 如果不想字符串被转义可以使用\$
  var number = 100
  println("商品数量有\$number")
  
字符串比较
  java写法  
  String s1 = "text";
  String s2 = "text";
  if (s1.equals(s2)) {
      
  }
  Kotlin写法
  var s1 = "text"
  var s2 = "text"
  if (s1 == s2) {
  
  }
  
数组
  java写法  
  int[] array1 = {1,2,3};
  float[] array2 = {1f,2,3f};
  String[] array3 = {"1","2","3"};
  Kotlin写法
  var array1 = intArrayOf(1,2,3)
  var array2= floatArrayOf(1f,2f,3f)
  var array3 = arrayListOf("1",""2","3")
  
循环
  java写法  
  String[] array = {"1",""2","3"};
  for(int i=0;i<array.length;i++){
    System.out.println(array[i]);
  }
  Kotlin写法
  var array = arrayListOf("1",""2","3")
  for(i in array.indices){
    println(array[i])
  }
  
角标循环
  java写法
  String[] array = {"1",""2","3"};
  for(int =0;i<array.length;i++){
    System.out.println(array[i])
  }  
  Kotlin写法
  var array = arrayListOf("1",""2","3")
  for(i in until array.size){
    println(array[i])
  }
  
高级循环  
  java写法
  String[] array = {"1",""2","3"}; 
  for(String text:array){
    System.out.println(text);
  }
  Kotlin写法
  var array = arrayListOf("1",""2","3")
  for(text in array){
    println(text)
  }
  
判断器
  java写法  
  int count = 1;
  switch (count) {
      case 0:
          System.out.println(count);
          break;
      case 1:
      case 2:
          System.out.println(count);
          break;
      default:
          System.out.println(count);
          break;
  }
  Kotlin写法  
  var count = 1
  when(count){
    0-> println(count)
    in 1..2 -> println(count)
    else -> println(count)
  }
  
构造函数
  java写法
  public class MyView extends View {
  
      public MyView(Context context) {
          this(context, null);
      }
  
      public MyView(Context context, @Nullable AttributeSet attrs) {
          this(context, attrs, 0);
      }
  
      public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
          super(context, attrs, defStyleAttr);
      }
  }  
  Kotlin写法 
  class MyView : View {
  
      constructor(context : Context) : this(context, null)
  
      constructor(context : Context, attrs : AttributeSet?) : this(context, attrs, 0)
  
      constructor(context : Context, attrs : AttributeSet?, defStyleAttr : Int) : super(context, attrs, defStyleAttr)
  }
  
类创建
  java写法
  public class Person {
  
      String name;
      int age;
  
      public Person(String name, int age) {
          this.name = name;
          this.age = age;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public int getAge() {
          return age;
      }
  
      public void setAge(int age) {
          this.age = age;
      }
  }
  Person person = new Person("CurvedBowZhang", 100);
  person.setName("ZJX");
  person.setAge(50);
  System.out.println("name: " + person.getName() + ", age: " + person.getAge());  
  Kotlin写法 
  class Person (var name:String,var age:Int)
  var person = Person("Newsuper",10)
  person.name = "aaa"
  person.age = 50
  
私有化set方法
  java写法  --引用上java
  Kotlin写法  
  class Person {
    var name : String? = null
    private set
    var age : Int = 0
    private set
  }
  
枚举
  java写法 
  enum Sex {
    MAN(true),WOMEN(false);
    Sex(boolean isMan){}
  } 
  Kotlin写法 
  enum class Sex(var isMan:Boolean){
     MAN(true),WOMEN(false);
  }
  
接口
  java写法
  public interface Callback {
    void onSuccess();
    void onFail();
  }  
  Kotlin写法   
  interface Callback {
      fun onSuccess();
      fun onFail();
  }  
  
匿名内部类
  java写法
  new Callback(){
    @Override
    public void onSuccess(){
    
    }
    @override
    public void onFail(){
    
    }
  } ; 
  Kotlin写法  
  object: Callback {
    override fun onSuccess(){
    
    }
    override fun onFail(){
    
    }
  }
  
内部类
  java写法  
  public class MainActivity extends AppCompatActivity {
    public inner MyTask {}
  }
  Kotlin写法 
  class MainActivity: AppCompatActivity(){
    inner class MyTask{}
  }
  
内部类访问外部类同名变量
  java写法  
  String name = "Cugfdgdf";
  public class Mytask {
    String name = "ZJS"
    public void show(){
      System.out.println(name + "---" + MainActivity.this.name);
    }
  }
  Kotlin写法   
  var name = "Cugfdgdf"
  inner class MyTask {
    var name = "zjs"
    fun show(){
        println(name + "---" + this@MainActivity.name);
    }
  }
  
抽象类
  java写法
  public abstract class BaseActivity extends AppCompatActivity implements Runnable{
    abstract void init();
  }  
  Kotlin写法  
  abstract class BaseActivity: AppCompatActivity(),Runnable{
    abstract fun init()
  }
  
静态变量和方法
  java写法  
  public class ToastUtils {
    public static Toast sToast;
    
    public static void show(){
      sToast.show();
    }
  }
  Kotlin写法 (称之为伴生对象)
  compain object ToastUtils{
    var sToast : Toast? = null
    
    fun show(){
      sToast!!.show()
    }
  }
  
可变参数
  java写法 
  public int add(int... array){
    int count = 0;
    for(int i:array){
      count += i;
    }
    return count;
  } 
  Kotlin写法   
  fun add(vararg array :Int) :Int {
    var count = 0
    array.foteach{
      count += i
    }
    return count
  }
  
泛型
  java写法
  public class Bean<T extends String>{
    T data;
    public Bean(T t){
     this.data = t;
    }
  }  
  Bean<String> bean = new Bean<>("66666");
  Kotlin写法  
  class Bean<T : Comparable<String>>(t: T){
    var data = t
  }
  var bean = Bean<String>("6666")
  
构造代码块
  java写法
  public class MainActivity extends AppCompatActivity {
    int num;
    {
      num = 1;
    }
  }  
  Kotlin写法 
  class MainActivity : AppCompatActivity(){
    var num = 0
    init {
      num = 1
    }
  }
  
静态代码块
  java写法  
    public class MainActivity extends AppCompatActivity {
      static  int num;
      static{
        num = 1;
      }
    }  
  Kotlin写法   
  class MainActivity: AppCompatActivity(){
    companion object{
      var num = 0
      init {
        num = 1
      }
    }
  }

可见修饰符
  java写法
  public 所有类可见
  protected 子类可见
  default 同一包下的类可见
  private 仅对自己可见  
  Kotlin写法  
    public 所有类可见
    protected 子类可见
    internal 同Module下的类可见
    private 仅对自己可见 
   
无需findviewbyid
  <TextView
      android:id="@+id/tv_content"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Hello World!" />
      tv_content.text  = "改变文本"

空安全
  fun getText(): String{
    return "text"
  }
  如果 text 对象为空则不会报错，但是 text.length 的结果会等于 null
  val text = getText()
  print(text?.length)
  
运算符重载
  +a    a.unaryPlus()
  -a    a.unaryMinus()
  !a    a.not()
  a++   a.inc()
  a--   a.dec()
  
  a+b   a.plus(b)
  a-b   a.minus(b)
  a*b   a.times(b)
  a/b   a.div(b)
  a%b   a.rem(b)
  a..b  a.rangTo(b)
  
  a in b  b.contains(a)
  a !in b !b.contains(a)
  
  a[i]  a.get(i)
  a[i,j]  a.get(i,j)
  a[i_,1,...,i_n]  a.get(i_1,...,i,i_n)
  a[i] = b  a.set(i,b)
  a[i,j] = b  a.set(i,j,b)
  a[i_1,...,i_n]  a.set(i_1,...,i_n,b)
  
  a()  a.invoke()
  a(i)  a.invoke(i)
  a(i,j)  a.invoke(i,j)
  a(i_1,...,i_n)  a.invoke(i_1,...,i_n)
  
  a += b   a.plusAssign(b)
  a -= b   a.minusAssign(b)
  a *= b   a.timeAssign(b)
  a /= b   a.divAssign(b)
  a %= b   a.remAssign(b)
  
  a == b  a?.equals(b) ?:(b===null)
  a != b  !(a?.equals(b)?: (b===null))
  
  a > b  a.compareTo(b) > 0
  a < b  a.compareTo(b) < 0
  a >= b a.compareTo(b) >= 0
  a <= b a.compareTo(b) <= 0


let函数
  在函数内可通过 it 指该对象，返回值为函数块的最后一行或指定return表达式
  fun main(){
    val text = "CAAA"
    println(text.length)
    var result = 1000
    println(result)
  }
    let写法
  fun main(){
    val result  = "CAAA".let {
      println(it.length)
      1000
    }
    println(result)
  }
  最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理
  mVideoPlayer?.setVideoView(activity.course_video_view)
  mVideoPlayer?.setControllerView(activity.course_video_controller_view)
  mVideoPlayer?.setCurtainView(activity.course_video_curtain_view)
    let写法
  mVideoPlayer?.let {
       it.setVideoView(activity.course_video_view)
       it.setControllerView(activity.course_video_controller_view)
       it.setCurtainView(activity.course_video_curtain_view)
  } 
    
with函数
   它是将某个对象作为函数的参数，在函数块内可通过this指代该对象，返回值为函数块的最后一行或指定return表达式
   class Person(var name : String, var age : Int)
   fun main() {
       var person = Person("CurvedBowZhang", 100)
       println(person.name + person.age)
       var result = 1000
       println(result)
   }
   
   with写法
   fun main(){
     var result = with(Person("CAAA",199)){
       println(name+age)
       1000
     }
     println(result)
   }
   适用于调用同一个类的多个方法时，可以省去类名重复，直接调用类的方法即可，
   经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上
   override fun onBindViewHolder(holder: ViewHolder, position: Int){
       val item = getItem(position)?: return
       holder.nameView.text = "姓名：${item.name}"
       holder.ageView.text = "年龄：${item.age}"
   }
   override fun onBindViewHolder(holder: ViewHolder, position: Int){
       val item = getItem(position)?: return
       with(item){
           holder.nameView.text = "姓名：$name"
           holder.ageView.text = "年龄：$age"
       }
   } 
run函数
   是let 和with 2个函数的结合体，run函数只接收一个lambda函数作为参数，以闭包形式返回，返回值为最后一行
的值或指定的return 表达式
   一般写法
   var person = Person("CurvedBowZhang", 100)
   println(person.name + "+" + person.age)
   var result = 1000
   println(result)
   run 写法
   var person = Person("CurvedBowZhang", 100)
   var result = person.run{
      println("$name+$age")
      1000
   }
   println(result)
   适用于let,with函数任何场景。因为run函数是let,with两个函数结合体，准确来说它弥补了let函数在函数体内
必须使用it参数替代对象，在run函数中可以像with函数一样可以省略，直接访问实例的公有属性和方法，另一方面
它弥补了with函数传入对象判空问题，在run函数中可以像let函数一样做判空处理，这里还是借助 onBindViewHolder
案例进行简化
   
   override fun onBindViewHolder(holder: ViewHolder, position: Int){
       val item = getItem(position)?: return
       holder.nameView.text = "姓名：${item.name}"
       holder.ageView.text = "年龄：${item.age}"
   }
   override fun onBindViewHolder(holder: ViewHolder, position: Int){
       val item = getItem(position)?: return
       item?.run {
           holder.nameView.text = "姓名：$name"
           holder.ageView.text = "年龄：$age"
       }
   }
apply函数
   从结构上来看apply和run函数很像，唯一不同就是他们各自返回的值不一样，run函数以闭包形式返回最后一行代码的
的值，而apply函数的返回是传入对象的本身
   一般写法
   val person = Person("CurvedBowZhang", 100)
   person.name = "ZJX"
   person.age = 50
   apply 写法
   val person = Person("CurvedBowZhang", 100).apply{
     name = "ZJC"
     age  =  99
   }
   整体功能和run很像，apply一般用于对象实例初始化时，需要对对象中的属性进行赋值，或动态inflate出一个xml
的view需要给view绑定数据用到，比如
   mRootView = View.inflate(activity, R.layout.example_view, null)
   mRootView.tv_cancel.paint.isFakeBoldText = true
   mRootView.tv_confirm.paint.isFakeBoldText = true
   mRootView.seek_bar.max = 10
   mRootView.seek_bar.progress = 0
   apply写法
   mRootView = View.inflate(activity,R.layout.example_view,null).apply{
      tv_cancel.paint.isFakeBoldText  =true
      tv_confirm.paint.isFakeBoldText = true
      seek_bar.max = 10
      seek_bar.progress = 0
   }
   多层级判空问题
   if(mSectionMetaData  == null || mSectionMetaData .questionnaire == null
      || mSectionMetaData .section == null){
        return;
      }
   if(mSectionMetaData .questionnaire.userProject != null){
     renderAnalysis();
     return;
   }
   if(mSectionMetaData .section != null && !mSectionMetaData .section.sectionArticles.isEmpty()){
     fetchQuestionData();
     return;
   }
   kotlin的apply函数优化
   mSectionMetaData?.apply {  
       //mSectionMetaData不为空的时候操作mSectionMetaData   
   }?.questionnaire?.apply {  
       //questionnaire不为空的时候操作questionnaire   
   }?.section?.apply {   
       //section不为空的时候操作section  
   }?.sectionArticle?.apply {   
       //sectionArticle不为空的时候操作sectionArticle
   }
also函数
   also函数的结构实际上和let很像,唯一区别就是返回值不一样,let以闭包形式返回,返回函数体内最后一行的值,如果
最后一行为空就返回一个Unit类型的默认值,而also函数返回的则是传入对象的本身
   fun main() {
       val result = "CurvedBowZhang".let {
           println(it.length)
           1000
       }
       println(result) // 打印：1000
   }
   fun main() {
       val result = "CurvedBowZhang".also {
           println(it.length)
       }
       println(result) // 打印：CurvedBowZhang
   }    
   适用于let函数的任何场景,also函数和let函数很像,只是唯一的不同点就是let函数最后的返回值是最后一行,而also
函数的返回值是返回当前的这个对象,一般用于多个扩展函数链式调用 
    
协程
   子任务协作运行,优雅的处理异步问题解决方案
   实际是就是极大程度的复用线程,通过让线程满载运行,达到最大利用cpu,进而提升应用性能
   在当前app module 中配置环境和依赖
   kotlin{
     experimental {
       coroutines 'enable'
     }
   } 
   dependencies {
       implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:0.20'
       implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:0.20'
   }
   协程启动的3种方式
   runBlocking : T
   launch : Job
   async/await : Deferred   
   
   runBlocking   
     println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
     println("测试开始")
     runBlocking {
         println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
         println("测试延迟开始")
         delay(20000) // 因为 Activity 最长响应时间为 15 秒
         println("测试延迟结束")
     }
     println("测试结束")
     17:02:08.686 System.out: 测试是否为主线程 true
     17:02:08.686 System.out: 测试开始
     17:02:08.688 System.out: 测试是否为主线程 true
     17:02:08.688 System.out: 测试延迟开始
     17:02:28.692 System.out: 测试延迟结束
     17:02:28.693 System.out: 测试结束
     结论:runBlocking 运行在主线程，过程中 App 出现过无响应提示，由此可见 runBlocking 和它的名称一样，
          真的会阻塞当前的线程，只有等 runBlocking 里面的代码执行完了才会执行 runBlocking 外面的代码
   launch 
      println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
      println("测试开始")
      launch {
          println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
          println("测试延迟开始")
          delay(20000)
          println("测试延迟结束")
      }
      println("测试结束")
      17:19:17.190 System.out: 测试是否为主线程 true
      17:19:17.190 System.out: 测试开始
      17:19:17.202 System.out: 测试结束
      17:19:17.203 System.out: 测试是否为主线程 false
      17:19:17.203 System.out: 测试延迟开始
      17:19:37.223 System.out: 测试延迟结束
   async/await 
       测试的时候是主线程，但是到了 launch 中就会变成子线程，这种效果类似 new Thread()，有木有？
       和 runBlocking 最不同的是 launch 没有执行顺序这个概念
       
       println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
       println("测试开始")
       async {
           println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
           println("测试延迟开始")
           delay(20000)
           println("测试延迟结束")
       }
       println("测试结束")
       17:29:00.694 System.out: 测试是否为主线程 true
       17:29:00.694 System.out: 测试开始
       17:29:00.697 System.out: 测试结束
       17:29:00.697 System.out: 测试是否为主线程 false
       17:29:00.697 System.out: 测试延迟开始
       17:29:20.707 System.out: 测试延迟结束
       这结果不是跟 launch 一样么？那么这两个到底有什么区别呢？，让我们先看一段测试代码
       
       println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
       println("测试开始")
       val async = async {
           println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
           println("测试延迟开始")
           delay(20000)
           println("测试延迟结束")
           return@async "666666"
       }
       println("测试结束")
       
       runBlocking {
           println("测试返回值：" + async.await())
       }
       17:50:57.117 System.out: 测试是否为主线程 true
       17:50:57.117 System.out: 测试开始
       17:50:57.120 System.out: 测试结束
       17:50:57.120 System.out: 测试是否为主线程 false
       17:50:57.120 System.out: 测试延迟开始
       17:51:17.131 System.out: 测试延迟结束
       17:51:17.133 System.out: 测试返回值：666666
       看到这里你是否懂了，async 和 launch 还是有区别的，async 可以有返回值，通过它的 await 方法进行获取
       需要注意的是这个方法只能在协程的操作符中才能调用
    线程调度
    println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
    println("测试开始")
    launch(CommonPool) { // 同学们，敲重点
        println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
        println("测试延迟开始")
        delay(20000)
        println("测试延迟结束")
    }
    println("测试结束")
    18:00:23.243 System.out: 测试是否为主线程 true
    18:00:23.244 System.out: 测试开始
    18:00:23.246 System.out: 测试结束
    18:00:23.246 System.out: 测试是否为主线程 false
    18:00:23.247 System.out: 测试延迟开始
    18:00:43.256 System.out: 测试延迟结束
    Q：这个跟刚刚的代码有什么不一样吗？
    
    A：当然不一样，假如一个网络请求框架维护了一个线程池，一个图片加载框架也维护了一个线程池.......，你会发现其实这样不好的地方在于，这些线程池里面的线程没有被重复利用，于是乎协程主动维护了一个公共的线程池 CommonPool，很好的解决了这个问题
    
    Q：还有刚刚不是说能线程调度吗？为什么还是在子线程运行？
    
    A：因为我刚刚只用了 CommonPool 这个关键字，我再介绍另一个关键字 UI，光听名字就知道是啥了
    
    println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
    println("测试开始")
    launch(UI) {
        println("测试是否为主线程" + (Thread.currentThread() == Looper.getMainLooper().thread))
        println("测试延迟开始")
        delay(20000)
        println("测试延迟结束")
    }
    println("测试结束")
    18:07:20.181 System.out: 测试是否为主线程 true
    18:07:20.181 System.out: 测试开始
    18:07:20.186 System.out: 测试结束
    18:07:20.192 System.out: 测试是否为主线程 true
    18:07:20.192 System.out: 测试延迟开始
    18:07:40.214 System.out: 测试延迟结束
  
  java写法  
  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法 
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法 
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法 
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法 
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法 
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法 
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法   
  
  java写法  
  Kotlin写法  
  
  java写法  
  Kotlin写法 

  java写法  
  Kotlin写法     
        