package id.ac.unhas.twodo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.unhas.twodo.model.Todo

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    fun getData(): MutableLiveData<ArrayList<Todo>> {
        return repository.getTodo()
    }
}