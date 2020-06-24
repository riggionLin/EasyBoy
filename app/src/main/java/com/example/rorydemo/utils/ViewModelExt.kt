package com.example.rorydemo.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/24.
 */
inline fun <reified T : ViewModel> FragmentActivity.viewModelOf(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.viewModelOf(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.activityViewModelOf(): T {
    return requireActivity().viewModelOf()
}