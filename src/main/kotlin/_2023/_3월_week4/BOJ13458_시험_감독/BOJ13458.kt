package _2023._3월_week4.BOJ13458_시험_감독

/*
    https://www.acmicpc.net/problem/13458

    수학

    Long 주의
 */

fun main() {
    val n = readln().toInt()
    val a = readln().trim().split(" ").map { it.toInt() }.toIntArray()
    val (b, c) = readln().trim().split(" ").map { it.toInt() }

    println(Solution().solution(n, a, b, c))
}

class Solution {
    fun solution(n: Int, a: IntArray, b: Int, c: Int): Long {
        var answer = 0L

        for (i in 0 until n) {
            var remain = a[i] - b
            answer++
            if (remain > 0) {
                answer += remain / c
                remain %= c
                if (remain > 0) answer++
            }
        }

        return answer
    }
}
