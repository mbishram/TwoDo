package id.ac.unhas.twodo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.ac.unhas.twodo.R
import id.ac.unhas.twodo.model.Todo
import kotlinx.android.synthetic.main.todo_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class TodoAdapter(private val listTodo: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoHolder>(), Filterable {
    companion object {
        var isFiltered = false
    }

    private lateinit var onItemClickCallback: OnItemClickCallback
    private var todoFilteredList = listOf<Todo>()

    inner class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemTodo: Todo) {
            with(itemView) {
                txt_todo_name.text = itemTodo.name
                txt_todo_desc.text = itemTodo.desc
                txt_todo_due.text = String.format(
                    resources.getString(R.string.due),
                    itemTodo.dueDate,
                    itemTodo.dueTime
                )
                txt_todo_create.text =
                    String.format(resources.getString(R.string.create_date), itemTodo.created)
                txt_todo_edit.text = if (itemTodo.edited != null)
                    String.format(resources.getString(R.string.edited_date), itemTodo.edited)
                else String.format(resources.getString(R.string.edited_date), "Not yet edited")

                card_todo.setOnClickListener {
                    onItemClickCallback.onItemClicked(itemTodo)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoHolder(view)
    }

    override fun getItemCount(): Int {
        return if (isFiltered) {
            todoFilteredList.size
        } else {
            listTodo.size
        }
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        if (isFiltered) {
            holder.bind(todoFilteredList[position])
        } else {
            holder.bind(listTodo[position])
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Todo)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keywords = constraint.toString()
                if (keywords.isEmpty())
                    isFiltered = false
                else {
                    isFiltered = true
                    val filteredList = ArrayList<Todo>()
                    for (todo in listTodo) {
                        if (todo.toString().toLowerCase(Locale.ROOT)
                                .contains(keywords.toLowerCase(Locale.ROOT))
                        )
                            filteredList.add(todo)
                    }
                    todoFilteredList = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = todoFilteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                todoFilteredList = results?.values as List<Todo>
                notifyDataSetChanged()
            }
        }
    }
}