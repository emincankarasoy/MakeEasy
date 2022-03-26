package com.emincankarasoy.makeeasy.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var transactionID: Int,
    var category: String,
    var type: TransactionTypes,
    var description: String,
    var date: String,
    var count: Int
)
