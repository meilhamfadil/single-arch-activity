package id.kudzoza.core.helper

import id.kudzoza.core.base.SingleLiveEvent

/**
 * Created by Kudzoza
 * on 04/10/2021
 **/

object EventHelper {

    val message = SingleLiveEvent<String>()
    val messageResource = SingleLiveEvent<Int>()
    val requesting = SingleLiveEvent<Boolean>()
    val busy = SingleLiveEvent<Boolean>()
    val finish = SingleLiveEvent<Void>()

}