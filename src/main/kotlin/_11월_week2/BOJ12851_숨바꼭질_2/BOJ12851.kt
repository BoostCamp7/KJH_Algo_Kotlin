package _11월_week2.BOJ12851_숨바꼭질_2

/*
   BFS
   반례 케이스를 못찾겠다... (14% 틀림)
*/

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().trim().split(" ").map { it.toInt() }

    Solution().solution(n, k).forEach {
        println(it)
    }
}

class Solution {

    private val visited = IntArray(100001) { -1 }
    private val queue = ArrayDeque<Int>()

    fun solution(n: Int, k: Int): IntArray {
        if (n == k) return intArrayOf(0, 1)

        visited[n] = 0
        queue.addLast(n)

        var minTime = Int.MAX_VALUE
        var resultCount = 0

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            if (visited[current] > minTime) break

            for (i in 0..2) {
                val next = when (i) {
                    1 -> current + 1
                    2 -> current - 1
                    else -> 2 * current
                }

                if (next in 0..100000) {
                    if (next == k) {
                        minTime = visited[current] + 1
                        resultCount++
                    } else if (visited[next] == -1 || visited[next] == visited[current] + 1) {
                        queue.addLast(next)
                        visited[next] = visited[current] + 1
                    }
                }
            }
        }

        return intArrayOf(minTime, resultCount)
    }
}
