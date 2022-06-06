package id.kudzoza.example.example.screen.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kudzoza.example.data.model.MovieModel
import id.kudzoza.example.example.databinding.ItemMovieBinding

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

class MovieAdapter(
    private val onClickItem: (MovieModel) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.VH>() {

    private val data = mutableListOf<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val current = data[position]
        holder.binding.apply {
            title.text = current.title

            title.setOnClickListener {
                onClickItem.invoke(current)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAll(newData: List<MovieModel>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class VH(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

}