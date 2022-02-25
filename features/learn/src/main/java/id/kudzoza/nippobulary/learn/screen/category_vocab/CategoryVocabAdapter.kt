package id.kudzoza.nippobulary.learn.screen.category_vocab

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.kudzoza.core.AppResource
import id.kudzoza.core.util.drawable
import id.kudzoza.nippobulary.domain.model.CategoryModel
import id.kudzoza.nippobulary.domain.model.CategoryVocabModel
import id.kudzoza.nippobulary.learn.databinding.ItemCategoryBinding
import id.kudzoza.nippobulary.learn.databinding.ItemCategoryVocabBinding

/**
 * Created by Kudzoza
 * on 06/12/2021
 **/

class CategoryVocabAdapter(
    private val onClickItem: (CategoryVocabModel) -> Unit,
) : RecyclerView.Adapter<CategoryVocabAdapter.VH>() {

    private val data = mutableListOf<CategoryVocabModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCategoryVocabBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = data[position]
        holder.binding.apply {
            label.text = item.name

            container.setOnClickListener {
                onClickItem.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAll(newData: List<CategoryVocabModel>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemCategoryVocabBinding) : RecyclerView.ViewHolder(binding.root)

}