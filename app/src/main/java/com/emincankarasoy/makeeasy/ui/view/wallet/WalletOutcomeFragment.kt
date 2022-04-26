package com.emincankarasoy.makeeasy.ui.view.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.source.TransactionRepository
import com.emincankarasoy.makeeasy.databinding.FragmentWalletOutcomingBinding
import com.emincankarasoy.makeeasy.ui.adapter.SwipeController
import com.emincankarasoy.makeeasy.ui.adapter.SwipeControllerActions
import com.emincankarasoy.makeeasy.ui.adapter.SwipeControllerDrawer
import com.emincankarasoy.makeeasy.ui.adapter.WalletRecyclerAdapter
import com.emincankarasoy.makeeasy.ui.viewmodel.TransactionViewModel
import com.emincankarasoy.makeeasy.util.ViewModelFactory

class WalletOutcomeFragment : Fragment() {
    private lateinit var binding: FragmentWalletOutcomingBinding
    private lateinit var recyclerAdapter: WalletRecyclerAdapter
    private lateinit var swipeController:SwipeController

    private val transactionViewModel: TransactionViewModel by lazy{
        ViewModelFactory(TransactionRepository(requireContext()),this).create(TransactionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet_outcoming,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionViewModel.refreshOutcome()
        recyclerAdapter = WalletRecyclerAdapter()
        binding.walletOutcomingRecyclerView.adapter = recyclerAdapter
        binding.walletOutcomingRecyclerView.layoutManager = LinearLayoutManager(context)

        transactionViewModel.outcomeTransaction.observe(viewLifecycleOwner){
            recyclerAdapter.setTransactionList(it)
        }

        swipeController = SwipeController(SwipeControllerActions(recyclerAdapter,transactionViewModel),requireContext(),requireActivity().resources)

        val itemTouchHelper = ItemTouchHelper(swipeController)
        itemTouchHelper.attachToRecyclerView(binding.walletOutcomingRecyclerView)

        SwipeControllerDrawer(binding.walletOutcomingRecyclerView,swipeController).addItemDecoration()
    }
}