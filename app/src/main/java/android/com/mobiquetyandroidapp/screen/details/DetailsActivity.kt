package android.com.mobiquetyandroidapp.screen.details

import android.com.mobiquetyandroidapp.BuildConfig
import android.com.mobiquetyandroidapp.R
import android.com.mobiquetyandroidapp.data.remote.Product
import android.com.mobiquetyandroidapp.screen.base.BaseActivity
import android.com.mobiquetyandroidapp.utils.C
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.ac_details.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailsActivity : BaseActivity<DetailsViewModel>() {

    override val viewModel: DetailsViewModel by viewModel()

    // life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        showBackButton()

        title = getString(R.string.product)

        intent.extras?.let { bundle ->
            val productModel: Product? = bundle.getParcelable(C.Extra.PRODUCT_ITEM)
            productModel?.let { product ->
                val pictureUrl = BuildConfig.API_URL + product.url.replace("/", "")
                Glide.with(this)
                        .load(pictureUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(iv_product_image)
                tv_name.text = product.name
                val price = "${product.salePrice.amount} ${product.salePrice.currency}"
                tv_price.text = price
            }
        }
    }

    // inherited

    override fun getLayoutRes() = R.layout.ac_details
}