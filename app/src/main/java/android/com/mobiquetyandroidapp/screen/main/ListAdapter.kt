package android.com.mobiquetyandroidapp.screen.main

import android.com.mobiquetyandroidapp.BuildConfig
import android.com.mobiquetyandroidapp.R
import android.com.mobiquetyandroidapp.data.remote.Product
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_product.view.*

class ListAdapter(
        private val context: Context,
        private val onProductClicked: (Product) -> Unit
) : RecyclerView.Adapter<ListAdapter.VH>() {

    private val data = mutableListOf<Product>()

    /* inherited */

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(
            holder: VH,
            position: Int
    ) {
        holder.bind(data[position], onProductClicked)
    }

    /* own methods */

    fun update(newData: List<Product>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product, onProductClicked: (Product) -> Unit) = with(itemView) {
            tv_product_name.text = product.name
            val pictureUrl = BuildConfig.API_URL + product.url.replace("/", "")
            Glide.with(context)
                    .load(pictureUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(iv_product_thumbnail)

            itemView.setOnClickListener {
                onProductClicked(product)
            }
        }
    }
}