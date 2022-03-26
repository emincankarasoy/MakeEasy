package com.emincankarasoy.makeeasy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){

        }
    }



}