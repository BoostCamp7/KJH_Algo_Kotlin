package _2023._9월_week3.BOJ16936_나3곱2

/*
    https://www.acmicpc.net/problem/16936

    백트래킹
 */

fun main() {
    val n = readln().toInt()
    val B = readln().trim().split(" ").map { it.toLong() }.toLongArray()

    println(Solution(n, B).solution())
}

class Solution(private val n: Int, private val B: LongArray) {
    private var isFinished = false
    private val visited = BooleanArray(n)
    private val answer = LongArray(n)

    fun solution(): String {
        B.sort()

        for (i in 0 until n) {
            backTracking(i, 0)
        }

        return answer.joinToString(" ")
    }

    private fun backTracking(index: Int, depth: Int) {
        if (isFinished) return
        if (visited[index]) return

        visited[index] = true
        answer[depth] = B[index]

        if (depth == n - 1) {
            isFinished = true
            return
        }

        if (B[index] % 3 == 0L) {
            for (i in index - 1 downTo 0) {
                if (B[i] == B[index] / 3) {
                    backTracking(i, depth + 1)
                    break
                }
            }
        }

        for (i in index + 1 until n) {
            if (B[i] == B[index] * 2) {
                backTracking(i, depth + 1)
                break
            }
        }

        visited[index] = false
    }
}
