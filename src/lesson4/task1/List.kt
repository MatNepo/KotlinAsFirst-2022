@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = TODO()

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = TODO()

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    // making alphabet
    // 1) arabian alphabet list
    val arabianAlphabet = listOf(
        1000, 900, 500, 400,
        100, 90, 50, 40,
        10, 9, 5, 4, 1
    )
    // 2) roman alphabet list
    val romanAlphabet = listOf(
        "M",  // 1000
        "CM", // 900
        "D",  // 500
        "CD", // 400

        "C",  // 100
        "XC", // 90
        "L",  // 50
        "XL", // 40

        "X",  // 10
        "IX", // 9
        "V",  // 5
        "IV", // 4
        "I"   // 1
    )
    var nCopy = n // f.e., 1978 = M CM LXX VIII
    val result = buildString {
        for (i in arabianAlphabet.indices) { // i belongs [0, arabianAlphabet.size), i++
            val iterationsMax = nCopy / arabianAlphabet[i] // here find max amount of iterations
            for (k in 0 until iterationsMax) {
                // 1978/1000=1 -> 978/900=1 -> 78/500=0 ... 78/50=1 ... 28/10=2 -> 8/5=1 -> 3/1=3
                nCopy %= arabianAlphabet[i]
                // 1978 -> 1978 % 1000 = 978 -> 978 % 900 = 78 -> 78 % 50 = 28 -> 28 % 10 = 18 -> 18 % 10 = 8 ->
                // -> 8 % 5 = 3 -> 3 % 1 = 0
                append(romanAlphabet[i])
                // M -> M CM -> M CM LXX -> M CM LXX VIII
            }
        }
    }
    return result
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    // making basic alphabet
    val units = listOf(
        "",        // 0
        "один ",   // 1
        "два ",    // 2
        "три ",    // 3
        "четыре ", // 4
        "пять ",   // 5
        "шесть ",  // 6
        "семь ",   // 7
        "восемь ", // 8
        "девять "  // 9
    )
    val fromTenToNineteen =
        listOf(
            "десять ",       // 10
            "одиннадцать ",  // 11
            "двенадцать ",   // 12
            "тринадцать ",   // 13
            "четырнадцать ", // 14
            "пятнадцать ",   // 15
            "шестнадцать ",  // 16
            "семнадцать ",   // 17
            "восемнадцать ", // 18
            "девятнадцать "  // 19
        )
    val dozens =
        listOf(
            "",             // 0
            "",             // 10
            "двадцать ",    // 20
            "тридцать ",    // 30
            "сорок ",       // 40
            "пятьдесят ",   // 50
            "шестьдесят ",  // 60
            "семьдесят ",   // 70
            "восемьдесят ", // 80
            "девяносто "    // 90
        )
    val hundreds =
        listOf(
            "",           // 0
            "сто ",       // 100
            "двести ",    // 200
            "триста ",    // 300
            "четыреста ", // 400
            "пятьсот ",   // 500
            "шестьсот ",  // 600
            "семьсот ",   // 700
            "восемьсот ", // 800
            "девятьсот "  // 900
        ) // basic alphabet ended

    // Getting started Main part:
    // f.e. in number 111999, 111 has the same rule of pronunciation as 999, just add "тысяча" after 111
    val nFirstThree = n / 1000 // f.e., 1) 111999 -> 111  |  2) 999 -> 0
    // hundreds of thousands sound the same as
    val nLastThree = n % 1000 // 1) 111999 -> 999 |  2) 111000 -> 0

    val result =
        // get started nFirstThree pronunciation
        hundreds[nFirstThree / 100] + // 1) 111 -> 1  |  2) 0 -> 0
                // check the 2nd digit from the 1st three
                (if (nFirstThree % 100 / 10 == 1) { /* f.e., 1) 111999 -> 1>1<1... -> 1 -> ifIn:
                                                             2) 999 -> 0>0<0.... -> else */
                    fromTenToNineteen[nFirstThree % 10] // if 11..19 -> get pronunciation fromTenToNineteen
                } else { /* f.e., 1) 123999
                                  2) 999
                                  3) 456777 */
                    (dozens[nFirstThree % 100 / 10] + /* f.e., 1) 123999 -> 1>2<3... -> 2
                                                               2) 999 -> 0>0<0... -> 0
                                                               3) 456777 -> 4>5<6... -> 4 */
                            when (nFirstThree % 10) { /* f.e., 1) 123999 -> 12>3<... -> 3
                                                               2) 999 -> 00>0<.... -> 0
                                                               3) 456777 -> 45>6<... -> 6 */
                                0 -> ""
                                1 -> "одна "
                                2 -> "две "
                                else -> units[nFirstThree % 10] // "три ", "четыре  ", ...
                            })
                }) +
                (if (nFirstThree != 0) { /* f.e., 1) 213999 -> 213... -> ifIn
                                                  2) 456777 -> 456... -> ifIn
                                                  3) 999 -> 000... -> ifExit */
                    // last 2 digits of number have the same pronunciation rule despite numbers 11..19
                    if (nFirstThree % 100 in 11..19) { /* f.e., 1) 213999 -> 213... -> ifIn
                                                                      2) 456777 -> 456... -> else */
                        "тысяч "
                    } else { // f.e. 456777
                        when (nFirstThree % 10) {
                            1 -> "тысяча "
                            in 2..4 -> "тысячи "
                            else -> "тысяч "
                        }
                    }
                } else { // f.e., 999
                    ""
                }) + // end of nFirstThree pronunciation
                /* get started nLastThree pronunciation
                 * this step is similar to the step above, so comments not needed */
                (hundreds[nLastThree / 100] +
                        if (nLastThree % 100 / 10 == 1) {
                            fromTenToNineteen[nLastThree % 10]
                        } else {
                            (dozens[nLastThree % 100 / 10] +
                                    units[nLastThree % 10])
                        }) // end of nLastThree pronunciation
    return result.trim() // return result deleting spaces at the beginning and at the end
}