package id.kudzoza.core.data.model

/**
 * Created by Kudzoza
 * on 19/09/2021
 **/

sealed class DataState<out T> {
    class Success<T>(val data: T?) : DataState<T>()
    class Error(val message: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Finish : DataState<Nothing>()
}
