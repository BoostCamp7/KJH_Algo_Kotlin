package _2023._10월_week4.BOJ25682_체스판_다시_칠하기_2

/*
    https://www.acmicpc.net/problem/25682

    2차원 누적합

    <누적합 참고 자료>
    https://nahwasa.com/entry/%EB%88%84%EC%A0%81-%ED%95%A9prefix-sum-2%EC%B0%A8%EC%9B%90-%EB%88%84%EC%A0%81%ED%95%A9prefix-sum-of-matrix-with-java
 */

fun main() {
    val (n, m, k) = readln().trim().split(" ").map { it.toInt() }
    val board = Array(n) { readln().toCharArray() }

    println(solution(n, m, k, board))
}

fun solution(n: Int, m: Int, k: Int, board: Array<CharArray>): Int {
    var answer = Int.MAX_VALUE
    val boardSize = k * k
    val blackFirst = Array(n + 1) { IntArray(m + 1) { 0 } }

    if (board[0][0] != 'B') {
        blackFirst[1][1] = 1
    }

    for (row in 1..n) {
        for (col in 1..m) {
            blackFirst[row][col] = if (board[row - 1][col - 1] == 'B') {
                if ((row + col) % 2 == 0) {
                    blackFirst[row - 1][col] + blackFirst[row][col - 1] - blackFirst[row - 1][col - 1] + 1
                } else {
                    blackFirst[row - 1][col] + blackFirst[row][col - 1] - blackFirst[row - 1][col - 1]
                }
            } else {
                if ((row + col) % 2 == 0) {
                    blackFirst[row - 1][col] + blackFirst[row][col - 1] - blackFirst[row - 1][col - 1]
                } else {
                    blackFirst[row - 1][col] + blackFirst[row][col - 1] - blackFirst[row - 1][col - 1] + 1
                }
            }
        }
    }

    for (i in k..n) {
        for (j in k..m) {
            val blackSum = blackFirst[i][j] - blackFirst[i - k][j] - blackFirst[i][j - k] + blackFirst[i - k][j - k]
            answer = minOf(answer, blackSum, boardSize - blackSum)
        }
    }

    return answer
}
