package com.sun.me

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/9.
 */
class DebugActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this,"hello CC",Toast.LENGTH_SHORT).show()
        finish()
    }
}