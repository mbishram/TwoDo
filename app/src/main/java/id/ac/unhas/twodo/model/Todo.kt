package id.ac.unhas.twodo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "desc")
    var desc: String = "",

    @ColumnInfo(name = "due_date")
    var dueDate: String = "",

    @ColumnInfo(name = "due_time")
    var dueTime: String = "",

    @ColumnInfo(name = "created")
    var created: String = "",

    @ColumnInfo(name = "edited")
    var edited: String? = null,

    @ColumnInfo(name = "remind")
    var remind: Boolean = false
)