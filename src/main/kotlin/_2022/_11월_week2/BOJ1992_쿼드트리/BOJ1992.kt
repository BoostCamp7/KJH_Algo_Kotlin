package _2022._11월_week2.BOJ1992_쿼드트리

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val data = Array(n) {
        readLine()
            .trim()
            .chunked(1)
            .map { it.toInt() }
            .toIntArray()
    }

    print(Solution().solution(n, data))
}

class Solution {

    private lateinit var data: Array<IntArray>
    private var result = StringBuilder()

    fun solution(n: Int, data: Array<IntArray>): String {
        this.data = data

        compression(0, 0, n)

        return result.toString()
    }

    // set을 이용해 중복체크 할 수 있다.
    private fun check(row: Int, col: Int, n: Int): Boolean {
        val temp = data[row][col]

        for (i in row until row + n) {
            for (j in col until col + n) {
                if (data[i][j] != temp) return false
            }
        }

        return true
    }

    private fun compression(row: Int, col: Int, n: Int) {
        if (n == 1) {
            result.append(data[row][col])
            return
        }

        if (check(row, col, n)) {
            result.append(data[row][col])
        } else {
            result.append("(")
            val nextN = n / 2
            val nextRow = row + nextN
            val nextCol = col + nextN
            compression(row, col, nextN)
            compression(row, nextCol, nextN)
            compression(nextRow, col, nextN)
            compression(nextRow, nextCol, nextN)
            result.append(")")
        }
    }
}
