package id.kudzoza.example.global.screen

/**
 * Created by Kudzoza
 * on 06/06/2022
 **/

sealed class GlobalEvent {
    object BackPressed : GlobalEvent()
}