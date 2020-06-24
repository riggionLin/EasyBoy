package com.example.rorydemo.utils

import android.content.Context
import android.widget.Toast

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/22.
 */


fun Context.showToast(string: String){
    Toast.makeText(this,string,Toast.LENGTH_SHORT).show()
}