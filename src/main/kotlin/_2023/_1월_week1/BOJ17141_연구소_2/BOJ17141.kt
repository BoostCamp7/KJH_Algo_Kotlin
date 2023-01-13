package _2023._1월_week1.BOJ17141_연구소_2

/*
    https://www.acmicpc.net/problem/17141

    조합(DFS)
    BFS

    DFS로 조합 구하기 (BOJ19942 에서 사용한 방식에 개수 확인하는 로직만 추가)
    바이러스를 놓을 수 있는 모든 조합에 대해 BFS 수행
    BFS 수행하여 최소 시간 갱신
    모든 빈 칸에 퍼트릴 수 없는 경우는 제외
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val place = List(n) {
        readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    }

    println(Solution().solution(n, m, place))
}

data class Point(
    val row: Int,
    val col: Int
)

class Solution {
    private var n = 0
    private var m = 0
    private lateinit var place: List<IntArray>

    private val dy = intArrayOf(1, -1, 0, 0)
    private val dx = intArrayOf(0, 0, 1, -1)

    private val virusList = ArrayList<Point>()
    private var result = Int.MAX_VALUE

    fun solution(n: Int, m: Int, place: List<IntArray>): Int {
        this.n = n
        this.m = m
        this.place = place
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (place[i][j] == 2) virusList.add(Point(i, j))
            }
        }

        val selected = ArrayDeque<Int>()
        combination(0, selected)

        return if (result == Int.MAX_VALUE) -1 else result
    }

    private fun bfs(selected: ArrayDeque<Int>) {
        val visited = List(n) { row ->
            IntArray(n) { col ->
                if (place[row][col] == 1) -2 else -1
            }
        }

        val queue = ArrayDeque<Point>()
        for (index in selected) {
            val virus = virusList[index]
            queue.addLast(virus)
            visited[virus.row][virus.col] = 0
        }

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            for (i in 0 until 4) {
                val nRow = current.row + dy[i]
                val nCol = current.col + dx[i]

                if (nRow in 0 until n && nCol in 0 until n) {
                    if (visited[nRow][nCol] == -1) {
                        visited[nRow][nCol] = visited[current.row][current.col] + 1
                        queue.addLast(Point(nRow, nCol))
                    }
                }
            }
        }

        if (visited.any { intArray -> intArray.any { it == -1 } }.not()) {
            val max = visited.maxOf { intArray -> intArray.maxOf { it } }
            if (max < result) result = max
        }
    }

    private fun combination(index: Int, selected: ArrayDeque<Int>) {
        if (selected.size == m) {
            bfs(selected)
        }

        if (index == virusList.size) return

        selected.addLast(index)
        combination(index + 1, selected)

        selected.removeLast()
        combination(index + 1, selected)
    }
}
