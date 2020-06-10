package com.sun.me

import android.content.Intent
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent
import com.billy.cc.core.component.CCUtil



/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/9.
 */
class ComponentMe : IComponent {
    override fun getName(): String = "ComponentMe"

    override fun onCall(cc: CC?): Boolean {

        when(cc?.actionName){
            "useractivity" -> {
                CCUtil.navigateTo(cc, UserActivity::class.java)
                CC.sendCCResult(cc.callId, CCResult.success())
                return false
            }
            "testactivity" -> {
                CCUtil.navigateTo(cc, TestActivity::class.java)
                CC.sendCCResult(cc.callId, CCResult.success())
                return false
            }
        }
        return false
    }

}