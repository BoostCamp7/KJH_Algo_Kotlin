package _2023._5월_week4.BOJ6497_전력난

/*
    https://www.acmicpc.net/problem/6497

    MST
    크루스칼
 */

fun main() {
    while (true) {
        val (m, n) = readln().trim().split(" ").map { it.toInt() }
        if (m == 0 && n == 0) break

        val routes = Array(n) {
            val (x, y, dist) = readln().trim().split(" ").map { it.toInt() }
            Route(x, y, dist)
        }

        println(Solution(m, n, routes).solution())
    }
}

data class Route(
    val x: Int,
    val y: Int,
    val dist: Int,
) : Comparable<Route> {
    override fun compareTo(other: Route): Int {
        return this.dist.compareTo(other.dist)
    }
}

class Solution(private val m: Int, private val n: Int, private val routes: Array<Route>) {

    private val parents = IntArray(m) { it }

    fun solution(): Int {
        var minimumCost = 0

        routes.sort()

        for (route in routes) {
            if (isConnected(route.x, route.y)) continue
            mergeGraph(route.x, route.y)
            minimumCost += route.dist
        }

        return routes.sumOf { it.dist } - minimumCost
    }

    private fun getParent(index: Int): Int {
        if (parents[index] == index) return index
        parents[index] = getParent(parents[index])
        return parents[index]
    }

    private fun mergeGraph(x: Int, y: Int) {
        val aParent = getParent(x)
        val bParent = getParent(y)
        if (aParent < bParent) {
            parents[bParent] = aParent
        } else {
            parents[aParent] = bParent
        }
    }

    private fun isConnected(x: Int, y: Int): Boolean {
        return getParent(x) == getParent(y)
    }
}
