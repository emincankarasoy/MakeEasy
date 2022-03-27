package com.emincankarasoy.makeeasy.ui.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.util.extensions.setBackgroundColorWithTransactionType

@BindingAdapter("app:transactionBackground")
fun viewBackgroundColorWithTransactionType(view: View,transaction: Transaction){
    view.setBackgroundColorWithTransactionType(transaction)
}
