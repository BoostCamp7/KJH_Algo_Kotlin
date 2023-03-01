package _2023._2월_week4.BOJ14267_회사_문화_1

/*
    https://www.acmicpc.net/problem/14267

    Tree (Graph)
    DP
    DFS
 */

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val parent = readln().trim().split(" ").map { it.toInt() }
    val compliments = IntArray(n)
    repeat(m) {
        val (employee, weight) = readln().trim().split(" ").map { it.toInt() }
        compliments[employee - 1] += weight
    }

    println(Solution(n, m, parent, compliments).solution().joinToString(" "))
}

class Solution(
    private val n: Int,
    private val m: Int,
    private val parent: List<Int>,
    private val compliments: IntArray
) {
    private val tree = Array(n) { ArrayList<Int>() }

    fun solution(): IntArray {
        for (i in 1 until n) {
            tree[parent[i] - 1].add(i)
        }

        dfs(0)

        return compliments
    }

    private fun dfs(index: Int) {
        for (next in tree[index]) {
            compliments[next] += compliments[index]
            dfs(next)
        }
    }
}
