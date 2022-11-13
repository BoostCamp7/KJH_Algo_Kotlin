package _11월_week2.BOJ1025_제곱수_찾기

/*
   3% 실패...
*/

import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val table = Array(n) { readLine().trim().chunked(1).map { it.toInt() }.toIntArray() }

    print(Solution().solution(n, m, table))
}

class Solution {

    private var n: Int = 0
    private var m: Int = 0
    private lateinit var table: Array<IntArray>

    fun solution(n: Int, m: Int, table: Array<IntArray>): Int {
        this.n = n
        this.m = m
        this.table = table

        var max = -1

        for (i in 0 until n) {
            for (j in 0 until m) {
                for (di in -n..n) {
                    for (dj in -m..m) {
                        if (di == 0 && dj == 0) continue
                        // 숫자로 만들기
                        makeNumbers(i, j, di, dj).forEach { number ->
                            if (number > max && isSquare(number)) max = number
                        }
                    }
                }
            }
        }

        return max
    }

    private fun makeNumbers(i: Int, j: Int, di: Int, dj: Int): IntArray {
        val tempList = ArrayList<Int>()
        val numberList = ArrayList<Int>()

        var tempI = i
        var tempJ = j

        while (tempI in 0 until n && tempJ in 0 until m) {
            tempList.add(table[tempI][tempJ])
            numberList.add(tempList.joinToString("").toInt())
            tempI += di
            tempJ += dj
        }

        return numberList.toIntArray()
    }

    private fun isSquare(number: Int): Boolean {
        return (sqrt(number.toDouble()) % 1) == 0.0
    }
}
