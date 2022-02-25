package id.kudzoza.nippobulary.learn.screen.favorite

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.kudzoza.core.AppKeyword
import id.kudzoza.nippobulary.learn.screen.sentence.SentenceFragment
import id.kudzoza.nippobulary.learn.screen.vocab.VocabFragment

/**
 * Created by Kudzoza
 * on 06/01/2022
 **/

class FavoritePager(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val sentenceFragment by lazy {
        SentenceFragment.newInstance().apply {
            arguments = bundleOf(AppKeyword.EXTRAS_FROM_FAVORITE_PAGES to true)
        }
    }
    private val vocabFragment by lazy {
        VocabFragment.newInstance().apply {
            arguments = bundleOf(AppKeyword.EXTRAS_FROM_FAVORITE_PAGES to true)
        }
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        if (position == 0) sentenceFragment
        else vocabFragment

}