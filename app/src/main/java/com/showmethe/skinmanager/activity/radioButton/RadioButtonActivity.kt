package com.showmethe.skinmanager.activity.radioButton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.showmethe.skinlib.SkinManager
import com.showmethe.skinmanager.R
import com.showmethe.skinmanager.databinding.ActivityRadioButtonBinding
import com.showmethe.skinmanager.databinding.ActivityTextViewBinding
import kotlinx.android.synthetic.main.activity_radio_button.*

class RadioButtonActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRadioButtonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_radio_button)

        SkinManager.get().bindings(binding)

        change.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                SkinManager.get().switchThemeByName("Theme1")
            }else{
                SkinManager.get().switchThemeByName("Theme2")
            }
        }

    }
}
