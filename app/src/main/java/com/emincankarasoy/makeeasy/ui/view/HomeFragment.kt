package com.emincankarasoy.makeeasy.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.source.TransactionRepository
import com.emincankarasoy.makeeasy.databinding.FragmentHomeBinding
import com.emincankarasoy.makeeasy.ui.adapter.WalletRecyclerAdapter
import com.emincankarasoy.makeeasy.ui.viewmodel.TransactionViewModel
import com.emincankarasoy.makeeasy.util.ViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerAdapter: WalletRecyclerAdapter

    private val transactionViewModel: TransactionViewModel by lazy{
        ViewModelFactory(TransactionRepository(requireContext()),this).create(TransactionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionViewModel.refreshLastTen()
        recyclerAdapter = WalletRecyclerAdapter()
        binding.last10RecyclerView.adapter = recyclerAdapter
        binding.last10RecyclerView.layoutManager = object : LinearLayoutManager(context){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        transactionViewModel.lastTenTransaction.observe(viewLifecycleOwner){
            recyclerAdapter.setTransactionList(it)
        }
    }


}