package id.ac.unhas.twodo.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.unhas.twodo.model.Todo

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    fun getData(): MutableLiveData<ArrayList<Todo>> {
        return repository.getTodo()
    }

    fun deleteData(item: Todo, view: View) {
        repository.deleteTodo(item, view)
    }

    fun deleteAllData(view: View) {
        repository.deleteAllTodo(view)
    }

    fun addData(item: Todo, view: View) {
        repository.addTodo(item, view)
    }

    fun updateTodo(item: Todo, id: Int, view: View) {
        repository.updateTodo(item, id, view)
    }
}