package id.kudzoza.nippobulary.learn.screen.sentence

import id.kudzoza.nippobulary.domain.model.SentenceModel

/**
 * Created by Kudzoza
 * on 11/12/2021
 **/

interface SentenceAction {

    fun onClickFavorite(sentence: SentenceModel)

    fun onClickSpeakOut(text: String)

}