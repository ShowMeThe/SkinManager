package com.example.ken.kmvvm.skin

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.showmethe.skinlib.SkinManager

/**
 *  com.example.ken.kmvvm.skin
 *  2020/1/14
 *  22:23
 */


@BindingAdapter("skin")
fun View.skin(url:String){
    SkinManager.patchView(this,url)
}

@BindingAdapter("skin")
fun ViewGroup.skin(url:String){

}