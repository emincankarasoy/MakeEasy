package com.emincankarasoy.makeeasy.data.source

import android.content.Context
import android.util.Log
import com.emincankarasoy.makeeasy.data.LocalDatabase
import com.emincankarasoy.makeeasy.data.model.Task

class TaskRepository(context: Context) : Repository {
    private val taskDAO = LocalDatabase.invoke(context).taskDao()

    suspend fun add(task: Task){
        taskDAO.add(task)
    }

    suspend fun addAll(tasks : List<Task>){
        taskDAO.insertAll(tasks).also {
            Log.i("ROOM", "Insert All Completed!")
        }
    }

    suspend fun getAll():List<Task>{
        return taskDAO.getAll()
    }

    suspend fun delete(task: Task){
        taskDAO.delete(task)
    }

    suspend fun deleteAll(){
        taskDAO.deleteAll()
    }
}