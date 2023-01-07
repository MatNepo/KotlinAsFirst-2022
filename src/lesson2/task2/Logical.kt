@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая (2 балла)
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean =
    number / 1000 + (number / 100) % 10 == (number / 10) % 10 + number % 10
//  ((abcd -> a) + (abcd -> ab -> b) = a + b) == ((abcd -> abc -> c) + (abcd -> d) = c + d)
/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
    x1 == x2 || // the same horizontal
            y1 == y2 || // the same vertical
            abs(x1 - x2) == abs(y1 - y2) // e same diagonal th(horizontal length 2 pass == vertical...)


/**
 * Простая (2 балла)
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    val i = when {
        month in 1..7 && month % 2 == 1 -> 31 // odd months
        month in 1..7 && month != 2 -> 30 // even months despite February
        month in 8..12 && month % 2 == 1 -> 30 // odd months
        month in 8..12 -> 31 // even months despite February
        month == 2 && year % 4 == 0 && year % 100 != 0 || year % 400 == 0 -> 29 // February, leap year
        else -> 28 // February, not leap year
    }
    return i
}

/**
 * Простая (2 балла)
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean =
    /* ] l - distance between centres of the circle and circumference
       => to let circle be inside the circumference the following statement should be right:
       l + r1 <= r2 => */
    sqrt(sqr(x2 - x1) + sqr(y2 - y1)) + r1 <= r2

/**
 * Средняя (3 балла)
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean =
    a <= r && (b <= s || c <= s) || b <= r && (a <= s || c <= s) || c <= r && (a <= s || b <= s)
