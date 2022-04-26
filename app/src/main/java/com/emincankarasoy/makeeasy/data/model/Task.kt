package com.emincankarasoy.makeeasy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    var title : String,
    var description : String,
    var date : String,
    var isComplete : Boolean,
    var alarm : String,
    var event : String
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}
