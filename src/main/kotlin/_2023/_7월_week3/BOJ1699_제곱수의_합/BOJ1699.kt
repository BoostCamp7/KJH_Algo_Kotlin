package _2023._7월_week3.BOJ1699_제곱수의_합

/*
    https://www.acmicpc.net/problem/1699

    DP

    1, 2, 4, 9, 16... 등 i^2 는 무조건 1
    -> x - i^2 에서 i를 증가시키며 다 반복해보면서 최소값 찾기
 */

import kotlin.math.min

fun main() {
    val n = readln().toInt()

    println(solution(n))
}

fun solution(n: Int): Int {
    val dp = IntArray(n + 1) { it }

    for (i in 2..n) {
        var j = 1
        while (j * j <= i) {
            dp[i] = min(dp[i], dp[i - (j * j)] + 1)
            j++
        }
    }

    return dp[n]
}
