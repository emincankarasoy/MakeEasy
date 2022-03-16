package com.emincankarasoy.makeeasy.data.model

data class Transaction(
    var transactionID: Int,
    var category: String,
    var type: TransactionTypes,
    var description: String,
    var date: String,
    var count: Int
)
