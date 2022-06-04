package id.kudzoza.core.data.model

/**
 * Created by Kudzoza
 * on 19/09/2021
 **/

sealed class SimpleResource<out T> {
    class Success<T>(val data: T?) : SimpleResource<T>()
    class Error(val message: String) : SimpleResource<Nothing>()
}
