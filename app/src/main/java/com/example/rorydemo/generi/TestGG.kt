package com.example.rorydemo.generi

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/12.
 */
class TestGG<T>{

    inline fun <reified T> printIfTypeMatch(item: Any) {
        if (item is T) { // 👈 这里就不会在提示错误了
            println(item)
        }
    }
}