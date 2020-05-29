package id.ac.unhas.twodo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.unhas.twodo.R
import id.ac.unhas.twodo.model.Todo
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter(private val listTodo: ArrayList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

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

    override fun getItemCount(): Int = listTodo.size

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(listTodo[position])
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Todo)
    }
}