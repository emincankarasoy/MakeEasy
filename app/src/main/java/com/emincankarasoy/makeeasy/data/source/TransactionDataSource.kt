package com.emincankarasoy.makeeasy.data.source

import android.content.Context
import com.emincankarasoy.makeeasy.data.database.LocalDatabase
import com.emincankarasoy.makeeasy.data.database.TransactionDAO
import com.emincankarasoy.makeeasy.data.model.Transaction
import kotlinx.coroutines.launch

class TransactionDataSource(context : Context) : BaseDataSource(){

    private val transactionDAO: TransactionDAO = LocalDatabase(context).transactionDao()

    fun getLast10() : List<Transaction>{
        return transactionDAO.getLast10()
    }

}