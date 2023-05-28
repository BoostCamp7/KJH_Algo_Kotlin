package _2023._5월_week3.BOJ4179_불

fun main() {
    val (r, c) = readln().trim().split(" ").map { it.toInt() }
    val maze = Array(r) { readln().trim().toCharArray() }

    println(Solution().solution(r, c, maze))
}

data class Point(
    val row: Int,
    val col: Int,
)

data class Result(
    val point: Point,
    val count: Int,
)

class Solution {
    private val dy = intArrayOf(0, -1, 0, 1)
    private val dx = intArrayOf(1, 0, -1, 0)

    fun solution(r: Int, c: Int, maze: Array<CharArray>): String {
        val jihunQueue = ArrayDeque<Result>()
        val fireQueue = ArrayDeque<Result>()
        val jihunVisited = Array(r) { BooleanArray(c) { false } }
        val fireVisited = Array(r) { IntArray(c) { -1 } }

        for (i in 0 until r) {
            for (j in 0 until c) {
                if (maze[i][j] == 'J') {
                    jihunQueue.addLast(Result(Point(i, j), 0))
                    jihunVisited[i][j] = true
                } else if (maze[i][j] == 'F') {
                    fireQueue.addLast(Result(Point(i, j), 0))
                    fireVisited[i][j] = 0
                }
            }
        }

        while (fireQueue.isNotEmpty()) {
            val current = fireQueue.removeFirst()

            for (f in 0 until 4) {
                val nFRow = current.point.row + dy[f]
                val nFCol = current.point.col + dx[f]

                if ((nFRow in 0 until r).not() || (nFCol in 0 until c).not()) continue
                if (maze[nFRow][nFCol] == '#' || fireVisited[nFRow][nFCol] >= 0) continue

                fireVisited[nFRow][nFCol] = current.count + 1
                fireQueue.addLast(Result(Point(nFRow, nFCol), current.count + 1))
            }
        }

        while (jihunQueue.isNotEmpty()) {
            val current = jihunQueue.removeFirst()

            for (j in 0 until 4) {
                val nJRow = current.point.row + dy[j]
                val nJCol = current.point.col + dx[j]

                if ((nJRow in 0 until r).not() || (nJCol in 0 until c).not()) return (current.count + 1).toString()
                if (maze[nJRow][nJCol] == '#' ||
                    jihunVisited[nJRow][nJCol] ||
                    (fireVisited[nJRow][nJCol] == -1 || fireVisited[nJRow][nJCol] > current.count + 1).not()
                ) {
                    continue
                }

                jihunVisited[nJRow][nJCol] = true
                jihunQueue.addLast(Result(Point(nJRow, nJCol), current.count + 1))
            }
        }

        return "IMPOSSIBLE"
    }
}
