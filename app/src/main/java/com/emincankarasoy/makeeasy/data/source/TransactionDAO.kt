package com.emincankarasoy.makeeasy.data.source

import androidx.room.*
import com.emincankarasoy.makeeasy.data.model.Task
import com.emincankarasoy.makeeasy.data.model.Transaction

@Dao
interface TransactionDAO {
    @Query("SELECT * FROM `transactions` ORDER BY uuid DESC LIMIT 10")
    suspend fun getLastTenTransaction() : List<Transaction>

    @Query("SELECT * FROM `transactions` WHERE type = 'INCOME' ORDER BY uuid DESC")
    suspend fun getIncomeTransactions() : List<Transaction>

    @Query("SELECT * FROM `transactions` WHERE type = 'OUTCOME' ORDER BY uuid DESC")
    suspend fun getOutcomeTransactions() : List<Transaction>

    @Query("SELECT * FROM `transactions`")
    suspend fun getALL() : List<Transaction>

    @Insert
    suspend fun add(transaction : Transaction) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transaction : List<Transaction>)

    @Delete
    suspend fun delete(transaction:Transaction)

    @Query("DELETE FROM `transactions`")
    suspend fun deleteAll()

    @Update
    suspend fun update(transaction: Transaction)
}