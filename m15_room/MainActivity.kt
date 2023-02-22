package com.example.m15_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.m15_room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainViewModel by viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val wordDao = (application as App).db.wordDao()
                return MainViewModel(wordDao) as T
                }
            }
        }

        binding.editText.doOnTextChanged { text, _, _, _ ->
            if (!checkChar(text)){
                binding.textInput.isErrorEnabled = false
                binding.buttonAdd.isEnabled = true
            } else {
                binding.textInput.error = "Недопустимое сочетание символов"
                binding.textInput.isErrorEnabled = true
                binding.buttonAdd.isEnabled = false
            }
        }

        binding.buttonAdd.setOnClickListener {
            val newWord = binding.editText.text
            viewModel.onAddBtn(newWord.toString())
        }

        binding.buttonDelete.setOnClickListener {
            viewModel.onDeleteBtn()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.allWord
                .collect{
                    binding.texView.text = it.joinToString (
                        separator = ", "
                            )
                }
        }
    }

    private fun checkChar(char: CharSequence?): Boolean {
        return Regex("""[0123456789~!@#'$^&*+.,_\s]""")
            .containsMatchIn(char.toString())
    }
}