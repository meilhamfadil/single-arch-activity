package id.kudzoza.core.base

import androidx.lifecycle.ViewModel

/**
 * Created by Kudzoza
 * on 04/06/2022
 **/

abstract class BaseViewModel : ViewModel() {

    val eventShowMessage = SingleLiveEvent<String>()
    val eventShowMessageRes = SingleLiveEvent<String>()

}