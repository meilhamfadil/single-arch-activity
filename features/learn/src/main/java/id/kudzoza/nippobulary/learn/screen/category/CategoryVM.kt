package id.kudzoza.nippobulary.learn.screen.category

import androidx.lifecycle.ViewModel
import id.kudzoza.core.core.SingleLiveEvent
import id.kudzoza.core.data.model.Resource
import id.kudzoza.core.util.launch
import id.kudzoza.core.util.main
import id.kudzoza.nippobulary.domain.CategoryUseCase
import id.kudzoza.nippobulary.domain.model.CategoryModel
import id.kudzoza.nippobulary.domain.payload.CategoryPayload
import kotlinx.coroutines.flow.collect

/**
 * Created by Kudzoza
 * on 06/12/2021
 **/

class CategoryVM(
    private val categoryUseCase: CategoryUseCase = CategoryUseCase.getInstance(),
) : ViewModel() {

    val eventLoadData = SingleLiveEvent<Resource<List<CategoryModel>>>()

    init {
        loadCategories()
    }

    fun loadCategories() = launch {
        categoryUseCase.getLocalCategories().collect {
            main { eventLoadData.value = it }
        }
    }

}