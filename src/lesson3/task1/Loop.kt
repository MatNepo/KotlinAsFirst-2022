@file:Suppress("UNUSED_PARAMETER", "NAME_SHADOWING", "UNREACHABLE_CODE", "UNUSED_EXPRESSION")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var counter = 0 // reset counter
    var m = n // copy initial number
    while (true) {
        counter++
        m /= 10 // remove the least significant digit
        if (m == 0) return counter // all figures r considered
    }
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
// Использовал рекурсивную функцию, но она заняла 39 секунд компиляции из-за многократного вызова функции
//fun fib(n: Int): Int {
//    return if (n in 1..2) 1 // special situation for 1st and 2nd numbers
//    else fib(n - 1) + fib(n - 2) // using recurse function
//}
// Код из циклов больше, но его компиляция быстрее:
fun fib(n: Int): Int {
    var fib1 = 1 // 1st and 2nd elements = 1
    var fib2 = 1 // if n = 1 or 2 -> fib(n) = fib2 = 1
    var fibRes = 1 // sum of fib(n) and fib(n-1)
    var counter = 0
    while (counter < n - 2) {
        fibRes = fib1 + fib2
        fib1 = fib2 // renew fib1
        fib2 = fibRes // renew result
        counter++
    }
    return fibRes
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    val minDivider = 2 // min value of result
    for (minDivider: Int in 2..n) {
        if (n % minDivider == 0) return minDivider
    }
    return minDivider
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var maxDivider = n
    while (true) {
        maxDivider--
        if (n % maxDivider == 0) return maxDivider
    }
    return maxDivider
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X / 2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var xNext = x
    var result = 0
    while (xNext != 1) {
        if (xNext % 2 == 0) {
            xNext /= 2
        } else {
            xNext = xNext * 3 + 1
        }
        result++
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
//fun lcm(m: Int, n: Int): Int {
//    val k = 2
//    for (k: Int in 2..m * n) { // m * n = max result
//        if (k % m == 0 && k % n == 0) {
//            return k
//        }
//    }
//    return k
//}
/* Если раскомментировать верхние строки с программой, то всё будет работать, но компиляция заняла 20 секунд
   lcm(m, n) = |m * n| / gcd(m, n) , gcd - the greatest common divisor
                                     lcm - the least common multiple
                                     m, n - initial numbers
   Ниже реализована функция, делающая, то же самое, что и функция выше, но с использованием подфункции: */
// sub function gcd:
fun gcd(m: Int, n: Int): Int {
    // to find max and min numbers from m and n
    var maxNumber = max(m, n)
    var minNumber = min(m, n)
    var buffer: Int
    while (maxNumber % minNumber != 0) {
        buffer = minNumber
        minNumber = maxNumber % minNumber
        maxNumber = buffer
    }
    // gcd result will be in minNumber
    return minNumber
}

// function with result lcm:
fun lcm(m: Int, n: Int): Int = abs(m * n) / gcd(m, n)
// компиляция заняла 15 ms (чуть быстрее)

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
// this task ca be easily solved with using sub function gcd which was written above, so
// if gcd == 1 -> solved
fun isCoPrime(m: Int, n: Int): Boolean = gcd(m, n) == 1

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var nCopy = n
    var result = 0

    while (nCopy != 0) { // 345 -> 34 -> 3 -> exit
        val buffer = nCopy % 10 // 5 -> 4 -> 3
        result = result * 10 + buffer // 5 -> 5 * 10 + 4 = 54 -> 54 * 10 + 3
        nCopy /= 10 // 345 -> 34 -> 3 -> 0
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
// using function revert which was written above, if number == reverse number -> it's palindrome
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val lastDigit = n % 10 // 25323 -> 3
    var nCopy = n
// example: 25323  |  111
    while (nCopy != 0) { // 25323 -> 2532  |  111 -> 11 -> 1 -> false
        if (nCopy % 10 != lastDigit) { // 3 == 3 -> 2 != 3 -> true  |  1 == 1 -> 1 == 1 -> 1 == 1
            return true
        }
        nCopy /= 10 // 25323 -> 2532  |  111 -> 11 -> 1
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {

    var angle = x % (2 * PI) // bring to the gap 0..(2 * PI)
    val angleSquare = sqr(angle) // save angle^2
    var result = 0.0 // result, type Double
    var i = 1 // amount of operations - needed to get odd factorials

    while (abs(angle) > eps) { // if |angle| >= 0 -> end
        result += angle
        angle *= -angleSquare / (++i * ++i) // x * (-x^2 / (++i * ++i))
    }
    // end
    return result
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {

    val angle = x % (2 * PI)
    val angleSquare = sqr(angle) // save angle^2
    var result = 0.0
    var i = 1.0 // amount of operations - needed to get even factorials
    var currentElement = 1.0 // 1st element value

    while (abs(angle) > eps) { // if |angle| >= 0 -> end
        result += currentElement
        currentElement *= -angleSquare / (++i * ++i) // cE * (-x^2 / (++i * ++i))
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {

    var currentNumber = 1
    var currentSquareNumber = sqr(currentNumber)
    // to find amount of digits in a number use those function which was made above digitNumber(x)
    var currentLength = digitNumber(currentSquareNumber)
    var bufferLength = 0

    while (bufferLength + currentLength < n) {
        // renew all results for next iteration
        currentNumber++ // increase current number, so take the next one (1, 2, 3, 4, ...)
        bufferLength += currentLength // bufferLength will sum all lengths of numbers received before
        currentSquareNumber = sqr(currentNumber)
        currentLength = digitNumber(currentSquareNumber)
    }
    bufferLength += currentLength // sum of all lengths
    while (bufferLength != n) { // f.e., 1 4 9 16 25 36 and n = 8, so the result should be 3
        currentSquareNumber /= 10 // remove last digit
        bufferLength-- // n = 9 -> n = 8 -> exitWhile
    }
    // exitWhile
    return currentSquareNumber % 10 // last digit of currentSquareNumber is a result
}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {

    var previousNumber = 0 // at the beginning fib(n - 1) = 0
    var currentNumber = 1 // at the beginning fib(n) = 1
    // to find amount of digits in a number use those function which was made above digitNumber(x)
    var currentLength = digitNumber(currentNumber)
    var bufferLength = 0

    while (bufferLength + currentLength < n) {
        // renew all results for next iteration
        bufferLength += currentLength // bufferLength will sum all lengths of numbers received before
        currentNumber += previousNumber // 1 -> 2 -> 3 -> 5 ...
        previousNumber = currentNumber - previousNumber // 1 -> 1 -> 2 -> 3 ...
        currentLength = digitNumber(currentNumber) // 1 -> 1 -> 1 -> 1 ...
    }
    bufferLength += currentLength // sum of all lengths
    while (bufferLength != n) { // f.e., 1 1 2 3 5 8 13 and n = 7, so the result should be 1
        currentNumber /= 10 // remove last digit
        bufferLength-- // n = 8 -> n = 7 -> exitWhile
    }
    // exitWhile
    return currentNumber % 10 // last digit of currentSquareNumber is a result
}
