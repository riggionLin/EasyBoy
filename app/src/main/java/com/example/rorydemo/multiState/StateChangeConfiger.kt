package com.example.rorydemo.multiState

import com.example.rorydemo.R

object StateChangeConfiger {
    //错误页面的控件id必须一致
    var errorViewLayout: Int = R.layout.xbu_error_view

    var emptyViewLayout = R.layout.xbu_empty_view

    var loadingViewLayout = R.layout.xbu_loading_view

    var showLoadingViewAndHideContentView = true//显示loading的时候，是否隐藏contentView

    var loadingViewDelayHide = false//loadingView延时隐藏


}