package _2023._3월_week5.BOJ2110_공유기_설치

/*
    https://www.acmicpc.net/problem/2110

    이분 탐색

    1. 정렬
    2. left = 1, right = 마지막 집 - 처음 집 으로 초기화
    3. (left + right) / 2 를 공유기 설치 간격으로 두고 이분 탐색하며 최대 거리 갱신
 */

import kotlin.math.max

fun main() {
    val (n, c) = readln().trim().split(" ").map { it.toInt() }
    val xPoints = IntArray(n) { readln().toInt() }

    println(Solution().solution(n, c, xPoints))
}

class Solution {
    fun solution(n: Int, c: Int, xPoints: IntArray): Int {
        xPoints.sort()

        var answer = 0

        var left = 1
        var right = xPoints.last() - xPoints.first()

        while (left <= right) {
            val distance = (left + right) / 2

            var prev = xPoints[0]
            var count = 1

            for (i in 1 until n) {
                if (xPoints[i] - prev >= distance) {
                    count++
                    prev = xPoints[i]
                }
            }

            if (count >= c) {
                answer = max(distance, answer)
                left = distance + 1
            } else {
                right = distance - 1
            }
        }

        return answer
    }
}
