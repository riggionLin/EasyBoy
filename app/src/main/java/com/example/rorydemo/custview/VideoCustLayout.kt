package com.example.rorydemo.custview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.rorydemo.R
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent
import org.jetbrains.anko.dip
import org.jetbrains.anko.margin

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/7/7.
 */

class VideoCustLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), OperationVideo {

    //添加房间的人数
    var count = 0
    //学员端视频容器
    var fl: FlexboxLayout
    //讲师端视频容器
    var head: LinearLayout
    var width5: Int = 0 //标准4+1学员视频宽度
    var width6: Int = 0 //标准6+1学员视频宽度
    var twidth6: Int = 0 //讲师视频宽度
    var width7: Int = 0 //标准7+1人宽度
    var twidth7: Int = 0 //讲师视频宽度
    var width8: Int = 0 //标准8+1人宽度
    var twidth8: Int = 0 //讲师视频宽度
    var width9: Int = 0 //标准9+1人宽度
    var twidth9: Int = 0 //讲师视频宽度
    var tabHight: Int //视频区域的高度

    //维护的数组
    private var videoList: ArrayList<UserBean> = ArrayList()

    init {
        var ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustVideoCustLayout)
        //自定义参数高度
        tabHight = ta.getDimensionPixelSize(R.styleable.CustVideoCustLayout_tab_hight, 150)
        ta.recycle()
        //填充自定义布局
        val view = View.inflate(context, R.layout.activity_video_cust, this) as View
        fl = view.findViewById(R.id.fl)
        //设置tab的高度
        fl.layoutParams.height = tabHight
        fl.justifyContent = JustifyContent.CENTER
        head = view.findViewById(R.id.ll_head)
        //根据屏幕的宽度来动态计算视频区域显示的空间大小
        setAutoWH(ScreenUtils.getAppScreenWidth())
    }

    //设置数据
    override fun setData(list: ArrayList<UserBean>) {
        //判断列表的用户是否大于9个且小于17个则说明需要添加占位用户
        if (list.size in 10..16) {
            var tempUser = 17 - list.size
            for (num in 1..tempUser) {
                list.add(UserBean().apply {
                    isPlaceholder = true
                    userid = "temp$num"
                    defaulUrl = R.mipmap.default_icon2
                    username = "temp$num"
                })
            }
        }
        //判断当前列表元素数量的小于设置的数据列表元素数量时，说明是增加视频控件操作
        if (videoList.size < list.size) {
            //获取差异的数据
            val diffList = getDifferent(list, videoList)
            //保存最新的列表
            videoList = list
            //通过遍历
            diffList.forEachIndexed { index, userBean ->
                addCusetView(userBean)
            }
        } else if (videoList.size == list.size) { //更新操作
            if (list.size == 17) { //比较2个数组并进行处理
                updateUI(list)
            }
            videoList = list

        } else { //删除视图操作
            val diffList = getDifferent(list, videoList) //获取差异的数据
            videoList = list
            diffList.forEachIndexed { index, userBean ->
                //添加视图
                remove(userBean)
            }
        }
        //动态调整布局
        autoLayout()
        //更新视图
        videoList.forEachIndexed { index, userBean ->
            //添加视图
            update(userBean)
        }

    }

    var width5temp: Int = 0 //标准4+1学员视频宽度
    /**
     * 防止均分屏蔽出现遗漏的像素引起的布局问题
     */
    private fun setAutoWH(width: Int) {
        width5 = (width - dip(4)) / 5
        width5temp = (width) / 5
        width6 = (width - width5temp - dip(5f)) / 5
        twidth6 = width - (width6 * 5) - dip(5f)
        width7 = (width - width5temp - dip(6f)) / 6
        twidth7 = width - (width7 * 6) - dip(6f)
        width8 = (width - width5temp - dip(7f)) / 7
        twidth8 = width - (width8 * 7) - dip(7f)
        width9 = (width - width5temp - dip(8f)) / 8
        twidth9 = width - (width9 * 8) - dip(8f)
        //println("width5=$width5 width6=$width6 width7=$width7 width8=$width8 width9=$width9")
        //println("校验width5=${width5 * 5} width6=${(width6 * 5) + twidth6} width7=${(width7 * 6) + twidth7}width8=${(width8 * 7) + twidth8} width9=${(width9 * 8) + twidth9}")
    }


    /**
     * 动态调整布局，根据视频控件数
     */
    private fun autoLayout() {
        if (fl.flexItemCount == 0) return

        when (fl.flexItemCount) {
            1, 2, 3, 4, 5 -> {
                updateUILayout(JustifyContent.CENTER, width5, tabHight, fl.flexItemCount)
            }
            6 -> {
                updateUILayout(JustifyContent.CENTER, width6, tabHight, fl.flexItemCount - 1)
                val tView = fl.getReorderedChildAt(fl.flexItemCount - 1)
                tView.layoutParams.width = twidth6
            }
            7 -> {
                updateUILayout(JustifyContent.CENTER, width7, tabHight, fl.flexItemCount - 1)
                val tView = fl.getReorderedChildAt(fl.flexItemCount - 1)
                tView.layoutParams.width = twidth7
            }
            8 -> {
                updateUILayout(JustifyContent.CENTER, width8, tabHight, fl.flexItemCount - 1)
                val tView = fl.getReorderedChildAt(fl.flexItemCount - 1)
                tView.layoutParams.width = twidth8
            }
            9 -> {
                if (count == 9) { //学员8+1讲师
                    updateUILayout(JustifyContent.CENTER, width9, tabHight, fl.flexItemCount - 1)
                    val tView = fl.getReorderedChildAt(fl.flexItemCount - 1)
                    tView.layoutParams.width = twidth9
                } else { //学员9+1讲师
                    updateUILayout(
                        JustifyContent.FLEX_START,
                        width9,
                        (tabHight - dip(1f)) / 2,
                        fl.flexItemCount
                    )
                }
            }
            else -> {
                updateUILayout(
                    JustifyContent.FLEX_START,
                    width9,
                    (tabHight - dip(1f)) / 2,
                    fl.flexItemCount
                )
            }
        }

    }


    private fun updateUILayout(justifyContent: Int, width: Int, height: Int, cycleNum: Int) {
        fl.justifyContent = justifyContent
        //遍历
        var j = 0
        while (j < cycleNum) {
            val view = fl.getReorderedChildAt(j)
            view.layoutParams.width = width
            view.layoutParams.height = height
            j++
        }
    }

    /**
     * 添加视频区域的控件
     */
    private fun addCusetView(user: UserBean) {
        if (count > 16) {
            ToastUtils.showShort("超出最大范围17个视频")
            return
        }
        //添加视频控件的容器RelativeLayout
        val videoContainer = View.inflate(context, R.layout.cust_video, null) as View
        //用户的名称
        val tvName = videoContainer.findViewById<TextView>(R.id.tv_name)
        //默认背景图片
        val ivImage = videoContainer.findViewById<ImageView>(R.id.iv_cover)
        tvName.text = user.username
        //判断是否为占位图
        if (user.isPlaceholder) {
            ivImage.setImageResource(user.defaulUrl)
        } else {
            if (user.imgeUrl != -1) {
                ivImage.setImageResource(user.imgeUrl)
            }
        }
        //布局参数
        var param: FlexboxLayout.LayoutParams? = null
        if (count <= 4) { //小于或等于5人数时，均分屏幕宽度
            param = FlexboxLayout.LayoutParams(width5, tabHight)
            if (count >= 1) {
                param.setMargins(0, 0, dip(1f), 0)
            }
            //动态调整布局
        } else if (count == 5) { //第6人添加时,动态布局
            param = getParam(width6, tabHight, twidth6, tabHight)
        } else if (count == 6) { //第7人人添加时,动态布局
            param = getParam(width7, tabHight, twidth7, tabHight)
        } else if (count == 7) { //添加第8个人时,动态布局
            param = getParam(twidth8, tabHight, twidth8, tabHight)
        } else if (count == 8) { //添加第9个人时,动态布局
            param = getParam(twidth9, tabHight, twidth9, tabHight)
        } else if (count == 9) { //添加第10个人时，换行并调整高度，且将讲师端移动到右边容器单独显示
            param = getSwitchParam(width9, (tabHight - dip(1f)) / 2)
        } else {
            param = FlexboxLayout.LayoutParams(width9, (tabHight - dip(1f)) / 2).apply {
                setMargins(0, dip(1f), dip(1f), 0)
            }
        }
        //是否均分布局 0 不均分 1均分
        param?.flexGrow = 0.0f
        param?.order = 1
        videoContainer.layoutParams = param

        if (count <= 8) { //显示视图在8个以内，索引值从倒数第2个插入
            var index = if (fl.flexItemCount - 1 < 0) 0 else fl.flexItemCount - 1
            fl.addView(videoContainer, index)
        } else { //大于8个，则从尾部插入
            fl.addView(videoContainer)
        }
        //设置tag用于区分视图和点击事件的处理
        videoContainer.tag = user.userid
        videoContainer.setOnClickListener { view ->
            ToastUtils.showShort(view.tag.toString() + "被点击")
        }
        count++
    }

    /**
     * 移除指定视频控件
     */
    private fun remove(user: UserBean) {
        //移除学员区的视频控件
        var j = 0
        out@ while (j < fl.flexItemCount) {
            val view = fl.getReorderedChildAt(j)
            if (view.tag == user.userid) {
                fl.removeView(view)
                count--
                break@out
            }
            j++
        }
        //删除第10个时，调整布局，将讲师视频添加到学员视频区
        if (count == 10) {
            if (head.childCount >= 1) {//说明数量超过8
                val headView = head.getChildAt(0)
                head.removeView(headView)
                fl.addView(headView)
            }
        }

        //移除讲师端布局
        /*  if (head.visibility == View.VISIBLE){
              if (head.childCount >= 1){//说明数量超过8
                  val headView =head.getChildAt(0)
                  if (headView.tag == user.userid){
                      count--
                      head.visibility = View.GONE
                  }
              }
          }*/
    }

    //更新视频控件
    private fun update(user: UserBean) {
        var j = 0
        out@ while (j < fl.flexItemCount) {
            val view = fl.getReorderedChildAt(j)
            if (view.tag == user.userid) {
                val tt = view.findViewById<TextView>(R.id.tv_name)
                tt.text = user.username
                break@out
            }
            j++
        }
    }

    /**
     * 用于获取添加6-9人动态布局的参数
     * 学员端的宽高，讲师端的宽高
     */
    private fun getParam(
        width: Int,
        height: Int,
        tWidth: Int,
        tHeight: Int
    ): FlexboxLayout.LayoutParams {
        //重置学生端的宽高
        var j = 0
        while (j < fl.flexItemCount - 1) {
            val sView = fl.getReorderedChildAt(j)
            sView.layoutParams.width = width
            sView.layoutParams.height = height
            j++
        }
        //重置讲师端的宽高
        val tView = fl.getReorderedChildAt(fl.flexItemCount - 1)
        tView.layoutParams.width = tWidth
        tView.layoutParams.height = tHeight
        //返回添加学生端的布局参数
        return FlexboxLayout.LayoutParams(
            width,
            height
        ).apply {
            //设置边距
            setMargins(0, 0, dip(1f), 0)
        }
    }

    /**
     * 获取10人时换行参数
     */
    private fun getSwitchParam(width: Int, height: Int): FlexboxLayout.LayoutParams {
        //获取最后一个子控件（讲师端），并从学员容器中移除，迁移到右边容器中单独开来
        var childView = fl.getReorderedChildAt(fl.flexItemCount - 1)
        fl.removeView(childView)
        head.apply {
            visibility = View.VISIBLE //显示右部容器
            addView(childView) //添加讲师端
        }
        //动态的遍历学员容器，调整参数
        var j = 0
        while (j < fl.flexItemCount) {
            val view = fl.getReorderedChildAt(j)
            view.layoutParams.width = width
            view.layoutParams.height = height
            j++
        }
        //添加控件的布局参数
        return FlexboxLayout.LayoutParams(width, height).apply {
            setMargins(0, dip(1f), dip(1f), 0)
        }
    }


    /**
     * 比较2个队列的差异
     */
    private fun getDifferent(prelist: List<UserBean>, curlist: List<UserBean>): List<UserBean> {
        val diff = ArrayList<UserBean>()
        val map = HashMap<UserBean, Int>(curlist.size)
        for (stu in curlist) {
            map[stu] = 1
        }
        for (stu in prelist) {
            if (map[stu] != null) {
                map[stu] = 2
                continue
            }
            diff.add(stu)
        }
        for ((key, value) in map) {
            if (value == 1) {
                diff.add(key)
            }
        }
        for (stu in diff) {
            println(" the differ " + stu.userid)
        }
        return diff
    }

    /**
     * 用于处理最大人数的Ui更新问题
     */
    private fun updateUI(list: ArrayList<UserBean>) {

        for (num in 0 until list.size) {
            if (videoList[num].userid == list[num].userid) { //若相同则更新内部的数据
                //更新UI的内容
                update(list[num])
            } else { //不相同的情况，以新的列表数据为主更新UI
                val showView = fl.getReorderedChildAt(num - 1)
                showView.tag = list[num].userid
                val tt = showView.findViewById<TextView>(R.id.tv_name)
                val iv = showView.findViewById<ImageView>(R.id.iv_cover)
                if (list[num].isPlaceholder) {
                    iv.setImageResource(list[num].defaulUrl)
                } else {
                    iv.setImageResource(list[num].imgeUrl)
                }
                tt.text = list[num].username
                /*if (videoList[num].userid.contains("temp") && !list[num].userid.contains("temp")) { //填充占位图
                    //更新占位的UI
                    //获取真实的索引
                } else { //移除占位图
                }*/
            }
        }
    }
}