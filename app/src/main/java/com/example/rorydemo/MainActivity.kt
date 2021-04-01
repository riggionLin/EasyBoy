package com.example.rorydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.billy.cc.core.component.CC
import com.blankj.utilcode.util.ToastUtils
import com.example.rorydemo.custview.CustActivity
import com.example.rorydemo.generi.GenericActivity
import com.example.rorydemo.intentService.IntentServiceActivity
import com.example.rorydemo.kotlin.KotlinActivity
import com.example.rorydemo.launch.LaunchActivity
import com.example.rorydemo.leecode.LeeCodeActivity
import com.example.rorydemo.multiState.IStateChangeView
import com.example.rorydemo.multiState.MultiStateView
import com.example.rorydemo.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IStateChangeView {

    //IStateChangeView
    override fun getStateView(): MultiStateView? {
        //返回不同布局的view
        return super.getStateView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //IStateChangeView 设置需要切换不同布局的上下文
        stateViewSetup(this)

        btn.setOnClickListener {
            startActivity(Intent(this,ViewModelActivity::class.java))
        }

        btn1.setOnClickListener {
            startActivity(Intent(this, IntentServiceActivity::class.java))
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

        btn4.setOnClickListener {
            startActivity(Intent(this, GenericActivity::class.java))
        }

        btn5.setOnClickListener {
            startActivity(Intent(this, LeeCodeActivity::class.java))
        }

        btn6.setOnClickListener {
            startActivity(Intent(this, KotlinActivity::class.java))
        }

        btn7.setOnClickListener {
            startActivity(Intent(this, LaunchActivity::class.java))
        }
        fill(1, arrayOf(1,2,3))

        btn8.setOnClickListener {
            startActivity(Intent(this, CustActivity::class.java))
        }

        btn9.setOnClickListener {
            //Toast.makeText(this,"自定义吐司",Toast.LENGTH_SHORT).show()
            utils.ToastUtils.showCenterToast(this,"自定义吐司")
        }
        btn9.postDelayed({
            stateViewContent()
        },2000)
    }


    private fun <T> fill(t: T, arr:Array<in T>){
        arr[0] = t
    }

    private fun <T> copy( arr1:Array<out T>, arr2:Array<in T>){
        arr1.forEachIndexed { index, t ->
            arr2[index] = arr1[index]
        }
    }


}
