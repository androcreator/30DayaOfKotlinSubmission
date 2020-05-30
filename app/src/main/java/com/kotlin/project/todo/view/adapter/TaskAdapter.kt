package com.kotlin.project.todo.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.project.R
import com.kotlin.project.todo.data.database.STATUS
import com.kotlin.project.todo.data.database.entity.ToDoItems
import com.kotlin.project.todo.extension.getDateTimeFromTimeStamp
import com.kotlin.project.todo.interfaces.ITaskItemClickCallback


class TaskAdapter(
    private val listOfTask:List<ToDoItems>,
    var iTaskItemClickCallback: ITaskItemClickCallback
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

        when(listOfTask.get(position).status){
            STATUS.TODAY ->{
                holder.footer.setTextColor(Color.parseColor("#008000"))
            }
            STATUS.ELAPSED ->{
                holder.footer.setTextColor(Color.parseColor("#800000"))
            }
            STATUS.UPCOMING ->{
                holder.footer.setTextColor(Color.parseColor("#FFA500"))
            }
        }
        holder.footer.text = listOfTask.get(position).status.toString()
        holder.date.text = "Date:\n"+listOfTask.get(position).dateTime.getDateTimeFromTimeStamp()
        holder.descTV.text = "Description :\n"+listOfTask.get(position).desc

        holder.itemView.setOnClickListener {
            iTaskItemClickCallback.onTaskItemClick(listOfTask.get(position))
        }

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