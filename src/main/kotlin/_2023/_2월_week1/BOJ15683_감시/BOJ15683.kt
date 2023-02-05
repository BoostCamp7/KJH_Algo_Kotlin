package _2023._2월_week1.BOJ15683_감시

/*
    https://www.acmicpc.net/problem/15683

    DFS
    완전탐색

    Deep Copy 시 Array 내부 IntArray도 따로 clone 해주어야 했음
 */

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val office = Array(n) {
        readln().trim().split(" ").map { it.toInt() }.toIntArray()
    }

    println(Solution(n, m, office).solution())
}

data class CCTV(
    val type: Int,
    val row: Int,
    val col: Int
)

class Solution(
    private val n: Int,
    private val m: Int,
    private val office: Array<IntArray>
) {
    // 상, 하, 좌, 우 순서
    private val dy = intArrayOf(-1, 1, 0, 0)
    private val dx = intArrayOf(0, 0, -1, 1)
    private val directions = arrayOf(
        arrayOf(),
        arrayOf(intArrayOf(0), intArrayOf(1), intArrayOf(2), intArrayOf(3)), // 상, 하, 좌, 우
        arrayOf(intArrayOf(0, 1), intArrayOf(2, 3)), // 상하, 좌우
        arrayOf(intArrayOf(0, 3), intArrayOf(1, 3), intArrayOf(0, 2), intArrayOf(1, 2)), // 우상, 우하, 좌상, 좌하
        arrayOf(
            intArrayOf(0, 2, 3),
            intArrayOf(0, 1, 3),
            intArrayOf(1, 2, 3),
            intArrayOf(0, 1, 2)
        ), // 상좌우, 우상하, 하좌우, 좌상하
        arrayOf(intArrayOf(0, 1, 2, 3)) // 상하좌우
    )

    private val cctvArray = ArrayList<CCTV>()
    private var result = Int.MAX_VALUE
    private var cctvCount = 0

    fun solution(): Int {
        for (row in 0 until n) {
            for (col in 0 until m) {
                if (office[row][col] in 1..5) {
                    cctvArray.add(
                        CCTV(office[row][col], row, col)
                    )
                }
            }
        }

        cctvCount = cctvArray.size
        dfs(office, 0)

        return result
    }

    private fun dfs(office: Array<IntArray>, index: Int) {
        if (index == cctvCount) {
            val sum = office.sumOf { colArray -> colArray.count { it == 0 } }
            if (result > sum) result = sum
            return
        }

        val current = cctvArray[index]

        for (direction in directions[current.type]) {
            val tmp = office.clone().map { it.clone() }.toTypedArray()
            for (i in direction) {
                check(tmp, current.row, current.col, i)
            }
            dfs(tmp, index + 1)
        }
    }

    private fun check(office: Array<IntArray>, row: Int, col: Int, direction: Int) {
        var nRow = row
        var nCol = col
        while (true) {
            nRow += dy[direction]
            nCol += dx[direction]

            if ((nRow in 0 until n).not() || (nCol in 0 until m).not() || office[nRow][nCol] == 6) break
            if (office[nRow][nCol] == 0) {
                office[nRow][nCol] = -1
            }
        }
    }
}

/*
private fun dfs(office: Array<IntArray>, index: Int) {
    if (index == cctvCount) {
        val sum = office.sumOf { colArray -> colArray.count { it == 0 } }
        if (result > sum) result = sum
        return
    }

    val current = cctvArray[index]

    when (current.type) {
        1 -> { // 상, 하, 좌, 우
            directions[1]

            val tmpUp = office.clone().map { it.clone() }.toTypedArray()
            check(tmpUp, current.row, current.col, 0)
            dfs(tmpUp, index + 1)

            val tmpDown = office.clone().map { it.clone() }.toTypedArray()
            check(tmpDown, current.row, current.col, 1)
            dfs(tmpDown, index + 1)

            val tmpLeft = office.clone().map { it.clone() }.toTypedArray()
            check(tmpLeft, current.row, current.col, 2)
            dfs(tmpLeft, index + 1)

            val tmpRight = office.clone().map { it.clone() }.toTypedArray()
            check(tmpRight, current.row, current.col, 3)
            dfs(tmpRight, index + 1)
        }
        2 -> { // 상하, 좌우
            val tmpUpDown = office.clone().map { it.clone() }.toTypedArray()
            check(tmpUpDown, current.row, current.col, 0)
            check(tmpUpDown, current.row, current.col, 1)
            dfs(tmpUpDown, index + 1)

            val tmpLeftRight = office.clone().map { it.clone() }.toTypedArray()
            check(tmpLeftRight, current.row, current.col, 2)
            check(tmpLeftRight, current.row, current.col, 3)
            dfs(tmpLeftRight, index + 1)
        }
        3 -> { // 우상, 우하, 좌상, 좌하
            val tmpUpRight = office.clone().map { it.clone() }.toTypedArray()
            check(tmpUpRight, current.row, current.col, 0)
            check(tmpUpRight, current.row, current.col, 3)
            dfs(tmpUpRight, index + 1)

            val tmpDownRight = office.clone().map { it.clone() }.toTypedArray()
            check(tmpDownRight, current.row, current.col, 1)
            check(tmpDownRight, current.row, current.col, 3)
            dfs(tmpDownRight, index + 1)

            val tmpUpLeft = office.clone().map { it.clone() }.toTypedArray()
            check(tmpUpLeft, current.row, current.col, 0)
            check(tmpUpLeft, current.row, current.col, 2)
            dfs(tmpUpLeft, index + 1)

            val tmpDownLeft = office.clone().map { it.clone() }.toTypedArray()
            check(tmpDownLeft, current.row, current.col, 1)
            check(tmpDownLeft, current.row, current.col, 2)
            dfs(tmpDownLeft, index + 1)
        }
        4 -> { // 상좌우, 우상하, 하좌우, 좌상하
            val tmpULR = office.clone().map { it.clone() }.toTypedArray()
            check(tmpULR, current.row, current.col, 0)
            check(tmpULR, current.row, current.col, 2)
            check(tmpULR, current.row, current.col, 3)
            dfs(tmpULR, index + 1)

            val tmpUDR = office.clone().map { it.clone() }.toTypedArray()
            check(tmpUDR, current.row, current.col, 0)
            check(tmpUDR, current.row, current.col, 1)
            check(tmpUDR, current.row, current.col, 3)
            dfs(tmpUDR, index + 1)

            val tmpDLR = office.clone().map { it.clone() }.toTypedArray()
            check(tmpDLR, current.row, current.col, 1)
            check(tmpDLR, current.row, current.col, 2)
            check(tmpDLR, current.row, current.col, 3)
            dfs(tmpDLR, index + 1)

            val tmpUDL = office.clone().map { it.clone() }.toTypedArray()
            check(tmpUDL, current.row, current.col, 0)
            check(tmpUDL, current.row, current.col, 1)
            check(tmpUDL, current.row, current.col, 2)
            dfs(tmpUDL, index + 1)
        }
        5 -> { // 상하좌우
            val tmpUDLR = office.clone().map { it.clone() }.toTypedArray()
            check(tmpUDLR, current.row, current.col, 0)
            check(tmpUDLR, current.row, current.col, 1)
            check(tmpUDLR, current.row, current.col, 2)
            check(tmpUDLR, current.row, current.col, 3)
            dfs(tmpUDLR, index + 1)
        }
    }
}*/
