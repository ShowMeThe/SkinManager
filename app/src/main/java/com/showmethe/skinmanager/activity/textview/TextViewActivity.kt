package com.showmethe.skinmanager.activity.textview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.showmethe.skinlib.SkinManager
import com.showmethe.skinmanager.R
import com.showmethe.skinmanager.databinding.ActivityTextViewBinding
import kotlinx.android.synthetic.main.activity_text_view.*

class TextViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTextViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_text_view)

        SkinManager.get().bindings(binding)


    }
}
