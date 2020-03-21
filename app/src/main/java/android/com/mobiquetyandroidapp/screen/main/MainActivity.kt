package android.com.mobiquetyandroidapp.screen.main

import android.com.mobiquetyandroidapp.R
import android.com.mobiquetyandroidapp.screen.base.BaseActivity
import android.com.mobiquetyandroidapp.screen.details.DetailsActivity
import android.com.mobiquetyandroidapp.utils.C
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.ac_main.*
import kotlinx.android.synthetic.main.loader.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()

    private lateinit var adapter: ListAdapter

    // life

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        title = getString(R.string.products)

        spl_products.setOnRefreshListener {
            viewModel.getProducts()
        }

        adapter = ListAdapter(this) { product ->
            startActivity(Intent(this, DetailsActivity::class.java).apply {
                putExtra(C.Extra.PRODUCT_ITEM, product)
            })
        }
        rv.adapter = adapter

        initObservers()
    }


    // inherited

    override fun getLayoutRes() = R.layout.ac_main

    // own

    private fun initObservers() {
        with(viewModel) {
            isLoading.observe(this@MainActivity, Observer {
                lyt_loader.visibility = if (it) android.view.View.VISIBLE else android.view.View.GONE
                if (!it) {
                    spl_products.isRefreshing = false
                }
            })
            isProductsLoaded.observe(this@MainActivity, Observer {
                adapter.update(it)
                spl_products.isRefreshing = false
            })
        }
    }
}