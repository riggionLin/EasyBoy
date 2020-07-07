package com.example.rorydemo.custview

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.example.rorydemo.R
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent

import kotlinx.android.synthetic.main.activity_cust.*
import org.jetbrains.anko.dip

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/7/7.
 */
class CustActivity :AppCompatActivity(){
    //添加房间的人数
    var count = 0
    //支持的最大人数
    var maxCount = 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cust)
        fl.justifyContent  = JustifyContent.CENTER
        println("屏幕宽度：${ScreenUtils.getAppScreenWidth()}")
        println("屏幕高度：${ScreenUtils.getAppScreenHeight()}")
        println("应用屏幕宽度：${ScreenUtils.getAppScreenWidth()}")
        println("应用屏幕高度：${ScreenUtils.getAppScreenHeight ()}")
        println("判断是否有底部导航栏：${BarUtils.isNavBarVisible(this)}")

        var width = (ScreenUtils.getAppScreenWidth() / 8)
        println("平均宽度：${width}")

        tv_click.setOnClickListener {
            if (count > 16) return@setOnClickListener
            val tv1 = TextView(this)
            tv1.gravity = Gravity.CENTER
            tv1.text = "我是测试文本"
            var param:FlexboxLayout.LayoutParams? = null
            if (count < 8){
                param = FlexboxLayout.LayoutParams(
                    width,
                    dip(150)
                )
            }else{
                if (count == 8){ //调整原理布局
                    var j = 0
                    while (j<fl.flexItemCount){
                       val view = fl.getReorderedChildAt(j)
                        view.layoutParams.height = dip(75)
                        j++
                    }
                }
                param = FlexboxLayout.LayoutParams(
                    width,
                    dip(75)
                )
            }
            param?.flexGrow = 1.0f //均分
            param?.order = 1
            tv1.layoutParams = param
            tv1.setBackgroundResource(R.drawable.cust_shap)
            fl.addView(tv1)
            count++
        }

        remove_click.setOnClickListener {
            if (fl.flexItemCount == 0) return@setOnClickListener
          fl.removeViewAt(fl.flexItemCount - 1)
            count--
        }
    }
}