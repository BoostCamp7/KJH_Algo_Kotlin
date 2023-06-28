package _2023._6월_week4.BOJ17218_비밀번호_만들기

/*
    https://www.acmicpc.net/problem/17218

    DP
    최장 공통 문자열(LCS: Longest Common Substring)
 */

import kotlin.math.max

fun main() {
    val str1 = readln()
    val str2 = readln()

    solution(str1, str2)
}

fun solution(str1: String, str2: String) {
    val answer = ArrayDeque<Char>()
    val n = str1.length
    val m = str2.length
    val dp = Array(n + 1) { IntArray(m + 1) { 0 } }

    for (i in 1..n) {
        for (j in 1..m) {
            if (str1[i - 1] == str2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    var count = dp[n][m]
    var i = n
    var j = m

    while (count > 0) {
        if (dp[i - 1][j] == dp[i][j]) {
            i--
        } else if (dp[i][j - 1] == dp[i][j]) {
            j--
        } else {
            count--
            i--
            j--
            answer.addFirst(str1[i])
        }
    }

    println(answer.joinToString(""))
}
