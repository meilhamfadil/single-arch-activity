package id.kudzoza.nippobulary.learn.screen.sentence

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import id.kudzoza.core.AppKeyword
import id.kudzoza.core.core.SingleLiveEvent
import id.kudzoza.core.data.model.Resource
import id.kudzoza.core.util.launch
import id.kudzoza.core.util.main
import id.kudzoza.nippobulary.domain.SentenceUseCase
import id.kudzoza.nippobulary.domain.model.SentenceModel
import id.kudzoza.nippobulary.domain.payload.SentencePayload
import kotlinx.coroutines.flow.collect

/**
 * Created by Kudzoza
 * on 11/12/2021
 **/

class SentenceVM(
    private val arguments: SavedStateHandle,
    private val sentenceUseCase: SentenceUseCase,
) : ViewModel() {

    constructor(arguments: SavedStateHandle) : this(arguments, SentenceUseCase.getInstance())

    private val categoryCode
        get() = arguments.get<String>(AppKeyword.EXTRAS_CATEGORY_CODE).orEmpty()

    private val isFromFavoritePage
        get() = arguments.get<Boolean>(AppKeyword.EXTRAS_FROM_FAVORITE_PAGES) ?: false

    val eventSentence = SingleLiveEvent<Resource<List<SentenceModel>>>()

    init {
        loadSentence()
    }

    fun loadSentence() = launch {
        with(
            if (isFromFavoritePage) sentenceUseCase.getLocalFavoriteSentence()
            else sentenceUseCase.getLocalSentence(categoryCode)
        ) {
            collect {
                main { eventSentence.value = it }
            }
        }
    }

    fun updateFavorite(sentence: SentenceModel) = launch {
        sentenceUseCase.addToFavorite(sentence)
    }

}