package com.sun.me

import android.app.Application
import com.billy.cc.core.component.CC

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/9.
 */
class DebugApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CC.enableDebug(BuildConfig.DEBUG);
        CC.enableVerboseLog(BuildConfig.DEBUG);
        CC.enableRemoteCC(BuildConfig.DEBUG);
    }
}