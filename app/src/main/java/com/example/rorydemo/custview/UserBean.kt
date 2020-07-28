package com.example.rorydemo.custview

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/7/10.
 */
class UserBean {
     var userid:String = ""
     var username:String = ""
     var imgeUrl:Int =-1
     var defaulUrl:Int = -1
     var isPlaceholder = false //是否是占位图
     override fun equals(other: Any?): Boolean {
          if (this === other) return true
          if (javaClass != other?.javaClass) return false

          other as UserBean

          if (userid != other.userid) return false

          return true
     }

     override fun hashCode(): Int {
          return userid.hashCode()
     }


}