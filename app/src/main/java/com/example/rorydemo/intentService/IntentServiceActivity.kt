package com.example.rorydemo.intentService

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rorydemo.R
import kotlinx.android.synthetic.main.activity_intent_service.*
import android.content.IntentFilter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/10.
 * 使用广播和Service进行通讯
 */
class IntentServiceActivity :AppCompatActivity(){
    var index = 0
    companion object{
        val RESULT_DOWNLOAD_IMAGE = "com.example.rorydemo.intentService.result.DOWNLOAD_IMAGE"
        val PARAM_FLAG = "taskId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)
        EventBus.getDefault().register(this)
        btn.setOnClickListener {
            var intent= Intent(this, DownLoadIMageIntentService::class.java).apply {
                putExtra("imgUrl","http://${++index}.png")
            }
            this.startService(intent)
            ll_content.addView(TextView(this).apply {
                text = "http://${index}.png"
                tag = "http://${index}.png"
            })
        }

        //注册广播
        /*val filter = IntentFilter(RESULT_DOWNLOAD_IMAGE)
        registerReceiver(receiver,filter)*/
    }

    /**
     * 广播接收者
     */
    private var receiver = object :BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
           var downLoadUrl= p1?.getStringExtra(PARAM_FLAG)
            var view =ll_content.findViewWithTag<TextView>(downLoadUrl)
            view.text = downLoadUrl + " is successful!!"
        }

    }

    /**
     * EventBus
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(downLoadUrl:String){
        //var downLoadUrl= p1?.getStringExtra(PARAM_FLAG)
        var view =ll_content.findViewWithTag<TextView>(downLoadUrl)
        view.text = downLoadUrl + " is successful!!"
    }

    override fun onDestroy() {
        super.onDestroy()
        //注销广播
        //unregisterReceiver(receiver)
        EventBus.getDefault().unregister(this)
    }
}