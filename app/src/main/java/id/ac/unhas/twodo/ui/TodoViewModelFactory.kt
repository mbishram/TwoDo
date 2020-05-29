package id.ac.unhas.twodo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.unhas.twodo.database.TodoRepository

class TodoViewModelFactory(private val repository: TodoRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TodoRepository::class.java).newInstance(repository)
    }
}