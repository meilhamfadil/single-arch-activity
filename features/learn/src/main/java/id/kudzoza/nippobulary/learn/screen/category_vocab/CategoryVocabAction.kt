package id.kudzoza.nippobulary.learn.screen.category_vocab

import id.kudzoza.nippobulary.domain.model.CategoryVocabModel

/**
 * Created by Kudzoza
 * on 06/12/2021
 **/

interface CategoryVocabAction {

    fun onClickCategory(category: CategoryVocabModel)

}