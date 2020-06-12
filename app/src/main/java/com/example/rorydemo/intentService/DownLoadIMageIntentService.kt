package com.example.rorydemo.intentService

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast
import android.R.attr.path
import com.example.rorydemo.MainActivity
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import org.greenrobot.eventbus.EventBus


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
        Toast.makeText(this,"图片地址${imgUrl}，下载完成",Toast.LENGTH_SHORT).show()

        //通过广播的方式切换线程通信
        /*val intent = Intent(IntentServiceActivity.RESULT_DOWNLOAD_IMAGE)//发送指定的广播
        intent.putExtra(IntentServiceActivity.PARAM_FLAG, imgUrl)//发送广播的同时将对应path传过去
        sendBroadcast(intent)*/

        //发送EventBus
        EventBus.getDefault().post(imgUrl?:"")
    }

}
