package com.example.wanandroid.domain


/**
 * A generic class that holds a value with its loading status.
 */
sealed class Result<out T> {

    data class Error(val errorMsg: String) : Result<Nothing>()

    data class Success<out T>(val data: T) : Result<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[errorMsg=$errorMsg]"
        }
    }

}