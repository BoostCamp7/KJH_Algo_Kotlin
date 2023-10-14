package _2023._10월_week2.BOJ1595_북쪽나라의_도로

/*
    https://www.acmicpc.net/problem/1595

    DFS
 */

import kotlin.math.max

fun main() {
    var n = 0
    val map = Array(10001) { ArrayList<Road>() }

    while (true) {
        try {
            val (a, b, weight) = readln().trim().split(" ").map { it.toInt() }
            map[a].add(Road(b, weight))
            map[b].add(Road(a, weight))
            n = max(n, a)
            n = max(n, b)
        } catch (e: RuntimeException) {
            break
        }
    }

    println(Solution(n, map).solution())
}

data class Road(
    val dest: Int,
    val weight: Int
)

class Solution(private val n: Int, private val map: Array<ArrayList<Road>>) {

    private var answer = 0
    private val visited = BooleanArray(10001) { false }

    fun solution(): Int {
        var index = 0
        while (map[++index].isNotEmpty()) {
            dfs(index, 0)
        }

        return answer
    }

    fun dfs(index: Int, weight: Int) {
        visited[index] = true

        for (road in map[index]) {
            if (visited[road.dest].not()) {
                dfs(road.dest, weight + road.weight)
            } else {
                answer = max(answer, weight)
            }
        }

        visited[index] = false
    }
}
