package com.example.rorydemo.launch

import androidx.lifecycle.*
import com.example.rorydemo.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/24.
 */
class LaunchViewModel :ViewModel(){
    val textChange= MutableLiveData<String>()

    fun doSomeThing(){
        viewModelScope.launch(Dispatchers.Main) {

            textChange.postValue("开始任务2")
            withContext(Dispatchers.IO){
                delay(3000)
            }
            textChange.postValue("完成")
        }
    }

}