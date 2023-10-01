package _2023._9월_week4.BOJ1647_도시_분할_계획

/*
    https://www.acmicpc.net/problem/1647

    최소 스패닝 트리 (MST)
    Kruskal
    Union-Find 최적화

    MST 에서 가장 비용이 큰 마지막 길을 없애 마을 두개로 나누기 (n-2 개 길의 비용만 합치기)

    2% 시간초과 -> PQ 사용 : 그대로 2% -> Union-Find 최적화 후 통과 :
    1. find 할 때 마다 parent를 root로 바꿔주어 root를 찾는 과정 줄이기 -> 이 때 이미 해결
    2. union시 root마다 graph의 depth를 카운트하여 더 작은 depth를 가진 graph를 큰 graph의 root에 합치기 -> 추가적인 최적화
 */

import java.util.PriorityQueue

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val routes = PriorityQueue<Route>(m)
    repeat(m) {
        val (a, b, cost) = readln().trim().split(" ").map { it.toInt() }
        routes.add(Route(a, b, cost))
    }

    println(Solution(n, m, routes).solution())
}

data class Route(
    val a: Int,
    val b: Int,
    val cost: Int,
) : Comparable<Route> {
    override fun compareTo(other: Route): Int {
        return cost.compareTo(other.cost)
    }
}

class Solution(
    private val n: Int,
    private val m: Int,
    private val routes: PriorityQueue<Route>
) {
    private val parent = IntArray(n + 1) { it }
    private val size = IntArray(n + 1) { 1 }

    fun solution(): Int {
        var answer = 0
        var count = 0

        while (count < n - 2) {
            val route = routes.poll()
            val nA = find(route.a)
            val nB = find(route.b)

            if (nA == nB) continue

            union(nA, nB)
            answer += route.cost
            count++
        }

        return answer
    }

    private fun union(a: Int, b: Int) {
        if (size[a] < size[b]) {
            parent[a] = b
            size[b] += size[a]
        } else {
            parent[b] = a
            size[a] += size[b]
        }
    }

    private fun find(a: Int): Int {
        if (a == parent[a]) return a
        parent[a] = find(parent[a])
        return parent[a]
    }
}
