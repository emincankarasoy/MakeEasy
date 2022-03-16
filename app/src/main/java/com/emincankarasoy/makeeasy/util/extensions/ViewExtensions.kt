package com.emincankarasoy.makeeasy.util.extensions

import android.view.View
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.data.model.TransactionType

fun View.setBackgroundColorWithTransactionType(transaction: Transaction){
    when(transaction.type){
        TransactionType.INCOMING -> {
            this.setBackgroundResource(R.drawable.recycler_item_wallet_type_incoming)
        }
        TransactionType.OUTCOMING -> {
            this.setBackgroundResource(R.drawable.recycler_item_wallet_type_outcoming)
        }
    }
}