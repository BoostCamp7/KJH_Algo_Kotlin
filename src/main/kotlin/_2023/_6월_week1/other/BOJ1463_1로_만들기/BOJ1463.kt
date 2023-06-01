package _2023._6월_week1.other.BOJ1463_1로_만들기

/*
    https://www.acmicpc.net/problem/1463

    BFS로 풀이
    백준 분류 -> DP

    나중에 DP로 풀어보기
 */

fun main() {
    val x = readln().toInt()
    println(Solution().solution(x))
}

data class Element(
    val number: Int,
    val count: Int,
)

class Solution {
    fun solution(x: Int): Int {
        val queue = ArrayDeque<Element>()
        queue.addLast(Element(x, 0))

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            if (current.number == 1) return current.count
            if (current.number % 3 == 0) queue.addLast(Element(current.number / 3, current.count + 1))
            if (current.number % 2 == 0) queue.addLast(Element(current.number / 2, current.count + 1))
            queue.addLast(Element(current.number - 1, current.count + 1))
        }

        return -1
    }
}
