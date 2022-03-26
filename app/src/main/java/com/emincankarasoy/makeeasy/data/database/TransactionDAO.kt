package com.emincankarasoy.makeeasy.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emincankarasoy.makeeasy.data.model.Transaction

@Dao
interface TransactionDAO {
    @Query("SELECT * FROM `transaction` ORDER BY id DESC LIMIT 10")
    fun getLast10(): List<Transaction>

    @Query("SELECT * FROM `transaction`")
    fun get() : List<Transaction>

    @Query("SELECT * FROM 'transaction' WHERE id = :id")
    fun get(id:Int) : Transaction

    @Query("DELETE FROM `transaction`")
    fun delete()

    @Query("DELETE FROM `transaction` WHERE id = :id")
    fun delete(id:Int)

    @Insert
    fun insert(transaction: Transaction) : Long

    @Insert
    fun insert(vararg transactions:Transaction) : List<Long>
}