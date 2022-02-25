package id.kudzoza.nippobulary.learn.screen.category

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import id.kudzoza.core.AppKeyword
import id.kudzoza.core.core.ActivityContract
import id.kudzoza.core.core.BaseFragment
import id.kudzoza.core.data.model.Resource
import id.kudzoza.core.helper.EventHelper
import id.kudzoza.core.util.catchToNull
import id.kudzoza.nippobulary.domain.model.CategoryModel
import id.kudzoza.nippobulary.domain.payload.CategoryPayload
import id.kudzoza.nippobulary.learn.databinding.FragmentCategoryBinding

/**
 * Created by Kudzoza
 * on 06/12/2021
 **/

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(
    FragmentCategoryBinding::inflate
), CategoryAction {

    private val vm: CategoryVM by viewModels()

    private val categoryAdapter
        get() = catchToNull { binding.recyclerCategory.adapter as CategoryAdapter }

    override fun onResume() {
        super.onResume()
        if (isViewed)
            vm.loadCategories()
        isViewed = true
    }

    override fun onViewReady() = with(binding) {

        toolbar.setNavigationOnClickListener {
            (requireActivity() as ActivityContract).toggleDrawer()
        }

        recyclerCategory.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerCategory.adapter = CategoryAdapter(this@CategoryFragment::onClickCategory)

        refreshCategory.setOnRefreshListener {
            vm.loadCategories()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun registerObserver() = with(vm) {
        eventLoadData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.refreshCategory.isRefreshing = true
                }
                is Resource.Success -> {
                    categoryAdapter?.replaceAll(it.data.orEmpty())
                }
                is Resource.Error -> {
                    EventHelper.message.value = it.message
                }
                is Resource.Finish -> {
                    binding.refreshCategory.isRefreshing = false
                }
            }
        }
    }

    override fun onClickCategory(category: CategoryModel) {
        findNavController().navigate(
            features.categoryToSentenceNavigation.invoke(),
            bundleOf(
                AppKeyword.EXTRAS_CATEGORY_NAME to category.name,
                AppKeyword.EXTRAS_CATEGORY_CODE to category.code
            )
        )
    }
}