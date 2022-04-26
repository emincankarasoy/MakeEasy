package com.emincankarasoy.makeeasy.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.model.Task
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.databinding.RecyclerItemTaskBinding

class TaskRecyclerAdapter : RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder>() {
    private lateinit var binding: RecyclerItemTaskBinding
    var taskList : ArrayList<Task> = arrayListOf()

    class ViewHolder(val binding: RecyclerItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_item_task,
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.taskVariable = taskList[position]
    }

    override fun getItemCount(): Int = taskList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setTransactionList(taskList: List<Task>){
        this.taskList.clear()
        this.taskList.addAll(taskList)
        notifyDataSetChanged()
    }


}