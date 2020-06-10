package com.example.rorydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.badoo.mobile.util.WeakHandler
import com.billy.cc.core.component.CC
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel= ViewModelProviders.of(this).get(MyViewModel::class.java)
        var handle = object :WeakHandler(){

        }
        handle.postDelayed({},10)
        handle.sendEmptyMessage(1)

        //set3
        val firstStr= MutableLiveData<String>()
        val lastStr= MutableLiveData<String>()

        val server = MediatorLiveData<String>()

        server.addSource(firstStr){
            tv_name.text = it
        }
        server.addSource(lastStr){
            tv_last.text = it
        }

        server.observe(this, Observer {  })

        //set1
        viewModel.name?.observe(this,Observer{ name ->
            tv_name.text = name
        })

        //set2
        viewModel.currInfoMap.observe(this,Observer{
            tv_name.text = it
        })

        viewModel.currInfoSwitchMap.observe(this, Observer { it ->
            val sb = StringBuffer()
            it.forEachIndexed { index, s ->
                if (index == 0){
                    sb.append(s)
                }else{
                    sb.append("、${s}")
                }
            }
            tv_name.text = sb.toString()
        })

        btn.setOnClickListener {
            //viewModel.name?.postValue("测试哈哈！")
            //viewModel.user.value = User("小米")
            //viewModel.id.postValue(1)
            lastStr.postValue("last")
            firstStr.postValue("first")
        }

        btn1.setOnClickListener {

           var intent= Intent(this,DownLoadIMageIntentService::class.java).apply {
                putExtra("imgUrl","http://${index++}.png")
            }
          this.startService(intent)
        }

        btn2.setOnClickListener {
            CC.obtainBuilder("ComponentMe")
                .setActionName("useractivity")
                .build()
                .call()
        }
        btn3.setOnClickListener {
            CC.obtainBuilder("ComponentMe")
                .setActionName("testactivity")
                .build()
                .call()
        }
    }
    var index = 0
}
