package com.emincankarasoy.makeeasy.data.source

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.emincankarasoy.makeeasy.data.model.Task
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class CloudRepository(private val context: Context) {
    private val taskRepository = TaskRepository(context)
    private val transactionRepository = TransactionRepository(context)
    private val mCloud = FirebaseFirestore.getInstance()
    private val sharedPreferences = context.getSharedPreferences("make_easy",0)

    @SuppressLint("CommitPrefEdits")
    fun syncDataOnCloud(){
        CoroutineScope(Dispatchers.IO).launch {
            var count = sharedPreferences.getInt("count",0)
            while (count > 0){
                mCloud.collection(sharedPreferences.getString("uuid","0").toString()).document("Mk02410924eY965Q"+(count-1)+"CdA745E".toString()).delete()
                count--
            }
            taskRepository.getAll().forEach {
                val task = HashMap<String, Any>()
                task["title"] = it.title
                task["description"] = it.description
                task["date"] = it.date
                task["isComplete"] = it.isComplete
                task["alarm"] = it.alarm
                task["event"] = it.event
                mCloud.collection(sharedPreferences.getString("uuid","0").toString()).document("Mk02410924eY965Q" + (++count) +"CdA745E".toString()).set(task).addOnSuccessListener {
                    Log.i("Firebase" , "Task Successful")
                }
            }
            transactionRepository.getAll().forEach{
                val transaction = HashMap<String,Any>()
                transaction["category"] = it.category
                transaction["description"] = it.description
                transaction["date"] = it.date
                transaction["count"] = it.count
                transaction["type"] = it.type
                mCloud.collection(sharedPreferences.getString("uuid","0").toString()).document("Mk02410924eY965Q"+(++count)+"CdA745E".toString()).set(transaction).addOnSuccessListener {
                    Log.i("Firebase" , "Transaction Successful")
                }
            }
            sharedPreferences.edit().putInt("count",count)
        }
    }

    fun syncDataOnLocal(){
        CoroutineScope(Dispatchers.IO).launch{
            val taskList = ArrayList<Task>()
            val transactionList = ArrayList<Transaction>()
            mCloud.collection(sharedPreferences.getString("uuid","0").toString()).addSnapshotListener{ value ,error ->
                if (error != null){
                    Log.i("Firebase" ,"Get From Cloud Error!")
                }else {
                    if (value != null){
                        for (document in value.documents){
                            if (document.getString("title") != null){
                                Log.i("Firebase","Task Created!")
                                taskList.add(Task(
                                    document.getString("title") as String,
                                    document.getString("description") as String,
                                    document.getString("date") as String,
                                    document.getBoolean("isComplete") as Boolean,
                                    document.getString("alarm") as String,
                                    document.getString("event") as String
                                ))
                            }else{
                                Log.i("Firebase" , "Transaction Created!")
                                transactionList.add(
                                    Transaction(
                                        document.getString("category") as String,
                                        document.getString("description") as String,
                                        document.getString("date") as String,
                                        document.getString("count") as String,
                                        document.getString("type") as String
                                ))
                            }
                        }
                        CoroutineScope(Dispatchers.IO).launch {
                            taskRepository.deleteAll()
                            transactionRepository.deleteAll()
                            taskRepository.addAll(taskList as List<Task>)
                            transactionRepository.addAll(transactionList as List<Transaction>)
                        }
                    }
                }
            }
        }

    }
}