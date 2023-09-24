package _2023._9월_week3.BOJ1027_고층_건물

/*
    https://www.acmicpc.net/problem/1027

    완전탐색
 */

import kotlin.math.max

fun main() {
    val n = readln().toInt()
    val buildings = readln().trim().split(" ").map { it.toInt() }.toIntArray()

    println(solution(n, buildings))
}

fun solution(n: Int, buildings: IntArray): Int {
    var answer = 0

    fun checkLeft(index: Int): Int {
        if (index == 0) return 0

        var count = 0
        var last = 0.0
        for (k in index - 1 downTo 0) {
            val current = (buildings[index] - buildings[k]) / (index - k).toDouble()
            if (last > current || k == index - 1) {
                count++
                last = current
            }
        }
        return count
    }

    fun checkRight(index: Int): Int {
        if (index == n - 1) return 0

        var count = 0
        var last = 0.0

        for (k in index + 1 until n) {
            val current = (buildings[index] - buildings[k]) / (index - k).toDouble()
            if (last < current || k == index + 1) {
                count++
                last = current
            }
        }
        return count
    }

    for (i in 0 until n) {
        answer = max(answer, checkLeft(i) + checkRight(i))
    }

    return answer
}
