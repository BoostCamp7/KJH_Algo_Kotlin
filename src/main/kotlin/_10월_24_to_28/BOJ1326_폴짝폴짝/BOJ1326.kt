package _10월_24_to_28.BOJ1326_폴짝폴짝

/*
   BFS 방식
*/

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val stepList = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    val (start, dest) = readLine().trim().split(" ").map { it.toInt() - 1 }

    print(Solution().solution(n, stepList, start, dest))
}

class Solution {
    fun solution(n: Int, stepList: IntArray, start: Int, dest: Int): Int {
        val queue = ArrayDeque<Int>()
        val visited = IntArray(n) { -1 }

        queue.addLast(start)
        visited[start] = 0

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            for (i in current until n step (stepList[current])) {
                if (visited[i] == -1) {
                    queue.addLast(i)
                    visited[i] = visited[current] + 1
                    if (i == dest) return visited[i]
                }
            }

            for (i in current downTo 0 step (stepList[current])) {
                if (visited[i] == -1) {
                    queue.addLast(i)
                    visited[i] = visited[current] + 1
                    if (i == dest) return visited[i]
                }
            }
        }

        return -1
    }
}
