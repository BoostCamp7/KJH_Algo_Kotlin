package _2023._5월_week3.BOJ17243_Almost_K_Increasing_Subsequence

fun main() {
    val (n, k) = readln().trim().split(" ").map { it.toInt() }
    val numArray = readln().trim().split(" ").map { it.toInt() }.toIntArray()

    println(Solution().solution(n, k, numArray))
}

class Solution {
    fun solution(n: Int, k: Int, numArray: IntArray) {

    }
}
