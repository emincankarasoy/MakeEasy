package com.emincankarasoy.makeeasy.data.source

import androidx.room.*
import com.emincankarasoy.makeeasy.data.model.Task

@Dao
interface TaskDAO {
    @Insert
    suspend fun add(task: Task) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(task : List<Task>)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM `tasks`")
    suspend fun getAll() : List<Task>

    @Query("DELETE FROM `tasks`")
    suspend fun deleteAll()
}