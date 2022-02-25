package id.kudzoza.core.util

import com.google.gson.Gson
import id.kudzoza.core.data.model.ApiModel
import id.kudzoza.core.data.model.Payload
import id.kudzoza.core.data.model.Resource
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Kudzoza
 * on 19/09/2021
 **/


fun <T> failure(e: Exception): Payload<T> {
    e.printStackTrace()

    return when (e) {
        is HttpException -> {
            /**
             * Fetch error from server
             * getting response from base model
             * get message field from response
             */
            try {
                /**
                 * Fetching error message from response
                 */
                val body = e.response()?.errorBody()
                val responseDao = Gson().fromJson(body?.string(), ApiModel::class.java)
                when (e.code()) {
                    500 -> failure(responseDao?.message ?: "Server Error")
                    504 -> failure(responseDao?.message ?: "Response Error")
                    502 -> failure(responseDao?.message ?: "Resource Not Found")
                    404 -> failure(responseDao?.message ?: "Resource Not Found")
                    403 -> failure(responseDao?.message ?: "Unauthorized")
                    400 -> failure(responseDao?.message ?: "Bad Request")
                    else -> failure(responseDao?.message ?: "Unknown Error")
                }
            } catch (exception: Exception) {
                /**
                 * When model cannot get response from server
                 * detect exception and
                 */
                when (exception) {
                    is UnknownHostException -> failure("Unknown host : ${e.message}")
                    is SocketTimeoutException -> failure("Socket Timeout : ${e.message}")
                    else -> failure("Cannot Connect : ${e.message}")
                }
            }
        }
        is UnknownHostException -> failure("Unknown host : ${e.message}")
        is SocketTimeoutException -> failure("Socket Timeout : ${e.message}")
        is ConnectException -> failure("Cannot Connet : ${e.message}")
        else -> failure("Unknown Error : ${e.message}")
    }
}

fun <T> failure(message: String) = Payload<T>(
    ok = false,
    message = message,
    data = null
)

fun <T> success(data: T? = null, message: String = "OK") = Payload(
    ok = true,
    message = message,
    data = data
)

fun rLoading() = Resource.Loading
fun <T> rSuccess(data: T?) = Resource.Success(data)
fun rError(message: String) = Resource.Error(message)
fun rFinish() = Resource.Finish

fun json(data: Any): String? = Gson().toJson(data)

fun <T> toObject(value: String?, classOfT: Class<T>): T? =
    if (value.isNullOrEmpty()) null
    else try {
        Gson().fromJson(value, classOfT)
    } catch (e: Exception) {
        null
    }