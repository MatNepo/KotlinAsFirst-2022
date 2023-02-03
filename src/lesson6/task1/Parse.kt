@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence", "UNREACHABLE_CODE", "UNUSED_EXPRESSION")

package lesson6.task1

import java.util.*

// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двух символьной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readlnOrNull()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String = TODO()

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String = TODO()

/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String = TODO()

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, — пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    try {
        if (jumps.contains(Regex("""[^-%+\d\s]""")) || jumps.isEmpty()
        ) {
            return -1
        }
            val jumpsList = jumps.split(" ") // create list with jumps and info abt them separated
        /*
          220
          +
          224
          %+
          228
          %-
          230
          +
          232
          %%-
          234
          %
        */
        var maxJump = -1 // reset maxJump
        for (current in jumpsList.indices step 2) { // go through jumpsList with step 2
            if (jumpsList[current + 1].contains('+') && jumpsList[current].toInt() > maxJump) {
                maxJump = jumpsList[current].toInt()
            }
        }
        return maxJump
    } catch (e: Exception) {
        return -1 // smth wrong or no gd tries -> return -1
    }
}

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * При нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String = TODO()

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
// this sub function will be needed later in the main part of computeDeviceCells() fun
fun endOfCurrentLoop(str: String): Int {
    var loopCommands = 0
    // endCurrentLoopCounter - value with a position of the element which ends current loop
    for (endCurrentLoopCounter in str.indices) { /* f.e., 1) [>]>[<] i = : 0 -> 2 -> return i
                                                          2) [>[<<]] i = : 0 -> 2 -> 5 -> 6 -> return i */
        when (str[endCurrentLoopCounter]) { /* f.e., 1) [>]>[<] loopCommands = : 0 -> 1 -> 0
                                                     2) [>[<<]] loopCommands = : 0 -> 1 -> 2 -> 1 -> 0 */
            '[' -> loopCommands++ // current loop starts
            ']' -> if (loopCommands == 1) { // current loop ended
                return endCurrentLoopCounter // end of current sub fun, return result to main part
            } else loopCommands--
        }
    }
    return -1 // just value to return which will never be reached cause of loopBeginEnd check
}

fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val availableChars = setOf('>', '<', '+', '-', '[', ']') // available alphabet
    // to make easier working with chars, remove spaces, saving on;y useful chars
    val withoutSpaces = commands.filter { it != ' ' } // if it's not a space -> add to

    // get started with checking that every "[" has a pair like "]"
    var loopBeginEnd = 0 // needed to check that all loops, which started, ended
    for (char in withoutSpaces) when (char) { /* f.e., 1) [>[<<]] loopBeginEnd = : 0-> 1 -> 2 -> 1 -> 0
                                                       2) [>]<[>] loopBeginEnd = : 0 -> 1 -> 0 -> 1 -> 0
                                                       3) [[>>[<]>] loopBeginEnd = : 0 -> 1 -> 2 -> 3 -> 2 -> 1
                                                       4) >[>[q]] loopBeginEnd = : 0 -> 1 -> 2 -> exception */
        !in availableChars /* if unavailable char met */ -> {
            throw IllegalArgumentException()
            println(
                "{\n\t\"java.lang.IllegalArgumentException\"\n" +
                        "\t\"message\": Unavailable char met\"\n}",
            )
        } // exception catch
        '[' -> {
            loopBeginEnd++
        } // 1st element "[" of pair "[]" found, so ++
        ']' -> {
            loopBeginEnd--
            if (loopBeginEnd < 0) break
        } // 2nd element "]" of pair "[]" found, so --
    }
    if (loopBeginEnd != 0) {
        throw IllegalArgumentException()
        println(
            "{\n\t\"java.lang.IllegalArgumentException\"\n" +
                    "\t\"message\": Unpaired closing bracket\n}"
        )
    }  /* f.e., 1) [>[<<]] loopBeginEnd = 0 -> all is gd
                2) [>]<[>] loopBeginEnd = 0 -> all is gd
                3) [[>>[<]>] loopBeginEnd = 1 -> exception */
    // end of checking on pairs

    // create variables and values
    // to work with current element it's easier to use array and then convert it to List
    val result = Array(cells) { 0 } // reset result array
    var detector = cells / 2 // set detector on start position
    /* I tried first to use withoutSpaces but realised that withoutSpaces can't be used
       cause of spaces in initial commands, it'll influence on detector start position
       f.e., if commands = "<<<    >", start detector value will be between "<<< " and "   >" but
       for withoutSpaces the same initial commands will detect detector start between "<<" and "<>"
     */
    val amountOfCommands = commands.length // withoutSpaces can't be used cause of spaces in initial List
    var currentCommand = 0
    var currentCommandCounter = 1
    val bufferLoopPair = Stack<Int>() // just buffer needed
    // main part starts
    while (currentCommand < amountOfCommands && currentCommandCounter <= limit) {
        when (commands[currentCommand]) { // go through commands using lambda
            '>' -> detector++ // next cell
            '<' -> detector-- // previous cell
            '+' -> result[detector]++ // cell value ++
            '-' -> result[detector]-- // cell value --
            '[' -> // beginning of possible cycle
                if (result[detector] == 0) { // according to the initial task
                    currentCommand += endOfCurrentLoop(commands.substring(currentCommand))
                } else bufferLoopPair.push(currentCommand) // save loop start cell

            ']' -> // end of possible cycle
                if (result[detector] != 0) { // according to the initial task
                    currentCommand = bufferLoopPair.peek()
                } else bufferLoopPair.pop()
        }
        currentCommand++ // next command
        currentCommandCounter++ // renew currentCommandCounter
        /* before end or continue the cycle it's needed to check that detector
           belongs [0, cells - 1] => [0, cells), case cells has Int type.
           So if it doesn't belong to range -> throw IllegalStateException() */
        if (detector !in 0 until cells) {
            throw IllegalStateException()
            println(
                "{\n\t\"java.lang.IllegalArgumentException\"" +
                        "\n\t\"message\": java.lang.IndexOutOfBoundsException\n}"
            )
        }
    }
    return result.toList()
} // interesting task, looks like realisation of Turing Machine
