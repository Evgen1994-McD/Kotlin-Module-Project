import java.util.*

class ArchiveMenu {

    private var archives: MutableList<Pair<String, MutableList<Note>>> = mutableListOf()
    private val scanner = Scanner(System.`in`)

    fun show() {
        while (true) {
            println("Архивы:")
            println("0. Создать архив")
            for ((index, archive) in archives.withIndex()) {
                println("${index + 1}. ${archive.first}")
            }
            println("${archives.size + 1}. Выход")

            print("Введите номер операции: ")
            val input = readInput()
            if (input == archives.size + 1) break // Выход из программы
            else if (input == 0) createArchive()
            else if (input > 0 && input <= archives.size) selectArchive(input - 1)
            else invalidOption()
        }
    }

    private fun createArchive() {
        print("Введите имя архива: ")
        val name = scanner.nextLine()

        if (name.isNotBlank()) {
            val newArchive = Pair(name, mutableListOf<Note>())
            archives.add(newArchive)
            NoteMenu(newArchive.second).show()
        } else {
            println("Имя архива не может быть пустым.")
        }
    }

    private fun selectArchive(index: Int) {
        val selectedArchive = archives[index]
        NoteMenu(selectedArchive.second).show()
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