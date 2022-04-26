package com.emincankarasoy.makeeasy.ui.adapter

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.emincankarasoy.makeeasy.ui.viewmodel.TaskViewModel
import com.emincankarasoy.makeeasy.ui.viewmodel.TransactionViewModel

class SwipeControllerActions(private var viewModel: ViewModel) {

    private var recyclerAdapterTask: TaskRecyclerAdapter? = null
    private var recyclerAdapterWallet: WalletRecyclerAdapter? = null

    constructor(recyclerAdapter: TaskRecyclerAdapter,viewModel: TaskViewModel) : this(viewModel) {
        recyclerAdapterTask = recyclerAdapter
    }

    constructor(recyclerAdapter: WalletRecyclerAdapter,viewModel: TransactionViewModel) : this(viewModel) {
        recyclerAdapterWallet = recyclerAdapter
    }

    fun onLeftClicked(position: Int) {

    }
    @SuppressLint("NotifyDataSetChanged")
    fun onRightClicked(position: Int) {
        if (recyclerAdapterTask == null){
                (viewModel as TransactionViewModel).deleteTransaction(recyclerAdapterWallet?.transactionList!![position])
            recyclerAdapterWallet?.notifyDataSetChanged()
        }else{
            (viewModel as TaskViewModel).delete(recyclerAdapterTask?.taskList!![position])
            recyclerAdapterTask?.notifyDataSetChanged()
        }
    }
}