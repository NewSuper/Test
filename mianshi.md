尚德公司面试
1.java有什么特性，继承有什么用处，多态有什么用处
 答：java是面向对象的+简单的+分布式的+健壮+安全+可移植的+解释型的+高性能的+多线程的+动态的
   继承简化了人们对事物的认识的描述，能清晰体现相关类的层次结构关系，提供了软件复用功能，
   增强一致性减少模块间的接口的界面，大大增加程序的易维护性，提供多重继承机制。
   多态提高了代码的扩展性，前期定义的代码可使用后期的内容，弊端是前期定义的内容不能使用后期子类的特有内容。

2.反射是什么，在哪里用到，怎么利用反射创建一个对象
  答：先明确2个概念：
    静态编译：在编译时确定类型，绑定对象，即通过
	动态编译：运行时确定类型，绑定对象，最大限度的发挥了java的灵活性，体现了多态的应用，有以降低类之间的耦合性。
	java反射机制是在运行状态中，对于任意一个实体类，都能够知道这个类的所有属性和方法
	对于任意一个对象，都能够调用它的任意方法和属性，这种动态获取信息以及调用对象方法的功能称为反射机制。
	java程序可加载一个运行时才得知名称的classm,获悉其完整构造（不包括methods定义），并生成其对象实体、
或对其fields设值，或唤起其methods。

3.代理模式与装饰模式的区别，手写一个静态代理，一个动态代理
  答：2者看起来都很像，都实现基础对象实现的接口，在其自身对象都保存着对被代理、被装饰者的对象引用。
  装饰者模式的定义：动态的将责任附加到被装饰者对象上，用于扩展对象的功能，比继承的灵活性大。java 的IO设计为典型应用
  代理模式定义：对其他对象进行代理，以控制对被代理对象的访问，spring 的业务逻辑层方法生成的代理类，主要进行一些事物控制
     由定义可以看出装饰的责任是扩展功能 ，而代理主要控制访问。

4.对象加载的过程，属性先加载还是方法先加载

   1.（静态变量和静态代码块）和（普通代码块和成员变量）看编写的顺序加载 如果静态代码块先加载然后打印静态变量会打印null.
       普通代码块同理。
   2.局部代码块和局部变量按编写顺序加载,构造代码块同理。
   3.子类和父类的方法只有调用才加载。

5.垃圾回收机制与jvm结构
       System.gc()用于调用垃圾收集器，在调用时，垃圾收集器将运行以回收未使用的内存空间，它将尝试释放被丢弃对象占用的内存，
   另此方法无法保证垃圾收集器的调用。所以 JVM怎么确定哪些对象应该进行回收呢？----
   引用计数法： 判断对象的引用数量，实现方式：给对象添加一个引用计数器，每当有引用对他进行引用时，计数器的值就+1，当引用失效，
 也就是不再执行此对象，它的计数器就-1，若某一个对象的计数器为0，那么表示这个对象没人对它引用，就会被gc回收掉。但不足！！！
    可达性计数法：因为上一个方法有缺陷才产生这个，通过判断对象的引用链是否可达来决定对象是否可以被回收。
	确定一哪些对象可被回收时，jvm在什么时机进行回收呢？
	1.会在cpu空闲时自动回收
	2.在堆内存存储满了后
	3，主动调用system.gc后尝试进行回收
	如何回收：-----
	1.标记-清除算法
	    标记所有需要回收的对象，标记完成后就进行统一回收掉哪些带有标记的对象，优点是简单，缺点是效率问题，标记清除后会产生
   大量不连续的
	内存碎片，当程序在以后的运行过程中，需要分配较大对象无法找到足够的连续内存而造成内存空间浪费。
	2.复制 算法：
	    复制将可用内存按容量划分成大小相同的2块，每次只使用其中的一块，当这块内存用完后，就将还存活的对象复制到另一块上，
		然后再把已使用的内存空间一次清理掉，这样 使得每次都是对其中的一块进行内存回收。内存分配时也就不用考虑内存碎片等
		情况了。只是这种算法的代价是将内存缩小为原来的一半。
    3.标记-整理算法
	    与标记清除算法相似，区别是仅对不存活的对象进行处理，剩余存活对象不做任何处理，造成内存碎片，而标记整理算法
		不仅对不存活对象进行清除，还对剩余存活的进行整理，重新整理，因此其不会产生内存碎片。
	4.分代收集算法
	    比较智能，是当下jvm使用最多的一种方法，他本身不是一个新的算法，而是他在具体的场景自动选择以上3种算法进行垃圾回收。
	jvm内存区----新生代      老年代    永久代
	    新生代的目标是尽可能的快速收集掉那些生命周期短的对象，一般情况下，所有新生成的对象首先都是放在新生代的。
		   ----（如果老年代满了，就会触发一次FULL GC,也就是新生代，老年代都进行回收，注意，新生代发生的GC也叫做MinorGC,
		   minorGc发生频率高，不一定等eden区满了才触发）
	    老年代存放的都是一些生命周期长的对象，就像上面所叙述的那样，在新生代3经历了N次垃圾回收后仍然存活的对象就会放到此区。
		永久代主要用于存放静态文件，如java类，方法等。
		总结：
		1.在新生代中，每次垃圾收集时都发现有大批对象死去，只有少量存活，那就选用复制算法，只需要付出少量存活对象的复制成本就可
	 完成收集。    2.老年代因为存活率高，没有额外空间对他进行分配担保，就必须用标记-清除或者标记-整理。

