package com.showmethe.skinlib

import android.view.View

/**
 * com.example.ken.kmvvm.skin
 * 2020/1/15
 **/

private const val TextView = "androidx.appcompat.widget.AppCompatTextView"
private const val MaterialTextView = "com.google.android.material.textview.MaterialTextView"

private const val Button = "androidx.appcompat.widget.AppCompatButton"
private const val MaterialButton = "com.google.android.material.button.MaterialButton"

enum class ViewType{
    View,TextView,MaterialTextView,Button,MaterialButton
}

fun View.viewType (): ViewType {
    return when (this::class.java.name) {
        TextView -> {
            ViewType.TextView
        }
        MaterialTextView -> {
            ViewType.MaterialTextView
        }
        Button -> {
            ViewType.Button
        }
        MaterialButton -> {
            ViewType.MaterialButton
        }
        else -> ViewType.View
    }
}