package com.example.myapplication.data

sealed class Results<T>(
    val data:T ? = null,
    val msg:String ? = null
){
    class Success<T>(data:T?) : Results<T>(data)
    class Error<T>(data: T? = null , msg: String?) : Results<T>(data, msg)

}
