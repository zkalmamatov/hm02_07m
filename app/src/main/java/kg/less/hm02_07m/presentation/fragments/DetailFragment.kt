package kg.less.hm02_07m.presentation.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import kg.less.hm02_07m.databinding.FragmentDetailBinding
import kg.less.hm02_07m.presentation.fragments.viewmodel.TaskViewModel
import kg.less.hm02_07m.presentation.model.TaskUI
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: TaskViewModel by viewModel()

    private var taskId: Int = -1
    private var taskUI: TaskUI? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
        arguments?.let {
            taskId = it.getInt("taskId")
        }
        viewModel.viewModelScope.launch {
            viewModel.getTask(id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
        setUpListeners()
    }

    private fun updateUI() {
        binding.tvTask2.setText(taskUI?.taskName)
        binding.tvDate2.setText(taskUI?.taskDate)
        taskUI?.taskPhoto?.let {
            binding.addPhoto.setImageURI(Uri.parse(it))
        }
    }

    private fun setUpListeners() {
        binding.btnSaveChange.setOnClickListener {
            val updatedTask = taskUI?.copy(
                taskName = binding.tvTask2.text.toString(),
                taskDate = binding.tvDate2.text.toString()
            )
            updatedTask?.let {
                viewModel.updateTask(it)
                findNavController().navigateUp()
            }
        }
    }
}