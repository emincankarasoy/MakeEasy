package com.emincankarasoy.makeeasy.ui.view.wallet

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.model.Transaction
import com.emincankarasoy.makeeasy.data.source.TransactionRepository
import com.emincankarasoy.makeeasy.databinding.DialogTransactionBinding
import com.emincankarasoy.makeeasy.ui.viewmodel.TransactionViewModel
import com.emincankarasoy.makeeasy.util.ViewModelFactory

class TransactionFragment : DialogFragment() {
    
    private lateinit var binding: DialogTransactionBinding
    private val spinnerItems = arrayOf("INCOME","OUTCOME")
    private val transactionViewModel: TransactionViewModel by lazy{
        ViewModelFactory(TransactionRepository(requireContext()),this).create(TransactionViewModel::class.java)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_transaction,container,false)

        ArrayAdapter.createFromResource(requireContext(),R.array.transactionTypeArray,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item).also {
            it.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            binding.transactionType.adapter = it
        }
        setClickForView()
        setupItems()

        return binding.root
    }

    private fun setClickForView(){
        binding.transactionDialogCheckButton.setOnClickListener{
            transactionViewModel.add(
                Transaction(
                    binding.transactionCategoryText.text.toString(),
                    binding.transactionDescription.text.toString(),
                    "${binding.transactionDate.text} ${binding.transactionTime.text}",
                    binding.transactionCount.text.toString(),
                    spinnerItems[binding.transactionType.selectedItemPosition]))
            dismiss()
            transactionViewModel.refreshOutcome()
            transactionViewModel.refreshIncome()
        }

        binding.transactionDialogDismissButton.setOnClickListener {
            dismiss()
        }
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    private fun setupItems(){
        binding.transactionDate.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP){
                val calendar : Calendar = Calendar.getInstance()
                val mYear = calendar.get(Calendar.YEAR)
                val mMonth = calendar.get(Calendar.MONTH)
                val mDay = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    requireActivity(),
                    { _, year, monthOfYear, dayOfMonth ->
                        binding.transactionDate.setText("$dayOfMonth-${monthOfYear+1}-$year")
                    }, mYear, mMonth, mDay)
                datePickerDialog.show()

            }
            return@setOnTouchListener true
        }

        binding.transactionTime.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP){
                val calendar : Calendar = Calendar.getInstance()
                val mHour = calendar.get(Calendar.HOUR)
                val mMinute = calendar.get(Calendar.MINUTE)

                val timePickerDialog = TimePickerDialog(requireActivity(),
                    { _, hours , minute ->
                        binding.transactionTime.setText("$hours:$minute")
                    }, mHour, mMinute,true)
                timePickerDialog.show()
            }
            return@setOnTouchListener true
        }

    }

}