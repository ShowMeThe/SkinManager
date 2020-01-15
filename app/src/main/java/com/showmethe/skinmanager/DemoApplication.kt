package com.showmethe.skinmanager

import android.app.Application
import com.showmethe.skinlib.SkinManager

/**
 * Author: showMeThe
 * Update Time: 2020/1/15
 * Package Name:com.showmethe.skinmanager
 */
class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SkinManager.init(this).addStyle(
            "Theme2" to R.style.Theme1,
            "Theme1" to R.style.Theme1).build()

    }
}