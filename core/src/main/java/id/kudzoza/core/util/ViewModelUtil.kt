package id.kudzoza.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Kudzoza
 * on 03/08/2021
 **/

fun ViewModel.launch(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    action: suspend () -> Unit
) {
    viewModelScope.launch(dispatcher) {
        action.invoke()
    }
}

suspend fun main(
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    action: suspend () -> Unit
) {
    withContext(dispatcher) {
        action.invoke()
    }
}