6.自定义View,事件分发机制讲一讲
    事件序列
      1.手指接触屏幕后会产生一系列事件，action-down(手指刚接触屏幕)  action-move(手指在屏幕移动) action-up(手指从屏幕上松开)
	    事件传递的顺序：Activity---Window---decor view---我们的layout,viewGroup---我们布局中被点中的子view
	   如果子view没有消耗事件，那最后事件会传回Activity  由Activity处理（activity的onTouchevent调用）
     	三大方法  dispatchTouchEvent  onInterceptEvent onTouchEvent
	    dispatchTouchEvent 用来进行事件的分发，事件传递到当前的view时，此方法会被调用，此方法包含了具体的分发逻辑，返回结果受
        当前view的onTouchEvent方法和下级View的dispatchEvent方法影响。
        onIterceptTouchEvent方法在dispatchTouchEvent内部被调用，用来判断是否拦截某个事件，如果当前view拦截了某个事件，
        那么在同一个事件序列当中，此方法不会被再次调用，返回结果表示是否拦截当前事件。此方法只有viewgroup中有，view没有
        onTouchEvent中用来处理点击事件，返回结果表示是否消耗当前事件，如果不消耗则在同一个事件序列中，当前view无法再次接收事件
        onTouchListener,onTouchEvent,onClicker的优先级
       1.onTouchListener和onTouchEvent都在dispatchOnTOuchEvent中被调用，onClicklistener在onTouchEvent中被调用。
       2.onTouchListener的优先级高于onTouchEvent方法，如果onTouchListener的onTouch方法返回true,则onTouchevent，
         onclicklistener不会调用，
       3.在onTouchEcvent方法中，如果当前view设置了onclicklistener那么onclicklistener的onclick会被调用
       4.只要view的clickable和long_clickable 有一个为true,view就会消耗当前事件，也就是说onTouchEvent方法会返回true
       5.view的long_clickable属性默认为false，而clickable的属性和具体的view有关
       viewgroup的事件分发逻辑
    public boolean dispatchEvent(MotionEvent event){
      boolean consume = false;
      if(onIterceptTouchEvent(event)){
        consume = onTouchEvent();
      }else{
        consume = child.dispatchTouchEvent(event)
      }
      return consume
    }
    从上伪代码中可总结出，
    ViewGroup中的事件分发流程：
    1.事件传递到viewgroup时，dispatchtouchevent会被调用 ，如果这个viewgroup的oniterceptTouchevent方法返回true,则表示它要
      拦截事件，事件就会交给当前viewgroup的onTouchEvent方法处理
    2,如果当前viewgroup的oniterceptTouchEvent返回false,即不拦截事件，则会调用子元素的dispatchTouchEvent，这样就传递给子元素
    3,如果子元素没消耗事件，也就是子元素的dispathchtouchEvent方法返回false，那事件会由当前viewgroup自己处理，当前viewgroup的
      onTouchEvent会被调用，如果当前viewgroup的dispatchEvent返回false,最后就会一层层往上，如果事件一直没消耗，那最后activity
      的 onTouchevent会被调用
    4.需要解释viewgroup继承自view，viewgroup并没有 onTouchevent方法，在所有子元素没消耗事件时，viewgroup会调用父类，也就是
       view的dispatchevent，从而调用onTouchEvent来自己处理事件，如果没消耗掉，dispatchevent会返回false,从而将事件向上层传递
    5.如果action-down事件子元素没处理（onTouchevent返回false） ,那此事件序列其他事件都不会分派给子元素处理
    6.viewgroup默认不拦截任何事件
    7.对于action-down事件，viewgroup每次都会调用onitercepttouchevent方法来判断是否需要拦截事件，一旦确认要拦截，后续的
      action-move和action-up都viewgroup自己处理，不会传递给子view。也不会调用onitercepttouchevent，所以onitercepttouchevent
      方法不是每次事件都会被调用的。
    8.子view可通过requestDisallowInterceptTouchEvent来干预父元素的除了action-down的事件分发过程
      View的事件分发逻辑    -----requestDisallowInterceptTouchEvent
      requestDisallowInterceptTouchEvent方法用于影响父元的事件拦截策略
      requestDisallowInterceptTouchEvent（true）表示不允许父元素拦截事件，这样事件就传递给子view，----大都用于处理滑动冲突
     常用的滑动冲突处理逻辑
        1.利用父布局的onitercepttouchevent.思路是在父布局需要处理事件拦截下来，其他时候不拦截
        对于action-down事件，onitercepttouchevent方法必须返回true,因为一旦返回true,子元素永远也接收不到事件了，还解决毛线
        主要的逻辑在action-move上，是否需要拦截在这写
        对于acTion-up返回false，因为一旦父元素返回true,那view就接收不到action-up事件，也无法触发onclick事件
       2.利用子view的requestDisallowInterceptTouchEvent
       父布局默认拦截除了action-down的所有事件，子view中在dispatchtouchevent方法根据需要来干预父布局的拦截策略，默认不允许
       父布局拦截事件，在需要父布局处理事件时，通过requestDisallowInterceptTouchEvent（false）让父布局处理事件,其他子view处理
       注意：同样的对于action-down事件，onitercepttouchevent必须返回false,其他默认返回true,
         在子view的didpatchtouchevent中，对于action-down通过调用requestDisallowInterceptTouchEvent（true）默认不允许
      父布局拦截事件，这样后续事件交给子view处理
         在子view的dispatchtouchevent，对于action-move事件，默认是子view处理，在需要父布局处理时，调用
      requestDisallowInterceptTouchEvent（false）方法让父布局拦截事件，交给父布局处理。

7.http与https有什么区别
   1.传输信息安全性不同
     http协议：超文本传输协议，信息明文传输，如果攻击者截取web浏览器的网站服务器之间的传输报文，就可直接读懂其信息
     https协议：具有安全性的SSL加密传输协议，为浏览器和服务器的通信加密，确保数据传输安全
   2.连接方式不同
     http:无状态的   https:SSL+HTTP协议构建的可进行加密传输、身份认证的网络协议
   3.端口不同   http  80   https  443

