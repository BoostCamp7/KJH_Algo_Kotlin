package _2023._3월_week2.BOJ16928_뱀과_사다리_게임

/*
    https://www.acmicpc.net/problem/16928

    BFS

    HashMap 사용시 -> 시간 초과
    Queue에 카운트를 포함하여 next 추가 (visited X) -> 메모리 초과
    countArray에 주사위 굴린 횟수 저장 -> 통과
 */

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val ladders = Array(n) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }
    val snakes = Array(m) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

    println(Solution().solution(n, m, ladders, snakes))
}

class Solution {
    fun solution(n: Int, m: Int, ladders: Array<IntArray>, snakes: Array<IntArray>): Int {
        val graph = IntArray(101) { it }
        val queue = ArrayDeque<Int>()
        val countArray = IntArray(101) { 0 }

        for (i in 0 until n) graph[ladders[i][0]] = ladders[i][1]
        for (i in 0 until m) graph[snakes[i][0]] = snakes[i][1]

        queue.addLast(1)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            if (current == 100) return countArray[100]

            for (i in 1..6) {
                if (current + i > 100) break
                val next = graph[current + i]

                if (countArray[next] == 0) {
                    countArray[next] = countArray[current] + 1
                    queue.addLast(next)
                }
            }
        }

        return -1
    }
}

