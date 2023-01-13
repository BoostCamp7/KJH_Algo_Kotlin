package _2022._10월_3_to_7.BOJ1406_에디터

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val string = readLine()
    val n = readLine().toInt()

    val result = LinkedList<Char>()
    string.forEach {
        result.add(it)
    }

    val iterator = result.listIterator(result.size)

    for (i in 0 until n) {
        val str = readLine()
        when (str[0]) {
            'L' -> if (iterator.hasPrevious()) iterator.previous()
            'D' -> if (iterator.hasNext()) iterator.next()
            'B' -> if (iterator.hasPrevious()) {
                iterator.previous()
                iterator.remove()
            }
            'P' -> iterator.add(str[2])
        }
    }
    print(result.joinToString(""))
}