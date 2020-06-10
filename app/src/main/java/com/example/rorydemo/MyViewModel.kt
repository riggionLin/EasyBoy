package com.example.rorydemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.badoo.mobile.util.WeakHandler

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/1.
 */
class MyViewModel :ViewModel(){
    var name :MutableLiveData<String>? = null
    get() {
        if (field == null){
            field = MutableLiveData<String>()
        }
        return  field
    }

    var user = MutableLiveData<User>()

    var id = MutableLiveData<Int>()

    val currInfoMap: LiveData<String> = Transformations.map(user){ it ->
        it.name
    }

    val currInfoSwitchMap:LiveData<List<String>> = Transformations.switchMap(id,::getListData1)



    fun getListData1(id:Int):LiveData<List<String>>{
        var list = MutableLiveData<List<String>>()
        var myList = listOf<String>("小米","华为","苹果")
        list.postValue(myList)
        return list
    }
}