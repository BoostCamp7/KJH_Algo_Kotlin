package _2022._12월_week2.BOJ2668_숫자고르기

/*
    DFS
    사이클이 생기는 숫자들을 set에 저장
*/

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val numList = IntArray(n) { readLine().toInt() }

    val result = Solution().solution(n, numList)
    println(result.size)
    result.forEach {
        println(it)
    }
}

class Solution {

    private lateinit var graph: IntArray
    private lateinit var visited: BooleanArray
    private val resultSet = HashSet<Int>()

    fun solution(n: Int, numList: IntArray): IntArray {
        graph = IntArray(n + 1) { index -> if (index > 0) numList[index - 1] else 0 }
        visited = BooleanArray(n + 1) { false }

        for (i in 1..n) {
            dfs(i, graph[i])
        }

        return resultSet.toIntArray()
    }

    private fun dfs(first: Int, next: Int): Boolean {
        if (visited[next]) {
            return if (first == next) {
                resultSet.add(next)
                true
            } else {
                false
            }
        }

        visited[next] = true
        return if (dfs(first, graph[next])) {
            resultSet.add(next)
            resultSet.add(graph[next])
            true
        } else {
            false
        }
    }
}
