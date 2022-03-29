package com.emincankarasoy.makeeasy.ui.view.wallet

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.databinding.DialogTransactionBinding
import kotlinx.coroutines.selects.select

class TransactionFragment : DialogFragment() {
    
    private lateinit var binding: DialogTransactionBinding
    private val spinnerItems = arrayOf("INCOME","OUTCOME")
    private var selectedSpinnerItems = "INCOME"

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_transaction,container,false)

        val spinnerAdapter = ArrayAdapter(requireContext(),R.layout.dialog_transaction,spinnerItems)
        spinnerAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.transactionType.adapter = spinnerAdapter

        binding.transactionType.setOnItemClickListener { _, _, position, _ ->
            selectedSpinnerItems = spinnerItems[position]
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}