## 要点提醒：
### 1 ···  基于Databinding下，进行统一管理颜色切换(如果好用，后面会继续维护这个库)
### 2 ...  实验效果看https://github.com/ShowMeThe/MaterialWanAndroid 的效果
<img src="https://github.com/ShowMeThe/WanAndroid/blob/master/theme1.gif" width ="200" alt="GZA9mT.gif" border="0" /></br></br>

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
     <style name="MaterialTheme.Blue">
         <item name="theme_text_color">@color/colorAccent</item>
         <item name="theme_viewGroup_backgroundColor">@color/colorAccent</item>
         <item name="theme_viewGroup_background">@drawable/shape_drawer_head_bg</item>
         <item name="theme_button_rippleColor">@color/color_5f4fc3f7</item>
         <item name="theme_button_iconTint">@color/colorAccent</item>
         <item name="theme_button_textColor">@color/colorAccent</item>
         <item name="theme_button_strokeColor">@color/colorAccent</item>
         <item name="theme_bottom_navigation_iconTint">@color/colorAccent</item>
         <item name="theme_bottom_navigation_textColor">@color/colorAccent</item>
         <item name="theme_imageView_tint">@color/colorAccent</item>
         <item name="theme_card_strokeColor">@color/colorAccent</item>
         <item name="theme_floating_backgroundColor">@color/colorAccent</item>
         <item name="theme_edit_cursorDrawable">@drawable/shape_blue_cursor</item>
         <item name="theme_inputLayout_boxColor">@color/colorAccent</item>
         <item name="theme_inputLayout_hintColor">@color/colorAccent</item>
         <item name="theme_edit_highlightColor">@color/colorAccent</item>
    </style>


    <style name="MaterialTheme.Red">
        <item name="theme_text_color">@color/color_304ffe</item>
        <item name="theme_viewGroup_backgroundColor">@color/color_304ffe</item>
        <item name="theme_viewGroup_background">@drawable/shape_drawer_head_bg_red</item>
        <item name="theme_button_rippleColor">@color/color_5fff4081</item>
        <item name="theme_button_iconTint">@color/color_ff4081</item>
        <item name="theme_button_textColor">@color/color_ff4081</item>
        <item name="theme_button_strokeColor">@color/color_ff4081</item>
        <item name="theme_bottom_navigation_iconTint">@color/color_ff4081</item>
        <item name="theme_bottom_navigation_textColor">@color/color_ff4081</item>
        <item name="theme_imageView_tint">@color/color_ff4081</item>
        <item name="theme_card_strokeColor">@color/color_ff4081</item>
        <item name="theme_floating_backgroundColor">@color/color_ff4081</item>
        <item name="theme_edit_cursorDrawable">@drawable/shape_red_cursor</item>
        <item name="theme_inputLayout_boxColor">@color/color_ff4081</item>
        <item name="theme_inputLayout_hintColor">@color/color_ff4081</item>
        <item name="theme_edit_highlightColor">@color/color_ff4081</item>
    </style>

 ........省略..........
</resources>
```
4、在Application中初始化，支持自定义的view,需要另外实现Plugin的接口，支持外部Json
```
 val json = AssetFile.getJson(this,"orange.json")
        val colorEntity = json.fromJson<ColorEntity>()!!
        val json2 = AssetFile.getJson(this,"yellow.json")
        val colorEntity2 = json2.fromJson<ColorEntity>()!!

SkinManager.init(this).addStyle(
            themes_name[0] to R.style.MaterialTheme_Blue,
            themes_name[1] to R.style.MaterialTheme_Red,
            themes_name[2] to R.style.MaterialTheme_Purple)
            .addJson(themes_name[3] to colorEntity, themes_name[4] to colorEntity2)
            .addPlugin(
                RefreshPlugin(),SmartIPlugin(),
                ExpandIPlugin(), SearchChipGroup(),
                bannerPlugin, ShakingImageViewIPlugin(),
                WaveIPlugin())
            .build()
```

