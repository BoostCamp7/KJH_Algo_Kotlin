package _2023._5월_week5.BOJ2096_내려가기

/*
    https://www.acmicpc.net/problem/2096

    DP

    dp[i][j] = max or min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1]) + board[i][j]
 */

fun main() {
    val n = readln().toInt()
    val board = Array(n) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

    println(Solution().solution(n, board).joinToString(" "))
}

class Solution {
    fun solution(n: Int, board: Array<IntArray>): IntArray {
        val maxDp = IntArray(3) { board[0][it] }
        val minDp = IntArray(3) { board[0][it] }

        for (i in 1 until n) {
            val maxDpTemp = maxDp.toList()
            val minDpTemp = minDp.toList()
            for (j in 0 until 3) {
                val nColStart = if (j - 1 < 0) 0 else j - 1
                val nColEnd = if (j + 1 > 2) 2 else j + 1
                val subMaxDp = maxDpTemp.subList(nColStart, nColEnd + 1)
                val subMinDp = minDpTemp.subList(nColStart, nColEnd + 1)
                maxDp[j] = subMaxDp.maxOf { it } + board[i][j]
                minDp[j] = subMinDp.minOf { it } + board[i][j]
            }
        }

        return intArrayOf(maxDp.maxOf { it }, minDp.minOf { it })
    }
}
