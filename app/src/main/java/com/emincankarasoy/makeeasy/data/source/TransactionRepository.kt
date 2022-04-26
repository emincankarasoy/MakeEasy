package com.emincankarasoy.makeeasy.data.source

import android.content.Context
import com.emincankarasoy.makeeasy.data.LocalDatabase
import com.emincankarasoy.makeeasy.data.model.Transaction

class TransactionRepository(context: Context) : Repository {
    private val transactionDAO = LocalDatabase.invoke(context).transactionDao()

    suspend fun getLastTenTransaction() : List<Transaction>{
        return transactionDAO.getLastTenTransaction()
    }

    suspend fun add(transaction: Transaction) : Boolean{
        transactionDAO.add(transaction)
        return true
    }

    suspend fun getIncomeTransactions() : List<Transaction>{
        return transactionDAO.getIncomeTransactions()
    }

    suspend fun getOutcomeTransactions() : List<Transaction>{
        return transactionDAO.getOutcomeTransactions()
    }

    suspend fun deleteTransaction(transaction: Transaction){
        return transactionDAO.delete(transaction)
    }
}