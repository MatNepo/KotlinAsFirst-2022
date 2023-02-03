@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence", "NAME_SHADOWING")

package lesson7.task1

import java.io.File
import java.io.PrintStream
import java.util.*

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    PrintStream(File(outputName)).use { printStream ->
        File(inputName).forEachLine { currentLine: String ->
            /* first it's needed to check the line on empties,
               then (only after) add expression to check on '_' */
            if (currentLine.isEmpty() || currentLine[0] != '_') {
                printStream.println(currentLine) // ad current line if it's kk to the File
            }
        }
    }
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    val inputNameLower = File(inputName).readText().lowercase() // make input with only lower cases
    for (currentString in substrings.indices) { // go through each string of substrings
        if (!result.contains(substrings[currentString])) { // if no input in the current string
            result[substrings[currentString]] = 0 // result -> 0
        }
        val curSubLow = substrings[currentString].lowercase() // curSubLow => make CURrent SUBstring to LOWer case
        for (currentInput in inputNameLower.indices) { // go through the inputName
            if (inputNameLower.startsWith(curSubLow, currentInput) // true if starts with currentInput
            ) {
                result[substrings[currentString]] = // renew result
                    result[substrings[currentString]]!! + 1 // !! needs to include null result
            }
        }
    }
    return result
}


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */

fun mistakesChecking(line: MatchResult): CharSequence {
    val mistake = line.value
    return listOf(
        mistake[0],
        when (mistake[1]) {
            'ы' -> 'и'
            'Ы' -> 'И'
            'ю' -> 'у'
            'Ю' -> 'У'
            'я' -> 'а'
            'Я' -> 'А'
            else -> '-'
        },
    ).joinToString("")
}

fun wordsExceptionA(word: MatchResult): CharSequence {
    val mistake = word.value
    return listOf(
        when (mistake[4]) {
            'и' -> 'ы'
            'И' -> 'Ы'
            'у' -> 'ю'
            'У' -> 'Ю'
            'а' -> 'я'
            'А' -> 'Я'
            else -> '-'
        },
    ).joinToString("")
}

fun wordsExceptionB(word: MatchResult): CharSequence {
    val mistake = word.value
    return listOf(
        when (mistake[1]) {
            'и' -> 'ы'
            'И' -> 'Ы'
            'у' -> 'ю'
            'У' -> 'Ю'
            'а' -> 'я'
            'А' -> 'Я'
            else -> '-'
        },
    ).joinToString("")
}

