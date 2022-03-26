package com.emincankarasoy.makeeasy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emincankarasoy.makeeasy.data.model.Transaction

@Database(entities = [Transaction::class],version = 1)
abstract class LocalDatabase : RoomDatabase(){
    companion object{
        @Volatile private var instance: LocalDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,LocalDatabase::class.java,"MakeEasy"
        ).build()
    }
    abstract fun transactionDao() : TransactionDAO
}