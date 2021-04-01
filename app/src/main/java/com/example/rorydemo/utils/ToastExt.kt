package com.example.rorydemo.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ToastUtils

fun View.longToast(@StringRes msgRes: Int) {
    toast(msgRes, false)
}

fun View.toast(@StringRes msgRes: Int, short: Boolean = true) {
    toast(string(msgRes), short)
}

fun Fragment.longToast(@StringRes msgRes: Int) {
    toast(msgRes, false)
}

fun Fragment.toast(@StringRes msgRes: Int, short: Boolean = true) {
    toast(string(msgRes), short)
}

fun Context.longToast(@StringRes msgRes: Int) {
    toast(msgRes, false)
}

fun Context.toast(@StringRes msgRes: Int, short: Boolean = true) {
    toast(string(msgRes), short)
}

fun longToast(msg: CharSequence) {
    toast(msg, false)
}

fun toast(msg: CharSequence, short: Boolean = true) {
    toastInner(msg, if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG)
}

fun Context.showToast(string: String){
    Toast.makeText(this,string,Toast.LENGTH_SHORT).show()
}

private fun toastInner(msg: CharSequence, length: Int = Toast.LENGTH_SHORT) {
    if (msg.isBlank()) return
    when (length) {
        Toast.LENGTH_SHORT -> {
            ToastUtils.showShort(msg)
        }
        Toast.LENGTH_LONG -> {
            ToastUtils.showLong(msg)
        }
    }
}