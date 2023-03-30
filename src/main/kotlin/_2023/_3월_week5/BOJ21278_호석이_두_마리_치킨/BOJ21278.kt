package _2023._3월_week5.BOJ21278_호석이_두_마리_치킨

/*
    https://www.acmicpc.net/problem/21278

    Graph
    플로이드 워셜

    O(N^3)
    N이 작기 때문에 플로이드 워셜로도 가능
    최대 100^3
 */

import kotlin.math.min

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val roads = Array(m) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

    println(Solution(n, m, roads).solution())
}

data class Result(
    val A: Int,
    val B: Int,
    val sum: Int
) : Comparable<Result> {
    override fun toString(): String = "$A $B $sum"

    override fun compareTo(other: Result): Int {
        return if (sum != other.sum) sum.compareTo(other.sum)
        else if (A != other.A) A.compareTo(other.A)
        else B.compareTo(other.B)
    }
}

class Solution(private val n: Int, private val m: Int, private val roads: Array<IntArray>) {
    private val graph = Array(n + 1) { IntArray(n + 1) { n } }

    fun solution(): Result {
        var result = Result(0, 0, Int.MAX_VALUE)

        for (road in roads) {
            graph[road[0]][road[1]] = 1
            graph[road[1]][road[0]] = 1
        }

        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    if (i == j) {
                        graph[i][j] = 0
                        continue
                    }
                    graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])
                }
            }
        }

        for (i in 1 until n) {
            for (j in i + 1..n) {
                var sum = 0

                for (dest in 1..n) {
                    sum += min(graph[i][dest], graph[j][dest]) * 2
                }

                val currentResult = Result(i, j, sum)
                if (currentResult.sum < result.sum) result = currentResult
            }
        }

        return result
    }
}
