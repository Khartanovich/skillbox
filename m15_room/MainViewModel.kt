package com.example.m15_room

import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {


    val allWord: StateFlow<List<Dictionary>> = this.wordDao.getFirstFiveWord()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onAddBtn(newWord: String) {
        viewModelScope.launch {
//            val onlyWord = mutableListOf<String>()
//            allWord.value.let {
//                it.forEach {
//                    onlyWord.add(it.word)
//                }
//            }
//            var count = 0
//            for (dictionary in allWord.value) {
//                if (dictionary.word == newWord) {
//                    count = dictionary.numberRepeat + 1
//                }
//            }
//            if (onlyWord.contains(newWord)) {
//                update(newWord)
//            } else {
//                wordDao.insertWord(NewWordDictionary(word = newWord, numberRepeat = 1))
//            }
            wordDao.insertWord(Dictionary(word = newWord, numberRepeat = 1))
            wordDao.updateDictionary(newWord)
        }
    }

//    fun update(newWordUpdate: String) {
//        viewModelScope.launch {
//            wordDao.updateDictionary(newWordUpdate)
//        }
//    }

    fun onDeleteBtn() {
        viewModelScope.launch {
            wordDao.deleteAll()
        }
    }

}


