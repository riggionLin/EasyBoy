package com.example.rorydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.billy.cc.core.component.CC
import com.example.rorydemo.intentService.IntentServiceActivity
import com.example.rorydemo.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }

}
