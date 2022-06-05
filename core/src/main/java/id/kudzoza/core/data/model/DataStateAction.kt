package id.kudzoza.core.data.model

class DataStateAction<T> {
    var actionLoading: () -> Unit = {}
    var actionSuccess: (T?) -> Unit = {}
    var actionError: (DataState.Error) -> Unit = {}
    var actionFinish: () -> Unit = {}
}

fun <T> DataState<T>.resource(unit: DataStateAction<T>.() -> Unit) {
    val action = DataStateAction<T>()
    unit.invoke(action)
    when (this) {
        is DataState.Loading -> action.actionLoading.invoke()
        is DataState.Success -> action.actionSuccess.invoke(this.data)
        is DataState.Error -> action.actionError.invoke(this)
        is DataState.Finish -> action.actionFinish.invoke()
    }
}

fun <T> DataStateAction<T>.loading(unit: () -> Unit) {
    this.actionLoading = unit
}

fun <T> DataStateAction<T>.success(unit: (T?) -> Unit) {
    this.actionSuccess = (unit)
}

fun <T> DataStateAction<T>.error(unit: (DataState.Error) -> Unit) {
    this.actionError = unit
}

fun <T> DataStateAction<T>.finish(unit: () -> Unit) {
    this.actionFinish = unit
}
