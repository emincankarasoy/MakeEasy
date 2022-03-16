package com.emincankarasoy.makeeasy.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.databinding.RecyclerItemWalletBinding

class WalletRecyclerAdapter() : RecyclerView.Adapter<WalletRecyclerAdapter.ViewHolder>() {
    private lateinit var binding: RecyclerItemWalletBinding

    private var transactionList : ArrayList<Transaction> = arrayListOf()

    class ViewHolder(val binding: RecyclerItemWalletBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.recycler_item_wallet,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.transaction = transactionList[position]
    }

    override fun getItemCount(): Int = transactionList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setTransactionList(transactionList: ArrayList<Transaction>){
        this.transactionList.clear()
        this.transactionList.addAll(transactionList)
        notifyDataSetChanged()
    }
}