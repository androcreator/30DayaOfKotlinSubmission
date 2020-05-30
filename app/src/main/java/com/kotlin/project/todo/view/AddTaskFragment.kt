package com.kotlin.project.todo.view

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.kotlin.project.R
import com.kotlin.project.todo.data.database.STATUS
import com.kotlin.project.todo.data.database.entity.ToDoItems
import com.kotlin.project.todo.extension.getDateTimeFromTimeStamp
import com.kotlin.project.todo.extension.isToday
import com.kotlin.project.todo.extension.isYesterday
import com.kotlin.project.todo.utils.createNotification
import com.kotlin.project.todo.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddTaskFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModel()

    private var dateTime: Long = 0
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().toolbar?.title = "Add Task"
        editTaskInfo(view)
        view.findViewById<Button>(R.id.saveBT).setOnClickListener {
            if(valudateInput(view)){
                updateAndNavigate(view)
            }
            else{
                Toast.makeText(requireContext(), "Enter all the fields",Toast.LENGTH_LONG).show()
            }
        }
        view.dateET.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                openCalender(view)
            }
        }
        view.deleteBT.setOnClickListener {
            showDeleteAlert()
        }
    }

    fun openCalender(view: View) {
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

    fun updateAndNavigate(view: View) {
        val taskName = view.taskName.text.toString()
        val tag = view.tagET.text.toString()
        val description = view.description.text.toString()
        val remainder = view.reminderSW.isSaveEnabled
        when (view.saveBT.text) {
            "Save" -> {
                val task = ToDoItems(0, taskName, tag, description, remainder, dateTime, setStatus())
                taskViewModel.addTask(task)
                createNotification(requireContext())
            }
            "Update" -> {
                getSelectedDateTime()
                val task = ToDoItems(id = getSelectedItemId(), taskName = taskName, tag = tag, desc = description, isRemainder = remainder, dateTime = dateTime, status = setStatus())
                taskViewModel.update(task)
                createNotification(requireContext())
            }
        }
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    fun setStatus(): STATUS {
        if (dateTime.isToday()) {
            return STATUS.TODAY
        } else if (dateTime.isYesterday()) {
            return STATUS.ELAPSED
        } else {
            return STATUS.UPCOMING
        }
    }

    fun editTaskInfo(view: View) {
        arguments?.let {
            view.deleteBT.visibility = View.VISIBLE
            saveBT.text = "Update"
            AddTaskFragmentArgs.fromBundle(it).let {
                view.taskName.setText(it.editTaskArguments?.taskName)
                view.tagET.setText(it.editTaskArguments?.tag)
                view.dateET.setText(it.editTaskArguments?.dateTime?.getDateTimeFromTimeStamp())
                view.description.setText(it.editTaskArguments?.desc)
                view.reminderSW.isChecked = it.editTaskArguments!!.isRemainder
            }
        }
    }

    fun valudateInput(view: View):Boolean{
        if(view.taskName.text.toString() !="" &&
                view.tagET.text.toString() !="" &&
                view.dateET.text.toString() !="" &&
                view.description.text.toString() !=""){
            return true
        }
        return false
    }


    fun getSelectedItemId(): Int {
        return arguments?.let {
            AddTaskFragmentArgs.fromBundle(it).editTaskArguments.let {
                it?.id
            }
        }!!
    }

    fun getSelectedDateTime() {
        // Usage of Null Safety.
         arguments?.let {
            AddTaskFragmentArgs.fromBundle(it).editTaskArguments.let {
                this.dateTime = it?.dateTime!!
            }
        }!!
    }

    fun showDeleteAlert(){
        val builder1: AlertDialog.Builder =AlertDialog.Builder(requireContext())
        builder1.setMessage("Are you sure you want to delete?")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Yes",
            DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                // Usage of Higher Order function.
                taskViewModel.delete(::getSelectedItemId)
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            })

        builder1.setNegativeButton(
            "No",
            DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }
}
