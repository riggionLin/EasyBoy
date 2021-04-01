package com.example.rorydemo.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

/**
 * Description: 资源操作相关
 */

fun Context.color(id: Int) = ContextCompat.getColor(this, id)

fun Context.string(id: Int) = resources.getString(id)

fun Context.stringArray(id: Int) = resources.getStringArray(id)

fun Context.drawable(id: Int) = ContextCompat.getDrawable(this, id)

fun Context.dimenPx(id: Int) = resources.getDimensionPixelSize(id)

fun Context.integer(id: Int) = resources.getInteger(id)


fun View.color(id: Int) = context.color(id)

fun View.string(id: Int) = context.string(id)

fun View.stringArray(id: Int) = context.stringArray(id)

fun View.drawable(id: Int) = context.drawable(id)

fun View.dimenPx(id: Int) = context.dimenPx(id)

fun View.integer(id: Int) = context.integer(id)


fun Fragment.color(id: Int) = requireContext().color(id)

fun Fragment.string(id: Int) = requireContext().string(id)

fun Fragment.stringArray(id: Int) = requireContext().stringArray(id)

fun Fragment.drawable(id: Int) = requireContext().drawable(id)

fun Fragment.dimenPx(id: Int) = requireContext().dimenPx(id)

fun Fragment.integer(id: Int) = requireContext().integer(id)


fun RecyclerView.ViewHolder.color(id: Int) = itemView.color(id)

fun RecyclerView.ViewHolder.string(id: Int) = itemView.string(id)

fun RecyclerView.ViewHolder.stringArray(id: Int) = itemView.stringArray(id)

fun RecyclerView.ViewHolder.drawable(id: Int) = itemView.drawable(id)

fun RecyclerView.ViewHolder.dimenPx(id: Int) = itemView.dimenPx(id)

fun RecyclerView.integer(id: Int) = context.integer(id)
