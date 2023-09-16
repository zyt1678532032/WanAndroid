package com.example.wanandroid.util.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
open class BaseResponse<T> {
    @SerializedName("data")
    var data: T? = null

    @SerializedName("errorCode")
    val errorCode: Int? = null

    @SerializedName("errorMsg")
    val errorMsg: String? = null

    var status: RequestStatus? = null

}
