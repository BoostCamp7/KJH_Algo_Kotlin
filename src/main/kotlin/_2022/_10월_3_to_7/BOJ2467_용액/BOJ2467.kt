package _2022._10월_3_to_7.BOJ2467_용액

import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val list = readLine().split(" ").map { it.toInt() }.sorted()

    var left = 0
    var right = list.lastIndex

    var min = Int.MAX_VALUE
    var minLeft = Int.MAX_VALUE
    var minRight = Int.MAX_VALUE

    while (left < right) {
        val sum = list[left] + list[right]
        val absSum = abs(sum)

        if (absSum < min) {
            minLeft = list[left]
            minRight = list[right]
            min = absSum
        } else if (absSum == min) {
            minLeft = list[left]
            minRight = list[right]
        }

        if (sum < 0) left++ else right--
    }

    print("$minLeft $minRight")
}