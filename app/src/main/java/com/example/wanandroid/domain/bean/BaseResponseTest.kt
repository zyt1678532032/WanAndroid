package com.example.wanandroid.domain.bean

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class DataResponse<T> {
    @SerializedName("data") var data: T? = null
    @SerializedName("errorCode") var errorCode: Int? = null
    @SerializedName("errorMsg") var errorMsg: String? = null
}


@Keep
enum class RequestStatus {
    LOADING,
    SUCCESS,
    EXCEPTION_FAIL,
    SERVER_FAIL,
    AUTH_FAIL
}
