package _2023._3월_week5.BOJ17144_미세먼지_안녕

/*
    https://www.acmicpc.net/problem/17144

    구현, 시뮬
 */

fun main() {
    val (r, c, t) = readln().trim().split(" ").map { it.toInt() }
    val space = Array(r) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

    println(Solution(r, c, t, space).solution())
}

data class Point(
    val row: Int,
    val col: Int
)

class Solution(private val r: Int, private val c: Int, private val t: Int, private val space: Array<IntArray>) {
    private val dy = intArrayOf(1, -1, 0, 0)
    private val dx = intArrayOf(0, 0, 1, -1)
    private val airCleaner = ArrayList<Point>()

    fun solution(): Int {
        var answer = space

        for (i in 0 until r) {
            for (j in 0 until c) {
                if (space[i][j] == -1) airCleaner.add(Point(i, j))
            }
        }

        repeat(t) {
            answer = diffuseDust(answer)
            answer = activateAirCleaner(answer)
        }

        return answer.sumOf { col -> col.sumOf { it } } + 2
    }

    private fun diffuseDust(space: Array<IntArray>): Array<IntArray> {
        val temp = Array(r) { IntArray(c) { 0 } }

        for (row in 0 until r) {
            for (col in 0 until c) {
                if (space[row][col] == -1) {
                    temp[row][col] = -1
                    continue
                }
                if (space[row][col] == 0) continue

                temp[row][col] += space[row][col]

                for (i in 0 until 4) {
                    val nRow = row + dy[i]
                    val nCol = col + dx[i]

                    if ((nRow in 0 until r).not() ||
                        (nCol in 0 until c).not() ||
                        space[nRow][nCol] == -1
                    ) continue

                    temp[nRow][nCol] += space[row][col] / 5
                    temp[row][col] -= space[row][col] / 5
                }
            }
        }

        return temp
    }

    private fun activateAirCleaner(space: Array<IntArray>): Array<IntArray> {
        val temp = space.map { it.toList().toIntArray() }.toTypedArray()
        val top = airCleaner[0]
        val bottom = airCleaner[1]

        temp[top.row][1] = 0
        temp[bottom.row][1] = 0

        for (col in 2 until c) {
            temp[top.row][col] = space[top.row][col - 1]
            temp[bottom.row][col] = space[bottom.row][col - 1]
        }

        for (col in c - 2 downTo 0) {
            temp[0][col] = space[0][col + 1]
            temp[r - 1][col] = space[r - 1][col + 1]
        }

        for (row in top.row - 1 downTo 0) {
            temp[row][c - 1] = space[row + 1][c - 1]
        }
        for (row in 1 until top.row) {
            temp[row][0] = space[row - 1][0]
        }

        for (row in bottom.row + 1 until r) {
            temp[row][c - 1] = space[row - 1][c - 1]
        }
        for (row in r - 2 downTo bottom.row + 1) {
            temp[row][0] = space[row + 1][0]
        }

        return temp
    }
}
