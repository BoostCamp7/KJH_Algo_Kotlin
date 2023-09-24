package _2023._7월_week3.BOJ9466_텀_프로젝트

/*
    https://www.acmicpc.net/problem/9466

    DFS

    사이클 찾기
 */

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        val choices = readln().trim().split(" ").map { it.toInt() - 1 }.toIntArray()

        println(Solution(n, choices).solution())
    }
}

class Solution(private val n: Int, private val choices: IntArray) {
    private val visited = BooleanArray(n) { false }
    private val isFinished = BooleanArray(n) { false }
    private var count = 0

    fun solution(): Int {
        for (i in 0 until n) {
            if (isFinished[i].not()) dfs(i)
        }

        return n - count
    }

    private fun dfs(student: Int) {
        if (visited[student]) {
            isFinished[student] = true
            count++
        }

        visited[student] = true

        if (isFinished[choices[student]].not()) {
            dfs(choices[student])
        }

        visited[student] = false
        isFinished[student] = true
    }
}
