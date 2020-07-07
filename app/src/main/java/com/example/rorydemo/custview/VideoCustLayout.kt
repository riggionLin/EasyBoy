package com.example.rorydemo.custview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import com.billy.cc.core.component.CC.init
import com.example.rorydemo.R
import org.jetbrains.anko.dip


/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/7/7.
 */

class VideoCustLayout @JvmOverloads constructor(context:Context,attrs: AttributeSet? =null,defStyleAttr: Int = 0):LinearLayout(context,attrs,defStyleAttr){
    //设置最大高度
    var maxHeight =dip(200f)
    var realHeight = -1
    //间距todo
    var space = 10

    init {
        //addView()
        realHeight = maxHeight;
    }
    //动态高度

/*    fun addView(){
        val tv1 = TextView(context)
        val tv2 = TextView(context)
        val tv3 = TextView(context)
        //文字居中
        tv1.gravity = Gravity.CENTER
        tv2.gravity = Gravity.CENTER
        tv3.gravity = Gravity.CENTER
        tv1.textSize = 16f
        tv2.textSize = 16f
        tv3.textSize = 16f
        //设置权重
        tv1.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1.0f
        )
        tv2.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1.0f
        )
        tv3.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1.0f
        )

        tv1.setBackgroundResource(R.color.color_tv)
        tv2.setBackgroundResource(R.color.colorAccent)
        tv3.setBackgroundResource(R.color.color_tv2)
        tv1.text = "我是测试视频1"
        tv2.text = "我是测试视频2"
        tv3.text = "我是测试视频3"
        //每一行的属性
        val ll = LinearLayout(context)
        ll.layoutParams =
            ViewGroup.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                maxHeight
            )
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setBackgroundResource(R.color.colorPrimaryDark)
        ll.addView(tv1)
        ll.addView(tv2)
        ll.addView(tv3)
        addView(ll)
    }*/

    var lineNum = 1
    var viewCount = 0

    var line1:LinearLayout? = null
    var line2:LinearLayout? = null
    var line3:LinearLayout? = null

    fun addTvView(tv:TextView){
        if (viewCount > 8) {
            Toast.makeText(context,"已达到最大值不能再添加",Toast.LENGTH_SHORT).show()
            return
        }
        if (lineNum == 1){
            if ( viewCount==0){
                if (line1 == null){
                    line1 = LinearLayout(context)
                    line1?.orientation = LinearLayout.HORIZONTAL
                    line1?.setBackgroundResource(R.color.colorPrimaryDark)
                    line1?.layoutParams = LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, realHeight)
                    addView(line1)
                }
                line1?.addView(tv)
                viewCount++ //累加1
            }else{
                line1?.addView(tv)
                viewCount++ //累加1
                if (viewCount == 3) lineNum = 2
            }
        }else if (lineNum ==2){
            if (viewCount == 3){
                if (line2 == null){
                    line2 = LinearLayout(context)
                    line2?.orientation = LinearLayout.HORIZONTAL
                    line2?.setBackgroundResource(R.color.color_tv)
                    realHeight = maxHeight / 2
                    line1?.layoutParams = LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, realHeight)
                    line2?.layoutParams = LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, realHeight) //重新设置第一行
                    addView(line2)
                }
                line2?.addView(tv)
                viewCount++
            }else{
                line2?.addView(tv)
                viewCount++
                if (viewCount == 6) lineNum = 3
            }
        }else if (lineNum ==3){
            if (viewCount == 6){

                if (line3 == null){
                    line3 = LinearLayout(context)
                    line2?.orientation = LinearLayout.HORIZONTAL
                    line2?.setBackgroundResource(R.color.color_tv2)
                    realHeight = maxHeight / 3
                    line1?.layoutParams = LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, realHeight)
                    line2?.layoutParams = LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, realHeight)
                    line3?.layoutParams = LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, realHeight)
                    addView(line3)
                }
                line3?.addView(tv)
                viewCount++
            }else{
                line3?.addView(tv)
                viewCount++
            }
        }
    }

}