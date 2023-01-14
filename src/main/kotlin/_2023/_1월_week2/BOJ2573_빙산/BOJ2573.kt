package _2023._1월_week2.BOJ2573_빙산

/*
    https://www.acmicpc.net/problem/2573

    BFS -> 빙산 녹이기
    DFS -> 분리 검사
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val space = List(n) {
        readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    }

    println(Solution().solution(n, m, space))
}

data class Point(
    val row: Int,
    val col: Int
)

class Solution {
    private var n = 0
    private var m = 0
    private lateinit var space: List<IntArray>
    private val dy = intArrayOf(1, -1, 0, 0)
    private val dx = intArrayOf(0, 0, 1, -1)

    fun solution(n: Int, m: Int, space: List<IntArray>): Int {
        this.n = n
        this.m = m
        this.space = space

        var year = 0

        while (true) {
            val count = isSeparated()
            if (count == 0) {
                year = 0
                break
            } else if (count >= 2) break

            val visited = List(n) {
                BooleanArray(m) { false }
            }
            val queue = ArrayDeque<Point>()

            for (row in 0 until n) {
                for (col in 0 until m) {
                    if (this.space[row][col] > 0) {
                        queue.addLast(Point(row, col))
                        visited[row][col] = true
                    }
                }
            }

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                var sum = 0

                for (i in 0 until 4) {
                    val nRow = current.row + dy[i]
                    val nCol = current.col + dx[i]

                    if (nRow in 0 until n && nCol in 0 until m) {
                        if (this.space[nRow][nCol] == 0 && visited[nRow][nCol].not()) sum++
                    }
                }

                if (this.space[current.row][current.col] < sum) this.space[current.row][current.col] = 0
                else this.space[current.row][current.col] -= sum
            }

            year++
        }

        return year
    }

    private fun isSeparated(): Int {
        val visited = List(n) {
            BooleanArray(m) { false }
        }
        var count = 0

        for (row in 0 until n) {
            for (col in 0 until m) {
                if (space[row][col] > 0 && visited[row][col].not()) {
                    dfs(row, col, visited)
                    count++
                }
            }
        }

        return count
    }

    private fun dfs(row: Int, col: Int, visited: List<BooleanArray>) {
        visited[row][col] = true

        for (i in 0 until 4) {
            val nRow = row + dy[i]
            val nCol = col + dx[i]
            if (nRow in 0 until n && nCol in 0 until m) {
                if (space[nRow][nCol] > 0 && visited[nRow][nCol].not()) {
                    dfs(nRow, nCol, visited)
                }
            }
        }
    }
}
