package com.emincankarasoy.makeeasy.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.emincankarasoy.makeeasy.data.source.Repository
import com.emincankarasoy.makeeasy.data.source.TaskRepository
import com.emincankarasoy.makeeasy.data.source.TransactionRepository
import com.emincankarasoy.makeeasy.ui.viewmodel.TaskViewModel
import com.emincankarasoy.makeeasy.ui.viewmodel.TransactionViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val repository: Repository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(TransactionViewModel::class.java) ->
                TransactionViewModel(repository as TransactionRepository)
            isAssignableFrom(TaskViewModel::class.java) ->
                TaskViewModel(repository as TaskRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}

