package _2023._5월_week5.BOJ5214_환승

/*
    https://www.acmicpc.net/problem/5214

    BFS

    하나의 하이퍼 튜브로 1, 2, 3 이 이어져 있다면
    1 -> 2, 2 -> 3 의 거리만 1인 것이 아니라 1 -> 3 의 거리도 1이다.
    하이퍼튜브를 연결된 역들의 중간 노드로 간주하여 이동하도록 한다.
 */

fun main() {
    val (n, k, m) = readln().trim().split(" ").map { it.toInt() }
    val links = Array(m) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

    println(Solution().solution(n, k, m, links))
}

data class Element(
    val station: Int,
    val count: Int
)

class Solution {
    fun solution(n: Int, k: Int, m: Int, links: Array<IntArray>): Int {
        val stationGraph = Array(n + 1) { ArrayList<Int>() }
        val hyperTubes = Array(m) { ArrayList<Int>() }
        val queue = ArrayDeque<Element>()
        val visited = BooleanArray(m) { false }

        for (i in 0 until m) {
            hyperTubes[i].addAll(links[i].toList())
            for (station in links[i]) {
                stationGraph[station].add(i)
            }
        }

        queue.addLast(Element(1, 1))

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            if (current.station == n) return current.count

            for (nextTube in stationGraph[current.station]) {
                if (visited[nextTube]) continue
                visited[nextTube] = true

                for (nextStation in hyperTubes[nextTube]) {
                    queue.addLast(Element(nextStation, current.count + 1))
                }
            }
        }

        return -1
    }
}
