package _2023._2월_week3.BOJ4095_최대_정사각형

fun main() {
    while (true) {
        val (n, m) = readln().trim().split(" ").map { it.toInt() }
        if (n + m == 0) break
        val matrix = Array(n) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

        println(Solution().solution(n, m, matrix))
    }
}

class Solution {
    fun solution(n: Int, m: Int, matrix: Array<IntArray>): Int {
        val dp = Array(n) { row -> IntArray(m) { col -> matrix[row][col] } }

        for (i in 1 until n) {
            for (j in 1 until m) {
                if (dp[i][j] == 1) dp[i][j] += minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
            }
        }

        return dp.maxOf { intArray -> intArray.maxOf { it } }
    }
}
