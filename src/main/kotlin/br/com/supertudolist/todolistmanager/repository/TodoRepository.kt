package br.com.supertudolist.todolistmanager.repository

import br.com.supertudolist.todolistmanager.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, String>