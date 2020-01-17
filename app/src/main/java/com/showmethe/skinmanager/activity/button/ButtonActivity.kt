package com.showmethe.skinmanager.activity.button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.showmethe.skinlib.SkinManager
import com.showmethe.skinmanager.R
import com.showmethe.skinmanager.activity.MainAdapter
import com.showmethe.skinmanager.databinding.ActivityButtonBinding
import kotlinx.android.synthetic.main.activity_text_view.*

class ButtonActivity : AppCompatActivity() {

    private lateinit var binding:ActivityButtonBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_button)

        SkinManager.get().bindings(binding)

    }
}
