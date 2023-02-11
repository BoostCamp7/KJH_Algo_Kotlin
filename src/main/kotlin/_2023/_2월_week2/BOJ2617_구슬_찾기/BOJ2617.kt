package _2023._2월_week2.BOJ2617_구슬_찾기

/*
    https://www.acmicpc.net/problem/2617

    플로이드-워셜

    (n / 2) + 1 개 보다 멀리있는 구슬이 있는 경우
 */

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val sequences = Array(m) {
        val (heavy, light) = readln().trim().split(" ").map { it.toInt() }
        Sequence(heavy, light)
    }

    println(Solution().solution(n, m, sequences))
}

data class Sequence(
    val heavy: Int,
    val light: Int
)

class Solution {
    fun solution(n: Int, m: Int, sequences: Array<Sequence>): Int {
        val graph = Array(n + 1) { IntArray(n + 1) { MAX_NUM } }
        val lightCount = IntArray(n + 1) { 0 }
        val heavyCount = IntArray(n + 1) { 0 }
        val center = n / 2 + 1
        var result = 0

        for (sequence in sequences) {
            graph[sequence.heavy][sequence.light] = 1
        }

        for (mid in 1..n) {
            for (start in 1..n) {
                for (end in 1..n) {
                    graph[start][end] = minOf(graph[start][end], graph[start][mid] + graph[mid][end])
                }
            }
        }

        for (i in 1..n) {
            for (j in 1..n) {
                if (graph[i][j] == MAX_NUM) continue
                lightCount[i]++
                heavyCount[j]++
            }
        }

        for (i in 1..n) {
            if (lightCount[i] >= center || heavyCount[i] >= center) result++
        }

        return result
    }

    companion object {
        private const val MAX_NUM = 100
    }
}