9.静态方法，静态对象为什么不能继承
   java中静态属性和静态方法可以被继承，但没有被重写（overwrite）而是被隐藏
   原因：
   1.静态方法和属性是属于类的，调用的蚨直接通过类名，不需要继承机制也可调用
   2.多态之所以能够实现依赖继承+接口和重写+重载（继承和重写最关键）。有了继承和重写就可实现父类的引用指向子类的对象。
     重写和功能：重写后子类的优先级高于父类的优先级，但隐藏没优先级之分
   3.静态属性：静态方法和非静态的属性都可以继承和隐藏而不能被重写。因此不能实现多态，不能实现父类的引用指向不同子类的对象
      非静态方法可被继承和重写，因此可实现多态。

10.Activity怎么启动Service，Activity与Service交互，Service与Thread的区别

   2种启动方式的不同。如果在Service的onCrete或onStart做了耗时，会影响UI操作或阻塞主线程，最好在Service启动一个线程

   staerService会经历oncreate--onStart(如果Service还没运行，则andRoid先调用oncreate再调用onstart,如service已运行，
   则只调用onstart,所以Service的ondstart可能会多次调用)，stopService直接ondestory,如果调用都自己直接退出而没有调用、
   stopservice，serview会一直在后台运行，

   bindService会经历oncreate--onbind传递给unbindservice的intent对象会传递到onUnbind方法，注意：在service每一次
   开启关闭过程中，只的onstart可被多次调用，其他方法在一个生命周期中只会调用一次

11.介绍一下android动画
   帧动画  补间动画   属性动画

12.Launcher启动App的流程，中间有几种跨进程通信(socket)

13.Handler通信，Binder通信

15.RXJava怎么切换线程


14.你碰到过什么内存泄漏，怎么处理   ----->---->-----使用 LeakCanary 检测 Android 的内存泄漏
  Handler 引起的内存泄漏。
  单例模式引起的内存泄漏
  非静态内部类创建静态实例引起的内存泄漏
  非静态匿名内部类引起的内存泄漏
  注册/反注册未成对使用引起的内存泄漏
  集合对象没有及时清理引起的内存泄漏

16.Fragment hide show生命周期
   当使用hide、show方法来控制Fragment使用时，Fragment生命周期将不执行，在onResume以及onPause方法处理的事情
   将由onHiddenChange进行管理，当Fragment调用hide隐藏时，

   该方法会被调用，传入参数为true，表示该Fragment被隐藏了，当Fragment调用了show方法后，该方法传入的参数为
   false，表示该Fragment正在显示。


17.平常有用到什么锁，synchronized底层原理是什么

 synchronized的特性   原子性+ 可见性+ 有序性+ 可重入性

 所谓原子性就是指一个操作或者多个操作，要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。
 在Java中，对基本数据类型的变量的读取和赋值操作是原子性操作，即这些操作是不可被中断的，要么执行，
 要么不执行。但是像i++、i+=1等操作字符就不是原子性的，它们是分成读取、计算、赋值几步操作，原值在这些步骤
 还没完成时就可能已经被赋值了，那么最后赋值写入的数据就是脏数据，无法保证原子性。
 被synchronized修饰的类或对象的所有操作都是原子的，因为在执行操作之前必须先获得类或对象的锁，直到执行完
 才能释放，这中间的过程无法被中断（除了已经废弃的stop()方法），即保证了原子性。

   可见性是指多个线程访问一个资源时，该资源的状态、值信息等对于其他线程都是可见的。
synchronized和volatile都具有可见性，其中synchronized对一个类或对象加锁时，一个线程如果要访问该类或对象
必须先获得它的锁，而这个锁的状态对于其他任何线程都是可见的，并且在释放锁之前会将对变量的修改刷新到主存当中，
保证资源变量的可见性，如果某个线程占用了该锁，其他线程就必须在锁池中等待锁的释放。
而volatile的实现类似，被volatile修饰的变量，每当值需要修改时都会立即更新主存，主存是共享的，所有线程可见，
所以确保了其他线程读取到的变量永远是最新值，保证可见性。

    有序性值程序执行的顺序按照代码先后执行。
synchronized和volatile都具有有序性，Java允许编译器和处理器对指令进行重排，但是指令重排并不会影响单线程的顺序，
它影响的是多线程并发执行的顺序性。synchronized保证了每个时刻都只有一个线程访问同步代码块，也就确定了线程执行
同步代码块是分先后顺序的，保证了有序性。

    synchronized和ReentrantLock都是可重入锁。当一个线程试图操作一个由其他线程持有的对象锁的临界资源时，将会
处于阻塞状态，但当一个线程再次请求自己持有对象锁的临界资源时，这种情况属于重入锁。通俗一点讲就是说一个线程拥有了
锁仍然还可以重复申请锁

