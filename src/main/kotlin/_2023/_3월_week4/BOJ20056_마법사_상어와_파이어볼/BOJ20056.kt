package _2023._3월_week4.BOJ20056_마법사_상어와_파이어볼

/*
    https://www.acmicpc.net/problem/20056

    구현 (삼성 기출)

    "1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다." -> 이 조건 주의

    런타임 에러 (ArrayIndexOutOfBounds) -> move() 함수에서 nRow, nCol 구할 때 문제일듯
    => if (...) else if (...) 에서 if (...) if (...) 로 바꾸고 해결
 */

import kotlin.math.abs

fun main() {
    val (n, m, k) = readln().trim().split(" ").map { it.toInt() }
    val fireballs = Array(m) {
        val (r, c, w, s, d) = readln().trim().split(" ").map { it.toInt() }
        Fireball(r - 1, c - 1, w, s, d)
    }

    println(Solution(n, m, k, fireballs).solution())
}

data class Fireball(
    val row: Int,
    val col: Int,
    val weight: Int,
    val speed: Int,
    val direction: Int,
)

class Solution(private val n: Int, private val m: Int, private val k: Int, private val fireballs: Array<Fireball>) {
    private val fireballQueue = ArrayDeque<Fireball>()
    private val table = Array(n) { Array(n) { ArrayDeque<Fireball>() } }
    private val dy = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
    private val dx = arrayOf(0, 1, 1, 1, 0, -1, -1, -1)

    fun solution(): Int {
        for (fireball in fireballs) {
            fireballQueue.addLast(fireball)
        }

        repeat(k) {
            while (fireballQueue.isNotEmpty()) {
                move(fireballQueue.removeFirst())
            }

            for (i in 0 until n) {
                for (j in 0 until n) {
                    when (table[i][j].size) {
                        0 -> continue
                        1 -> {
                            fireballQueue.addLast(table[i][j].removeFirst())
                        }
                        else -> {
                            val nWeight = table[i][j].sumOf { it.weight } / 5
                            if (nWeight == 0) {
                                table[i][j].clear()
                                continue
                            }

                            val size = table[i][j].size
                            val nSpeed = table[i][j].sumOf { it.speed } / size
                            val evenCount = table[i][j].count { it.direction % 2 == 0 }
                            table[i][j].clear()

                            if (evenCount == size || evenCount == 0) { // 0, 2, 4, 6
                                for (k in 0..6 step 2) {
                                    fireballQueue.addLast(Fireball(i, j, nWeight, nSpeed, k))
                                }
                            } else { // 1, 3, 5, 7
                                for (k in 1..7 step 2) {
                                    fireballQueue.addLast(Fireball(i, j, nWeight, nSpeed, k))
                                }
                            }
                        }
                    }
                }
            }
        }

        return fireballQueue.sumOf { it.weight }
    }

    private fun move(fireball: Fireball) {
        var nRow = fireball.row + (dy[fireball.direction] * fireball.speed)
        var nCol = fireball.col + (dx[fireball.direction] * fireball.speed)
        if (nRow < 0) nRow = n - (abs(nRow) % n)
        if (nRow >= n) nRow %= n
        if (nCol < 0) nCol = n - (abs(nCol) % n)
        if (nCol >= n) nCol %= n
        table[nRow][nCol].addLast(Fireball(nRow, nCol, fireball.weight, fireball.speed, fireball.direction))
    }
}
