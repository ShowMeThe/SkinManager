package com.showmethe.skinlib

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.ArrayMap
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding

/**
 * Author: showMeThe
 * Update Time: 2020/1/17 14:28
 * Package Name:com.showmethe.skinlib
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
                R.attr.theme_text_backgroundColor,R.attr.theme_text_drawableTint,

            R.attr.theme_button_textColor,R.attr.theme_button_background,
            R.attr.theme_button_backgroundColor,R.attr.theme_button_drawableTint,R.attr.theme_button_iconTint,

            R.attr.theme_radio_textColor,R.attr.theme_radio_background,
            R.attr.theme_radio_backgroundColor,R.attr.theme_radio_drawableTint,R.attr.theme_radio_buttonTint

            )


        private val styleName = arrayOf(

            "theme_text_color","theme_text_background",
                "theme_text_backgroundColor","theme_text_drawableTint",

            "theme_button_textColor",
            "theme_button_background", "theme_button_backgroundColor",
            "theme_button_drawableTint","theme_button_iconTint",

            "theme_radio_textColor","theme_radio_background",
            "theme_radio_backgroundColor","theme_radio_drawableTint","theme_radio_buttonTint"
            )


        fun patchView(view: View,attr:String){
            get().patchView(view, attr)
        }

    }

    var enableSkin = false

    private val bindings = ArrayList<ViewDataBinding?>()
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

    }

    fun bindings(binding: ViewDataBinding?){
        bindings.add(binding)
    }


    fun invalidateAll(){
        bindings.forEach {
            it?.apply {
                invalidateAll()
            }
        }
    }

    fun switchThemeByName(name:String){
        enableSkin = true//开启
        for(style in styles){
            if(style.first == name){
                currentStyle = style.first
                break
            }
        }
        invalidateAll()
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
        if(!enableSkin) return
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
            ViewType.Button, ViewType.MaterialButton ->{
                val button = view as Button
                val theme = themes[currentStyle]
                theme?.apply {
                    attrs.forEach {
                        when {
                            it.trim() == "textColor" -> {
                                button.setTextColor(this["theme_button_textColor"].getColorStateList())
                            }
                            it.trim()== "background" -> {
                                if(view.viewType() == ViewType.MaterialButton){
                                    button.backgroundTintList = this["theme_button_background"].getColorStateList()
                                }else{
                                    button.background = this["theme_button_background"].getDrawable()
                                }
                            }
                            it.trim() == "backgroundColor" -> {
                                if(view.viewType() == ViewType.MaterialButton){
                                    button.backgroundTintList = this["theme_button_backgroundColor"].getColorStateList()
                                }else{
                                    button.setBackgroundColor(this["theme_button_backgroundColor"].getColor())
                                }
                            }
                            it.trim() == "drawableTint" -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    button.compoundDrawableTintList = this["theme_button_drawableTint"]!!.getColorStateList()
                                }else{
                                    button.compoundDrawables.forEach { drawable ->
                                        drawable?.apply {
                                            this.setTintList(theme["theme_button_drawableTint"]!!.getColorStateList())
                                        }
                                    }
                                }
                            }
                            it.trim() == "iconTint" ->{
                                  view::class.java.methods.filter { method -> method.name == "setIconTint" }
                                      .get(0).invoke(button,this@apply["theme_button_iconTint"]!!.getColorStateList())
                            }
                        }
                    }
                }
            }
            ViewType.RadioButton, ViewType.MaterialRadioButton ->{
                val button = view as RadioButton
                val theme = themes[currentStyle]
                theme?.apply {
                    attrs.forEach {
                        when {
                            it.trim() == "textColor" -> {
                                button.setTextColor(this["theme_radio_textColor"].getColorStateList())
                            }
                            it.trim()== "background" -> {
                                if(view.viewType() == ViewType.MaterialButton){
                                    button.backgroundTintList = this["theme_radio_background"].getColorStateList()
                                }else{
                                    button.background = this["theme_radio_background"].getDrawable()
                                }
                            }
                            it.trim() == "backgroundColor" -> {
                                if(view.viewType() == ViewType.MaterialButton){
                                    button.backgroundTintList = this["theme_radio_backgroundColor"].getColorStateList()
                                }else{
                                    button.setBackgroundColor(this["theme_radio_backgroundColor"].getColor())
                                }
                            }
                            it.trim() == "drawableTint" -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    button.compoundDrawableTintList = this["theme_radio_drawableTint"]!!.getColorStateList()
                                }else{
                                    button.compoundDrawables.forEach { drawable ->
                                        drawable?.apply {
                                            this.setTintList(theme["theme_radio_drawableTint"]!!.getColorStateList())
                                        }
                                    }
                                }
                            }
                            it.trim() == "buttonTint" ->{
                                button.buttonTintList = this["theme_radio_buttonTint"]!!.getColorStateList()
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
