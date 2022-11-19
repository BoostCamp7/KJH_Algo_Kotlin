package _11월_week3.BOJ1197_최소_스패닝_트리

/*
   < Kruskal vs Prim >
   V < E => Prim 사용
*/

fun main() = with(System.`in`.bufferedReader()) {
    val (v, e) = readLine().trim().split(" ").map { it.toInt() }
    val edgeList = Array(e) {
        val (a, b, c) = readLine()
            .trim()
            .split(" ")
            .map { it.toInt() }
        Edge(a, b, c)
    }

    print(Solution().solution(v, e, edgeList))
}

data class Edge(
    val a: Int,
    val b: Int,
    val weight: Int
) : Comparable<Int> {

    override fun compareTo(other: Int): Int {
        return compareTo(weight)
    }
}

class Solution {
    fun solution(v: Int, e: Int, edgeList: Array<Edge>): Int {

        return -1
    }
}
