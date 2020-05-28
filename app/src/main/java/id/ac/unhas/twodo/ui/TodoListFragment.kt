package id.ac.unhas.twodo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.unhas.twodo.R
import kotlinx.android.synthetic.main.fragment_todo_list.*

class TodoListFragment : Fragment() {
    private lateinit var viewModel: TodoViewModel
    private lateinit var viewModelFactory: TodoViewModelFactory
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = TodoViewModelFactory(TodoRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(TodoViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            rv_todo.adapter = TodoAdapter(ArrayList(it))
        })

        linearLayoutManager = LinearLayoutManager(context)
        rv_todo.layoutManager = linearLayoutManager
    }

}
