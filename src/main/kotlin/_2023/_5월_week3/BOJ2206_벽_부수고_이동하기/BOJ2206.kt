package _2023._5월_week3.BOJ2206_벽_부수고_이동하기

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val map = Array(n) { readln().trim().chunked(1).map { it.toInt() }.toIntArray() }

    println(Solution().solution(n, m, map))
}

data class Result(
    val row: Int,
    val col: Int,
    val breakCount: Int,
)

class Solution {
    private val dy = intArrayOf(0, -1, 0, 1)
    private val dx = intArrayOf(1, 0, -1, 0)

    fun solution(n: Int, m: Int, map: Array<IntArray>): Int {
        val queue = ArrayDeque<Result>()
        val visited = Array(n) { IntArray(m) { -1 } }

        queue.addLast(Result(0, 0, 0))
        visited[0][0] = 1

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            println(current)

            for (i in 0 until 4) {
                val nRow = current.row + dy[i]
                val nCol = current.col + dx[i]

                if ((nRow in 0 until n).not() || (nCol in 0 until m).not()) continue
                if (visited[nRow][nCol] < current.breakCount + 1) continue

                if (map[nRow][nCol] == 1) {
                    if (current.breakCount == 1) continue
                    visited[nRow][nCol] = visited[current.row][current.col] + 1
                    queue.addLast(Result(nRow, nCol, current.breakCount + 1))
                } else {
                    visited[nRow][nCol] = visited[current.row][current.col] + 1
                    queue.addLast(Result(nRow, nCol, current.breakCount))
                }
            }
        }

        return visited[n - 1][m - 1]
    }
}
