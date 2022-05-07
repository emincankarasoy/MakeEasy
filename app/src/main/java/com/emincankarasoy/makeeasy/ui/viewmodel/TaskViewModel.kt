package com.emincankarasoy.makeeasy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emincankarasoy.makeeasy.data.model.Task
import com.emincankarasoy.makeeasy.data.source.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel(){

    fun add(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            taskRepository.add(task)
        }
    }

    suspend fun getAll() : List<Task> = coroutineScope {
        val list = async<List<Task>> {
            try {
                taskRepository.getAll()
            }finally {

            }
        }
        list.await()
    }

    fun delete(task : Task){
        viewModelScope.launch (Dispatchers.IO) {
            taskRepository.delete(task)
        }
    }

}