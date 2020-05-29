package id.ac.unhas.twodo.ui.dialog

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import id.ac.unhas.twodo.R
import id.ac.unhas.twodo.model.Todo
import id.ac.unhas.twodo.ui.TodoListFragment
import kotlinx.android.synthetic.main.fragment_edit_dialog.*
import java.text.SimpleDateFormat
import java.util.*

// This thing is super not optimize, "if" everywhere! i'm sorry. Got no time!

class EditDialogFragment(private val data: Todo?, private val prevView: View?) : DialogFragment() {
    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()

        val date =
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                input_due_date.text = toEditable(getDate())

            }

        val time =
            OnTimeSetListener { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                input_due_time.text = toEditable(getTime())
            }

        input_due_date.setOnClickListener {
            clearFocus()
            val nowDate =
                data?.dueDate?.split("/")?.map { it.toInt() }?.reversed()
                    ?: arrayListOf(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )

            DatePickerDialog(
                requireContext(), date, nowDate[0], nowDate[1] - 1, nowDate[2]
            ).show()
        }

        input_due_time.setOnClickListener {
            clearFocus()
            val nowDate =
                data?.dueTime?.split(":")?.map { it.toInt() }
                    ?: arrayListOf(
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE)
                    )
            TimePickerDialog(
                requireContext(), time, nowDate[0], nowDate[1], true
            ).show()
        }

        cancel_form.setOnClickListener {
            dialog?.dismiss()
        }

        save_form.setOnClickListener {
            val newTodo = Todo()

            newTodo.id = data?.id
            newTodo.name = input_name.text.toString()
            newTodo.desc = input_note.text.toString()
            newTodo.dueDate = input_due_date.text.toString()
            newTodo.dueTime = input_due_time.text.toString()
            newTodo.created = getDate()
            newTodo.edited = getDate()
            newTodo.remind = txt_form_reminder.isChecked
            submitData(newTodo)
        }

        when (data) {
            null -> {
                // Adding
                txt_form_title.text = String.format(resources.getString(R.string.add_data))

            }
            else -> {
                // Edit
                txt_form_title.text = String.format(resources.getString(R.string.edit_data))
                input_name.text = toEditable(data.name)
                input_note.text = toEditable(data.desc)
                input_due_date.text = toEditable(data.dueDate)
                input_due_time.text = toEditable(data.dueTime)
                txt_form_reminder.isChecked = data.remind
            }
        }
    }

    override fun onPause() {
        super.onPause()

        input_name.text?.clear()
        input_note.text?.clear()
        input_due_date.text?.clear()
        input_due_time.text?.clear()
        txt_form_reminder.isChecked = false
    }

    private fun toEditable(string: String): Editable =
        Editable.Factory.getInstance().newEditable(string)

    private fun clearFocus() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
        edt_thief.requestFocus()
    }

    private fun getDate(): String {
        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format, Locale.US)
        return sdf.format(calendar.time)
    }

    private fun getTime(): String {
        val format = "HH:mm"
        val sdf = SimpleDateFormat(format, Locale.US)
        return sdf.format(calendar.time)
    }

    private fun submitData(data: Todo) {
        dialog?.dismiss()
        if (input_name.text.toString() == "" || input_note.text.toString() == "" || input_due_date.text.toString() == "" || input_due_time.text.toString() == "") {
            Snackbar.make(prevView!!, "You forgot something :(", Snackbar.LENGTH_LONG).show()
            return
        }

        if (this.data == null) {
            val testEllipsize = if (data.name.length > 24) "..." else ""
            Snackbar.make(
                prevView!!,
                "${data.name.take(24)}${testEllipsize} Added!",
                Snackbar.LENGTH_LONG
            )
                .show()
            data.edited = null
            TodoListFragment.viewModel.insertTodo(data)
        } else {
            val testEllipsize = if (data.name.length > 24) "..." else ""
            Snackbar.make(
                prevView!!,
                "${data.name.take(24)}${testEllipsize} Updated!",
                Snackbar.LENGTH_LONG
            )
                .show()
            Log.d("test", data.toString())
            TodoListFragment.viewModel.updateTodo(data)
        }
    }
}
