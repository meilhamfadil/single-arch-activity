package id.kudzoza.core.data.model

/**
 * Created by Kudzoza
 * on 19/09/2021
 **/

data class Payload<T>(
    val ok: Boolean,
    val message: String,
    val data: T?,
)