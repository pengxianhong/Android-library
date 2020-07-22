# Android-library
This is an Android dependent library
------
![框架图](https://github.com/pengxianhong/Android-library/blob/master/demoImage/androidlib.png)  
大致的框架的明细列了下，方便有幸看到我的库的朋友。这个库是我开发过程中遇到需求而该进来的的或者是我无意间发现的其他优秀的依赖库，所以，我就整合在一起，本意是方便自己，但是如果小伙伴们有幸找的我的库，并且有助于你的开发，请记得star，当然我不是在线求star，还有就是，如果有问题，你们可以提issue啊，我会尽力修改的。好了，废话说了这么多，接下来看下各个类所带来的效果吧，show time！  
### [![Jitpack](https://jitpack.io/v/pengxianhong/Android-library.svg)](https://jitpack.io/#pengxianhong/Android-library)  
# 引入  
### Step 1. Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:  
```
repositories {
    maven { url 'https://jitpack.io' }
}
```
### Step 2. Add the dependency  
```
dependencies {
    implementation 'com.github.pengxianhong:Android-library:1.5'
}
```
# 几个Demo
### 1. EasyToast
在BaseApplication中做初始化，否则会报空指针
```
EasyToast.init(this);
```
在你项目任何地方使用（如果是在子线程需要自己管理消息队列，否则会抛异常）
```
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