Vivo公司
1.简单描述下Handler,Handler是怎么切换线程的,Handler同步屏障
   从代码层面上来讲，同步屏障就是一个Message，一个target字段为空的Message。
 同步屏障的工作原理:
   同步屏障只在Looper死循环获取待处理消息时才会起作用，也就是说同步屏障在MessageQueue.next函数中发挥着作用。
   当设置了同步屏障之后，next函数将会忽略所有的同步消息，返回异步消息。换句话说就是，设置了同步屏障之后，
   Handler只会处理异步消息。再换句话说，同步屏障为Handler消息机制增加了一种简单的优先级机制，异步消息的优先级要高于同步消息
 同步屏障的应用
 Android应用框架中为了更快的响应UI刷新事件在ViewRootImpl.scheduleTraversals中使用了同步屏障
 void scheduleTraversals() {
     if (!mTraversalScheduled) {
         mTraversalScheduled = true;
         //设置同步障碍，确保mTraversalRunnable优先被执行
         mTraversalBarrier = mHandler.getLooper().getQueue().postSyncBarrier();
         //内部通过Handler发送了一个异步消息
         mChoreographer.postCallback(
                 Choreographer.CALLBACK_TRAVERSAL, mTraversalRunnable, null);
         if (!mUnbufferedInputDispatch) {
             scheduleConsumeBatchedInput();
         }
         notifyRendererOfFramePending();
         pokeDrawLockIfNeeded();
     }
 }
 mTraversalRunnable调用了performTraversals执行measure、layout、draw
 为了让mTraversalRunnable尽快被执行，在发消息之前调用MessageQueue.postSyncBarrier设置了同步屏障


2.Glide的缓存，有用过Glide的什么深入的API，自定义model是在Glide的什么阶段  ------>----重写glidemodel,重写applyOption
   1.with()
     with提供了几种构造方法，可以在源码里看，有Context, Activity, Fragment等，如果传入Application.this
    作为参数时，Glide的加载不受当前Activity生命周期影响，但这会导致即使Activity结束后，仍然继续加载图片。
   2.load()
   load可以加载网络图片链接，也可以加载本地图片地址，以及resourceId, File等等
   3.into()
   into直接传入ImageView;
   4.placeholder()
   在加载过程中设置占位图，可以传入Drawable或resouceId
   5.error()
   加载失败显示的图片，可以传入Drawable或resouceId
   6.override(int width, int height)
   设置加载图片的宽高，像素为单位，在自定义ImageView大小或者计算瀑布流高度时，偶尔会用到。
   7.dontAnimate()
   Glide加载时默认会有淡入淡出的加载效果，该方法可以去掉动画效果，直接显示图片
   8..skipMemoryCache(true)
   跳过图片缓存
   9.diskCacheStrategy(DiskCacheStrategy.ALL)
   设置缓存策略，可选的参数有 ：ALL(缓存所有数据), NONE(不缓存), SOURCE(只缓存源数据), RESULT(只缓存转换后的数据);
   10.priority(Priority.NORMAL)
   加载优先级，优先级越高越先加载
   11.crossFade(int duration)
   加载时淡入淡出动画时间，也可以不传，默认300ms
   12.animate()
   自定义加载动画
   13.thumbnail(0.1f)
   设置缩略图，先加载缩略图再加载完整图片,在大量的图片流列表里,这个方法还是挺好用的, 快速滑动列表时,不致于出现大量的空白.
   14.asBitmap()
   把图片当成bitmap对待，如果是Gif时会停留在第一帧
   15.asGif()
   加载GIF图片，不加该方法时，也可以加载GIF；加了该方法，如果资源不是GIF，会加载失败。
   16.bitmapTransform()
   对图片进行转换，只能用于bitmap
   17.transform()
   对图片进行转换
   16.Glide.get(this).clearDiskCache()
   清理磁盘缓存，需要在子线程中执行
   17.Glide.get(this).clearMemory();
   清理内存缓存，可以直接在主线程执行


3.讲讲mvc,mvp模式，presenter内存泄漏的问题

   p层的耗时任务在页面销毁时是否执行很关键：假设当页面销毁时，presenter层内的任务执行完，由于presenter没有再被内部
   类等持有引用，所以presenter是会被回收的，那view层也不被presenter持有引用，所以即使没在View销毁时清空软引用和置
   View为null，View同样会被销毁，不存在内存泄漏问题

   V层是否被presenter弱引用持有决定V层是否会内存泄漏：假设当页面销毁时，presenter层内的任务在执行, 由于V是被
   presenter弱引用持有，所以V是会被GC回收的，而Presenter由于任务还在执行，所以回收不了

   页面销毁时结束耗时任务可解决presenter和View的内存泄漏， 假设当页面销毁时，即使presenter对View是强引用持有，
   只要此时任务执行完或者解绑Rx的订阅，presenter和View都是可以被回收的，所以不存在内存泄漏

   Rx上游创建异步耗时线程跑，即使取消订阅，还是会内存泄漏，可能Rx不知道开了一个子线程在跑,而子线程持有presenter的引用

   线程的调度放心交给Rx来处理：Rx上游创建异步耗时线程跑，即使取消订阅，还是会内存泄漏，可能Rx不知道开了一个子线程在跑,
   而子线程持有presenter的引用 (这里参考链接 在Rx的上游执行异步耗时任务的测试)

   所以发生泄漏主要在: presenter的引用被rx开辟的线程所持有(或者Model的引用被持有), 从而导致V的引用被持有

   线程运行中，而View界面已经关闭，由于presenter不能被回收(被内部类持有引用)，所以导致presenter内存泄漏

   而如果View是被Presenter强引用持有的话，那View也不能被回收

   而如果View是被presenter弱引用持有的话，那么View是可以被GC回收的

   View的界面销毁, 此时线程运行结束或解除rx的订阅，由于presenter已不再被持有引用，故可GC回收, 而不管View是被
   Presenter强引用还是弱引用，View都会被GC回收

   MVP的内存泄漏可以通过解除Rx的订阅(RxLifecycle2框架或AutoDispose框架)来解决，前提是耗时任务都在Rx里去操作,
   使得Model、View、Presenter不被持有引用，从而可回收


