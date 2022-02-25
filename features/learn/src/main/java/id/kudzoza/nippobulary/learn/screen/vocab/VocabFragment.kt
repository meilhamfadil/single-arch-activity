package id.kudzoza.nippobulary.learn.screen.vocab

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.kudzoza.core.AppKeyword
import id.kudzoza.core.core.BaseFragment
import id.kudzoza.core.data.model.Resource
import id.kudzoza.core.helper.EventHelper
import id.kudzoza.core.util.areVisible
import id.kudzoza.core.util.catchToNull
import id.kudzoza.nippobulary.domain.model.VocabModel
import id.kudzoza.nippobulary.learn.R
import id.kudzoza.nippobulary.learn.databinding.FragmentVocabBinding
import java.util.*

/**
 * Created by Kudzoza
 * on 24/12/2021
 **/

class VocabFragment : BaseFragment<FragmentVocabBinding>(
    FragmentVocabBinding::inflate
), VocabAction, Toolbar.OnMenuItemClickListener, TextToSpeech.OnInitListener {

    private val vm: VocabVM by viewModels()

    private val vocabAdapter
        get() = catchToNull { binding.recyclerVocab.adapter as VocabAdapter }

    private val isFromFavorite
        get() = arguments?.getBoolean(AppKeyword.EXTRAS_FROM_FAVORITE_PAGES) ?: false

    private lateinit var textToSpeech: TextToSpeech

    override fun onViewReady() = with(binding) {

        // Init TTS
        textToSpeech = TextToSpeech(
            requireContext(),
            this@VocabFragment,
            "com.google.android.tts"
        )

        // init title and navigation toolbar
        toolbar.title = arguments?.getString(AppKeyword.EXTRAS_CATEGORY_NAME)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        // init recycler sentences
        recyclerVocab.layoutManager = LinearLayoutManager(requireContext())
        recyclerVocab.adapter = VocabAdapter(
            isFromFavorite,
            this@VocabFragment::onClickFavorite,
            this@VocabFragment::onClickSpeakOut
        )

        // init swipe refresh sentences
        refreshVocab.setOnRefreshListener {
            vm.loadSentence()
        }

        // init
        appbar.visibility = isFromFavorite.not().areVisible()
        toolbar.inflateMenu(R.menu.menu_vocab)
        toolbar.setOnMenuItemClickListener(this@VocabFragment)
        renderMenu()

    }

    override fun registerObserver() = with(vm) {

        eventVocab.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.refreshVocab.isRefreshing = true
                }
                is Resource.Success -> {
                    it.data?.let { list ->
                        vocabAdapter?.replaceAll(list)
                    }

                }
                is Resource.Error -> {
                    EventHelper.message.value = it.message
                }
                is Resource.Finish -> {
                    binding.refreshVocab.isRefreshing = false
                }
            }
        }

    }

    override fun onClickFavorite(vocab: VocabModel) {
        vm.updateFavorite(vocab)
        val message = if (vocab.favorite) "ditambahkan ke favorit" else "dihapus dari favorit"
        EventHelper.message.value = "${vocab.word} $message"
    }

    override fun onClickSpeakOut(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts1")
    }

    private fun renderMenu() {
        vocabAdapter?.itemContainer?.let { container ->
            binding.toolbar.menu.forEach {
                it.isVisible = when (it.itemId) {
                    R.id.menu_hide_word -> container.contains("word")
                    R.id.menu_hide_kanji -> container.contains("kanji")
                    R.id.menu_hide_hiragana -> container.contains("hiragana")
                    R.id.menu_hide_romanji -> container.contains("romanji")
                    R.id.menu_show_word -> container.contains("word").not()
                    R.id.menu_show_kanji -> container.contains("kanji").not()
                    R.id.menu_show_hiragana -> container.contains("hiragana").not()
                    R.id.menu_show_romanji -> container.contains("romanji").not()
                    else -> true
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        vocabAdapter?.itemContainer?.let { container ->
            when (item?.itemId) {
                R.id.menu_hide_word -> container.remove("word")
                R.id.menu_hide_kanji -> container.remove("kanji")
                R.id.menu_hide_hiragana -> container.remove("hiragana")
                R.id.menu_hide_romanji -> container.remove("romanji")
                R.id.menu_show_word -> container.add("word")
                R.id.menu_show_kanji -> container.add("kanji")
                R.id.menu_show_hiragana -> container.add("hiragana")
                R.id.menu_show_romanji -> container.add("romanji")
            }
            renderMenu()
            vocabAdapter?.notifyDataSetChanged()
        }
        return true
    }

    override fun onPause() {
        textToSpeech.stop()
        super.onPause()
    }

    override fun onDestroy() {
        textToSpeech.shutdown()
        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val v = Voice("female",
                Locale.JAPANESE,
                Voice.QUALITY_NORMAL,
                Voice.LATENCY_NORMAL,
                false,
                setOf()
            )
            textToSpeech.voice = v
        }
    }

    companion object {

        fun newInstance() = VocabFragment()

    }

}