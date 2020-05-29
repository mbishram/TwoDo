package id.ac.unhas.twodo

import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import id.ac.unhas.twodo.model.Todo
import id.ac.unhas.twodo.ui.TodoAdapter
import id.ac.unhas.twodo.ui.TodoListFragment
import id.ac.unhas.twodo.ui.dialog.EditDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    companion object {
        var isSortByDateCreatedAsc = true
        var isSortByDateCreatedDesc = false
        var isSortByDueTimeAsc = false
        var isSortByDueTimeDesc = false

        fun sortDate(listData: List<Todo>): List<Todo> {
            return if (isSortByDateCreatedAsc) {
                listData.sortedBy { it.id }
            } else {
                listData.sortedByDescending { it.id }
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun sortDue(listData: List<Todo>): List<Todo> {
            return if (isSortByDueTimeAsc) {
                listData.sortedBy {
                    LocalDateTime.parse(
                        "${it.dueDate}/${it.dueTime}",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy/HH:mm")
                    )
                }
            } else {
                listData.sortedByDescending {
                    LocalDateTime.parse(
                        "${it.dueDate}/${it.dueTime}",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy/HH:mm")
                    )
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragmentManager = supportFragmentManager
        val newFragment = EditDialogFragment(null, this.findViewById(R.id.main_activity))
        fab.setOnClickListener {
            newFragment.show(fragmentManager, "showForm")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu.findItem(R.id.action_search)).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Search todo"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                TodoListFragment.adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TodoListFragment.adapter.filter.filter(newText)
                return false
            }
        })

        searchView.setOnCloseListener {
            TodoAdapter.isFiltered = false
            true
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete_all -> showAlert(this.findViewById(R.id.main_activity))
            R.id.action_sort_date -> {
                isSortByDateCreatedAsc = true
                isSortByDateCreatedDesc = false
                isSortByDueTimeAsc = false
                isSortByDueTimeDesc = false
            }
            R.id.action_sort_date_desc -> {
                isSortByDateCreatedAsc = false
                isSortByDateCreatedDesc = true
                isSortByDueTimeAsc = false
                isSortByDueTimeDesc = false
            }
            R.id.action_sort_due -> {
                isSortByDateCreatedAsc = false
                isSortByDateCreatedDesc = false
                isSortByDueTimeAsc = true
                isSortByDueTimeDesc = false
            }
            R.id.action_sort_due_desc -> {
                isSortByDateCreatedAsc = false
                isSortByDateCreatedDesc = false
                isSortByDueTimeAsc = false
                isSortByDueTimeDesc = true
            }
            else -> Unit
        }

        updateObserver()
        return super.onOptionsItemSelected(item)
    }

    private fun showAlert(view: View) {
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.delete_alert_title_all))
            .setMessage(resources.getString(R.string.delete_alert_msg_all))
            .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                Snackbar.make(view, "All Todo are Deleted!", Snackbar.LENGTH_LONG)
                    .show()
                TodoListFragment.viewModel.deleteAllTodos()
            }
            .show()
    }

    private fun updateObserver() {
        // I'm sorry
        val newTodo = Todo()
        newTodo.id = -1
        newTodo.name = ""
        newTodo.desc = ""
        newTodo.dueDate = ""
        newTodo.dueTime = ""
        newTodo.created = ""
        newTodo.edited = ""
        newTodo.remind = false

        TodoListFragment.viewModel.insertTodo(newTodo)
        TodoListFragment.viewModel.deleteTodo(newTodo)
    }
}
