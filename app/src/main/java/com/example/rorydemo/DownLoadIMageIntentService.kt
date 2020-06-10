package com.example.rorydemo

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/4.
 */

class DownLoadIMageIntentService:IntentService(null){

    override fun onHandleIntent(intent: Intent?) {
         kotlin.runCatching {
             var imgUrl=intent?.getStringExtra("imgUrl")
             downLoadImage(imgUrl)
         }
    }

    private fun downLoadImage(imgUrl: String?) {
        Thread.sleep(3000)
        Log.d("test","图片地址${imgUrl}，下载完成")
    }

}
