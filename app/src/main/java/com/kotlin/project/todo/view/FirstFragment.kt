package com.kotlin.project.todo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.project.R
import com.kotlin.project.todo.data.database.entity.ToDoItems
import com.kotlin.project.todo.view.adapter.TaskAdapter
import com.kotlin.project.todo.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_first.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModel()

    private lateinit var tasklistObserver: Observer<List<ToDoItems>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskListObserver(view)

        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    fun taskListObserver(view: View){
        tasklistObserver = Observer<List<ToDoItems>>{ taskList ->
            taskList.size.let {
                if (it > 0){
                    view.welcometext.visibility = View.GONE
                    Log.d("Data::", taskList.get(0).taskName)
                    val adapter = TaskAdapter(taskList)
                    view.taskListRV.layoutManager = LinearLayoutManager(requireContext())
                    view.taskListRV.adapter = adapter
                }
                else{
                    view.welcometext.text = getString(R.string.welcome)
                }
            }
        }
        taskViewModel.getTaskList()?.observe(viewLifecycleOwner,tasklistObserver)

    }

}
