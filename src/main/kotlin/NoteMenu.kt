import java.util.*

class NoteMenu(private val notes: MutableList<Note>) {

    private val scanner = Scanner(System.`in`)

    fun show() {
        while (true) {
            println("Заметки:")
            println("0. Создать заметку")
            for ((index, note) in notes.withIndex()) {
                println("${index + 1}. ${note.title}")
            }
            println("${notes.size + 1}. Назад")

            print("Введите номер операции: ")
            val input = readInput()
            if (input == notes.size + 1) break // Возврат назад
            else if (input == 0) createNote()
            else if (input > 0 && input <= notes.size) viewNote(input - 1)
            else invalidOption()
        }
    }

    private fun createNote() {
        print("Введите заголовок заметки: ")
        val title = scanner.nextLine()

        if (title.isNotBlank()) {
            print("Введите содержание заметки: ")
            val content = scanner.nextLine()

            if (content.isNotBlank()) {
                notes.add(Note(title, content))
            } else {
                println("Содержание заметки не может быть пустым.")
            }
        } else {
            println("Заголовок заметки не может быть пустым.")
        }
    }

    private fun viewNote(index: Int) {
        val note = notes[index]
        println("Заголовок: ${note.title}")
        println("Содержание:\n${note.content}")
    }

    private fun invalidOption() {
        println("Некорректная операция. Попробуйте еще раз.")
    }

    private fun readInput(): Int {
        return try {
            scanner.nextInt().also { scanner.nextLine() }
        } catch (e: InputMismatchException) {
            scanner.nextLine()
            -1
        }
    }
}