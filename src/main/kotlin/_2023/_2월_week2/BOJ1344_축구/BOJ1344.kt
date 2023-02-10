package _2023._2월_week2.BOJ1344_축구

/*
    https://www.acmicpc.net/problem/1344

    DP

    A 테이블 (tableA) + B 테이블 (tableB) -> 3차원 배열 하나로 통합
    A, B 각각의 경우의 수를 구해 더하면 겹치는 경우가 생김
 */

fun main() {
    val percentA = readln().toInt() * 0.01
    val percentB = readln().toInt() * 0.01

    println(Solution().solution(percentA, percentB))
}

class Solution {
    fun solution(percentA: Double, percentB: Double): Double {
        val size = 90 / 5 // = 18 (2, 3, 5, 7, 11, 13, 17)
        val primeArray = intArrayOf(2, 3, 5, 7, 11, 13, 17)
        val table = Array(size + 1) { Array(size + 1) { DoubleArray(size + 1) { 0.0 } } }
        table[0][0][0] = 1.0

        for (i in 1..size) {
            for (a in 0..i) {
                for (b in 0..i) {
                    if (a > 0) table[i][a][b] += table[i - 1][a - 1][b] * percentA * (1 - percentB)
                    if (b > 0) table[i][a][b] += table[i - 1][a][b - 1] * percentB * (1 - percentA)
                    if (a > 0 && b > 0) table[i][a][b] += table[i - 1][a - 1][b - 1] * percentA * percentB
                    table[i][a][b] += table[i - 1][a][b] * (1 - percentA) * (1 - percentB)
                }
            }
        }

        var sum = 0.0
        for (a in 0..size) {
            for (b in 0..size) {
                if (a in primeArray || b in primeArray) sum += table[size][a][b]
            }
        }

        return sum
    }
}
