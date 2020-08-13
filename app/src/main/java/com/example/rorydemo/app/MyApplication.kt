package com.example.rorydemo.app

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit


/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/8/13.
 */
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        DoraemonKit.install(this);
    }
}