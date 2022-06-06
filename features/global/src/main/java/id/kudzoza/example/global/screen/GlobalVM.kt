package id.kudzoza.example.global.screen

import id.kudzoza.core.base.BaseViewModel
import id.kudzoza.core.base.SingleLiveEvent

/**
 * Created by Kudzoza
 * on 06/06/2022
 **/

class GlobalVM : BaseViewModel() {

    val eventBack = SingleLiveEvent<Void>()

    fun callEvent(event: GlobalEvent) {
        when (event) {
            is GlobalEvent.BackPressed -> eventBack.call()
        }
    }

}