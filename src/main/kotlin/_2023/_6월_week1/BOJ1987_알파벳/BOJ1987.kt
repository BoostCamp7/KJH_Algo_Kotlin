package _2023._6월_week1.BOJ1987_알파벳

/*
    https://www.acmicpc.net/problem/1987

    DFS
    백트래킹
 */

import kotlin.math.max

fun main() {
    val (r, c) = readln().trim().split(" ").map { it.toInt() }
    val board = Array(r) { readln().toCharArray() }

    println(Solution(r, c, board).solution())
}

class Solution(private val r: Int, private val c: Int, private val board: Array<CharArray>) {

    private val dy = intArrayOf(1, -1, 0, 0)
    private val dx = intArrayOf(0, 0, -1, 1)
    private val visited = BooleanArray(26) { false }
    private var maxCount = 0

    fun solution(): Int {
        dfs(0, 0, 1)

        return maxCount
    }

    private fun dfs(row: Int, col: Int, count: Int) {
        val current = board[row][col].code - 65
        visited[current] = true

        for (i in 0 until 4) {
            val nRow = row + dy[i]
            val nCol = col + dx[i]

            if ((nRow in 0 until r).not() || (nCol in 0 until c).not()) continue

            val next = board[nRow][nCol].code - 65

            if (visited[next]) {
                maxCount = max(maxCount, count)
            } else {
                dfs(nRow, nCol, count + 1)
            }
        }

        visited[current] = false
    }
}
