package id.ac.unhas.twodo.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import id.ac.unhas.twodo.dummy.DummyData
import id.ac.unhas.twodo.model.Todo

class TodoRepository {
    private var list: ArrayList<Todo> = arrayListOf()
    private val mutableLiveData: MutableLiveData<ArrayList<Todo>> =
        MutableLiveData<ArrayList<Todo>>()

    fun getTodo(): MutableLiveData<ArrayList<Todo>> {
        if (list.isEmpty()) {
            list.addAll(DummyData.listData)
        }
        mutableLiveData.value = list
        return mutableLiveData
    }

    fun deleteTodo(todo: Todo, view: View) {
        val testEllipsize = if (todo.name.length > 24) "..." else ""

        Snackbar.make(view, "${todo.name.take(24)}${testEllipsize} Deleted!", Snackbar.LENGTH_LONG)
            .show()
        list.remove(todo)
        mutableLiveData.value = list
    }

    fun deleteAllTodo(view: View) {
        Snackbar.make(view, "All Todos Deleted!", Snackbar.LENGTH_LONG).show()
        list.clear()
        mutableLiveData.value = list
    }

    fun addTodo(todo: Todo, view: View) {
        val testEllipsize = if (todo.name.length > 24) "..." else ""

        Snackbar.make(view, "${todo.name.take(24)}${testEllipsize} Added!", Snackbar.LENGTH_LONG)
            .show()
        todo.id = list.last().id + 1
        todo.edited = null
        list.add(todo)
        mutableLiveData.value = list
    }

    fun updateTodo(todo: Todo, id: Int, view: View) {
        val testEllipsize = if (todo.name.length > 24) "..." else ""

        Snackbar.make(view, "${todo.name.take(24)}${testEllipsize} Updated!", Snackbar.LENGTH_LONG)
            .show()

        with(list[id - 1]) {
            name = todo.name
            desc = todo.desc
            dueDate = todo.dueDate
            dueTime = todo.dueTime
            edited = todo.edited
            remind = todo.remind
        }
        mutableLiveData.value = list
    }
}