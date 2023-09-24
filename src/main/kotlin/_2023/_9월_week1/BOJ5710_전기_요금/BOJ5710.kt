package _2023._9월_week1.BOJ5710_전기_요금

/*
    https://www.acmicpc.net/problem/5710

    이분 탐색
    구현
 */

import kotlin.math.absoluteValue

fun main() {
    while (true) {
        val (A, B) = readln().trim().split(" ").map { it.toInt() }
        if (A == 0 && B == 0) break

        println(Solution().solution(A, B))
    }
}

class Solution {
    fun solution(A: Int, B: Int): Int {
        val watt = calWatt(A)
        var left = 0
        var right = watt / 2

        while (left <= right) {
            val mid = (left + right) / 2

            val sanggeun = calPrice(mid)
            val neighbor = calPrice(watt - mid)

            val diff = (sanggeun - neighbor).absoluteValue

            if (diff < B) {
                right = mid - 1
            } else if (diff > B) {
                left = mid + 1
            } else {
                return sanggeun
            }
        }

        return -1
    }

    private fun calWatt(price: Int): Int {
        return when {
            price <= 200 -> { price / 2 }
            price <= 29900 -> { ((price - 200) / 3) + 100 }
            price <= 4979900 -> { ((price - 29900) / 5) + 10000 }
            else -> { ((price - 4979900) / 7) + 1000000 }
        }
    }

    private fun calPrice(watt: Int): Int {
        return when {
            watt <= 100 -> { watt * 2 }
            watt <= 10000 -> { ((watt - 100) * 3) + 200 }
            watt <= 1000000 -> { ((watt - 10000) * 5) + 200 + 29700 }
            else -> { ((watt - 1000000) * 7) + 200 + 29700 + 4950000 }
        }
    }
}
