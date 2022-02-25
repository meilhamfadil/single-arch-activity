package id.kudzoza.nippobulary.learn.screen.vocab

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import id.kudzoza.core.AppKeyword
import id.kudzoza.core.core.SingleLiveEvent
import id.kudzoza.core.data.model.Resource
import id.kudzoza.core.util.launch
import id.kudzoza.core.util.main
import id.kudzoza.nippobulary.domain.VocabUseCase
import id.kudzoza.nippobulary.domain.model.VocabModel
import kotlinx.coroutines.flow.collect

/**
 * Created by Kudzoza
 * on 24/12/2021
 **/

class VocabVM(
    private val arguments: SavedStateHandle,
    private val vocabUseCase: VocabUseCase,
) : ViewModel() {

    constructor(arguments: SavedStateHandle) : this(arguments, VocabUseCase.getInstance())

    private val categoryCode
        get() = arguments.get<String>(AppKeyword.EXTRAS_CATEGORY_CODE).orEmpty()

    private val isFromFavoritePage
        get() = arguments.get<Boolean>(AppKeyword.EXTRAS_FROM_FAVORITE_PAGES) ?: false

    val eventVocab = SingleLiveEvent<Resource<List<VocabModel>>>()

    init {
        loadSentence()
    }

    fun loadSentence() = launch {
        with(
            if (isFromFavoritePage) vocabUseCase.getLocalFavoriteVocab()
            else vocabUseCase.getLocalVocab(categoryCode)
        ) {
            collect {
                main { eventVocab.value = it }
            }
        }
    }

    fun updateFavorite(vocab: VocabModel) = launch {
        vocabUseCase.addToFavorite(vocab)
    }

}