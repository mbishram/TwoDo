package id.ac.unhas.twodo.model

data class Todo(
    var name: String = "",
    var desc: String = "",
    var dueDate: String = "",
    var created: String = "",
    var edited: String? = null
)