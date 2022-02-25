package id.kudzoza.nippobulary.learn.screen.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.kudzoza.core.AppResource
import id.kudzoza.core.util.drawable
import id.kudzoza.nippobulary.domain.model.CategoryModel
import id.kudzoza.nippobulary.domain.payload.CategoryPayload
import id.kudzoza.nippobulary.learn.databinding.ItemCategoryBinding

/**
 * Created by Kudzoza
 * on 06/12/2021
 **/

class CategoryAdapter(
    private val onClickItem: (CategoryModel) -> Unit,
) : RecyclerView.Adapter<CategoryAdapter.VH>() {

    private val data = mutableListOf<CategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCategoryBinding.inflate(
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

            if (item.icon.isNotEmpty()) {
                Picasso.get().load(item.icon)
                    .error(AppResource.imagePlaceholder)
                    .into(icon)
            } else {
                icon.setImageDrawable(root.context.drawable(AppResource.imagePlaceholder))
            }

            container.setOnClickListener {
                onClickItem.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAll(newData: List<CategoryModel>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}