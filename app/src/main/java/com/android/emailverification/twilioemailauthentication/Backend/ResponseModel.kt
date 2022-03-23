package com.android.emailverification.twilioemailauthentication.Backend;

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
/**
 * Created by Ahmed Sadiq
 */
class ResponseModel {
    @SerializedName("errNum")
    @Expose
    var errNum: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null
}