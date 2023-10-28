package _2023._10월_week4.BOJ6087_레이저_통신

/*
    https://www.acmicpc.net/problem/6087

    BFS
    다익스트라
    PriorityQueue

    가장 빨리 도착점에 도착하는 게 아닌 가장 적은 거울을 쓰는것이 중요
    반례 모음 : https://bingorithm.tistory.com/2

    80퍼 메모리 초과 -> 반례 : https://www.acmicpc.net/board/view/109356
    -> PriorityQueue와 4차원 Visited로 해결
 */

import java.util.PriorityQueue

fun main() {
    val (w, h) = readln().trim().split(" ").map { it.toInt() }
    val map = Array(h) { readln().toCharArray() }

    println(solution(w, h, map))
}

data class Point(
    val row: Int,
    val col: Int,
    val direction: Int,
    val count: Int,
) : Comparable<Point> {
    override fun compareTo(other: Point): Int {
        return this.count.compareTo(other.count)
    }
}

fun solution(w: Int, h: Int, map: Array<CharArray>): Int {
    val dy = intArrayOf(1, 0, -1, 0)
    val dx = intArrayOf(0, 1, 0, -1)
    val visited = Array(h) { Array(w) { Array(4) { BooleanArray(4) { false } } } }
    val mirrorCounts = Array(h) { IntArray(w) { Int.MAX_VALUE } }
    val queue = PriorityQueue<Point>()

    for (row in 0 until h) {
        for (col in 0 until w) {
            if (map[row][col] == 'C') queue.offer(Point(row, col, 0, 0))
        }
    }

    val end = queue.poll()
    val start = queue.poll()

    for (d in 0 until 4) {
        queue.offer(Point(start.row, start.col, d, 0))
        visited[start.row][start.col][d][d] = true
    }
    mirrorCounts[start.row][start.col] = 0

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        for (d in 0 until 4) {
            val nRow = current.row + dy[d]
            val nCol = current.col + dx[d]
            val nCount = if (d == current.direction) current.count else current.count + 1

            if ((nRow in 0 until h).not() || (nCol in 0 until w).not()) continue
            if ((d + 2) % 4 == current.direction) continue
            if (visited[nRow][nCol][current.direction][d]) continue
            if (nCount > mirrorCounts[nRow][nCol] || map[nRow][nCol] == '*') continue

            visited[nRow][nCol][current.direction][d] = true
            mirrorCounts[nRow][nCol] = nCount
            if (nRow == end.row && nCol == end.col) continue
            queue.offer(Point(nRow, nCol, d, nCount))
        }
    }

    return mirrorCounts[end.row][end.col]
}
