## 要点提醒：
### 1 ···  基于Databinding下，进行统一管理颜色切换（实验）
### 2 ...  本库尚未开发完成，希望各位看官路过后留下你的意见，欢迎PR

## 设计构想：  
1、讲解设计思路和用发前先看一下预览的GIF:</br>
<img src="https://github.com/ShowMeThe/SkinManager/blob/master/app/gif/screen.gif" height="533" width ="300" alt="screen"/></br>
</br>
</br>
2、借助Databinding的BindAdapter和Kotlin的拓展方法，实现对布局文件xml的颜色切换处理

```
val TAG = "SkinManager"
//针对Widget
@BindingAdapter("skin")
fun View.skin(url:String){
    Log.d(TAG,this::class.java.name)
    SkinManager.patchView(this,url)
}
//针对ViewGroup
@BindingAdapter("skin")
fun ViewGroup.skin(url:String){
    Log.d(TAG,this::class.java.name)
    SkinManager.patchView(this,url)
}
```
3、attr设计
自定义了部分属性,然后在style中配置对应的属性
```
<resources>
    <!--TextView & MaterialTextView-->
    <attr name="theme_text_color" format="reference|color"/>
    <attr name="theme_text_background" format="reference"/>
    <attr name="theme_text_backgroundColor" format="reference|color"/>
    <attr name="theme_text_drawableTint" format="reference|color"/>
 
 ........省略..........
</resources>
```
4、初始化,可以不在Application中初始化
```
class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this).addStyle(
            "Theme1" to R.style.Theme1,
            "Theme2" to R.style.Theme2
        ).build()
    }
}
```

## TODO:  
Widget和ViewGroup未开发进度：</br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>ViewGroup</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>TextView</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>MaterialTextView</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>Button</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>MaterialButton</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>RadioButton</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>MaterialTextView</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>MaterialRadioButton</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>CardView</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<del>MaterialCardView</del></br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;CheckButton</br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;ImageView</br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Slide</br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;EditText</br>
