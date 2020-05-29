package id.ac.unhas.twodo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import id.ac.unhas.twodo.ui.TodoListFragment
import id.ac.unhas.twodo.ui.dialog.EditDialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_search -> true
            R.id.action_delete_all -> {
                showAlert(this.findViewById(R.id.main_activity))
                true
            }
            R.id.action_sort_date -> true
            R.id.action_sort_due -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAlert(view: View) {
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.delete_alert_title_all))
            .setMessage(resources.getString(R.string.delete_alert_msg_all))
            .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                TodoListFragment.viewModel.deleteAllData(view)
            }
            .show()
    }
}
