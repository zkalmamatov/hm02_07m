package kg.less.hm02_07m.presentation.fragments

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import kg.less.hm02_07m.R
import kg.less.hm02_07m.databinding.FragmentTaskListBinding
import kg.less.hm02_07m.presentation.fragments.adapter.TaskAdapter
import kg.less.hm02_07m.presentation.fragments.viewmodel.TaskViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TaskListFragment : Fragment() {

    private val binding by lazy {
        FragmentTaskListBinding.inflate(layoutInflater)
    }

    private val viewModel: TaskViewModel by viewModel()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTask()
        initialize()
        showTask()
        viewModel.loadTasks()
    }

    private fun addTask() {
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
        }
    }

    private fun initialize() {
        taskAdapter = TaskAdapter(emptyList(), { task ->
            viewModel.viewModelScope.launch {
                viewModel.getTask(id)
            }
            val action = TaskListFragmentDirections.actionTaskListFragmentToDetailFragment(task)
            findNavController().navigate(action)
        }, {task ->
            viewModel.deleteTask(task)
        })
        binding.rvTask.adapter = taskAdapter
        taskAdapter.attachSwipeToRecyclerView(binding.rvTask)
    }

    private fun showTask() {
        viewModel.viewModelScope.launch {
            viewModel.taskFlow.collectLatest {
                taskAdapter.updateTasks(it)
            }
        }
    }
}
