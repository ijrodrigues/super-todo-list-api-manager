package br.com.supertudolist.todolistmanager.controller.request

import br.com.supertudolist.todolistmanager.entity.Todo

data class CreateTodoRequest(val description : String){
    fun toEntity() = Todo(description)
}