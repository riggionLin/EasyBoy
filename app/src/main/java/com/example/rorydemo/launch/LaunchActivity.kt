package com.example.rorydemo.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.rorydemo.R
import com.example.rorydemo.utils.viewModelOf
import kotlinx.android.synthetic.main.launch_activity.*
import kotlinx.coroutines.*

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/24.
 */
class LaunchActivity : AppCompatActivity() {

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
            val time = System.currentTimeMillis()
            System.out.println("time1=" + time)
            lifecycleScope.launch(Dispatchers.IO) {
                val t1 = async {
                    delay(3000)
                    "task1"
                }
                val t2 = async {
                    delay(3000)
                    "task2"
                }
                withContext(Dispatchers.Main) {
                    var str = "${t1.await()}-${t2.await()}"
                    val time2 = System.currentTimeMillis()
                    val tmie3: Int = ((time2 - time) / 1000).toInt()
                    str += "一共耗时${tmie3}"
                    tv.text = str
                }
            }
        }
        btn4.setOnClickListener {
            val time = System.currentTimeMillis()
            lifecycleScope.launch(Dispatchers.IO) {
                delay(3000)
                var t1 = "task1"
                delay(3000)
                var t2 = "task2"

                withContext(Dispatchers.Main) {
                    var str = "${t1}-${t2}"
                    val time2 = System.currentTimeMillis()
                    val tmie3: Int = ((time2 - time) / 1000).toInt()
                    str += "一共耗时${tmie3}"
                    tv.text = str
                }
            }
        }

    }

    /**
     *Activity中使用协程
     */
    private fun test01() {
        tv.text = "开始处理耗时任务"
        lifecycleScope.launch(Dispatchers.IO) {
            delay(3000) //处理耗时操作
            withContext(Dispatchers.Main) {
                tv.text = "处理完成"
            }
        }
    }

}