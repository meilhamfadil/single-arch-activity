package id.kudzoza.nippobulary.learn.screen.word

import androidx.lifecycle.ViewModel
import id.kudzoza.core.core.SingleLiveEvent
import id.kudzoza.nippobulary.domain.WordUseCase

/**
 * Created by Kudzoza
 * on 21/12/2021
 **/

class WordVM(
    private val wordUseCase: WordUseCase = WordUseCase.getInstance(),
) : ViewModel() {

    val hiragana = mutableListOf<Pair<String, String>>()
    val katakana = mutableListOf<Pair<String, String>>()
    var current = "hiragana"

    val eventLoaded = SingleLiveEvent<Void>()

    init {
        loadData()
    }

    fun loadData() {
        hiragana.clear()
        hiragana.addAll(wordUseCase.getHiraganaAll())
        katakana.clear()
        katakana.addAll(wordUseCase.getKatakanaAll())
        eventLoaded.call()
    }

}