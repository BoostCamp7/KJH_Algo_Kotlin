package _2023._6월_week4.BOJ23740_버스_노선_개편하기

/*
    https://www.acmicpc.net/problem/23740

    정렬
 */

import kotlin.math.max
import kotlin.math.min

fun main() {
    val n = readln().toInt()
    val routes = Array(n) {
        val (s, e, c) = readln().trim().split(" ").map { it.toInt() }
        Route(s, e, c)
    }

    solution(n, routes)
}

data class Route(
    val s: Int,
    val e: Int,
    val c: Int,
) : Comparable<Route> {
    override fun compareTo(other: Route): Int {
        return if (s != other.s) {
            s.compareTo(other.s)
        } else {
            e.compareTo(other.e)
        }
    }

    override fun toString(): String = "$s $e $c"
}

fun solution(n: Int, routes: Array<Route>) {
    var routeCount = 0
    val routeResult = ArrayList<Route>()

    routes.sort()
    var firstStart = routes[0].s
    var lastEnd = -1
    var minCost = Int.MAX_VALUE

    for (i in routes.indices) {
        lastEnd = max(lastEnd, routes[i].e)
        minCost = min(minCost, routes[i].c)

        if (i == routes.lastIndex) {
            routeCount++
            routeResult.add(Route(firstStart, lastEnd, minCost))
            break
        }

        if (routes[i + 1].s > lastEnd) {
            routeCount++
            routeResult.add(Route(firstStart, lastEnd, minCost))
            firstStart = routes[i + 1].s
            lastEnd = -1
            minCost = Int.MAX_VALUE
        }
    }

    println(routeCount)
    println(routeResult.joinToString("\n"))
}
