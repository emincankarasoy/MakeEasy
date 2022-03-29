package com.emincankarasoy.makeeasy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    var category:String,
    var description: String,
    var date: String,
    var count: String,
    var type: String
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}
