package id.kudzoza.nippobulary.learn.screen.word

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kudzoza.nippobulary.learn.databinding.ItemWordBinding

/**
 * Created by Kudzoza
 * on 21/12/2021
 **/

class WordAdapter : RecyclerView.Adapter<WordAdapter.VH>() {

    private val data = mutableListOf<Pair<String, String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            word.text = data[position].second
            pronounce.text = data[position].first
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAll(newData: List<Pair<String, String>>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root)

}