package _2023._5월_week4.BOJ8983_사냥꾼

/*
    https://www.acmicpc.net/problem/8983

    이분탐색
    정렬
 */

fun main() {
    val (m, n, l) = readln().trim().split(" ").map { it.toInt() }
    val shootingZone = readln().trim().split(" ").map { it.toInt() }.toIntArray()
    val animals = Array(n) {
        val (x, y) = readln().trim().split(" ").map { it.toInt() }
        Point(x, y)
    }

    println(Solution().solution(m, n, l, shootingZone, animals))
}

data class Point(
    val x: Int,
    val y: Int,
)

class Solution {
    fun solution(m: Int, n: Int, l: Int, shootingZone: IntArray, animals: Array<Point>): Int {
        var answer = 0

        shootingZone.sort()

        for (animal in animals) {
            if (animal.y > l) continue
            var left = 0
            var right = m - 1
            val xStart = animal.x + animal.y - l
            val xEnd = animal.x - animal.y + l

            while (left <= right) {
                val mid = (left + right) / 2

                if (shootingZone[mid] in xStart..xEnd) {
                    answer++
                    break
                } else if (shootingZone[mid] < xStart) {
                    left = mid + 1
                } else { // shootingZone[mid] > xEnd
                    right = mid - 1
                }
            }
        }

        return answer
    }
}
