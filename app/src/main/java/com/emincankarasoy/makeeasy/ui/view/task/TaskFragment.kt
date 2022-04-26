package com.emincankarasoy.makeeasy.ui.view.task

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.model.Task
import com.emincankarasoy.makeeasy.data.source.TaskRepository
import com.emincankarasoy.makeeasy.databinding.DialogTaskBinding
import com.emincankarasoy.makeeasy.ui.viewmodel.TaskViewModel
import com.emincankarasoy.makeeasy.util.ViewModelFactory

class TaskFragment : DialogFragment() {

    private lateinit var binding:DialogTaskBinding

    private val taskViewModel: TaskViewModel by lazy{
        ViewModelFactory(TaskRepository(requireContext()),this).create(TaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_task,container,false)
        setupItems()
        setClickForView()
        return binding.root
    }

    private fun setClickForView(){
        binding.taskDialogCheckButton.setOnClickListener {
            createTask()
            dismiss()
        }
        binding.taskDialogDismissButton.setOnClickListener {
            dismiss()
        }
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    private fun setupItems(){
        binding.taskDate.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP){
                val calendar : Calendar = Calendar.getInstance()
                val mYear = calendar.get(Calendar.YEAR)
                val mMonth = calendar.get(Calendar.MONTH)
                val mDay = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    requireActivity(),
                    { _, year, monthOfYear, dayOfMonth ->
                        binding.taskDate.setText("$dayOfMonth-${monthOfYear+1}-$year")
                    }, mYear, mMonth, mDay)
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()

            }
            return@setOnTouchListener true
        }

        binding.taskTime.setOnTouchListener {view,motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP){
                val calendar : Calendar = Calendar.getInstance()
                val mHour = calendar.get(Calendar.HOUR)
                val mMinute = calendar.get(Calendar.MINUTE)

                val timePickerDialog = TimePickerDialog(requireActivity(),
                    { _, hours , minute ->
                        binding.taskTime.setText("$hours:$minute")
                    }, mHour, mMinute,true)
                timePickerDialog.show()
            }
            return@setOnTouchListener true
        }

    }

    private fun createTask(){
        val task : Task = Task(
            binding.taskTitle.text.toString(),
            binding.taskDescription.text.toString(),
            binding.taskDate.text.toString(),
            false,
            binding.taskTime.text.toString(),
            binding.taskEvent.text.toString(),
        )
        taskViewModel.add(task)
    }
}