package _2023._1월_week1.BOJ6593_상범_빌딩

/*
    https://www.acmicpc.net/problem/6593

    BFS

    3차원 길찾기
 */

fun main() = with(System.`in`.bufferedReader()) {
    while (true) {
        val (L, R, C) = readLine().trim().split(" ").map { it.toInt() }

        if (L == 0 && R == 0 && C == 0) break

        var startPoint = Point(0, 0, 0)
        var endPoint = Point(0, 0, 0)

        val building = List(L) { l ->
            val row = List(R) { r ->
                val col = readLine().trim().toCharArray()

                val start = col.indexOf('S')
                val end = col.indexOf('E')
                if (start != -1) startPoint = Point(l, r, start)
                if (end != -1) endPoint = Point(l, r, end)

                col
            }
            readLine() // empty line
            row
        }

        println(Solution().solution(L, R, C, building, startPoint, endPoint))
    }
}

data class Point(
    val l: Int,
    val r: Int,
    val c: Int
)

class Solution {
    fun solution(
        L: Int,
        R: Int,
        C: Int,
        building: List<List<CharArray>>,
        startPoint: Point,
        endPoint: Point
    ): String {
        val dy = intArrayOf(1, -1, 0, 0, 0, 0)
        val dx = intArrayOf(0, 0, 1, -1, 0, 0)
        val dz = intArrayOf(0, 0, 0, 0, 1, -1)

        val visited = List(L) {
            List(R) {
                IntArray(C) { -1 }
            }
        }

        val queue = ArrayDeque<Point>()
        queue.addLast(startPoint)
        visited[startPoint.l][startPoint.r][startPoint.c] = 0

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            for (i in 0 until 6) {
                val nL = current.l + dy[i]
                val nR = current.r + dx[i]
                val nC = current.c + dz[i]

                if (nL in 0 until L && nR in 0 until R && nC in 0 until C) {
                    if (building[nL][nR][nC] != '#' && visited[nL][nR][nC] == -1) {
                        val time = visited[current.l][current.r][current.c] + 1

                        if (building[nL][nR][nC] == 'E') {
                            if (time < visited[nL][nR][nC] || visited[nL][nR][nC] == -1) {
                                visited[nL][nR][nC] = time
                            }
                        } else {
                            visited[nL][nR][nC] = time
                            queue.addLast(Point(nL, nR, nC))
                        }
                    }
                }
            }
        }

        return if (visited[endPoint.l][endPoint.r][endPoint.c] == -1) {
            "Trapped!"
        } else {
            "Escaped in ${visited[endPoint.l][endPoint.r][endPoint.c]} minute(s)."
        }
    }
}
