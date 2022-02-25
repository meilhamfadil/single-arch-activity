package id.kudzoza.core.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kudzoza
 * on 31/10/2021
 **/

data class Paging<T>(
    @SerializedName("total")
    val total: String,
    @SerializedName("perPage")
    val perPage: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("lastPage")
    val lastPage: Int,
    @SerializedName("data")
    val data: List<T>,
)