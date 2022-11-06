package _11월_week1.BOJ5557_1학년

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val numbers = readLine().trim().split(" ").map { it.toInt() }

    print(Solution().solution(n, numbers))
}

class Solution {
    fun solution(n: Int, numbers: List<Int>): Long {
        val dpTable = Array(n) { LongArray(21) { 0L } }

        dpTable[0][numbers[0]] = 1L

        for (i in 1..n - 2) {
            for (j in 0..20) {
                if (dpTable[i - 1][j] > 0) {
                    if (j + numbers[i] <= 20) dpTable[i][j + numbers[i]] += dpTable[i - 1][j]
                    if (j - numbers[i] >= 0) dpTable[i][j - numbers[i]] += dpTable[i - 1][j]
                }
            }
        }

        return dpTable[n - 2][numbers[n - 1]]
    }
}
