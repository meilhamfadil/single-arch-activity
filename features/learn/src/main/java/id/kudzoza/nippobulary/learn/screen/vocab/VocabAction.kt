package id.kudzoza.nippobulary.learn.screen.vocab

import id.kudzoza.nippobulary.domain.model.VocabModel

/**
 * Created by Kudzoza
 * on 11/12/2021
 **/

interface VocabAction {

    fun onClickFavorite(vocab: VocabModel)

    fun onClickSpeakOut(text: String)

}