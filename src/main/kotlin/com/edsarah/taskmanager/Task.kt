package com.edsarah.taskmanager

/**
 * Represents a single task in the Edsarah Computers and Solutions Task Manager.
 *
 * Declared as a `data class` so the Kotlin compiler automatically generates:
 *  - equals() / hashCode()  -> value-based comparison, safe for collections
 *  - toString()             -> readable printing with println()
 *  - copy()                 -> easy immutable-style updates
 *  - componentN()           -> destructuring (val (id, title, done) = task)
 *
 * `id` and `title` are `val` (immutable) because a task's identity and
 * description should never be reassigned after creation.
 * `isComplete` is `var` because its state changes as work progresses.
 */
data class Task(
    val id: Int,
    val title: String,
    var isComplete: Boolean = false
)