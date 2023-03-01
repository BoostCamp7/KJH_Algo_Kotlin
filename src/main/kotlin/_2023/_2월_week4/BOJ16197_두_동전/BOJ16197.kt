package _2023._2월_week4.BOJ16197_두_동전

/*
    https://www.acmicpc.net/problem/16197

    BFS
 */

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val board = Array(n) { readln().toCharArray() }

    println(Solution(n, m, board).solution())
}

data class Coin(
    val row: Int,
    val col: Int
)

class Solution(private val n: Int, private val m: Int, private val board: Array<CharArray>) {
    private val dy = intArrayOf(1, -1, 0, 0)
    private val dx = intArrayOf(0, 0, 1, -1)

    fun solution(): Int {
        val queue = ArrayDeque<Triple<Coin, Coin, Int>>()

        val coinList = ArrayList<Coin>()
        for (row in 0 until n) {
            for (col in 0 until m) {
                if (board[row][col] == 'o') coinList.add(Coin(row, col))
            }
        }

        queue.addLast(Triple(coinList[0], coinList[1], 0))

        while (queue.isNotEmpty()) {
            val (coin1, coin2, count) = queue.removeFirst()

            if (count >= 10) return -1

            for (i in 0 until 4) {
                var nCoin1 = Coin(coin1.row + dy[i], coin1.col + dx[i])
                var nCoin2 = Coin(coin2.row + dy[i], coin2.col + dx[i])

                when {
                    nCoin1.row in 0 until n && nCoin1.col in 0 until m && nCoin2.row in 0 until n && nCoin2.col in 0 until m -> {
                        if (board[nCoin1.row][nCoin1.col] == '#') nCoin1 = coin1
                        if (board[nCoin2.row][nCoin2.col] == '#') nCoin2 = coin2
                        queue.addLast(Triple(nCoin1, nCoin2, count + 1))
                    }
                    nCoin2.row in 0 until n && nCoin2.col in 0 until m -> { // coin1 떨어짐
                        return count + 1
                    }
                    nCoin1.row in 0 until n && nCoin1.col in 0 until m -> { // coin2 떨어짐
                        return count + 1
                    }
                    else -> continue
                }
            }
        }
        return -1
    }
}
