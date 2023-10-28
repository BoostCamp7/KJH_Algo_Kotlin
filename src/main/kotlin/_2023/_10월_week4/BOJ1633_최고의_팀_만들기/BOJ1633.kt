package _2023._10월_week4.BOJ1633_최고의_팀_만들기

/*
    https://www.acmicpc.net/problem/1633

    DP

    DP[index][whiteCount][blackCount]
 */

import kotlin.math.max

fun main() {
    val players = ArrayList<Player>()

    while (true) {
        try {
            val (white, black) = readln().trim().split(" ").map { it.toInt() }
            players.add(Player(white, black))
        } catch (e: RuntimeException) {
            break
        }
    }

    println(solution(players.toTypedArray()))
}

data class Player(
    val white: Int,
    val black: Int,
)

fun solution(players: Array<Player>): Int {
    val n = players.size
    val dp = Array(n + 1) { Array(16) { IntArray(16) { 0 } } }

    for (i in 0 until n) {
        for (w in 0..15) {
            for (b in 0..15) {
                if (w < 15) dp[i + 1][w + 1][b] = max(dp[i + 1][w + 1][b], dp[i][w][b] + players[i].white)
                if (b < 15) dp[i + 1][w][b + 1] = max(dp[i + 1][w][b + 1], dp[i][w][b] + players[i].black)
                dp[i + 1][w][b] = max(dp[i + 1][w][b], dp[i][w][b])
            }
        }
    }

    return dp[n][15][15]
}