fun sibilants(inputName: String, outputName: String) {
    val mistakeFinder = Regex("""[жшчщ][ыяю]""", RegexOption.IGNORE_CASE)
    PrintStream(File(outputName)).use { printStream ->
        File(inputName).forEachLine { line ->
            mistakeFinder.replace(line, ::mistakesChecking)
        }
    }
    val outputNameLow = outputName.split(" ")
    for (current in outputNameLow) {
        val buffer = current
        val curLow = current.lowercase()
        if (curLow.contains("брош") && curLow.contains("ра")) {
            mistakeFinder.replace(buffer, ::wordsExceptionA)
        } else if (curLow.contains("ж") && curLow.contains("ри")) {
            mistakeFinder.replace(buffer, ::wordsExceptionB)
        } else if (curLow.contains("пара") && curLow.contains("т")) {
            mistakeFinder.replace(buffer, ::wordsExceptionA)
        }
        Regex(outputName, RegexOption.IGNORE_CASE).replace(current, buffer)
    }
    println(outputName)
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    var text = File(inputName).readText() // to work with input file
    File(outputName).bufferedWriter().use {
        it.write("<html><body>") // start label
        mapOf( // translate special text symbols to html
            Pair("~~(.*?)~~", Pair("s", "~~")),
            Pair("\\*\\*(.*?)\\*\\*", Pair("b", "**")),
            Pair("\\*(.*?)\\*", Pair("i", "*"))
        ).forEach /* go through map */ { (mapPair, mapValue) ->
            text = Regex(mapPair).replace(text) /* make a replacement using lambda: */ { it ->
                "<${mapValue.first}>" + it.value.replace(mapValue.second, "") + "</${mapValue.first}>"
                // f.e., <i> + (some text without ~~, * or **) + </i>
            }
        }
        val textList = text.split("\n") // make a list from initial text using new line as a split reason
        var isParagraph = false // detecting of paragraph, at the beginning its value = false (boolean)
        if (text.isBlank()) it.write("<p></p>") // if no text (empty) -> just write <p></p>
        textList.forEachIndexed { index, line -> // go through lines giving each an index
            // if it's paragraph, index not a 0 and preorder line of the List was empty ->
            if (isParagraph && index != 0 && textList[index - 1].isBlank()) {
                isParagraph = false // renew isParagraph
                it.write("</p>")
            }
            // if it's a paragraph and line is not empty ->
            if (!isParagraph && line.isNotBlank()) {
                isParagraph = true // renew isParagraph
                it.write("<p>")
            }
            it.write(line) // write current line
        }
        it.write(
            // if isParagraph == true, it's needed to end the paragraph writing "</p>", else nothing
            (if (isParagraph) "</p>" else "") +
                    "</body></html>" // add ending of an output file
        )
    }
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
 19935 | 22
-198     906
----
   13
   -0
   --
   135
  -132
  ----
     3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */

// main fun is used to watch program output
fun main(args: Array<String>) {
    val reader = Scanner(System.`in`)
    println("INT 1:")
    val divisible: Int = reader.nextInt()
    println("INT 2:")
    val divider: Int = reader.nextInt()
    //ввод закончен

    printDivisionProcess(divisible, divider, "file.txt")
}

fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {

    val writer = File(outputName) // создаём выходной файл
    val printStream = PrintStream(writer)

    // необязательно, но так понятнее
    val divisible: Int = lhv // делимое
    val divider: Int = rhv // делитель

    val divisibleString = divisible.toString()
    println(" $divisible | $divider") // 1я строка вывода
    printStream.println(" $divisible | $divider")

    val quotient: Int = divisible / divider // частное
    val quotientString = quotient.toString()

    var deductible = quotientString[0].digitToInt() * divider // вычитаемое = частное * делитель
    val deductibleString = deductible.toString()


    var minuend = divisibleString.substring(0, deductibleString.length).toInt() // текущая разность
    // текущая разность это остаток, но не итоговый, например, в примере из условия это 13 и 135
    var index2: Int
    if (deductible > minuend) { // вычитаемое > текущей разности
        minuend = divisibleString.substring(0, deductibleString.length + 1).toInt()
        index2 = deductibleString.length + 1
    } else {
        index2 = deductibleString.length
        // вывод первого вычитаемого
        print("-$deductible")
        printStream.print("-$deductible")
    }

    val pos1 = divisibleString.length + 3 // начало частного
    val pos2 = deductibleString.length // сохраняем позицию вычитаемого
    // расчет пробелов для того, чтобы вывести частное
    for (i in 1..pos1 - pos2) {
        printStream.print(" ")
        print(" ")
    }

    println(quotient)
    printStream.println(quotient)
    // _ выводим
    for (i in 1..deductibleString.length + 1) {
        print("-")
        printStream.print("-")
    }

    // что имеется: длина на которую надо сделать отступ
    // deductible - делитель
    // в modString 906
    // нужно учесть: больше ли верх
    // посчитать снос верха
    // посчитать необходимый отступ
    //divisible, divisibleString, divider, quotient, quotientString, deductible, deductibleString
    // var space = deductibleString.length //отступ слева должен считаться как 1(по умолчанию) + вычитаемое минус частное

    var minuendLengthBefore = minuend.toString().length // длина текущей разности
    minuend -= deductible // текущая разность - вычитаемое (обновили текущую разность)
    var space = 1 + minuendLengthBefore - minuend.toString().length // space это количество отступов слева

    // здесь пробегаемся по всем цифрам в частном, кроме самой первой, тк ее уже учли
    for (i in 1 until quotientString.length) {
        val minuendBefore = minuend // нужно для того, чтобы вывести 0
        minuend *= 10 // для сноса цифры
        minuend += divisibleString[index2].digitToInt() //снос цифры
        index2++

        // просто вывод с учетом всех особенностей
        println()
        printStream.println()
        for (i in 1..space) { // пробелы перед текущей разностью
            print(" ")
            printStream.print(" ")
        }
        if (minuendBefore == 0) { // если остаток текущей разности == 0
            print("0")
            printStream.print("0")
        }

        // вывод текущей разности
        println(minuend)
        printStream.println(minuend)
        deductible = quotientString[i].digitToInt() * divider // вычитаемое = (текущая цифра частного) * делитель
        for (i in 1 until space) { // кол-во пробелов перед вычитаемым
            print(" ")
            printStream.print(" ")
        }

        // учет особенности вывода
        if (deductible.toString().length < minuend.toString().length || minuendBefore == 0) {
            printStream.print(" ")
            print(" ")
        }
        // знак минуса для всех вычитаемых кроме первого
        print("-")
        printStream.print("-")
        // вычитаемое
        println(deductible)
        printStream.println(deductible)
        if (minuendLengthBefore - minuend.toString().length == 0 || deductible == 0) {
            for (i in 1..space) {
                print(" ")
                printStream.print(" ")
            }
        } else {
            for (i in 1 until space) {
                print(" ")
                printStream.print(" ")
            }
        }

        val deductibleLength = deductible.toString().length
        // вывод чёрточек перед результатом текущей разности
        for (i in 0..deductibleLength) {
            print("-")
            printStream.print("-")
        }
        // аналогичное было объяснено выше
        minuendLengthBefore = minuend.toString().length
        minuend -= deductible
        val minuendString = minuend.toString()
        space += if (minuendLengthBefore - minuendString.length == 0 && deductible != 0) {
            1
        } else {
            minuendLengthBefore - minuendString.length
        }
    }
//  Вывод остатка от деления
    println()
    printStream.println()
    for (i in 1..space) { // кол-во пробелов перед итоговым остатком
        printStream.print(" ")
        print(" ")
    }
    // вывод остатка
    printStream.println(minuend)
    println(minuend)
}