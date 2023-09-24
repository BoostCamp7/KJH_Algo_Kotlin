package _2023._9월_week1.BOJ1865_웜홀

/*
    https://www.acmicpc.net/problem/1865

    Bellman-Ford

    91퍼 이후 메모리 초과
 */

fun main() {
    val tc = readln().toInt()
    repeat(tc) {
        val (n, m, w) = readln().trim().split(" ").map { it.toInt() }
        val graph = Array(n + 1) { ArrayList<Edge>() }
        repeat(m) {
            val (s, e, t) = readln().trim().split(" ").map { it.toInt() }
            graph[s].add(Edge(e, t))
            graph[e].add(Edge(s, t))
        }
        repeat(w) {
            val (s, e, t) = readln().trim().split(" ").map { it.toInt() }
            graph[s].add(Edge(e, -t))
        }

        println(Solution(n, m, w, graph).solution())
    }
}

data class Edge(
    val e: Int,
    val t: Int
)

class Solution(
    private val n: Int,
    private val m: Int,
    private val w: Int,
    private val graph: Array<ArrayList<Edge>>,
) {
    companion object {
        private const val INF = 99999999
    }

    fun solution(): String {
        var answer = false

        for (i in 1..n) {
            if (bellmanFord(i)) {
                answer = true
                break
            }
        }

        return if (answer) "YES" else "NO"
    }

    private fun bellmanFord(start: Int): Boolean {
        val dist = IntArray(n + 1) { INF }
        dist[start] = 0
        var isUpdated: Boolean

        for (i in 1..n) {
            isUpdated = false

            for (j in 1..n) {
                for (edge in graph[j]) {
                    if (dist[j] == INF) continue

                    if (dist[edge.e] > dist[j] + edge.t) {
                        dist[edge.e] = dist[j] + edge.t
                        isUpdated = true

                        if (i == n) return true
                    }
                }
            }

            if (isUpdated.not()) break
        }

        return false
    }
}
