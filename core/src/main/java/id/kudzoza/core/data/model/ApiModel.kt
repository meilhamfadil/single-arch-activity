package id.kudzoza.core.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kudzoza
 * on 19/09/2021
 **/

data class ApiModel<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: T?,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)