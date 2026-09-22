package com.edsarah.taskmanager

/**
 * Owns the in-memory list of tasks and exposes every operation the CLI needs.
 *
 * Encapsulation: the list is `private`, so no outside code can corrupt it.
 * Callers must use the public functions below.
 */
class TaskManager {

    // MutableList demonstrates Kotlin's mutable collection interface.
    // Kept private so the class controls all mutation.
    private val tasks = mutableListOf<Task>()

    // `var` because the counter increments every time a task is added.
    // Starts at 1 so the first task the user sees is "1", not "0".
    private var nextId = 1

    /**
     * Creates a new task, assigns the next available ID, stores it,
     * and returns the new Task so the caller can display it.
     */
    fun addTask(title: String): Task {
        val task = Task(id = nextId++, title = title)
        tasks.add(task)
        return task
    }

    /**
     * Removes every task whose id matches [id].
     * `removeAll` with a lambda is used instead of `remove` because it
     * returns a Boolean indicating whether anything was actually removed.
     */
    fun removeTask(id: Int): Boolean = tasks.removeAll { it.id == id }

    /**
     * Returns a *copy* of the internal list so external code cannot
     * mutate our private collection. This is a common defensive pattern.
     */
    fun listTasks(): List<Task> = tasks.toList()

    /**
     * Finds the task with the given [id] and marks it complete.
     * The `?: return false` (elvis operator) short-circuits when no
     * matching task exists, avoiding a NullPointerException.
     */
    fun markTaskComplete(id: Int): Boolean {
        val task = tasks.find { it.id == id } ?: return false
        task.isComplete = true
        return true
    }

    /**
     * Case-insensitive substring search over task titles.
     * `filter` returns a new list; the original collection is untouched.
     */
    fun searchTasks(keyword: String): List<Task> =
        tasks.filter { it.title.contains(keyword, ignoreCase = true) }

    // Single-expression helper functions.
    // `count { ... }` is preferred over a manual loop for readability.
    fun completedCount(): Int = tasks.count { it.isComplete }
    fun pendingCount(): Int   = tasks.count { !it.isComplete }
    fun isEmpty(): Boolean    = tasks.isEmpty()
}