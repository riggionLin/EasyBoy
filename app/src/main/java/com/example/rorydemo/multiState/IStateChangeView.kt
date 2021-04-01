/*
 * Copyright (C) 2017 Ricky.yao https://github.com/vihuela
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package com.example.rorydemo.multiState

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rorydemo.R
import com.example.rorydemo.utils.click
import com.example.rorydemo.utils.toast
import org.jetbrains.anko.findOptional

//apply for MultiStateView
//step 1: xml文件中标识 android:contentDescription="setup_stateView"
//step 2：在activity或fragment 使用布局之前调用stateViewSetup()方法完成布局调整
//step 3：外部实现此接口，覆写通过getStateView()方法，调用getStateView(context: T)
interface IStateChangeView {

    //初始化使用--------------------------------------------------------------------------------------
    fun getStateView(): MultiStateView? = null

    fun <T> getStateView(context: T): MultiStateView? {
        when (context) {
            is Fragment -> {
                return context.view?.findOptional<MultiStateView>(R.id.multiStateView)
            }
            is Activity -> {
                return context.findOptional<MultiStateView>(R.id.multiStateView)
            }
            else -> throw IllegalArgumentException("context must be Fragment Or Activity")
        }
    }

    /**
     * fragment中在onCreateView()
     */
    fun View.wrapStateView(): View {
        // 若不需要包裹StateView则直接返回
        if (!isRegisterStateView()) return this

        // 寻找标记ContentView
        val tag = "setup_stateView"
        val outputViews = ArrayList<View>()
        findViewsWithText(outputViews, tag, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION)
        val targetContent =
            outputViews.elementAtOrNull(0) /* 取最外层的标记View */ ?: return this // 如果没有标记View，则不需要做替换，直接返回原始View

        val targetParent = targetContent.parent as? ViewGroup
        val targetIndex: Int = targetParent?.indexOfChild(targetContent) ?: -1
        val targetContentLp = targetContent.layoutParams

        // 如果有父布局，必须先从父布局移除
        targetParent?.removeViewAt(targetIndex)

        // 使用StateView包裹ContentView
        val stateView = MultiStateView(context).apply {
            id = R.id.multiStateView
            fullReplace = StateChangeConfiger.showLoadingViewAndHideContentView//loading显示时是否替换contentView
            loadingViewDelayHide = StateChangeConfiger.loadingViewDelayHide
            setViewForState(targetContent, MultiStateView.VIEW_STATE_CONTENT)
            setViewForState(StateChangeConfiger.errorViewLayout, MultiStateView.VIEW_STATE_ERROR)
            setViewForState(StateChangeConfiger.emptyViewLayout, MultiStateView.VIEW_STATE_EMPTY)
            setViewForState(StateChangeConfiger.loadingViewLayout, MultiStateView.VIEW_STATE_LOADING)
            viewState = if (isStateViewDefaultLoading())
                MultiStateView.VIEW_STATE_LOADING
            else {
                initForContentView()
                MultiStateView.VIEW_STATE_CONTENT
            }
        }

        // 如果没有父布局，则以stateView为根布局, 否则返回原始根布局
        return if (targetParent != null) {
            targetParent.addView(stateView, targetIndex, targetContentLp)
            this
        } else {
            stateView
        }
    }

    /**
     * activity中onCreate()调用，
     */
    fun <T> stateViewSetup(source: T) {

        if (!isRegisterStateView()) return

        val root: View = when (source) {
            is View -> source
            is Activity -> source.findViewById(android.R.id.content)
            else -> throw IllegalArgumentException("source must be View Or Activity")
        }

        val targetRoot: ViewGroup = root as? ViewGroup ?: return
        val TAG: String = "setup_stateView"
        val outputViews = ArrayList<View>()
        targetRoot.findViewsWithText(outputViews, TAG, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION)
        if (outputViews.size == 1) {
            val targetContent = when (outputViews[0]) {
                //todo 可能仅需要替换某些刷新View里的子View
//                is SmartRefreshLayout -> outputViews[0].findViewById(R.id.mRecycleView)
                else -> outputViews[0]
            }
            val targetContentLp = targetContent.layoutParams
            val targetParent = targetContent.parent as? ViewGroup

            if (targetParent != null) {
                //有父容器时替换某个位置
                val targetIndex: Int = targetParent.indexOfChild(targetContent)
                targetParent.apply {
                    removeViewAt(targetIndex)
                    val stateView = MultiStateView(targetRoot.context).apply {
                        id = R.id.multiStateView
                        fullReplace = StateChangeConfiger.showLoadingViewAndHideContentView//loading显示时是否替换contentView
                        loadingViewDelayHide = StateChangeConfiger.loadingViewDelayHide
                        setViewForState(targetContent, MultiStateView.VIEW_STATE_CONTENT)
                        setViewForState(StateChangeConfiger.errorViewLayout, MultiStateView.VIEW_STATE_ERROR)
                        setViewForState(StateChangeConfiger.emptyViewLayout, MultiStateView.VIEW_STATE_EMPTY)
                        setViewForState(StateChangeConfiger.loadingViewLayout, MultiStateView.VIEW_STATE_LOADING)
                        viewState = if (isStateViewDefaultLoading())
                            MultiStateView.VIEW_STATE_LOADING
                        else {
                            initForContentView()
                            MultiStateView.VIEW_STATE_CONTENT
                        }
                    }
                    addView(stateView, targetIndex, targetContentLp)
                }
            }
        }
    }

    fun isRegisterStateView(): Boolean = true

    //提供运行时方法--------------------------------------------------------------------------------------
    fun onStateViewRetryListener() {
        stateViewLoading()
    }

    fun stateViewLoading() {
        getStateView()?.viewState = MultiStateView.VIEW_STATE_LOADING
    }

    fun stateViewError(error: Any, content: CharSequence) {
        getStateView()?.apply {
            viewState = MultiStateView.VIEW_STATE_ERROR
            val errorView = getView(MultiStateView.VIEW_STATE_ERROR)?.findOptional<TextView>(R.id.tv)
            val retryButton = getView(MultiStateView.VIEW_STATE_ERROR)?.findOptional<View>(R.id.retry)
            val netErrorIv = getView(MultiStateView.VIEW_STATE_ERROR)?.findOptional<View>(R.id.ic_net_error)
            if (content.isNotBlank()) errorView?.text = content
            retryButton?.setOnClickListener { onStateViewRetryListener() }
            when (error) {
                github.library.utils.Error.NetWork -> netErrorIv?.visibility = View.VISIBLE
                else -> netErrorIv?.visibility = View.GONE
            }
        }
    }

    //默认空布局
    fun stateViewEmpty() {
        getStateView()?.viewState = MultiStateView.VIEW_STATE_EMPTY
    }

    //默认空布局修改图片和文字
    fun stateViewEmpty(image: Int, content: CharSequence) {
        getStateView()?.apply {
            viewState = MultiStateView.VIEW_STATE_EMPTY
            val emptyTxt = getView(MultiStateView.VIEW_STATE_EMPTY)?.findOptional<TextView>(R.id.tv)
            val emptyImg = getView(MultiStateView.VIEW_STATE_EMPTY)?.findOptional<ImageView>(R.id.iv_empty)
            if (content.isNotBlank()) emptyTxt?.text = content
            emptyImg?.setImageResource(image)
        }
    }

    //自定义空布局
    fun stateViewEmpty(
        resLayoutId: Int,
        clickViewId: Int,
        clickViewId2: Int,
        clickViewCallback: () -> Unit,
        clickViewCallback2: () -> Unit
    ): View? {
        getStateView()?.apply {
            setViewForState(resLayoutId, MultiStateView.VIEW_STATE_EMPTY, true)
            val targetClickView = getView(MultiStateView.VIEW_STATE_EMPTY)?.findOptional<View>(clickViewId)
            val targetClickView2 = getView(MultiStateView.VIEW_STATE_EMPTY)?.findOptional<View>(clickViewId2)
            targetClickView?.click {
                clickViewCallback.invoke()
            }
            targetClickView2?.click {
                clickViewCallback2.invoke()
            }
        }
        return getStateView()?.getView(MultiStateView.VIEW_STATE_EMPTY)
    }

    fun stateViewContent() {
        getStateView()?.viewState = MultiStateView.VIEW_STATE_CONTENT
    }

    fun stateViewShowMessage(content: String) {
        stateViewContent()
        toast(content)
    }

    //初始状态为loading
    fun isStateViewDefaultLoading() = true
}