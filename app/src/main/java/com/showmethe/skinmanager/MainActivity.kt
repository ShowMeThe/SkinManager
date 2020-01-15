package com.showmethe.skinmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.showmethe.skinmanager.databinding.ActivityMainBinding

/**
 * Author: showMeThe
 * Update Time: 2020/1/15
 * Package Name:com.showmethe.skinmanager
 */
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


         
    }
}
