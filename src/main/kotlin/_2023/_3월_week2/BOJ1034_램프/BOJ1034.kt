package _2023._3월_week2.BOJ1034_램프

/*
    https://www.acmicpc.net/problem/1034

    풀이 해석 : https://gona.tistory.com/31

    k값에 따라 모든 경우의 수를 확인할 필요 없이 k가 짝수냐, 홀수냐에 따라 답을 구함
 */

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val table = Array(n) { readln().trim().chunked(1).map { it.toInt() }.toIntArray() }
    val k = readln().toInt()

    println(Solution().solution(n, m, table, k))
}

class Solution() {
    private var answer = 0

    fun solution(n: Int, m: Int, table: Array<IntArray>, k: Int): Int {
        for (row in 0 until n) {
            var zeroCount = 0

            for (col in 0 until m) {
                if (table[row][col] == 0) zeroCount++
            }

            var result = 0

            if (zeroCount <= k && (zeroCount % 2) == (k % 2)) {
                for (i in 0 until n) {
                    if (table[row].contentEquals(table[i])) result++
                }
            }

            if (result > answer) answer = result
        }

        return answer
    }
}
