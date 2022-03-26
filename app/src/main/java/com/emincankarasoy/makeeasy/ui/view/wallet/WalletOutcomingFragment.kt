package com.emincankarasoy.makeeasy.ui.view.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.data.model.TransactionTypes
import com.emincankarasoy.makeeasy.databinding.FragmentWalletOutcomingBinding
import com.emincankarasoy.makeeasy.ui.adapter.WalletRecyclerAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WalletOutcomingFragment : Fragment() {
    private lateinit var binding: FragmentWalletOutcomingBinding
    private lateinit var recyclerAdapter: WalletRecyclerAdapter

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
        recyclerAdapter = WalletRecyclerAdapter()
        binding.walletOutcomingRecyclerView.adapter = recyclerAdapter
        binding.walletOutcomingRecyclerView.layoutManager = LinearLayoutManager(context)

        recyclerAdapter.setTransactionList(
            arrayListOf(
                Transaction(1, "Restaurant", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),350),
                Transaction(1, "Market", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),250),
                Transaction(1, "Restaurant", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),350),
                Transaction(1, "Market", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),250),
                Transaction(1, "Restaurant", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),350),
                Transaction(1, "Market", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),250),
                Transaction(1, "Restaurant", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),350),
                Transaction(1, "Market", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),250),
                Transaction(1, "Restaurant", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),350),
                Transaction(1, "Market", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),250),
                Transaction(1, "Restaurant", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),350),
                Transaction(1, "Market", TransactionTypes.OUTCOME,"Deneme", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),250)
            )
        )
    }
}