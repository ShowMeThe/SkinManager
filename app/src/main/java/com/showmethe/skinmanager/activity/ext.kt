package com.showmethe.skinmanager.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * Author: showMeThe
 * Update Time: 2020/1/17 10:14
 * Package Name:com.showmethe.skinmanager.activity
 */

inline fun <reified T> Context.start(bundle: Bundle? = null){
    val intent = Intent(this,T::class.java)
    bundle?.apply { intent.putExtras(this) }
    startActivity(intent)
}