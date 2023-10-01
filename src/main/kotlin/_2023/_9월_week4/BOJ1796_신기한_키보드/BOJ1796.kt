package _2023._9월_week4.BOJ1796_신기한_키보드

/*
    https://www.acmicpc.net/problem/1796

    DP

    dp[i][j] : i - 1번째 알파벳 모두 출력 완료, j = 마지막 위치 왼쪽, 오른쪽 (0, 1)
 */

import kotlin.math.absoluteValue
import kotlin.math.min

fun main() {
    val s = readln()

    println(Solution(s).solution())
}

class Solution(private val s: String) {

    val dp = Array(27) { IntArray(2) { 0 } }

    fun solution(): Int {
        buildDP()

        return dp[26].minOf { it } + s.length
    }

    private fun buildDP() {
        var posLeft = 0
        var posRight = 0

        for (alpha in 0 until 26) {
            val left = s.indexOf('a' + alpha)
            val right = s.lastIndexOf('a' + alpha)

            if (left != -1) {
                dp[alpha + 1][0] = min(
                    dp[alpha][0] + calDist(posLeft, true, left, right),
                    dp[alpha][1] + calDist(posRight, true, left, right),
                )

                dp[alpha + 1][1] = min(
                    dp[alpha][0] + calDist(posLeft, false, left, right),
                    dp[alpha][1] + calDist(posRight, false, left, right),
                )

                posLeft = left
                posRight = right
            } else {
                dp[alpha + 1][0] = dp[alpha][0]
                dp[alpha + 1][1] = dp[alpha][1]
            }
        }
    }

    private fun calDist(start: Int, destIsLeft: Boolean, left: Int, right: Int): Int {
        return if (destIsLeft) {
            (start - right).absoluteValue + (right - left).absoluteValue
        } else {
            (start - left).absoluteValue + (left - right).absoluteValue
        }
    }
}
