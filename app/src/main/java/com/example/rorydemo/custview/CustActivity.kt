package com.example.rorydemo.custview

import android.os.Bundle
import android.view.Gravity
import android.view.View
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
        var width5 = (ScreenUtils.getAppScreenWidth() / 5)


        var width = (ScreenUtils.getAppScreenWidth() / 8)
        println("平均宽度：${width}")

        var list = ArrayList<UserBean>()
        for (i in 1..2 ){
            list.add(UserBean().apply { username = "haha$i"
                userid = "xiaoe$i"})
        }
        vcl.setData(list)

        /*vcl.post(Runnable {
            vcl.setAutoWH(vcl.width)
           println("测试宽度"+vcl.width)
        })*/

        //添加
        tv_click.setOnClickListener {
            ++count
            if (count == 18) count = 1
            var list = ArrayList<UserBean>()

            for (i in 1..count){
                list.add(UserBean().apply { username = "haha$i"
                userid = "xiaoe$i"
                imgeUrl = R.mipmap.st1}
                )
            }
            vcl.setData(list)

        }
        //删除
        remove_click.setOnClickListener {
            var list = ArrayList<UserBean>()
            --count
            for (i in 1..count){
                list.add(UserBean().apply { username = "haha$i"
                    userid = "xiaoe$i"})
            }
            vcl.setData(list)
        }


        update_click.setOnClickListener { //更新
            var list = ArrayList<UserBean>()
            for (i in 1..3){
                list.add(UserBean().apply { username = "xiaoe$i"
                    userid = "xiaoe$i"})
            }
            vcl.setData(list)
        }
    }

    private fun getParam(TeacherWidth:Int,count:Int):FlexboxLayout.LayoutParams{
        val width =(ScreenUtils.getAppScreenWidth() - TeacherWidth) /count
        var j = 0
        while (j<fl.flexItemCount -1){
            val view = fl.getReorderedChildAt(j)
            view.layoutParams.width = width
            j++
        }
        return  FlexboxLayout.LayoutParams(
            width,
            dip(150)
        )
    }

    private fun getChangeParam(TeacherWidth:Int):FlexboxLayout.LayoutParams{
        val width =(ScreenUtils.getAppScreenWidth() - TeacherWidth) /8
        return FlexboxLayout.LayoutParams(
            width,
            dip(75)
        )
    }
}