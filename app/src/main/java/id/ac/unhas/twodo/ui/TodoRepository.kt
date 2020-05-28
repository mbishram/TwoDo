package id.ac.unhas.twodo.ui

import androidx.lifecycle.MutableLiveData
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
}