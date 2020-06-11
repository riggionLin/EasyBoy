package com.example.rorydemo.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rorydemo.R
import com.example.rorydemo.User
import kotlinx.android.synthetic.main.activity_view_model.*
import kotlinx.android.synthetic.main.activity_view_model.tv_last
import kotlinx.android.synthetic.main.activity_view_model.tv_name

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/10.
 */

class ViewModelActivity :AppCompatActivity(){

    private lateinit var viewModel:MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        //ViewModel的实例化
        viewModel= ViewModelProviders.of(this).get(MyViewModel::class.java)

        //LiveData的基本使用
        stepOne()
        //Transformations.map的使用
        stepTow()
        //Transformations.switchMap的使用
        stepThree()
        //addSource的使用
        stepFour()
    }

    private fun stepFour() {
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

        tv_set4.setOnClickListener {
            lastStr.postValue("last")
            firstStr.postValue("first")
        }
    }

    private fun stepThree() {
        //switchMap的使用
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
        tv_set3.setOnClickListener {
            viewModel.id.postValue(1)
        }
    }

    private fun stepTow() {
        //map的使用
        viewModel.currInfoMap.observe(this,Observer{
            tv_name.text = it
        })

        tv_set2.setOnClickListener {
            viewModel.user.value = User("小米")
        }
    }

    private fun stepOne() {
        viewModel.name?.observe(this, Observer{ name ->
            tv_name.text = name
        })

        tv_set1.setOnClickListener {
            viewModel.name?.postValue("测试哈哈！")
        }
    }
}