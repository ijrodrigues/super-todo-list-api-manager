package br.com.supertudolist.todolistmanager.entity

import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Todo(
    val description : String,
    @Id val id : String = UUID.randomUUID().toString(),
    val createdAt : LocalDateTime = now(),
    val done : Boolean = false,
    val doneAt : LocalDateTime? = null)