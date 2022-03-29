package com.emincankarasoy.makeeasy.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.data.source.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel(private val transactionRepository: TransactionRepository) : ViewModel() {

    private val _lastTenTransaction = MutableLiveData<List<Transaction>>()
    val lastTenTransaction:LiveData<List<Transaction>>
        get() = _lastTenTransaction

    private val _outcomeTransaction = MutableLiveData<List<Transaction>>()
    val outcomeTransaction:LiveData<List<Transaction>>
        get() = _outcomeTransaction

    private val _incomeTransaction = MutableLiveData<List<Transaction>>()
    val incomeTransaction:LiveData<List<Transaction>>
        get() = _incomeTransaction

    fun add(transaction: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.add(transaction)
            refreshLastTen()
            refreshIncome()
            refreshOutcome()
        }
    }

    fun refreshLastTen(){
        viewModelScope.launch(Dispatchers.IO) {
            _lastTenTransaction.postValue(transactionRepository.getLastTenTransaction())
        }
    }

    fun refreshIncome(){
        viewModelScope.launch(Dispatchers.IO){
            _incomeTransaction.postValue(transactionRepository.getIncomeTransactions())
        }
    }

    fun refreshOutcome(){
        viewModelScope.launch(Dispatchers.IO){
            _outcomeTransaction.postValue(transactionRepository.getOutcomeTransactions())
        }
    }

    fun deleteTransaction(transaction: Transaction){
        viewModelScope.launch (Dispatchers.IO){
            transactionRepository.deleteTransaction(transaction)
            refreshIncome()
            refreshLastTen()
            refreshOutcome()
        }
    }
}