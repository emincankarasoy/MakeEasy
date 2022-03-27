package com.emincankarasoy.makeeasy.util.extensions

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.data.source.Repository
import com.emincankarasoy.makeeasy.util.ViewModelFactory

fun Fragment.getViewModelFactory(repository: Repository) : ViewModelFactory{
    return ViewModelFactory(repository,this)
}

fun View.setBackgroundColorWithTransactionType(transaction: Transaction){
    when(transaction.type){
        "Income" -> {
            this.setBackgroundResource(R.drawable.recycler_item_wallet_type_incoming)
        }
        "Outcome" -> {
            this.setBackgroundResource(R.drawable.recycler_item_wallet_type_outcoming)
        }
    }
}