package id.ac.unhas.twodo.model

data class Todo(
    var id: Int = -1,
    var name: String = "",
    var desc: String = "",
    var dueDate: String = "",
    var dueTime: String = "",
    var created: String = "",
    var edited: String? = null,
    var remind: Boolean = false
)