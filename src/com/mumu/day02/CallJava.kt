package com.mumu.day02

import com.mumu.day02.utils.StringUtils
import com.mumu.day02.utils.User


fun callJava() {
    var stringUtils = StringUtils()

    println(stringUtils.BB())
}


fun setUser(): String {
    var user = User()
    user.userName = "admin"
    user.password = "123456"
    user.idCard = 360728

    var map = mutableMapOf<String, Any>()
    map.putIfAbsent("userName", user.userName)
    map.put("password", user.password)
    map.put("idCard", user.idCard)
    
    var iterator = map.iterator()
    while (iterator.hasNext()) {
        println("a--->" + iterator.next())
    }

    return user.toString()
}


fun main() {
    println("hello Kotlin!")

    callJava()

    var user = setUser()
    println(user)

}
