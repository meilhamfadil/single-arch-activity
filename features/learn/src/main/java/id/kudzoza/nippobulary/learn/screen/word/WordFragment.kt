package id.kudzoza.nippobulary.learn.screen.word

import android.annotation.SuppressLint
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import id.kudzoza.core.core.ActivityContract
import id.kudzoza.core.core.BaseFragment
import id.kudzoza.core.util.catchToNull
import id.kudzoza.nippobulary.learn.R
import id.kudzoza.nippobulary.learn.databinding.FragmentWordBinding

/**
 * Created by Kudzoza
 * on 21/12/2021
 **/

class WordFragment : BaseFragment<FragmentWordBinding>(
    FragmentWordBinding::inflate
), Toolbar.OnMenuItemClickListener {

    private val vm: WordVM by viewModels()

    private val wordAdapter
        get() = catchToNull { binding.recyclerWord.adapter as WordAdapter }

    override fun onViewReady() = with(binding) {

        recyclerWord.layoutManager = GridLayoutManager(requireContext(), 5)
        recyclerWord.adapter = WordAdapter()

        refreshWord.setOnRefreshListener {
            vm.loadData()
        }

        toolbar.inflateMenu(R.menu.menu_word)
        toolbar.setOnMenuItemClickListener(this@WordFragment)
        toolbar.setNavigationOnClickListener {
            (requireActivity() as ActivityContract).toggleDrawer()
        }
        renderMenu()

    }

    override fun registerObserver() = with(vm) {

        eventLoaded.observe(viewLifecycleOwner) {
            wordAdapter?.replaceAll(
                if (current == "hiragana") hiragana
                else katakana
            )
        }

    }

    private fun renderMenu() {
        binding.toolbar.menu.forEach {
            it.isVisible = when (it.itemId) {
                R.id.menu_switch_hiragana -> vm.current == "katakana"
                R.id.menu_switch_katakana -> vm.current == "hiragana"
                else -> true
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_switch_hiragana -> vm.current = "hiragana"
            R.id.menu_switch_katakana -> vm.current = "katakana"
        }
        vm.eventLoaded.call()
        renderMenu()
        return true
    }

}