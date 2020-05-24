package com.kotlin.project.todo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.project.R
import com.kotlin.project.todo.data.database.entity.ToDoItems
import com.kotlin.project.todo.extension.getDateTimeFromTimeStamp


class TaskAdapter(
    private val listOfTask:List<ToDoItems>
): RecyclerView.Adapter<TaskAdapter.TaskItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.adapter_task,parent,false)
        return TaskItemHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfTask.size
    }

    override fun onBindViewHolder(holder: TaskItemHolder, position: Int) {
        holder.header.text = listOfTask.get(position).taskName
        holder.footer.text = "Status :"+listOfTask.get(position).status.toString()
        holder.date.text = "Date:\n"+listOfTask.get(position).dateTime.getDateTimeFromTimeStamp()
        holder.descTV.text = "Description :\n"+listOfTask.get(position).desc

    }


    class TaskItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var header: TextView
        var footer: TextView
        var date: TextView
        var descTV: TextView

        init {
            header = itemView.findViewById(R.id.header)
            footer = itemView.findViewById(R.id.footer)
            date = itemView.findViewById(R.id.dateTV)
            descTV = itemView.findViewById(R.id.descTV)
        }
    }

}