package _2023._7월_week1.BOJ19538_루머

/*
    https://www.acmicpc.net/problem/19538

    BFS
 */

fun main() {
    val n = readln().toInt()
    val graph = Array(n) { readln().trim().split(" ").dropLast(1).map { it.toInt() - 1 }.toIntArray() }
    val m = readln().toInt()
    val spreaders = readln().trim().split(" ").map { it.toInt() - 1 }.toIntArray()

    println(solution(n, m, graph, spreaders).joinToString(" "))
}

fun solution(n: Int, m: Int, graph: Array<IntArray>, spreaders: IntArray): IntArray {
    val answer = IntArray(n) { -1 }
    val neighborCount = IntArray(n) { 0 }
    val queue = ArrayDeque<Int>()

    for (spreader in spreaders) {
        answer[spreader] = 0
        queue.addLast(spreader)
    }

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()

        for (next in graph[current]) {
            neighborCount[next]++

            if (answer[next] == -1 && neighborCount[next] >= (graph[next].size + 1) / 2) {
                answer[next] = answer[current] + 1
                queue.addLast(next)
            }
        }
    }

    return answer
}
