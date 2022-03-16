package com.emincankarasoy.makeeasy.util.extensions

import android.view.View
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.data.model.TransactionTypes

fun View.setBackgroundColorWithTransactionType(transaction: Transaction){
    when(transaction.type){
        TransactionTypes.INCOMING -> {
            this.setBackgroundResource(R.drawable.recycler_item_wallet_type_incoming)
        }
        TransactionTypes.OUTCOMING -> {
            this.setBackgroundResource(R.drawable.recycler_item_wallet_type_outcoming)
        }
    }
}