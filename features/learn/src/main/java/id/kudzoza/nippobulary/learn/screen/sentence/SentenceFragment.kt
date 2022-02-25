package id.kudzoza.nippobulary.learn.screen.sentence

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
import id.kudzoza.nippobulary.domain.model.SentenceModel
import id.kudzoza.nippobulary.learn.R
import id.kudzoza.nippobulary.learn.databinding.FragmentSentenceBinding
import java.util.*


/**
 * Created by Kudzoza
 * on 11/12/2021
 **/

class SentenceFragment : BaseFragment<FragmentSentenceBinding>(
    FragmentSentenceBinding::inflate
), SentenceAction, Toolbar.OnMenuItemClickListener, TextToSpeech.OnInitListener {

    private val vm: SentenceVM by viewModels()

    private val sentenceAdapter
        get() = catchToNull { binding.recyclerSentence.adapter as SentenceAdapter }

    private val isFromFavorite
        get() = arguments?.getBoolean(AppKeyword.EXTRAS_FROM_FAVORITE_PAGES) ?: false

    private lateinit var textToSpeech: TextToSpeech

    override fun onViewReady() = with(binding) {

        // Init TTS
        textToSpeech = TextToSpeech(
            requireContext(),
            this@SentenceFragment,
            "com.google.android.tts"
        )

        // init title and navigation toolbar
        toolbar.title = arguments?.getString(AppKeyword.EXTRAS_CATEGORY_NAME)
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        // init recycler sentences
        recyclerSentence.layoutManager = LinearLayoutManager(requireContext())
        recyclerSentence.adapter = SentenceAdapter(
            isFromFavorite,
            this@SentenceFragment::onClickFavorite,
            this@SentenceFragment::onClickSpeakOut
        )

        // init swipe refresh sentences
        refreshSentence.setOnRefreshListener {
            vm.loadSentence()
        }

        // init
        appbar.visibility = isFromFavorite.not().areVisible()
        toolbar.inflateMenu(R.menu.menu_sentence)
        toolbar.setOnMenuItemClickListener(this@SentenceFragment)
        renderMenu()

    }

    override fun registerObserver() = with(vm) {

        eventSentence.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.refreshSentence.isRefreshing = true
                }
                is Resource.Success -> {
                    it.data?.let { list ->
                        sentenceAdapter?.replaceAll(list)
                    }
                }
                is Resource.Error -> {
                    EventHelper.message.value = it.message
                }
                is Resource.Finish -> {
                    binding.refreshSentence.isRefreshing = false
                }
            }
        }

    }

    override fun onClickFavorite(sentence: SentenceModel) {
        vm.updateFavorite(sentence)
        val message = if (sentence.favorite) "ditambahkan ke favorit" else "dihapus dari favorit"
        EventHelper.message.value = "${sentence.word} $message"
    }

    override fun onClickSpeakOut(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts1")
    }

    private fun renderMenu() {
        sentenceAdapter?.itemContainer?.let { container ->
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
        sentenceAdapter?.itemContainer?.let { container ->
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
            sentenceAdapter?.notifyDataSetChanged()
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

        fun newInstance() = SentenceFragment()

    }

}