package com.emincankarasoy.makeeasy.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.data.source.TaskRepository
import com.emincankarasoy.makeeasy.databinding.FragmentTaskBinding
import com.emincankarasoy.makeeasy.ui.adapter.SwipeController
import com.emincankarasoy.makeeasy.ui.adapter.SwipeControllerActions
import com.emincankarasoy.makeeasy.ui.adapter.SwipeControllerDrawer
import com.emincankarasoy.makeeasy.ui.adapter.TaskRecyclerAdapter
import com.emincankarasoy.makeeasy.ui.viewmodel.TaskViewModel
import com.emincankarasoy.makeeasy.util.ViewModelFactory

class TaskFragment : Fragment() {
    private lateinit var binding : FragmentTaskBinding
    private lateinit var recyclerAdapter : TaskRecyclerAdapter
    private lateinit var swipeController: SwipeController

    private val taskViewModel : TaskViewModel by lazy {
        ViewModelFactory(TaskRepository(requireContext()),this).create(TaskViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = TaskRecyclerAdapter()
        binding.taskRecyclerView.adapter = recyclerAdapter
        binding.taskRecyclerView.layoutManager = LinearLayoutManager(context)
        taskViewModel.tasksLiveData.observe(viewLifecycleOwner){
            recyclerAdapter.setTransactionList(it)
        }

        swipeController = SwipeController(SwipeControllerActions(recyclerAdapter,taskViewModel),requireContext(),requireActivity().resources)

        val itemTouchHelper = ItemTouchHelper(swipeController)
        itemTouchHelper.attachToRecyclerView(binding.taskRecyclerView)

        SwipeControllerDrawer(binding.taskRecyclerView,swipeController).addItemDecoration()
    }
}