package com.emincankarasoy.makeeasy.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.emincankarasoy.makeeasy.data.model.Transaction

@Dao
interface TransactionDAO {
    @Query("SELECT * FROM `transactions` ORDER BY uuid DESC LIMIT 10")
    suspend fun getLastTenTransaction() : List<Transaction>

    @Query("SELECT * FROM `transactions` WHERE type = 'INCOME' ORDER BY uuid DESC")
    suspend fun getIncomeTransactions() : List<Transaction>

    @Query("SELECT * FROM `transactions` WHERE type = 'OUTCOME' ORDER BY uuid DESC")
    suspend fun getOutcomeTransactions() : List<Transaction>

    @Insert
    suspend fun add(transaction : Transaction) : Long

    @Delete
    suspend fun delete(transaction:Transaction)

    @Update
    suspend fun update(transaction: Transaction)
}