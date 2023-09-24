package _2023._9월_week1.BOJ24524_아름다운_문자열

/*
    https://www.acmicpc.net/problem/24524

    Greedy
 */

fun main() {
    val S = readln()
    val T = readln()

    println(Solution().solution(S, T))
}

class Solution {
    fun solution(S: String, T: String): Int {
        var answer = 0
        val tQueue = Array(26) { ArrayDeque<Int>() }

        for (i in S.indices) {
            tQueue[S[i] - 'a'].addLast(i)
        }

        var index = 0
        var last = -1

        while (true) {
            val queueIdx = T[index] - 'a'

            while (tQueue[queueIdx].isNotEmpty() && last >= tQueue[queueIdx].first()) {
                tQueue[queueIdx].removeFirst()
            }

            if (tQueue[queueIdx].isNotEmpty()) {
                last = tQueue[queueIdx].removeFirst()
            } else {
                break
            }

            index++
            if (index == T.length) {
                index = 0
                last = -1
                answer++
            }
        }

        return answer
    }
}
