package com.showmethe.skinlib

import android.view.View

/**
 * com.example.ken.kmvvm.skin
 * 2020/1/15
 **/

private const val TextView = "androidx.appcompat.widget.AppCompatTextView"
private const val MaterialTextView = "com.google.android.material.textview.MaterialTextView"

enum class ViewType{
    View,TextView,MaterialTextView
}

fun View.viewType (): ViewType {
    val clazz = this::class.java.name
    if( clazz == TextView){
        return ViewType.TextView
    }else if(clazz == MaterialTextView){
        return ViewType.MaterialTextView
    }
    return ViewType.View
}