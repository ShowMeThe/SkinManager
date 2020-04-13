package com.showmethe.skinmanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.GridLayoutManager
import com.showmethe.skinlib.SkinManager
import com.showmethe.skinmanager.R
import com.showmethe.skinmanager.activity.button.ButtonActivity
import com.showmethe.skinmanager.activity.radioButton.RadioButtonActivity
import com.showmethe.skinmanager.activity.textview.TextViewActivity
import com.showmethe.skinmanager.adapter.GridSpaceItemDecoration
import com.showmethe.skinmanager.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.change
import kotlinx.android.synthetic.main.activity_text_view.*

/**
 * Author: showMeThe
 * Update Time: 2020/1/15
 * Package Name:com.showmethe.skinmanager
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val list = ObservableArrayList<String>()
    private lateinit var adapter:MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )



        list.add("TextView")
        list.add("Button")
        list.add("RadioButton")
        adapter = MainAdapter(this,list)
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(this,2)
        rv.addItemDecoration(GridSpaceItemDecoration(2,30))




        adapter.setOnItemClickListener { view, position ->
            when(position){
                0-> start<TextViewActivity>()
                1-> start<ButtonActivity>()
                2-> start<RadioButtonActivity>()
            }
        }

    }
}
