package kg.less.hm02_07m.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import kg.less.hm02_07m.R
import kg.less.hm02_07m.databinding.FragmentAddTaskBinding
import kg.less.hm02_07m.presentation.fragments.taskList.TaskViewModel
import kg.less.hm02_07m.presentation.model.TaskUI
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddTaskFragment : Fragment() {

    private val binding by lazy {
        FragmentAddTaskBinding.inflate(layoutInflater)
    }

    private val viewModel: TaskViewModel by viewModel()
    private lateinit var imageLauncher: ActivityResultLauncher<String>
    private var imageString: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        imageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                binding.changePhoto.setImageURI(uri)
                imageString = uri.toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.changePhoto.setOnClickListener {
            imageLauncher.launch("image/*")
        }

        binding.btnAddTask.setOnClickListener {
            val task = binding.tvTask.text.toString()
            val date = binding.tvDate.text.toString()
            val taskUI = TaskUI(0, task, date, imageString.toString())
            viewModel.insertTask(taskUI)

            viewModel.viewModelScope.launch {
                viewModel.insertMessageFlow.collectLatest {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
            findNavController().navigate(R.id.action_addTaskFragment_to_taskListFragment)
        }
    }
}