package com.emincankarasoy.makeeasy.data.source

import android.content.Context
import com.emincankarasoy.makeeasy.data.LocalDatabase
import com.emincankarasoy.makeeasy.data.model.Task

class TaskRepository(context: Context) : Repository {
    private val taskDAO = LocalDatabase.invoke(context).taskDao()

    suspend fun add(task: Task){
        taskDAO.add(task)
    }

    suspend fun getAll():List<Task>{
        return taskDAO.getAll()
    }

    suspend fun delete(task: Task){
        taskDAO.delete(task)
    }
}