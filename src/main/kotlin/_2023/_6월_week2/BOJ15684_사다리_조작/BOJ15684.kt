package _2023._6월_week2.BOJ15684_사다리_조작

/*
    https://www.acmicpc.net/problem/15684

    DFS, 백트래킹
    완전탐색
 */

import kotlin.math.min

fun main() {
    val (n, m, h) = readln().trim().split(" ").map { it.toInt() }
    val lines = Array(m) {
        val (a, b) = readln().trim().split(" ").map { it.toInt() - 1 }
        Line(a, b)
    }

    println(Solution(n, m, h, lines).solution())
}

data class Line(
    val position: Int,
    val col: Int,
)

class Solution(
    private val n: Int,
    private val m: Int,
    private val h: Int,
    private val lines: Array<Line>,
) {
    private val visited = Array(h) { BooleanArray(n) { false } }
    private var answer = Int.MAX_VALUE

    fun solution(): Int {
        for (line in lines) {
            visited[line.position][line.col] = true
        }

        dfs(0, 0)

        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(index: Int, count: Int) {
        if (count > 3) return
        if (startGame()) {
            answer = min(answer, count)
            return
        }

        for (i in index until h) {
            for (j in 0 until n - 1) {
                if (visited[i][j] || (j > 0 && visited[i][j - 1]) || visited[i][j + 1]) continue

                visited[i][j] = true
                dfs(i, count + 1)
                visited[i][j] = false
            }
        }
    }

    private fun startGame(): Boolean {
        for (i in 0 until n) {
            var index = i
            var row = 0

            while (row < h) {
                if (visited[row][index]) {
                    index++
                } else if (index > 0 && visited[row][index - 1]) {
                    index--
                }
                row++
            }

            if (index != i) return false
        }

        return true
    }
}
