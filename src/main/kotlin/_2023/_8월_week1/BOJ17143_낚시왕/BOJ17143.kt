package _2023._8월_week1.BOJ17143_낚시왕

/*
    https://www.acmicpc.net/problem/17143

    구현
 */

fun main() {
    val (row, col, m) = readln().trim().split(" ").map { it.toInt() }
    val sharks = Array(m) {
        val (r, c, s, d, z) = readln().trim().split(" ").map { it.toInt() }
        Shark(r, c, s, d, z)
    }

    println(Solution(row, col, m, sharks).solution())
}

data class Shark(
    val r: Int,
    val c: Int,
    val s: Int,
    val d: Int,
    val z: Int
)

class Solution(
    private val row: Int,
    private val col: Int,
    private val m: Int,
    private val sharks: Array<Shark>
) {
    private val map = Array(row + 1) { IntArray(col + 1) { -1 } }
    private var answer = 0

    fun solution(): Int {
        for (i in 0 until m) {
            map[sharks[i].r][sharks[i].c] = i
        }

        for (i in 1 until col) {
            fishing(i)
            moveSharks()
        }
        fishing(col)

        return answer
    }

    private fun fishing(col: Int) {
        for (i in 1..row) {
            if (map[i][col] < 0) continue

            answer += sharks[map[i][col]].z
            map[i][col] = -1
            break
        }
    }

    private fun moveSharks() {
        val tempMap = Array(row + 1) { IntArray(col + 1) { -1 } }

        for (i in 1..row) {
            for (j in 1..col) {
                move(i, j, tempMap)
            }
        }

        tempMap.copyInto(map)
    }

    private fun move(r: Int, c: Int, temp: Array<IntArray>) {
        val sharkIdx = map[r][c]
        if (sharkIdx == -1) return

        val shark = sharks[sharkIdx]

        var direction = shark.d
        var nRow = r
        var nCol = c

        for (i in 0 until shark.s) {
            when (direction) {
                1 -> {
                    nRow--
                    if (nRow < 1) {
                        direction = 2
                        nRow = 2
                    }
                }
                2 -> {
                    nRow++
                    if (nRow > row) {
                        direction = 1
                        nRow = row - 1
                    }
                }
                3 -> {
                    nCol++
                    if (nCol > col) {
                        direction = 4
                        nCol = col - 1
                    }
                }
                4 -> {
                    nCol--
                    if (nCol < 1) {
                        direction = 3
                        nCol = 2
                    }
                }
            }
        }

        if (temp[nRow][nCol] >= 0 && sharks[temp[nRow][nCol]].z > shark.z) return

        sharks[sharkIdx] = Shark(nRow, nCol, shark.s, direction, shark.z)
        temp[nRow][nCol] = sharkIdx
    }
}
