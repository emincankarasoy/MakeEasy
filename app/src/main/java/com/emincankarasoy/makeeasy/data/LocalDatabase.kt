package com.emincankarasoy.makeeasy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.emincankarasoy.makeeasy.data.model.Task
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.data.source.TaskDAO
import com.emincankarasoy.makeeasy.data.source.TransactionDAO

@Database(entities = [Transaction::class, Task::class], version = 2,exportSchema = false)
abstract class LocalDatabase : RoomDatabase(){
    abstract fun transactionDao() : TransactionDAO
    abstract fun taskDao() : TaskDAO

    companion object{
        @Volatile private var instance : LocalDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,LocalDatabase::class.java,"makeeasy"
        ).fallbackToDestructiveMigration().build()
    }
}