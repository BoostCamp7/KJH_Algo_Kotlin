package _10월_24_to_28.BOJ1012_유기농_배추

/*
   DFS 방식
*/

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()

    repeat(t) {
        readLine()
    }

    for (i in 0 until t) {
        val (m, n, k) = readLine().trim().split(" ").map { it.toInt() }
        val land = Array(m) { IntArray(n) { 0 } }
        for (j in 0 until k) {
            val (x, y) = readLine().trim().split(" ").map { it.toInt() }
            land[x][y] = 1
        }

        println(Solution().solution(m, n, land))
    }
}

data class Position(
    val x: Int,
    val y: Int
)

class Solution {

    private var visited = arrayOf(booleanArrayOf())
    private var m = 0
    private var n = 0
    private var land = arrayOf(intArrayOf())
    private val deltaList = arrayOf(
        Position(-1, 0),
        Position(0, -1),
        Position(1, 0),
        Position(0, 1)
    )

    private fun check(pos: Position) {
        visited[pos.x][pos.y] = true

        for (delta in deltaList) {
            val checkX = pos.x + delta.x
            val checkY = pos.y + delta.y
            if (checkX in IntRange(0, m - 1) && checkY in IntRange(0, n - 1)) {
                if (visited[checkX][checkY].not() && land[checkY][checkY] == 1) {
                    check(Position(checkX, checkY))
                }
            }
        }
    }

    fun solution(m: Int, n: Int, land: Array<IntArray>): Int {
        this.m = m
        this.n = n
        this.land = land
        visited = Array(m) { BooleanArray(n) { false } }

        var result = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (visited[i][j].not() && land[i][j] == 1) {
                    check(Position(i, j))
                    result++
                }
            }
        }

        return result
    }
}
