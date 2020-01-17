package com.showmethe.skinmanager.activity

import android.content.Context
import androidx.databinding.ObservableArrayList
import com.showmethe.skinlib.SkinManager
import com.showmethe.skinmanager.R
import com.showmethe.skinmanager.adapter.DataBindBaseAdapter
import com.showmethe.skinmanager.databinding.ItemMainCardBinding

/**
 * Author: showMeThe
 * Update Time: 2020/1/17 9:53
 * Package Name:com.showmethe.skinmanager.activity
 */
class MainAdapter(context: Context, data: ObservableArrayList<String>) :
    DataBindBaseAdapter<String, ItemMainCardBinding>(context, data) {
    override fun getItemLayout(): Int = R.layout.item_main_card

    override fun bindItems(binding: ItemMainCardBinding?, item: String, position: Int) {
        SkinManager.get().bindings(binding)
        binding?.bean = item
    }
}