4.ANR了解过吗？有没有实际的ANR定位问题的经历

     1. ANR错误定义：在Android上，如果你的应用程序有一段时间响应不够灵敏，系统会向用户显示一个对话框，这个对话框称
   作“应用程序无响应”（ANR：Application Not Responding）对话框。用户可以选择“等待”而让程序继续运行，也可以
   选择“强制关闭”。因此，在程序里对响应性能的设计很重要，这样，系统不会显示ANR给用户。
   默认情况下，在Android中Activity的最长执行时间是5秒（主要类型），BroadcastReceiver的最长执行时间的则是
   10秒，ServiceTimeout的最长执行时间是20秒（少数类型）。超出就会提示应用程序无响应（ANR错误）。

      2.ANR错误出现原因：只有当应用程序的UI线程响应超时才会引起ANR 超时产生的原因包括：①当前事件没有机会处理，
   例如UI线程正在响应另外的事件，当前事件被某个事件给阻塞掉了；②当前事件正在处理 但是由于耗时太长没有能及时的
   完成。其他原因：③在BroadcastReceiver里做耗时的操作或计算；④CPU使用过高；⑤发生了死锁；⑥耗时操作的动画需要
   大量的计算工作，可能导致CPU负载过重。
ANR定位方式及优化
   1.如果开发机器上出现ANR问题时，系统会生成一个traces.txt的文件放在/data/anr下，最新的ANR信息在最开始部分。
     通过adb命令将其导出到本地，输入以下字符： $adb pull data/anr/traces.txt
   2.为了执行一个长时间的耗时操作而创建一个工作线程最方便高效的方式是使用AsyncTask，只需要继承AsyncTask
     并实现doInBackground()方法来执行任务即可。为了把任务执行的进度呈现给用户，你可以执行publishProgress()方法，
     这个方法会触发onProgressUpdate()的回调方法。在onProgressUpdate()的回调方法中(它执行在UI线程)，你可以执行
     通知用户进度的操作
   3.如果你实现了Thread或者HandlerThread，请确保你的UI线程不会因为等待工作线程的某个任务而去执行Thread.wait()
     或者Thread.sleep()。UI线程不应该去等待工作线程完成某个任务，你的UI线程应该提供一个Handler给其他工作线程，
     这样工作线程能够通过这个Handler在任务结束的时候通知UI线程。
   4.开发在日常的开发过程中使用Thread或者HandlerThread，可以尝试调用
     Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)设置较低的优先级，否则仍然会降低程序响应，
     因为默认Thread的优先级和主线程相同。
   5.Activity的onCreate和onResume回调中尽量避免耗时的代码，应该尽可能的做比较少的事情，其实，任何执行在UI线程
     中的方法都应该尽可能简短快速。类似网络或者DB操作等可能长时间执行的操作，或者是类似调整bitmap大小等需要
     长时间计算的操作，都应该执行在工作线程中
   6.BroadcastReceiver中onReceive代码也要尽量减少耗时。如果必须在onReceive方法中执行耗时操作，建议使用
     IntentService进行处理，IntentService集开启线程和自动关闭服务两种功能于一身
   7.增加界面响应性（交互层面），这是一个成熟应用必备的标志—通常来说，100ms - 200ms是用户能够察觉到卡顿的上限。
     如果你的程序在启动阶段有一个耗时的初始化操作，可以考虑显示一个闪屏，要么尽快的显示主界面，然后马上显示一个
     加载的对话框，异步加载数据。无论哪种情况，你都应该显示一个进度信息，以免用户感觉程序有卡顿的情况

   辅助处理ANR问题的工具：
       Traceview - 系统性能分析工具，用于定位应用代码中的耗时操作


