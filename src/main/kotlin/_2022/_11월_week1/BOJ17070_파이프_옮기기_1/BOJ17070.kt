package _2022._11월_week1.BOJ17070_파이프_옮기기_1

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val house = Array(n) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    print(Solution().solution(n, house))
}

enum class Orientation {
    HORIZONTAL, DIAGONAL, VERTICAL
}

class Solution {
    private var n: Int = 0
    private lateinit var house: Array<IntArray>
    var result = 0

    private fun dfs(row: Int, col: Int, orientation: Orientation) {
        if (row == n - 1 && col == n - 1) {
            result++
            return
        }

        // 대각선
        if (row < n - 1 && col < n - 1 &&
            house[row + 1][col] == 0 && house[row][col + 1] == 0 && house[row + 1][col + 1] == 0
        ) {
            dfs(row + 1, col + 1, Orientation.DIAGONAL)
        }

        when (orientation) {
            Orientation.HORIZONTAL -> {
                if (col < n - 1 && house[row][col + 1] == 0) dfs(row, col + 1, Orientation.HORIZONTAL)
            }

            Orientation.DIAGONAL -> {
                if (col < n - 1 && house[row][col + 1] == 0) dfs(row, col + 1, Orientation.HORIZONTAL)
                if (row < n - 1 && house[row + 1][col] == 0) dfs(row + 1, col, Orientation.VERTICAL)
            }

            Orientation.VERTICAL -> {
                if (row < n - 1 && house[row + 1][col] == 0) dfs(row + 1, col, Orientation.VERTICAL)
            }
        }
    }

    fun solution(n: Int, house: Array<IntArray>): Int {
        this.n = n
        this.house = house

        dfs(0, 1, Orientation.HORIZONTAL)

        return result
    }
}
