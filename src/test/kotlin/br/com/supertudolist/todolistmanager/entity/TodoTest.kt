package br.com.supertudolist.todolistmanager.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.UUID

internal class TodoTest{

    @Test
    fun `should create a todo from description`(){

        val description = "Create a todo Test"
        val todo = Todo(description)

        assertNotNull(todo.id)
        assertEquals(description, todo.description)
        assertEquals(UUID.randomUUID().toString().length, todo.id.length)
        assertFalse(todo.done)
        assertNull(todo.doneAt)
    }
}