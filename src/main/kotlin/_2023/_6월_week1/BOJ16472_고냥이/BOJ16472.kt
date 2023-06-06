package _2023._6월_week1.BOJ16472_고냥이

/*
    https://www.acmicpc.net/problem/16472

    투 포인터

    set을 이용했을때 78% 시간초과
    -> 알파벳 개수 Array 만들고 +1, -1 하면서 카운트 세기
 */

import kotlin.math.max

fun main() {
    val n = readln().toInt()
    val string = readln()

    println(Solution().solution(n, string))
}

class Solution {
    fun solution(n: Int, string: String): Int {
        var answer = 0
        val alphaArray = IntArray(26) { 0 }
        var count = 0
        var left = 0
        var right = 0

        while (true) {
            while (right < string.length) {
                val index = string[right].code - 'a'.code
                right++
                alphaArray[index]++
                if (alphaArray[index] == 1) count++
                if (count > n) break
                answer = max(answer, right - left)
            }
            if (right == string.length) break

            while (count > n) {
                val index = string[left].code - 'a'.code
                alphaArray[index]--
                if (alphaArray[index] == 0) count--
                left++
            }
        }

        return answer
    }
}

/*
class Solution {
    fun solution(n: Int, string: String): Int {
        if (n >= string.length) return string.length

        var answer = 0
        var left = 0
        var right = n - 1

        while (right < string.length) {
            val count = string.substring(left, right + 1).toSet().size
            if (count <= n) {
                right++
                answer = max(answer, right - left)
            } else {
                left++
            }
        }

        return answer
    }
}
*/
