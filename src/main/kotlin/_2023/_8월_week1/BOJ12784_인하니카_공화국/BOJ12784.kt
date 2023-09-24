package _2023._8월_week1.BOJ12784_인하니카_공화국

/*
    https://www.acmicpc.net/problem/12784

    Tree
    DFS

반례 :
1
1 0
answer = 0
 */

import kotlin.math.min

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (n, m) = readln().trim().split(" ").map { it.toInt() }
        val bridges = Array(m) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

        println(Solution(n, m, bridges).solution())
    }
}

data class Node(
    val dest: Int,
    val dynamite: Int
)

class Solution(private val n: Int, private val m: Int, private val bridges: Array<IntArray>) {
    private val tree = Array(n + 1) { ArrayList<Node>() }

    fun solution(): Int {
        for (bridge in bridges) {
            tree[bridge[0]].add(Node(bridge[1], bridge[2]))
            tree[bridge[1]].add(Node(bridge[0], bridge[2]))
        }

        val answer = dfs(1, 0, Int.MAX_VALUE)

        return if (answer == Int.MAX_VALUE) 0 else answer
    }

    private fun dfs(current: Int, parent: Int, dynamite: Int): Int {
        var sum = 0

        for (node in tree[current]) {
            if (node.dest == parent) continue
            sum += dfs(node.dest, current, node.dynamite)
        }

        if (sum == 0) {
            sum = dynamite
        }

        return min(sum, dynamite)
    }
}
