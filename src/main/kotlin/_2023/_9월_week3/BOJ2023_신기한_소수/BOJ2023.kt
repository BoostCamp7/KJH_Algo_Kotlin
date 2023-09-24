package _2023._9월_week3.BOJ2023_신기한_소수

/*
    https://www.acmicpc.net/problem/2023

    수학 (소수)
    백트래킹

    소수 판별 : i = 2 ~ sqrt(num) 까지 num에 대한 약수가 하나도 없다면 num은 소수

    소수 만들기
    1. 자리수 맨 앞에는 2, 3, 5, 7 만 올 수 있음
    2. 2자리 이상인 경우 마지막 자리수는 1, 3, 7, 9 만 올 수 있음
        -> 문제에선 마지막 자리수를 하나씩 빼도 소수여야 하기 때문에 맨 앞자리 외에는 1, 3, 7, 9 만 올 수 있음
    3. 앞의 조건을 지키면서 만든 모든 수에 대해 소수인지를 판별하기
 */

import kotlin.math.sqrt

fun main() {
    val n = readln().toInt()

    Solution(n).solution()
}

class Solution(private val n: Int) {
    private val first = intArrayOf(2, 3, 5, 7)
    private val last = intArrayOf(1, 3, 7, 9)

    fun solution() {
        dfs(0, n)
        return
    }

    private fun dfs(num: Int, count: Int) {
        if (count == 0) println(num)

        val numList = if (count == n) first else last

        for (i in numList) {
            val temp = num * 10 + i
            if (isPrime(temp)) dfs(temp, count - 1)
        }
    }

    private fun isPrime(num: Int): Boolean {
        if (num < 2) return false
        for (i in 2..sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) return false
        }
        return true
    }
}
