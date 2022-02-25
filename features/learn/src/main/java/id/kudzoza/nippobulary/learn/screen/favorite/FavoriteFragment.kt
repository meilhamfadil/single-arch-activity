package id.kudzoza.nippobulary.learn.screen.favorite

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.kudzoza.core.core.ActivityContract
import id.kudzoza.core.core.BaseFragment
import id.kudzoza.nippobulary.learn.databinding.FragmentFavoriteBinding

/**
 * Created by Kudzoza
 * on 24/12/2021
 **/

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
) {

    private val vm: FavoriteVM by viewModels()

    private val pagerAdapter by lazy { FavoritePager(this@FavoriteFragment) }

    override fun onViewReady() = with(binding) {

        toolbar.setNavigationOnClickListener {
            (requireActivity() as ActivityContract).toggleDrawer()
        }

        pager.adapter = pagerAdapter
        pager.offscreenPageLimit = 1
        TabLayoutMediator(tab, pager) { tab, position ->
            tab.text =
                if (position == 0) "Frasa Umum"
                else "Kosa Kata"
        }.attach()

    }

    override fun registerObserver() = with(vm) {

    }

}