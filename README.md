# Overview

As a software engineer, I am working to deepen my understanding of
statically typed, JVM-based languages and how they improve on Java's
verbosity. Kotlin is now the primary language for Android development and
a common choice for backend services, so learning it expands the range of
projects I can build and the platforms I can target. The goal of this
project was to apply Kotlin in a real, running program — not just read
about its features — so that I could internalize the syntax and idioms
through practice.

The software I wrote is a console-based Task Manager called the **Edsarah
Computers and Solutions Task Manager**. It lets a user add tasks, remove
tasks, list all tasks, mark tasks complete, search tasks by keyword, and
view a summary of completed versus pending work. The program is a JVM
project built with Gradle and runs entirely in the terminal, driving an
interactive menu loop until the user chooses to exit.

My purpose in writing this software was to demonstrate the core features of
the Kotlin language in a single cohesive program. Specifically, it
demonstrates:

- **Variables** — `val` for immutable references (task IDs, titles, the app
  name) and `var` for mutable state (task completion status, the running
  flag, the next available ID).
- **Expressions** — `when` and `if` are used as expressions that return
  values, string templates are used for formatted output, and several
  functions are written as single expressions.
- **Conditionals** — `if / else if / else` for validation and `when` for
  menu dispatch.
- **Loops** — a `while` loop drives the main menu, a `for` loop lists tasks,
  and `forEach` iterates over search results.
- **Functions** — each menu action is its own function with an explicit
  parameter list and return type.
- **Classes** — a `TaskManager` class encapsulates the task list and every
  operation performed on it.
- **Data classes** (additional requirement) — a `data class Task` is used
  so Kotlin auto-generates `equals()`, `hashCode()`, `toString()`, and
  `copy()`.
- **Collections** (additional requirement) — a `MutableList<Task>` stores
  the tasks, and collection operations such as `add`, `removeAll`,
  `filter`, `find`, and `count` are used throughout.
- **The `when` keyword** (additional requirement) — used as an expression
  to dispatch menu choices and as a tool for clean, readable branching.

[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

The software was developed using the following tools:

- **Java Development Kit (JDK 17)** — the runtime that Kotlin compiles to.
- **Visual Studio Code** with the official **Kotlin by JetBrains**
  extension for syntax highlighting, IntelliSense, and Gradle integration.
- **Gradle** — used as the build tool to compile, run, and package the
  project as a standard JVM application.
- **Git and GitHub** — used for version control and to publish the code in
  a public repository named `KotlinTaskManager`.

The programming language used is **Kotlin**, targeting the **Java Virtual
Machine (JVM)**. No third-party libraries were required — the entire
program uses only Kotlin's standard library, including its collection
classes (`MutableList`, `List`) and its null-safety operators
(`?.`, `?:`, `toIntOrNull()`).

# Useful Websites

- [Kotlin Official Documentation](https://kotlinlang.org/docs/home.html)
- [Kotlin — Comparison to Java](https://kotlinlang.org/docs/comparison-to-java.html)
- [Kotlin Data Classes](https://kotlinlang.org/docs/data-classes.html)
- [Kotlin Collections Overview](https://kotlinlang.org/docs/collections-overview.html)
- [Kotlin Null Safety](https://kotlinlang.org/docs/null-safety.html)
- [Kotlin Wikipedia Entry](https://en.wikipedia.org/wiki/Kotlin_(programming_language))

# Future Work

- **Persist tasks to disk** — currently the task list lives only in memory,
  so it disappears when the program exits. Adding file I/O (e.g. writing to
  a JSON or CSV file) would let users keep their tasks between sessions.
- **Add due dates and priorities** — extend the `Task` data class with
  `dueDate: LocalDate?` and `priority: Int` fields, then add sort and filter
  options to the menu.
- **Add unit tests** — write tests for `TaskManager` using `kotlin.test`
  so that the add/remove/complete logic is verified automatically rather
  than only by hand.
- **Add an "edit task" option** — allow the user to rename a task after it
  has been created, demonstrating the `copy()` method of the data class.
- **Improve the CLI** — replace the numbered menu with a command-style
  interface (`add "buy milk"`) for faster interaction.