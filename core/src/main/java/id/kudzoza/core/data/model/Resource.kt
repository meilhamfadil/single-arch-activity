package id.kudzoza.core.data.model

/**
 * Created by Kudzoza
 * on 19/09/2021
 **/

sealed class Resource<out T> {
    class Success<T>(val data: T?) : Resource<T>()
    class Error(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object Finish : Resource<Nothing>()
}
