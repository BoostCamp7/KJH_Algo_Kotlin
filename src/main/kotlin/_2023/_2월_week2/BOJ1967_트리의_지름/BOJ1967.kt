package _2023._2월_week2.BOJ1967_트리의_지름

/*
    https://www.acmicpc.net/problem/1967

    DFS

    루트(1)에서 가장 먼 노드 찾기 -> 해당 노드에서 가장 먼 노드 찾기
 */

fun main() {
    val n = readln().toInt()
    val edges = Array(n - 1) {
        val (parent, child, weight) = readln().trim().split(" ").map { it.toInt() }
        Edge(parent, child, weight)
    }

    println(Solution(n, edges).solution())
}

data class Edge(
    val parent: Int,
    val child: Int,
    val weight: Int
)

data class Node(
    val node: Int,
    val weight: Int
)

class Solution(private val n: Int, private val edges: Array<Edge>) {
    private val tree = Array(n + 1) { ArrayList<Node>() }
    private var max = 0
    private var maxIndex = 1

    fun solution(): Int {
        for (edge in edges) {
            tree[edge.parent].add(Node(edge.child, edge.weight))
            tree[edge.child].add(Node(edge.parent, edge.weight))
        }

        val visited = BooleanArray(n + 1) { false }

        dfs(1, 0, visited.clone())
        max = 0
        dfs(maxIndex, 0, visited.clone())

        return max
    }

    fun dfs(node: Int, sum: Int, visited: BooleanArray) {
        visited[node] = true

        if (sum > max) {
            max = sum
            maxIndex = node
        }

        for (next in tree[node]) {
            if (visited[next.node].not()) {
                dfs(next.node, sum + next.weight, visited)
            }
        }
    }
}
