## 效果图:
![](https://github.com/Brioal/LineBreadGroup/blob/master/device-2017-04-30-222705.png)
## 使用方法
### 1.在项目的build.gradle文件做如下修改
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### 2.在app的build.gradle内作如下修改
```
dependencies {
      ...
	    compile 'com.github.Brioal:LineBreadGroup:1.0'
      ...
}
```
## 使用方法与普通ViewGroup一样,添加的View默认横向排序并且自动换行
## 完毕