5.性能优化你做过哪些？
   1. 我发现程序在冷启动的时候，会有 1s 左右的白屏闪现，低版本是黑屏的现象，在这期间我通过翻阅系统主题源码，发现
     系统 AppTheme 设置了一个 windowBackground ，由此推断就是这个属性捣的鬼，开始我是通过设置
      windowIsTranslucent 透明属性，发现虽然没有了白屏，但是中间还是有一小段不可见，这个用户体验还是不好的。
     最后我观察了市面上大部分的 Android 软件在冷启动的时候都会有一个  Splash 的广告页，同时在增加一个倒数的计时
     器，最后才进入到登录页面或者主页面。我最后也是这样做的，原因是这样做的好处可以让用户先基于广告对本 APP
     有一个基本认识，而且在倒数的时候也预留给咱们一些对插件和一些必须或者耗时的初始化做一些准备。
   2. 通过翻阅 Application 启动的源码，当我们点击桌面图标进入我们软件应用的时候，会由 AMS 通过 Socket 给
     Zygote 发送一个 fork 子进程的消息，当 Zygote fork 子进程完成之后会通过反射启动 ActivityThread##main
     函数，最后又由 AMS 通过 aidl 告诉 ActivityThread##H 来反射启动创建Application 实例，并且依次执行
     attachBaseContext 、onCreate 生命周期，由此可见我们不能在这 2 个生命周期里做主线程耗时操作。
   3.知道了  attachBaseContext 、onCreate  在应用中最先启动
     项目不及时需要的代码通过异步加载。
     将对一些使用率不高的初始化，做懒加载。
     将对一些耗时任务通过开启一个 IntentService来处理。
   4.Application 启动完之后，AMS 会找出前台栈顶待启动的 Activity , 最后也是通过 AIDL 通知 ActivityThread#H
     来进行对 Activity 的实例化并依次执行生命周期 onCreate、onStart、onRemuse  函数，那么这里由于 onCreate
     生命周期中如果调用了 setContentView 函数，底层就会通过将 XML2View 那么这个过程肯定是耗时的。
     所以要精简 XML 布局代码，尽可能的使用 ViewStub、include 、merge 标签来优化布局。接着在 onResume 声明
     周期中会请求 JNI 接收 Vsync (垂直同步刷新的信号) 请求，16ms 之后如果接收到了刷新的消息，那么就会对
     DecorView 进行 onMeasure->onLayout->onDraw 绘制。最后才是将 Activity 的根布局 DecorView 添加到
     Window 并交于 SurfaceFlinger 显示。
     所以这一步除了要精简 XML 布局，还有对自定义 View 的测量，布局，绘制等函数不能有耗时和导致 GC 的操作。
     最后也可以通过 TreaceView 工具来检测这三个声明周期耗时时间，从而进一步优化，达到极限。
     好处：
        1.  减少 OOM ，可以提高程序的稳定性。---- LeakCanary 这款性能检测工具
        2. 减少卡顿，提高应用流畅性。
            绘制原理：
            Activity--->
              1.onCreate  当activity成功创建，并调用oncreate生命周期时，会执行setcontentview布局id转换
                为view对象的一个过程。
              2.onResume  拿到转换后的view tree 请求vsync垂直同步的信息，收到消息后会执行
                performtraversals函数实现绘制
                包含以下3个方法
                onMeasure  用深度优先的原则递归所有视图的宽高，获取当前view的正确宽高后,可调用它的成员函数
                measure来设置它的大小，如果当前正在测量的子视图child是一个容器，那它又会重复执行操作，直到
                它的所有子视图的大小都测量完毕。
                onLayout  用深度优先原则得到所有视图view的位置，当一个子view在应用程序窗口左上角的位置确定
                后，再结合它在前面测量过中确定的宽高，就可完全确定它在应用程序窗口中的布局。
                onDraw    目前安卓支持2种绘制方式：软件绘制和硬件加速（android3.0已全面支持），硬件加速在
                UI 的显示和绘制的效率远高于CPU的绘制，缺点：1.耗电问题，cpu的功耗比cpu高   2.兼容问题，
                某些接口和函数不支持硬件加速  3，内存大：使用opengl的接口至少需要8MB的内存
            刷新原理:
            View 的 requestLayout 和 ViewRootImpl##setView 最终都会调用 ViewRootImpl 的 requestLayout
            方法，然后通过 scheduleTraversals 方法向 Choreographer 提交一个绘制任务，然后再通过
            DisplayEventReceiver 向底层请求 vsync 垂直同步信号，当 vsync 信号来的时候，会通过 JNI
            回调回来，在通过 Handler 往消息队列 post 一个异步任务，最终是 ViewRootImpl 去执行绘制任务，
            最后调用 performTraversals 方法，完成绘制。
            卡顿的根本原因：1.一个是主线程有其它耗时操作，导致doFrame没机会在vsync信号发出16毫秒内调用
                           2.当前doFrame方法耗时，绘制太久，下一个vsync信号来的时候这一帧还没画完，掉帧
             
        3. 减少内存占用，提高应用后台存活性。
        4. 减少程序异常，降低应用 Crash 率, 提高稳定性。


