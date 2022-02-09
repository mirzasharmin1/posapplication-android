package com.sharmin.posapplication.screens.order_placement

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.sharmin.posapplication.R
import com.sharmin.posapplication.databinding.ActivityOrderPlacementBinding
import com.sharmin.posapplication.screens.order.OrderActivity
import com.sharmin.posapplication.screens.settings.SettingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrderPlacementActivity : AppCompatActivity() {

    val viewModel: OrderPlacementViewModel by viewModels()
    private lateinit var binding: ActivityOrderPlacementBinding

    @Inject
    lateinit var productListFragment: ProductListFragment

    @Inject
    lateinit var cartListFragment: CartListFragment

    @Inject
    lateinit var cartTotalFragment: CartTotalFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderPlacementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setListeners()
    }

    private fun setupView() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.product_list_fragment, productListFragment)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.cart_list_fragment, cartListFragment)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.cart_total_fragment, cartTotalFragment)
            .commit()
    }

    private fun setListeners() {
        binding.submitOrder.setOnClickListener {
            viewModel.createTransaction()
        }

        viewModel.transactionSuccessful.observe(this, {
            if (it) {
                showTransactionSuccessDialog()
                viewModel.resetTransactionSuccessful()
            }
        })

        viewModel.transactionFailed.observe(this, {
            if (it) {
                showTransactionFailedDialog()
                viewModel.resetTransactionFailed()
            }
        })
    }

    private fun showTransactionSuccessDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.transaction_success_title)
        builder.setMessage(R.string.transaction_success_body)
        builder.setPositiveButton(R.string.okay_label) { dialog, _ ->
            dialog.dismiss()
            navigateToOrderActivity()
        }
        builder.show()
    }

    private fun showTransactionFailedDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.transaction_failed_title)
        builder.setMessage(R.string.transaction_failed_body)
        builder.setPositiveButton(R.string.go_back_label) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun navigateToOrderActivity() {
        val navIntent = Intent(this, OrderActivity::class.java)
        navIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(navIntent)

    }

    companion object {
        const val BUNDLE_PRODUCT_FILTER_TYPE = "product_type"
    }
}