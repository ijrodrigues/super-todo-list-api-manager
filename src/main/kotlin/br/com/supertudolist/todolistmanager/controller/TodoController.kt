package br.com.supertudolist.todolistmanager.controller

import br.com.supertudolist.todolistmanager.exceptions.NotFoundException
import br.com.supertudolist.todolistmanager.controller.request.CreateTodoRequest
import br.com.supertudolist.todolistmanager.controller.request.EditTodoRequest
import br.com.supertudolist.todolistmanager.controller.response.TodoResponse
import br.com.supertudolist.todolistmanager.controller.response.TodoResponse.Companion.fromTodo
import br.com.supertudolist.todolistmanager.repository.TodoRepository
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime.now

@RequestMapping("/todos")
@CrossOrigin("*")
@RestController
class TodoController(private val todoRepository: TodoRepository){


    @PostMapping()
    @ResponseStatus(CREATED)
    fun create(@RequestBody todoRequest : CreateTodoRequest): TodoResponse {
        val todo = todoRepository.save(todoRequest.toEntity())
        return fromTodo(todo)
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    fun edit(@PathVariable id : String, @RequestBody todoRequest : EditTodoRequest): TodoResponse {
        val todo = todoRepository.findById(id)
            .orElseThrow { throw NotFoundException() }
        return fromTodo(todoRepository.save(todo.copy(description = todoRequest.description)))
    }

    @PutMapping("/{id}/switch-status")
    @ResponseStatus(OK)
    fun switchStatus(@PathVariable id : String): TodoResponse {
        val todo = todoRepository.findById(id)
            .orElseThrow { throw NotFoundException() }

        return when{
            todo.done -> fromTodo(todoRepository.save(todo.copy(done = false, doneAt = null)))
            else -> fromTodo(todoRepository.save(todo.copy(done = true, doneAt = now())))
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    fun delete(@PathVariable id : String) {
        val todo = todoRepository.findById(id)
            .orElseThrow { throw NotFoundException() }
        todoRepository.deleteById(id)
    }
    @GetMapping()
    fun getAll() =
        todoRepository.findAll()
        .map { fromTodo(it) }
}