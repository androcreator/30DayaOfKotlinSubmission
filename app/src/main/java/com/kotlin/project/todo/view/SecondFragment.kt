package com.kotlin.project.todo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.kotlin.project.R
import com.kotlin.project.todo.data.database.STATUS
import com.kotlin.project.todo.data.database.entity.ToDoItems
import com.kotlin.project.todo.extension.getDateTimeFromTimeStamp
import com.kotlin.project.todo.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_second.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModel()

    private var dateTime:Long = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.saveBT).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

            val taskName = view.taskName.text.toString()
            val tag = view.tagET.text.toString()
            val description = view.description.text.toString()
            val remainder = view.reminderSW.isSaveEnabled

            var task = ToDoItems(0,taskName,tag,description,remainder,dateTime, STATUS.NONE)
           taskViewModel.addTask(task)
        }



        view.dateET.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                val builder = MaterialDatePicker.Builder.datePicker()
                val picker = builder.build()
                picker.show(requireFragmentManager(), "date_picker_tag")
                picker.addOnPositiveButtonClickListener {
                Log.d("Data::", "${it.getDateTimeFromTimeStamp()}")
                    val data = it.getDateTimeFromTimeStamp().toString()
                    view.dateET.setText(data)
                    dateTime = it
            }
            }
        }
    }


}
