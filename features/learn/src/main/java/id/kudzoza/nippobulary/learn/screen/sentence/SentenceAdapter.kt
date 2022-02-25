package id.kudzoza.nippobulary.learn.screen.sentence

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kudzoza.core.util.areVisible
import id.kudzoza.core.util.drawable
import id.kudzoza.nippobulary.domain.model.SentenceModel
import id.kudzoza.nippobulary.learn.R
import id.kudzoza.nippobulary.learn.databinding.ItemSentenceBinding

/**
 * Created by Kudzoza
 * on 06/12/2021
 **/

class SentenceAdapter(
    private val isFromFavorite: Boolean,
    private val onClickFavorite: (SentenceModel) -> Unit,
    private val onClickSpeakOut: (String) -> Unit,
) : RecyclerView.Adapter<SentenceAdapter.VH>() {

    private val data = mutableListOf<SentenceModel>()

    val itemContainer =
        if (isFromFavorite) mutableListOf("word", "kanji", "hiragana", "romanji")
        else mutableListOf("word", "kanji", "romanji")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemSentenceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = data[position]
        holder.binding.apply {
            word.visibility = itemContainer.contains("word").areVisible()
            hiragana.visibility = itemContainer.contains("hiragana").areVisible()
            kanji.visibility = itemContainer.contains("kanji").areVisible()
            romanji.visibility = itemContainer.contains("romanji").areVisible()

            word.text = item.word
            hiragana.text = item.hiragana
            kanji.text = item.kanji
            romanji.text = item.romanji

            favorite.setImageDrawable(
                root.context.drawable(
                    if (item.favorite) R.drawable.icon_favorite_filled
                    else R.drawable.icon_favorite
                )
            )

            favorite.setOnClickListener {
                data[position] = item.copy(favorite = item.favorite.not())
                notifyDataSetChanged()
                onClickFavorite.invoke(data[position])
            }

            speakOut.setOnClickListener {
                onClickSpeakOut.invoke(item.hiragana)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAll(newData: List<SentenceModel>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemSentenceBinding) : RecyclerView.ViewHolder(binding.root)

}