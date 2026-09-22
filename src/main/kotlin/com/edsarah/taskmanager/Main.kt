package com.edsarah.taskmanager

// `const val` is a compile-time constant -> the most efficient kind of `val`.
// Demonstrates the immutable side of Kotlin's variable system.
const val APP_NAME = "Edsarah Computers and Solutions - Task Manager"

// A raw string (triple-quoted) with trimIndent() keeps the menu tidy
// inside the source file without leading whitespace in the output.
private val MENU = """
    ============================================
     $APP_NAME
    ============================================
      1. Add task
      2. Remove task
      3. List all tasks
      4. Mark task as complete
      5. Search tasks
      6. Show statistics
      0. Exit
    --------------------------------------------
    Choose an option: """.trimIndent()

/**
 * Program entry point. Runs an interactive menu loop until the user exits.
 */
fun main() {
    val manager = TaskManager()      // single manager shared across the run
    var running = true               // `var` because it flips to false on exit

    println("Welcome to $APP_NAME")  // string template interpolation

    // The while loop drives the whole interactive session.
    while (running) {
        println(MENU)

        // `readLine()` returns String? (nullable). The `?.` safe-call
        // only invokes trim() when the input is not null.
        val choice = readlnOrNull()?.trim() ?: return

        // `when` as an expression -> cleaner than a chain of if/else if.
        when (choice) {
            "1" -> handleAdd(manager)
            "2" -> handleRemove(manager)
            "3" -> handleList(manager)
            "4" -> handleComplete(manager)
            "5" -> handleSearch(manager)
            "6" -> handleStats(manager)
            "0" -> {
                println("Goodbye from $APP_NAME!")
                running = false      // exits the while loop on next iteration
            }
            else -> println("Invalid option. Please try again.")
        }
    }
}

/* ---------- Menu handlers ------------------------------------------
   Each handler is a small, single-purpose function. This keeps `main`
   readable and makes the code easy to test and extend.            */

/** Prompts for a title, validates it, and adds a new task. */
private fun handleAdd(manager: TaskManager) {
    print("Enter task title: ")
    // `?: ""` converts a null (e.g. Ctrl+D on stdin) into an empty string
    // so the .isEmpty() check below always works safely.
    val title = readLine()?.trim().orEmpty()
    if (title.isEmpty()) {
        println("Task title cannot be empty.")
        return                    // early return keeps the happy path flat
    }
    val task = manager.addTask(title)
    println("Added -> $task")     // uses the auto-generated toString()
}

/** Prompts for an ID and removes the matching task. */
private fun handleRemove(manager: TaskManager) {
    // Guard clause: nothing to do if the list is empty.
    if (manager.isEmpty()) {
        println("No tasks to remove.")
        return
    }
    print("Enter task ID to remove: ")
    // `toIntOrNull()` is the null-safe way to parse an Int -> no
    // NumberFormatException, no try/catch needed.
    val id = readLine()?.trim()?.toIntOrNull()
    if (id == null) {
        println("Invalid ID. Please enter a number.")
        return
    }
    val removed = manager.removeTask(id)
    // `if` used as an *expression* returns a value directly to println.
    println(if (removed) "Task $id removed." else "No task found with ID $id.")
}

/** Prints every task with a checkbox showing completion status. */
private fun handleList(manager: TaskManager) {
    if (manager.isEmpty()) {
        println("No tasks yet.")
        return
    }
    println("--- Your tasks ---")
    // Classic for loop over the read-only snapshot returned by listTasks().
    for (task in manager.listTasks()) {
        val status = if (task.isComplete) "[x]" else "[ ]"
        println("${status} ${task.id}. ${task.title}")
    }
}

/** Prompts for an ID and marks the task complete. */
private fun handleComplete(manager: TaskManager) {
    print("Enter task ID to mark complete: ")
    val id = readLine()?.trim()?.toIntOrNull()
    if (id == null) {
        println("Invalid ID. Please enter a number.")
        return
    }
    val ok = manager.markTaskComplete(id)
    println(if (ok) "Task $id marked complete." else "No task found with ID $id.")
}

/** Case-insensitive substring search across all task titles. */
private fun handleSearch(manager: TaskManager) {
    print("Enter keyword: ")
    val keyword = readLine()?.trim().orEmpty()
    if (keyword.isEmpty()) {
        println("Keyword cannot be empty.")
        return
    }
    val results = manager.searchTasks(keyword)
    if (results.isEmpty()) {
        println("No tasks matched \"$keyword\".")
    } else {
        println("Matches for \"$keyword\":")
        // forEach shows the functional-style iteration Kotlin encourages.
        results.forEach {
            val done = if (it.isComplete) "(done)" else ""
            println("  ${it.id}. ${it.title} $done")
        }
    }
}

/** Shows a one-line summary of total / completed / pending tasks. */
private fun handleStats(manager: TaskManager) {
    val total = manager.listTasks().size
    val done  = manager.completedCount()
    val todo  = manager.pendingCount()
    println("Total: $total | Completed: $done | Pending: $todo")
}