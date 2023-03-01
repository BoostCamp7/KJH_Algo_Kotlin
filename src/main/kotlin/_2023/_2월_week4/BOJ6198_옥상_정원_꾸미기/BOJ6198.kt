package _2023._2월_week4.BOJ6198_옥상_정원_꾸미기

/*
    https://www.acmicpc.net/problem/6198

    Stack
    (Monotonic Stack)

    최대 카운트 N(N-1)/2 = 79999 + 79998 + .. + 2 + 1 일 수 있으므로 Int로 반환시 오류 -> Long 사용해야함
 */

fun main() {
    val n = readln().toInt()
    val buildings = IntArray(n) { readln().toInt() }

    println(Solution2().solution(n, buildings))
}

class Solution2 {
    fun solution(n: Int, buildings: IntArray): Long {
        var answer = 0L
        val stack = ArrayDeque<Int>()

        for (i in 0 until n) {
            while (stack.isNotEmpty() && stack.last() <= buildings[i]) stack.removeLast()
            answer += stack.size
            stack.addLast(buildings[i])
        }

        return answer
    }
}

// 스택 없이 한 풀이
// N(N-1)/2
class Solution1 {
    fun solution(n: Int, buildings: IntArray): Long {
        var answer = 0L

        for (i in 0 until n - 1) {
            for (j in i + 1 until n) {
                if (buildings[j] >= buildings[i]) break
                answer++
            }
        }

        return answer
    }
}
