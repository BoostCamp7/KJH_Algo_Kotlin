package _2023._1월_week2.BOJ3020_개똥벌레

/*
    https://www.acmicpc.net/problem/3020

    2중 for 문 -> 시간초과 O(N^2)
    -> O(N) 으로 맞추기 -> 누적합
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (n, h) = readLine().trim().split(" ").map { it.toInt() }
    val obstacles = IntArray(n) { readLine().toInt() }

    println(Solution().solution(n, h, obstacles).joinToString(" "))
}

class Solution {
    fun solution(n: Int, h: Int, obstacles: IntArray): IntArray {
        val floor = IntArray(h + 1) { 0 }
        val ceiling = IntArray(h + 1) { 0 }

        for (i in 0 until n) {
            if (i % 2 == 0) {
                floor[obstacles[i]] += 1
            } else {
                ceiling[obstacles[i]] += 1
            }
        }

        // 누적합
        for (i in h - 1 downTo 1) {
            floor[i] += floor[i + 1]
            ceiling[i] += ceiling[i + 1]
        }

        var min = n
        var count = 0

        for (i in 1..h) {
            val current = floor[i] + ceiling[h - i + 1]
            if (current < min) {
                min = current
                count = 1
            } else if (current == min) {
                count++
            }
        }

        return intArrayOf(min, count)
    }
}
