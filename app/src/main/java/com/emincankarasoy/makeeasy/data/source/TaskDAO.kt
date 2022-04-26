package com.emincankarasoy.makeeasy.data.source

import androidx.room.*
import com.emincankarasoy.makeeasy.data.model.Task

@Dao
interface TaskDAO {
    @Insert
    suspend fun add(task: Task) : Long

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM `tasks`")
    suspend fun getAll() : List<Task>
}