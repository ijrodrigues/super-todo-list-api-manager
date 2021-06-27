package br.com.supertudolist.todolistmanager.controller.response

import br.com.supertudolist.todolistmanager.entity.Todo
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class TodoResponse(
    val id : String,
    val description : String,
    val done : Boolean,
    @JsonFormat(pattern = "dd/mm/yyyy HH:mm")
    val createdAt : LocalDateTime,
    @JsonFormat(pattern = "dd/mm/yyyy HH:mm")
    val doneAt : LocalDateTime?){

    companion object{
        fun fromTodo(todo: Todo) =
            TodoResponse(todo.id, todo.description, todo.done, todo.createdAt, todo.doneAt)
    }
}