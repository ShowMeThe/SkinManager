package com.showmethe.skinlib

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.ArrayMap
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.collections.ArrayList

/**
 *  com.example.ken.kmvvm.skin
 *  2020/1/14
 *  23:17
 */
class SkinManager private constructor(var context: Context){

    companion object{
        private var instant: SkinManager? = null

        @Synchronized
        fun init(context: Context) : SkinManager {
            if(instant == null){
                instant = SkinManager(context)
            }
            return instant!!
        }

        fun get() : SkinManager {
            return instant!!
        }

        private val attrs = intArrayOf(
            R.attr.theme_text_color,R.attr.theme_text_background,
                R.attr.theme_text_backgroundColor,R.attr.theme_text_drawableTint)


        private val styleName = arrayOf("theme_text_color","theme_text_background",
                "theme_text_backgroundColor","theme_text_drawableTint")


        fun patchView(view: View,attr:String){
            get().patchView(view, attr)
        }

    }

    var enableSkin = false
       set(value){
       field = value
           currentStyle = styles[1].first
       }

    private val styles = ArrayList<Pair<String,Int>>()
    private val themes = ArrayMap<String,ArrayMap<String,Int>>()
    private var currentStyle = ""

    fun addStyle(vararg style:Pair<String,Int>) : SkinManager {
        styles.addAll(style.toList())
        return  this
    }

    fun build(){
        buildTheme()
    }

    private fun  buildTheme(){
        if(styles.isEmpty()) return
        styles.forEach {
            if(themes[it.first] == null){
                themes[it.first] = obtainAttr(it.second)
            }
        }
        currentStyle = styles[0].first
    }


    private fun obtainAttr(style:Int) : ArrayMap<String,Int>{
        val map = ArrayMap<String,Int>()
        val wrapper = ContextThemeWrapper(context,style)
        val array  = wrapper.obtainStyledAttributes(style, attrs)
        for(i in attrs.indices){
            map[styleName[i]]= array.getResourceId(i,-1)
        }
        array.recycle()
        return map
    }

    fun patchView(view: View,attr:String){
        val attrs = attr.split("|")
        if(attr.isEmpty()) return
        when(view.viewType()){
            ViewType.TextView, ViewType.MaterialTextView ->{
                val tv = view as TextView
                val theme = themes[currentStyle]
                 theme?.apply {
                    attrs.forEach {
                        when {
                            it.trim() == "textColor" -> {
                                tv.setTextColor(this["theme_text_color"].getColorStateList())
                            }
                            it.trim()== "background" -> {
                                tv.background = this["theme_text_background"].getDrawable()
                            }
                            it.trim() == "backgroundColor" -> {
                                tv.setBackgroundColor(this["theme_text_backgroundColor"].getColor())
                            }
                            it.trim() == "drawableTint" -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    tv.compoundDrawableTintList = this["theme_text_drawableTint"]!!.getColorStateList()
                                }else{
                                    tv.compoundDrawables.forEach { drawable ->
                                        drawable?.apply {
                                            this.setTintList(theme["theme_text_drawableTint"]!!.getColorStateList())
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else ->{

            }
        }
    }




    private fun Int?.getDrawable() : Drawable?{
        return ContextCompat.getDrawable(context,this!!)
    }

    private fun Int?.getColor() : Int{
        return ContextCompat.getColor(context,this!!)
    }

    private fun Int?.getColorStateList() : ColorStateList?{
        return ContextCompat.getColorStateList(context,this!!)
    }



}
