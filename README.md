# Android-library

This is an Android dependent library
------------------------------------

![框架图](https://github.com/pengxianhong/Android-library/blob/master/demoImage/androidlib.png)

大致的框架的明细列了下，方便有幸看到我的库的朋友。这个库是我开发过程中遇到需求而该进来的的或者是我无意间发现的其他优秀的依赖库，所以，我就整合在一起，本意是方便自己，但是如果小伙伴们有幸找的我的库，并且有助于你的开发，请记得star，当然我不是在线求star，还有就是，如果有问题，你们可以提issue啊，我会尽力修改的。好了，废话说了这么多，接下来看下各个类所带来的效果吧，show
time！

### [![Jitpack](https://jitpack.io/v/pengxianhong/Android-library.svg)](https://jitpack.io/#pengxianhong/Android-library)


# 引入

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```

### Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.pengxianhong:Android-library:1.5.1'
}
```

# 几个Demo

### 1. EasyToast

在BaseApplication中做初始化，否则会报空指针

```java
EasyToast.init(this);
```

在你项目任何地方使用（如果是在子线程需要自己管理消息队列，否则会抛异常）

```java
//默认提示
EasyToast.showToast("MainActivity", EasyToast.DEFAULT);
//成功提示
EasyToast.showToast("MainActivity", EasyToast.SUCCESS);
//警告提示
EasyToast.showToast("MainActivity", EasyToast.WARING);
//错误提示
EasyToast.showToast("MainActivity", EasyToast.ERROR);
```

效果图

![EasyToast](https://github.com/pengxianhong/Android-library/blob/master/demoImage/toast_0.jpg)
![EasyToast](https://github.com/pengxianhong/Android-library/blob/master/demoImage/toast_1.jpg)

![EasyToast](https://github.com/pengxianhong/Android-library/blob/master/demoImage/toast_2.jpg)
![EasyToast](https://github.com/pengxianhong/Android-library/blob/master/demoImage/toast_3.jpg)

### 2. CircleImageView

在你项目xml如下使用

```xml
<com.pengxh.app.multilib.widget.CircleImageView
        android:id="@+id/starCircleImage"
        android:layout_width="320px"
        android:layout_height="320px"
        android:src="@drawable/test"
        app:border_color="@color/colorMain"
        app:border_width="1dp" />
```

效果图

![CircleImageView](https://github.com/pengxianhong/Android-library/blob/master/demoImage/circleImage.jpg)

### 3. SwipeMenuListView

首先这个库要先感谢[baoyongzhang](https://github.com/baoyongzhang/SwipeMenuListView)。这个库作者已经不再更新，但是由于该库效果比较出众，所以，我就收录过来了，这里简单说下该库的使用方法，详细介绍可以看原库。

在你项目xml如下使用

```xml
<com.pengxh.app.multilib.widget.swipemenu.SwipeMenuListView
        android:id="@+id/swipeListView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:divider="@drawable/list_divider"
        android:dividerHeight="1px" />
```

添加侧滑按钮

```java
swipeListView.setMenuCreator(menu -> {
    SwipeMenuItem reBindItem = new SwipeMenuItem(activity);
    reBindItem.setBackground(new ColorDrawable(Color.rgb(91, 139, 255)));
    reBindItem.setWidth(DensityUtil.dip2px(100.0f));
    reBindItem.setTitle("重新绑定");
    reBindItem.setTitleSize(18);
    reBindItem.setTitleColor(Color.WHITE);
    menu.addMenuItem(reBindItem);

    SwipeMenuItem deleteItem = new SwipeMenuItem(activity);
    deleteItem.setBackground(new ColorDrawable(Color.rgb(251, 81, 81)));
    deleteItem.setWidth(DensityUtil.dip2px(70.0f));
    deleteItem.setTitle("删除");
    deleteItem.setTitleSize(18);
    deleteItem.setTitleColor(Color.WHITE);
    menu.addMenuItem(deleteItem);
});
```

侧滑按钮事件监听

```java
swipeListView.setOnMenuItemClickListener((position, menu, index) -> {
    switch (index) {
        case 0:
            //TODO
            break;
        case 1:
            //TODO
            break;
        }
    return false;
});
```

既然是侧滑删除列表，当然他的列表项也是可以点击的

```java
swipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //TODO
    }
});
```

效果图

![SwipeMenuListView](https://github.com/pengxianhong/Android-library/blob/master/demoImage/swipeListView_0.jpg)
![SwipeMenuListView](https://github.com/pengxianhong/Android-library/blob/master/demoImage/swipeListView_1.jpg)

### 4. SpeedRecyclerView

这个库要先感谢[huazhiyuan2008](https://github.com/huazhiyuan2008/RecyclerViewCardGallery)。这个库是我在开发过程中，正好公司需要用到3D效果的画廊，在github上面找了好久，才发现这个库最符合我项目的要求，所以就直接拿来用了，该库作者虽然没有明说停止更新，但是我看这个库最后一次更新是两年前，所以，为了方便自己，我就把这个库clone下来，弄到了我这个里面了，如果觉得好用，可以去原作者那里star，感谢！效果我就不贴了，原库里面都有。

### 接下来这几个对话框是我项目中用到的，都是仿iOS效果，所以我就一并弄上来了，虽然是我项目开发需要的，但是都是采用建造者模式实现的，用得着的朋友也是可以直接拿去用的，实在不行，改巴改巴然后再用应该问题不大。这几个库的使用方式都是采用链式调用，使用方法都是一样的，所以仅列举一例:

```java
new InputDialog.Builder()
            .setContext(this)
            .setTitle("请输入内容")
            .setCancelable(false)
            .setPositiveButton("确定")
            .setNegativeButton("取消")
            .setOnDialogClickListener(new InputDialogClickListener())
            .build()
            .show();
```

```java
class InputDialogClickListener implements InputDialog.onDialogClickListener {

    @Override
    public void onConfirmClick(Dialog dialog, String input) {
        if (!TextUtils.isEmpty(input)) {

        } else {

        }
    }

    @Override
    public void onCancelClick(Dialog dialog) {
        dialog.dismiss();
    }
}
```

### 5. InputDialog

效果图

![InputDialog](https://github.com/pengxianhong/Android-library/blob/master/demoImage/inputDialog.jpg)

### 6. MultiSelectDialog

效果图

![MultiSelectDialog](https://github.com/pengxianhong/Android-library/blob/master/demoImage/multiSelectDialog.jpg)

### 7. PermissionDialog

效果图

![PermissionDialog](https://github.com/pengxianhong/Android-library/blob/master/demoImage/permissionDialog.jpg)

---

接下来说下utils包里面的工具类的使用，我尽可能的做到使用简单。

### 1. BroadcastManager

BroadcastManager.getInstance(this)最好是先得到BroadcastManager对象manager，然后再做其他操作。

发广播

```java
manager.sendBroadcast("此处换成你自己广播的Action", "数据");
```

收广播。支持单个广播和多个广播Action

```java
manager.addAction("此处换成你自己广播的Action", new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {

    }
});
```

销毁广播。支持单个广播和多个广播Action

```java
manager.destroy("此处换成你自己广播的Action");
```

### 2. DensityUtil

这个类主要是用来获取硬件设备尺寸相关，比如横向、纵向像素，硬件设备dpi值等，还可以将px和dp相互转换，这里就简单说下使用方法

```java
HashMap<String, Integer> displaySize = DensityUtil.getDisplaySize(this);
```

### 3. FileUtil

刚好我只有kotlin项目里面用到了这个工具，所以就用kt语言介绍用法，Java也是如此使用

```kotlin
val file = File(context!!.cacheDir.absolutePath)
val fileSize = FileUtil.getFileSize(file)
cacheSize.text = FileUtil.formatFileSize(fileSize)
```

### 4. LogToFile

将Log日志写入文件中。之所以要写这个工具，主要是我之前做过一个串口通信的项目，没法实时查看log，没办法，我只能把log存到文件中，报错之后再打开文件去查看什么原因。没有集成Bugly的原因是内网没法用，所以，只能存文件。至于这个工具的使用是和Android自带的Log一样的用法，这就不多说了。

### 5. NotificationHelper

这个是最近才加上来的一个帮助类。8.0之后通知使用方式变了，所以为了一劳永逸，就撸了一个工具类，用法如下：

发通知

```java
NotificationHelper notificationHelper = NotificationHelper.getInstance(this);
Notification notification = notificationHelper.createDefault("", "");
notificationHelper.push(100, notification);
```

取消通知

```java
notificationHelper.cancel(100);
```

### 6. SaveKeyValues

这个类是我自己用的比较多的一个工具，主要是用法简单，不过用之前需要在BaseApplication中全局初始化一下

```java
SaveKeyValues.initSharedPreferences(this);
```

存数据

```java
SaveKeyValues.putValue("key","value");
```

取数据

```java
SaveKeyValues.getValue("key","");
```

### 7. TimeUtil

最后提一句这个工具，时间格式化工具，其实用的也挺多的，所以干脆工具化了

```java
TimeUtil.timestampToTime(System.currentTimeMillis(), TimeUtil.TIME);
```

### 本库中其他未提及到的工具有兴趣的小伙伴可以自行玩玩，项目中应该也是可以用的上的。

### 暂时就这么多了，本库会一直更新下去，会一直收集或者自定义一些简单且效果不错的控件和工具，既能方便自己，说不定还能方便大家，何乐而不为呢~~~