6.有什么实际解决UI卡顿优化的经历
7.有做过什么Bitmap优化的实际经验
8.项目搭建过程中有什么经验,有用到什么gradle脚本，分包有做什么操作
9.组件化有详细了解过吗？ARouter详细原理
10.讲一下事件分发机制,RecyclerView是怎么处理内部ViewClick冲突的
11.mainfest中配置LargeHeap，真的能分配到大内存吗？
B站
1.一个大致有序的数组如何排序，最快时间复杂度
2.如果叫你实现，你会怎样实现一个多主题的效果
3.如何自定义实现一个FlexLayout
4.tinker的原理是什么,还用过什么热修复框架，robust的原理是什么？
5.说说你对注解的了解，是怎么解析的
6.synchronized是公平锁还是非公平锁,ReteranLock是公平锁吗？是怎么实现的
7.泛型是怎么解析的，比如在retrofit中的泛型是怎么解析的
8.mvp与mvvm的区别，mvvm怎么更新UI,databinding用得多吗,databinding的原理？
9.kotlin ?的原理
10.在项目中有直接使用tcp,socket来发送消息吗
11.如何在网络框架里直接避免内存泄漏，不需要在presenter中释放订阅
12.生命周期都是通过什么调用的？有用过AIDL吗？
13.讲一下RecyclerView的缓存机制,滑动10个，再滑回去，会有几个执行onBindView
14.如何实现RecyclerView的局部更新，用过payload吗,notifyItemChange方法中的参数？
15.讲讲LinkedHashMap的数据结构
16.put post有什么区别
17.A Activity打开B Activity的生命周期变化，会有什么方法打断吗？
18.Fragment hide show生命周期变化
19.Fragment replace生命周期变化
B站二面
1.为什么考虑换工作
2.屏幕适配做过什么工作？
3.你们网络框架用的什么？为什么？
4.如果让你来开发B站的一个页面，哪一个页面可以很快入手
5.性能优化你做过什么工作？
6.h5与native通信你做过什么工作？
7.插件化的主要优点和缺点是什么？
8.你觉得B站目前的APP有什么问题？
9.如果产品要求你开发一个音频播放功能，你会怎么着手？预计会有什么坑？
10.ViewPager切换Fragment什么最耗时？
11.线程间同步的方法
12.锁之间的区别
B站三面
1.介绍一下你自已和项目
2.说说为什么考虑离职
3.说说对你们原来公司的印象
4.为什么想来B站？你在B站上常常在看什么？
5.期望薪资是多少？
6.你对搬到上海有什么想法？
腾讯
1.介绍一下你们项目的架构
2.Rxjava是怎么实现线程切换的
3.Rxjava自定义操作符
4.ARouter的原理
5.ARouter怎么实现接口调用
6.ARouter怎么实现页面拦截
7.MVP怎么处理内存泄漏
8.OkHttp怎么实现连接池
9.如果让你来实现一个网络框架，你会考虑什么
10.你做过什么性能优化的工作
11.热修复的原理，资源的热修复的原理,会不会有资源冲突的问题
12.ViewPager中嵌套ViewPager怎么处理滑动冲突
13.android源码中有哪些设计模式
14.说说binder机制的原理
腾讯二面
1.为什么考虑换一份工作？
2.在你们公司这几年感觉怎么样？
3.技术选型上，为什么这么考虑？从开发效率，产品性能，产品质量，产品体验等方面考虑
4.你们的产品为什么被砍掉，从哪方面考虑
5.在这几年里，你有做过什么觉得最有价值的工作
6.你还有什么要问我的吗？目前有几个offer，倾向性是怎样的？
网易云音乐
1.ViewPager2原理
2.LifeCycle的原理是怎样的？
3.ViewModel为什么在旋转屏幕后不会丢失状态
4.Drawable与View有什么区别,Drawable有哪些子类
5.属性动画更新时会回调onDraw吗？
6.OkHttp网络拦截器，应用拦截器?OKHttp有哪些拦截器，分别起什么作用
7.自定义实现一个九宫格如何实现
8.PathClassLoader与DexClassLoader有什么区别
9.这些年有做一些什么比较难的工作？
10.编译时注解与运行时注解，为什么retrofit要使用运行时注解？什么时候用运行时注解？
11.kotlin lazy使用,lazy viewmodel
12.有没有看一下Google官方的ViewModel demo
13.ViewModel在Activity初始化与在Fragment中初始化，有什么区别？
14.kotlin与Java互相调用有什么问题？
15.retrofit怎么做post请求
16.界面优化的一些方法，ConstraintLayout实现三等分,ConstraintLayout动画.
17.CoordinatorLayout自定义behavior,可以拦截什么？
网易云音乐二面
1.你们的项目中做过什么比较难的工作？
2.视频播放,一个player怎么实现预加载，避免loading
2.webView加载本地图片，如何从安全方面考虑
3.http1.0,http1.1,http1.2有什么区别
4.https与http有什么区别
5.有用过什么加密算法？AES,RAS什么原理？
6.android跨进程通信了解吗？共享内存用过吗？binder怎么验证pid?binder驱动了解吗？
7.SharedParence可以跨进程通信吗？如何改造成可以跨进程通信的.commit和apply的区别.
8.Seriazable与Parceable的区别
9.Bundle是什么数据结构?利用什么传递数据
10.Jvm的内存结构，Jvm的垃圾回收，方法区有什么东西？
11.h5与native交互，webView.loadUrl与webView.evaluateUrl区别
网易云音乐三面
1.有没有做过什么WebView秒开的一些优化
2.你们的项目中有什么难点？
3.native如何对h5进行鉴权，让某些页面可以调，某些页面不能调
4.有看过哪些框架的源码吗？
5.viewModel是怎么实现双向数据绑定的？
6.viewModel怎么实现自动处理生命周期？
7.图片加载优化有什么经验吗？
8.viewpager切换掉帧有什么处理经验？
9.一个wrap_content的ImageView，加载远程图片，传什么参数裁剪比较好?
10.两个getDrawable取得的对象，有什么区别？
11.补间动画与属性动画的区别，哪个效率更高？
12.jsBridge实现方式
13.平常是怎么了解一些新知识与业界动态的，最近有什么印象深刻的文章
14.平常抓包用什么工具？
15.Mvp与Mvvm有什么区别?
跟谁学
1.项目中的Webview与native通信
2.项目中对WebView的功能进行了怎样的增强
3.synchronized跟ReentranLock有什么区别？
4.synchronized与ReentranLock发生异常的场景.
5.算法,删除数组中的重复元素
6.手写双检查单例模式，各个步骤有什么区别
7.Activity生命周期
快手
1.string,equals,==有什么区别
2.AsyncTask内存泄露
3.dispatchTouchEvent,onInterceptEvent,onTouchEvent顺序，关系
4.onMeasure,onLayout,onDraw关系
5.算法题，反转数组
6.算法题，链表求和
7.说说你对协程的理解
8.协程怎么取消
9.说说MVP与MVVM的区别
快手二面
1.算法题，二叉树的最大深度
2.如果android端和IOS端调一个接口，一个通了一个没通，你会如何解决
3.如果android端和IOS端调一个接口，一个比较慢，一个比较快，有什么思路
4.ARouter的原理是什么？如果不用ARouter，你会怎么去解藕。接口？设计接口有什么需要注意的？
5.h5与native交互做过什么工作？
6.登陆功能，登陆成功然后跳转到一个新Activity，中间涉及什么？从事件传递，网络请求,AMS交互角度分析
7.AMS交互调用生命周期是顺序的吗？
8.binder进程间通信可以调用原进程方法吗？
9.mvp与mvvm有什么区别？
10.token放在本地如何保存？如何加密比较好？
快手三面
1.viewModel的原理，为什么可以在Activity销毁后保存数据
2.mvvm双向数据绑定的原理是怎样的？ViewModel
3.说说你们项目中的难点是怎样的？
4.伪代码实现一个长按事件
5.实现一个下载功能的接口
猿辅导
1.泛型有什么优点？
2.动态代理有什么作用？
3.拉圾回收的GCRoot是什么？
4.Handler机制了解吗？一个线程有几个Looper？为什么？
5.你了解协程吗？协程有什么作用？可以完全取代rxjava吗？
6.你们用的什么消息通信机制
7.你的项目有什么难点？介绍一下？
8.算法题，二叉树最长结点集合
猿辅导2面
1.你们项目中的难点是什么？
2.编译期注解处理的是字节码还是java文件
3.你在项目中有用到什么设计模式吗？
4.ARouter的原理是怎样的？
5.插件化的原理是怎样的？
6.算法题，K个一组反转链表
7.广播与RxBus的区别，全局广播与局部广播区别
猿辅导3面
1.你们项目中有什么难点？
2.@JavaScriptInterface为什么不通过多个方法来实现？
3.为什么不利用同步方法来做jsBridge交互？同步可以做异步，异步不能做同步
4.网络封装怎么实现？
5.算法题，不同面值的几个硬币，怎么求满足条件的最小值
斗鱼
1.说说HashMap的原理
2.说说Java的内存分区
3.讲讲你对垃圾回收机制的了解，老年代有什么算法？
4.说说你对volatile字段有什么用途？
5.说说事件分发机制，怎么写一个不能滑动的ViewPager
6.说说你对类加载机制的了解？DexClassLoader与PathClassLoader的区别
7.说说插件化的原理，资源的插件化id重复如何解决？
8.mvp与mvvm模式的区别是什么？
9.JetPack组件用过哪些？lifeCycle的原理是什么？如果在onStart里面订阅，会回调onCreate吗？
10.单例模式有什么缺点？
11.说说App的启动过程,在ActivityThread的main方法里面做了什么事，什么时候启动第一个Activity？
12.说说你对Handler机制的了解，同步消息，异步消息等
13.说说你对屏幕刷新机制的了解，双重缓冲，三重缓冲，黄油模型
14.onCreate,onResume,onStart里面，什么地方可以获得宽高
15.为什么view.post可以获得宽高，有看过view.post的源码吗？
16.attachToWindow什么时候调用？
17.DataBinding的原理了解吗？
滴滴一面
1.JVM类加载机制了解吗，类什么时候会被加载？类加载的过程具体生命周期是怎样的？
2.Handler内存泄漏的GCRoot是什么？
3.动画里面用到了什么设计模式？
4.OkHttp里面用到了什么设计模式？
5.OkHttp连接池是怎么实现的？里面怎么处理SSL？
6.泛型为什么要擦除？kotlin的泛型了解吗？泛型的pecs原则
7.同步屏障
8.性能优化做过什么工作？
9.RecyclerView的缓存结构是怎样的？缓存的是什么？cachedView会执行onBindView吗?
10.RecyclerView嵌套RecyclerView，NestScrollView嵌套ScrollView滑动冲突
11.ViewGroup在Action_Move时onIntercept返回true，事件怎么传递
12.Launcher启动图标，有几个进程？
13.JMM可见性，原子性，有序性，synchronized可以保证什么？
14.源码中有哪里用到了AtomicInt
15.AQS了解吗？
16.Activity内LinearLayout红色wrap_content,包含View绿色wrap_content,求界面颜色
17.ViewModel的使用中有什么坑？
18.有用DSL,anko写过布局吗？
19.HashMap查找的时间复杂度是多少？
20.阿里编程规范不建议使用线程池，为什么？
21.四种线程池原理？
22.了解哪些算法？
23.IdleHandler用过吗？
滴滴二面
1.如何封装一个字符串转数字的工具类
2.如何求当前Activity View的深度
3.多进程怎么实现？如果启动一个多进程APP，会有几个进程运行？
4.反射可以反射final修饰的字段吗？
5.Activity与AppCompactActivity区别，Activity会打包到包里面去吗？
6.如何让两个线程循环交替打印
7.怎么中止一个线程，Thread.Interupt一定有效吗？
8.动画连续调用的原理是什么？
9.做过一些SDK的操作吗？
10.协程可以在Java项目中使用吗？
11.SharedPreference原理？读取xml是在哪个线程?
12.了解APK打包的过程吗？
13.class文件的组成？常量池里面有什么内容？
14.自动装箱发生在什么时候？编译期还是运行期
15.bugly日志收集的原理是什么？
16.启动优化做过什么工作？如果首页就要用到的初始化？
17.DataBinding原理
滴滴3面
1.插件化的原理是什么？有没有什么非运行时插件化的解决方案？
2.ARouter的原理是怎样的？注解处理器是处理java还是字节码
3.java和字节码有什么区别？
4.kotlin空安全的原理是什么？
5.性能优化做过什么工作?有用过什么工具？有没有精确测量的工具？
6.kotlinc与javac编译字节码有什么区别？
7.你在团队中是怎样一个角色？
8.你有没有做什么推进项目的工作
9.说说热修复的原理？
字节跳动
1.你们的项目中有什么难点？
2.你们项目的稳定性如何？有做过什么稳定性优化的工作？
3.WebView性能优化做过什么工作？
4.AIDL in out oneWay代表什么意思？
5.线程池了解多少？拒绝策略有几种,为什么有newSingleThread
6.跨进程通信了解多少？管道了解吗？
7.协程介绍一下，讲一个协程的scope与context，协程的+号代表什么
8.Handler休眠是怎样的？epoll的原理是什么？如何实现延时消息，如果移除一个延时消息会解除休眠吗？
9.算法斐波那契台阶
10.手写生产者消息者模型
11.IdleHandler应用场景
字节跳动2面
1.自定义圆角图片
2.自定义LinearLayout，怎么测量子View宽高
3.setFactory和setFactory2有什么区别？
4.插件化换肤方案
5.插件化的原理，startActivity hook了哪个方法
6.手势操作ActionCancel后怎么取消
7.怎么优化xml inflate的时间，涉及IO与反射。了解compose吗？
8.算法题：二叉树的每一层最左边节点
9.RecyclerView 缓存结构，RecyclerView预取，RecyclerView局部刷新
11.setOnTouchListener,onClickeListener和onTouchEvent的关系

12.Activity的生命周期和启动模式