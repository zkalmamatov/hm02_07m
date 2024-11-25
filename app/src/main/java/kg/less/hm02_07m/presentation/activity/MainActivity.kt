package kg.less.hm02_07m.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kg.less.hm02_07m.databinding.ActivityMainBinding
import kg.less.hm02_07m.presentation.uimodel.TaskUI
import kg.less.hm02_07m.presentation.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setupListener()
    }

    private fun setupListener() {
        binding.btnShow.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.btnSave.setOnClickListener{
            val task = binding.tvTask.text.toString()
            val dates = binding.tvDate.text.toString()

            if (task.isBlank() || dates.isBlank()) {
                Toast.makeText(this, "поля пустые", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.insertTask(taskUI = TaskUI(0, task, dates))
        }
    }
}