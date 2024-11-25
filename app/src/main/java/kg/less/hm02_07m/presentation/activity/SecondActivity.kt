package kg.less.hm02_07m.presentation.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kg.less.hm02_07m.databinding.ActivitySecondBinding
import kg.less.hm02_07m.presentation.TaskAdapter
import kg.less.hm02_07m.presentation.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var myAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        myAdapter = TaskAdapter(emptyList())
        binding.rvTask.adapter = myAdapter

        viewModel.tasks.observe(this, Observer { task ->
            myAdapter.updateTasks(task)
        })

        viewModel.loadTasks()
    }
}