package _2023._2월_week1.BOJ1405_미친_로봇

/*
    https://www.acmicpc.net/problem/1405

    DFS
    완전탐색

    N은 14보다 작거나 같은 자연수
    그래프 크기 = 29 * 29, 기준점 (15, 15)
 */

fun main() {
    val (n, east, west, south, north) = readln().trim().split(" ").map { it.toInt() }
    val percents = intArrayOf(east, west, south, north).map { it * 0.01 }.toDoubleArray()

    println(Solution(n, percents).solution())
}

class Solution(
    private val n: Int,
    private val percents: DoubleArray
) {
    // 동, 서, 남, 북 순서
    private val dy = intArrayOf(0, 0, 1, -1)
    private val dx = intArrayOf(1, -1, 0, 0)

    private val visited = Array(30) { BooleanArray(30) { false } }
    private var result: Double = 0.0

    fun solution(): Double {
        visited[15][15] = true
        dfs(15, 15, 0, 1.0)

        return result
    }

    fun dfs(row: Int, col: Int, index: Int, percent: Double) {
        if (percent == 0.0) {
            return
        }

        if (index == n) {
            result += percent
            return
        }

        for (i in 0 until 4) {
            val nRow = row + dy[i]
            val nCol = col + dx[i]

            if (nRow in 0 until 30 && nCol in 0 until 30) {
                if (visited[nRow][nCol].not()) {
                    visited[nRow][nCol] = true
                    dfs(nRow, nCol, index + 1, percent * percents[i])
                    visited[nRow][nCol] = false
                }
            }
        }
    }
}
