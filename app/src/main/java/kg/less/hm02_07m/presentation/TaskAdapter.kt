package kg.less.hm02_07m.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.less.hm02_07m.data.database.dto.TaskDto
import kg.less.hm02_07m.databinding.ItemTaskBinding

class TaskAdapter(private var taskList: List<TaskDto>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(taskUI: TaskDto) {
            binding.tvTask1.text = taskUI.taskName
            binding.tvDate.text = taskUI.taskDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    fun updateTasks(newTasks: List<TaskDto>) {
        taskList = newTasks
        notifyDataSetChanged()
    }
}