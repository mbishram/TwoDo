package id.ac.unhas.twodo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unhas.twodo.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * from todo_table")
    fun getTodos(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)
}