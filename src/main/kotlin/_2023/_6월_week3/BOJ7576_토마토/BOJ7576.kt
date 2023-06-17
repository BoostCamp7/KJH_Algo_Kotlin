package _2023._6월_week3.BOJ7576_토마토

/*
    https://www.acmicpc.net/problem/7576

    BFS
 */

import kotlin.math.max

fun main() {
    val (m, n) = readln().trim().split(" ").map { it.toInt() }
    val box = Array(n) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }
    var answer = 0

    val dy = intArrayOf(1, -1, 0, 0)
    val dx = intArrayOf(0, 0, -1, 1)
    val queue = ArrayDeque<Point>()

    for (row in 0 until n) {
        for (col in 0 until m) {
            if (box[row][col] == 1) queue.addLast(Point(row, col, 0))
        }
    }

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        answer = max(answer, current.count)

        for (i in 0 until 4) {
            val nRow = current.row + dy[i]
            val nCol = current.col + dx[i]

            if ((nRow in 0 until n).not() || (nCol in 0 until m).not()) continue
            if (box[nRow][nCol] != 0) continue

            box[nRow][nCol] = 1
            queue.addLast(Point(nRow, nCol, current.count + 1))
        }
    }

    for (row in 0 until n) {
        for (col in 0 until m) {
            if (box[row][col] == 0) {
                answer = -1
                break
            }
        }
        if (answer == -1) break
    }

    println(answer)
}

data class Point(
    val row: Int,
    val col: Int,
    val count: Int,
)
