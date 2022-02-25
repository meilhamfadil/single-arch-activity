package id.kudzoza.core.data.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Kudzoza
 * on 31/10/2021
 **/

data class ApiModelPaging<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val dataPaging: Paging<T>,
)
