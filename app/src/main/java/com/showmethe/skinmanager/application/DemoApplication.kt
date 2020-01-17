package com.showmethe.skinmanager.application

import android.app.Application
import com.showmethe.skinlib.SkinManager
import com.showmethe.skinmanager.R

/**
 * Author: showMeThe
 * Update Time: 2020/1/15
 * Package Name:com.showmethe.skinmanager
 */
class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SkinManager.init(this).addStyle(
            "Theme1" to R.style.Theme1,
            "Theme2" to R.style.Theme2
        ).build()

    }
}