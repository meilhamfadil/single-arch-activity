package id.kudzoza.nippobulary.learn.screen.category

import id.kudzoza.nippobulary.domain.model.CategoryModel

/**
 * Created by Kudzoza
 * on 06/12/2021
 **/

interface CategoryAction {

    fun onClickCategory(category: CategoryModel)

}