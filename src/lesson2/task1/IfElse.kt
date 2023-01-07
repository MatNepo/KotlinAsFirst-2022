@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = when {
    age <= 0 || age >= 200 -> "$age некорректный возраст" // p.s., the oldest person was abt 122 y.o. ,that's why ... || age > 122 ...
    (age % 10 == 1) && (age % 100 != 11) -> "$age год"
    (age % 10 == 2 || age % 10 == 3 || age % 10 == 4) && (age % 100 != 12) -> "$age года"
    else -> "$age лет"
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double = when {
    /* 1) |_____s1_____|_s2_|_s3_|
                     |
          |_________l/2__________|
     */
    ((t1 * v1) /* = s1 */ >=
            (t1 * v1 + t2 * v2 + t3 * v3) / 2) /* l/2 = (s1 + s2 + s3) / 2 */ ->
        ((t1 * v1 + t2 * v2 + t3 * v3) / 2) / v1 // tHalf_1 = l/2 / v1

    /* 2) |__s1__|__s2__|___s3___|
                      |
          |__________l/2_________|
     */
    (t1 * v1) /* = s1 */ < (t1 * v1 + t2 * v2 + t3 * v3) / 2 /* = l/2 */ &&
            (t3 * v3) /* = s3 */ < (t1 * v1 + t2 * v2 + t3 * v3) / 2 /* = l/2 */ ->
        t1 + ((((t1 * v1 + t2 * v2 + t3 * v3) / 2) /* = l/2 */ - (t1 * v1) /* = s1 */) / v2 /* = tHalf_2 */) // = t1 + tHalf_2

    /* 3) |_s1_|_s2_|_____s3_____|
                      |
          |__________l/2_________|
     */
    (t3 * v3) < (t1 * v1 + t2 * v2 + t3 * v3) / 2 -> t1 + t2 + ((((t1 * v1 + t2 * v2 + t3 * v3) / 2) - (t1 * v1) - (t2 * v2)) / v3)
    else -> Double.NaN
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int = when {
    (kingX != rookX1 && kingY != rookY1) && (kingX != rookX2 && kingY != rookY2) -> 0
    (kingX == rookX1 || kingY == rookY1) && (kingX != rookX2 && kingY != rookY2) -> 1
    // to this moment all situations with (king_ != rook_2) 've seen, so it's not imp 2 include this statement below
    (kingX != rookX1 && kingY != rookY1) -> 2
    else -> 3
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int = when {
    (kingX != rookX && kingY != rookY) && (abs(bishopX - kingX) != abs(bishopY - kingY)) -> 0
    (kingX == rookX || kingY == rookY) && (abs(bishopX - kingX) != abs(bishopY - kingY)) -> 1
    (kingX != rookX && kingY != rookY) -> 2
    else -> 3
}
/*
   1 2 3 4 5 6 7 8
  +---R----------  1
    \ |            2
      K            3
      | \          4
      |   \        5
      |     \      6
      |       S    7
      |         \  8
 */

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int = when {
    // 1) sum of 2 sides always > then the 3rd one, otherwise -> doesn't exist
    (a <= 0 || b <= 0 || c <= 0) || (a + b < c) || (a + c < b) || (b + c < a) -> -1
    // 2) Pythagorean theorem
    sqr(a) + sqr(b) == sqr(c) || sqr(c) + sqr(b) == sqr(a) || sqr(a) + sqr(c) == sqr(b) -> 1
    // 3) the square of the biggest side > the sum of the squares of the other 2 sides
    // *if c is the biggest one -> c^2 > a^2 + b^2
    sqr(a) > sqr(b) + sqr(c) || sqr(b) > sqr(a) + sqr(c) || sqr(c) > sqr(a) + sqr(b) -> 2
    else -> 0
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    /* 1) a____b         |          a____b
                 c____d  |  c____d          */
    b < c || d < a -> -1

    /* 2) a____b     |     a____b
             c____d  |  c____d     */
    c in a..b && d > b -> b - c
    a in c..d && b > d -> d - a

    /* 3) a______b  |    a__b
            c__d    |  c______d    */
    c > a && d < b -> d - c
    a > c && b < d -> b - a

    else -> -1
}
