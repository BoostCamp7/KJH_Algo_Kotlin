package _2023._5월_week4.BOJ1774_우주신과의_교감

/*
    https://www.acmicpc.net/problem/1774

    MST
    크루스칼


 */

import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val points = Array(n) {
        val (x, y) = readln().trim().split(" ").map { it.toInt() }
        Point(x, y)
    }
    val routes = Array(m) {
        val (from, to) = readln().trim().split(" ").map { it.toInt() - 1 }
        Route(from, to, points[from].calculateDistance(points[to]))
    }

    println(String.format("%.2f", Solution(n, m, points, routes).solution()))
}

data class Point(
    val x: Int,
    val y: Int,
) {
    fun calculateDistance(other: Point): Double {
        return sqrt((this.x - other.x).toDouble().pow(2) + (this.y - other.y).toDouble().pow(2))
    }
}

data class Route(
    val from: Int,
    val to: Int,
    val dist: Double,
) : Comparable<Route> {
    override fun compareTo(other: Route): Int {
        return this.dist.compareTo(other.dist)
    }
}

class Solution(
    private val n: Int,
    private val m: Int,
    private val points: Array<Point>,
    private val routes: Array<Route>,
) {

    private val parents = IntArray(n) { it }

    fun solution(): Double {
        var answer: Double = 0.0
        val newRoutes = ArrayDeque<Route>()

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                newRoutes.addLast(Route(i, j, points[i].calculateDistance(points[j])))
            }
        }
        newRoutes.sort()

        for (route in routes) {
            mergeGraph(route.from, route.to)
        }

        for (route in newRoutes) {
            if (isConnected(route.from, route.to)) continue
            mergeGraph(route.from, route.to)
            answer += route.dist
        }

        return answer
    }

    private fun getParent(index: Int): Int {
        if (parents[index] == index) return index
        parents[index] = getParent(parents[index])
        return parents[index]
    }

    private fun mergeGraph(a: Int, b: Int) {
        val aParent = getParent(a)
        val bParent = getParent(b)
        if (aParent < bParent) {
            parents[bParent] = aParent
        } else {
            parents[aParent] = bParent
        }
    }

    private fun isConnected(a: Int, b: Int): Boolean {
        return getParent(a) == getParent(b)
    }
}
