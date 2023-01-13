package _2023._1월_week1.BOJ2533_사회망_서비스

/*
    https://www.acmicpc.net/problem/2533

    DP
    트리

    각각의 노드가 얼리어답터인 경우, 아닌 경우를 나누어 확인
    DFS를 통해 자식 노드를 탐색
    부모 노드가 얼리어답터이면 자식 노드가 얼리어답터인 경우, 아닌 경우 둘 중 최소 얼리어답터 수를 가진 경우를 채택
    부모가 얼리어답터가 아니면 자식 노드는 반드시 얼리어답터여야 함
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val tree = List(n - 1) {
        val (u, v) = readLine().trim().split(" ").map { it.toInt() }
        Edge(u, v)
    }

    println(Solution().solution(n, tree))
}

data class Edge(
    val u: Int,
    val v: Int
)

class Solution {
    private var n = 0
    private lateinit var treeList: List<ArrayList<Int>>
    private lateinit var visited: BooleanArray
    private lateinit var dp: List<IntArray>

    fun solution(n: Int, tree: List<Edge>): Int {
        this.n = n
        treeList = List(n + 1) { ArrayList() }
        for (edge in tree) {
            treeList[edge.u].add(edge.v)
            treeList[edge.v].add(edge.u)
        }
        visited = BooleanArray(n + 1) { false }
        dp = List(n + 1) { IntArray(2) { it } }

        visited[1] = true
        dfs(1)

        return dp[1].minOf { it }
    }

    private fun dfs(u: Int) {
        if (u == n) return
        if (treeList[u].isEmpty()) return

        for (child in treeList[u]) {
            if (visited[child].not()) {
                visited[child] = true
                dfs(child)
                dp[u][0] += dp[child][1]
                dp[u][1] += dp[child].minOf { it }
            }
        }
    }
}

/*
< 시도해 본 반례 >

16
1 2
1 3
1 4
1 5
1 6
2 7
3 8
4 9
5 10
8 11
9 12
9 13
9 14
9 15
9 16

>>5


8
1 2
1 3
1 4
2 5
2 6
4 7
4 8

>>3


9
1 2
1 3
2 4
3 5
3 6
4 7
4 8
4 9

>>3


10
1 2
2 3
2 4
4 6
4 5
6 7
6 8
8 9
8 10

>>4


6
1 2
2 3
2 4
4 5
4 6

>>2


11
1 2
2 3
2 4
2 5
3 6
6 7
4 8
4 9
5 10
1 11

>>5


5
1 5
2 5
3 5
4 5

>>1


9
1 2
2 3
3 4
4 5
2 6
6 7
7 8
1 9

>>4


5
1 2
2 3
3 4
4 5

>>2

 */