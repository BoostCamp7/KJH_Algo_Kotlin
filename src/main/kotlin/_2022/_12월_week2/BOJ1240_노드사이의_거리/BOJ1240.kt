package _2022._12월_week2.BOJ1240_노드사이의_거리

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val edgeList = List(n - 1) {
        readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    }

    for (i in 0 until m) {
        val (start, end) = readLine().trim().split(" ").map { it.toInt() }
        println(Solution().solution(n, start, end, edgeList))
    }
}

class Solution {
    fun solution(n: Int, start: Int, end: Int, edgeList: List<IntArray>): Int {
        val graph = List(n + 1) { IntArray(n + 1) { 0 } }

        val queue = ArrayDeque<Int>()
        queue.addLast(start)

        val visited = BooleanArray(n) { false }
        visited[start] = true

        edgeList.forEach { edge ->
            val (a, b, weight) = edge
            graph[a][b] = weight
            graph[b][a] = weight
        }

        // var result = Int.MAX_VALUE
        var sum = 0

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            for (i in 0 until n) {
                if (visited[i].not() && graph[current][i] > 0) {
                    visited[i] = true
                    sum += graph[current][i]
                    if (i == end) {
                        break
                    }
                    queue.addLast(i)
                }
            }
        }

        return sum
    }
}
