package _2023._10월_week4.BOJ26075_곰곰아_선_넘지마

/*
    https://www.acmicpc.net/problem/26075

    Greedy

    Long 범위 확인하기
 */

import kotlin.math.abs

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val s = readln()
    val t = readln()

    println(solution(n, m, s, t))
}

fun solution(n: Int, m: Int, s: String, t: String): Long {
    var answer = 0L
    val sIndexes = ArrayDeque<Int>()
    val tIndexes = ArrayDeque<Int>()

    for (i in 0 until n + m) {
        if (s[i] == '1') sIndexes.addLast(i)
        if (t[i] == '1') tIndexes.addLast(i)
    }

    repeat(m) {
        answer += abs(sIndexes.removeFirst() - tIndexes.removeFirst())
    }

    val half = answer / 2L

    return if (answer % 2L == 0L) {
        (half * half) * 2L
    } else {
        (half * half) + ((half + 1L) * (half + 1L))
    }
}
