package _2022._11월_week3.BOJ1197_최소_스패닝_트리

/*
   < Kruskal vs Prim >
   V < E => Prim 사용

   Kruskal 학습하고 사용해보기
   - Prim 은 코테 문제에서 쓰기 애매한 경우가 있음
   - Kruskal 부터 학습하는 것이 도움이 될 것
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
) : Comparable<Edge> {

    override fun compareTo(other: Edge): Int {
        return weight.compareTo(other.weight)
    }
}

class Solution {
    fun solution(v: Int, e: Int, edgeList: Array<Edge>): Int {
        return -1
    }
}
