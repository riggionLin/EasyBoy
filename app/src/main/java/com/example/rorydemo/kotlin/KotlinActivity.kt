package com.example.rorydemo.kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rorydemo.R
import com.example.rorydemo.utils.showToast
import kotlinx.android.synthetic.main.activity_kotlin.*

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/22.
 */
class KotlinActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        btn1.setOnClickListener {
            test01()
        }

        btn2.setOnClickListener {
            test02()
        }

        btn3.setOnClickListener {
            test03()
        }

        btn4.setOnClickListener {
            test04()
        }

        btn5.setOnClickListener {
            test05()
        }

    }

    fun <T> fill(arr:Array<in T>,t:T){
        arr[0] = t
    }

    /**
     * 数据交换使用also
     */
    private fun test05() {
        var a = 1
        var b = 2
        showToast("数据交换前a=${a} b=${b}")
        a = b.also {  b = a }
        showToast("数据交换后a=${a} b=${b}")
    }

    /**
     * also 返回是他自己 run 最后一条语句作为其结果
     */
    private fun test04() {
        var name = "xiaoeClien"

        name =  name.also {
            "ceshi"
        }
        System.out.println("also name返回值${name}")
        showToast("also name返回值${name}")
        name =  name.run {
            "test"
        }
        showToast("Run name返回值${name}")
        System.out.println("Run name返回值${name}")


    }

    /**
     * run let run作用域是this  let作用域是it
     */
    private fun test03() {
        val name = "xiaoeClien"
        name.run {
            System.out.println("Run name的长度${length}")
            showToast("Run name的长度${length}")
        }

        name.let {
            System.out.println("let name的长度${it.length}")
            showToast("let name的长度${it.length}")
        }
    }

    /**
     * with 和run使用 with是函数 run是扩展函数
     */
    private fun test02() {
        var name = "xiaoming"
        var cc = with(name){
           this.substring(0,3)
        }
        System.out.println("cc=${cc}")

        var age:Int?= null
        age = 16
       var hint= age?.run {
            if (age < 18) "未成年" else "已成年"
        }
        showToast("age=${hint}")
    }

    //使用 require 或者 check 函数作为条件检查
    private fun test01() {
        // 传统的做法
        val age = -1;
        if (age <= 0) {
            throw IllegalArgumentException("age must  not be negative")
        }

        //等于上面
        require(age > 0){
           "age must be negative"
       }
        //等于上面
        val name:String? = null
        checkNotNull(name){
            "name must not be null"
        }
    }
}