package com.example.rorydemo.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import com.example.rorydemo.R
import com.example.rorydemo.User
import com.example.rorydemo.utils.viewModelOf
import kotlinx.android.synthetic.main.launch_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/24.
 */
class LaunchActivity :AppCompatActivity(){

    val presenter by lazy {
        viewModelOf<LaunchViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch_activity)
        btn1.setOnClickListener {
            test01()
        }
        btn2.setOnClickListener {
            presenter.doSomeThing()
        }
        presenter.textChange.observe(this, Observer {
            tv.text = it
        })

        btn3.setOnClickListener {

        }

    }

    /**
     *Activity中使用协程
     */
    private fun test01() {
        tv.text = "开始处理耗时任务"
        lifecycleScope.launch(Dispatchers.IO) {
            delay(3000) //处理耗时操作
            withContext(Dispatchers.Main){
                tv.text = "处理完成"
            }
        }
    }

}