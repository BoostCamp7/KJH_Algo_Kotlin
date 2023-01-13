package _2022._11월_week4.BOJ2470_두_용액

/*
   BOJ2467_용액 과 비슷한 문제
   해당 문제에서 정렬만 추가
*/

import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val list = readLine().trim().split(" ").map { it.toInt() }

    print(Solution().solution(n, list).joinToString(" "))
}

class Solution {
    fun solution(n: Int, list: List<Int>): IntArray {
        val sortedList = list.sortedBy { it }

        var left = 0
        var right = sortedList.lastIndex

        var min = Int.MAX_VALUE
        var minLeft = Int.MAX_VALUE
        var minRight = Int.MAX_VALUE

        while (left < right) {
            val sum = sortedList[left] + sortedList[right]
            val absSum = abs(sum)

            if (absSum < min) {
                minLeft = sortedList[left]
                minRight = sortedList[right]
                min = absSum
            } else if (absSum == min) {
                minLeft = sortedList[left]
                minRight = sortedList[right]
            }

            if (sum < 0) left++ else right--
        }

        return intArrayOf(minLeft, minRight)
    }
}
