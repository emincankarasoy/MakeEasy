package com.emincankarasoy.makeeasy.ui.view.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.source.TransactionRepository
import com.emincankarasoy.makeeasy.databinding.FragmentWalletIncomingBinding
import com.emincankarasoy.makeeasy.ui.adapter.WalletRecyclerAdapter
import com.emincankarasoy.makeeasy.ui.viewmodel.TransactionViewModel
import com.emincankarasoy.makeeasy.util.extensions.getViewModelFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WalletIncomeFragment : Fragment() {
    private lateinit var binding:FragmentWalletIncomingBinding
    private lateinit var recyclerAdapter: WalletRecyclerAdapter

    private lateinit var transactionViewModel:TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet_incoming,container,false)
        initViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = WalletRecyclerAdapter()
        binding.walletIncomingRecyclerView.adapter = recyclerAdapter
        binding.walletIncomingRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun initViewModel(){
        val viewModelFactory = getViewModelFactory(TransactionRepository())
        transactionViewModel = viewModelFactory.create(TransactionViewModel::class.java)
    }
}