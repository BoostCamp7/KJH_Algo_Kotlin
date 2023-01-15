package _2023._1월_week2.BOJ2252_줄_세우기

/*
    https://www.acmicpc.net/problem/2252

    위상 정렬 (자료: https://www.notion.so/bh1018/3a49636d9fb04bcc92a7005aa9544f1c)
    * 노드가 32000개 이기 떄문에 인접 행렬로 풀면 메모리 초과 오류가 나기 때문에 인접 리스트로 풀어야 한다.
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val data = List(m) {
        readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    }

    println(Solution().solution(n, m, data).joinToString(" "))
}

class Solution {
    fun solution(n: Int, m: Int, data: List<IntArray>): IntArray {
        val indegree = IntArray(n + 1) { 0 }
        val graph = List(n + 1) { ArrayList<Int>() }

        for (i in 0 until m) {
            val (tall, small) = data[i]
            indegree[small]++
            graph[tall].add(small)
        }

        val queue = ArrayDeque<Int>()
        for (i in 1..n) {
            if (indegree[i] == 0) queue.addLast(i)
        }

        val result = ArrayList<Int>()
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            result.add(current)

            for (child in graph[current]) {
                indegree[child]--
                if (indegree[child] == 0) queue.addLast(child)
            }
        }

        return result.toIntArray()
    }
}
