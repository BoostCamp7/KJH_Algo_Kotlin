package _2023._9월_week3.BOJ12892_생일_선물

/*
    https://www.acmicpc.net/problem/12892

    투 포인터
 */

import kotlin.math.max

fun main() {
    val (n, d) = readln().trim().split(" ").map { it.toInt() }
    val gifts = Array(n) {
        val (p, v) = readln().trim().split(" ").map { it.toInt() }
        Gift(p, v)
    }

    println(solution(n, d, gifts))
}

data class Gift(
    val p: Int,
    val v: Int
)

fun solution(n: Int, d: Int, gifts: Array<Gift>): Long {
    var answer = 0L

    gifts.sortBy { it.p }

    var sum = 0L
    var start = 0
    var end = 0

    while (true) {
        while (end < n && gifts[end].p - gifts[start].p < d) {
            sum += gifts[end].v
            end++
        }
        answer = max(answer, sum)

        if (end == n) break

        sum -= gifts[start].v
        start++
    }

    return answer
}
