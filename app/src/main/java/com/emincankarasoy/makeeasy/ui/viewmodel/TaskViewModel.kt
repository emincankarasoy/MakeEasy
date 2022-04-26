package com.emincankarasoy.makeeasy.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emincankarasoy.makeeasy.data.model.Task
import com.emincankarasoy.makeeasy.data.source.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel(){

    val tasksLiveData : MutableLiveData<List<Task>> = MutableLiveData()

    fun add(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            taskRepository.add(task)
        }
    }

    fun getAll(){
        viewModelScope.launch (Dispatchers.IO){
            tasksLiveData.postValue(taskRepository.getAll())
        }
    }

    fun delete(task : Task){
        viewModelScope.launch (Dispatchers.IO) {
            taskRepository.delete(task)
        }
    }

}