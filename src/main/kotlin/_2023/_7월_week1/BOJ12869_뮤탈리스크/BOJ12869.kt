package _2023._7월_week1.BOJ12869_뮤탈리스크

/*
    https://www.acmicpc.net/problem/12869

    DP, 재귀

    BFS 풀이시 메모리 초과
    scv hp 배열을 정렬하지 않고 재귀호출시 틀리고 정렬해야 정답?
    -> 정렬하면 반복 횟수를 줄일 수 있기도 함
 */

import kotlin.math.max
import kotlin.math.min

fun main() {
    val n = readln().toInt()
    val scv = readln().trim().split(" ").map { it.toInt() }.toIntArray()

    println(Solution(n, scv).solution())
}

class Solution(private val n: Int, private val scv: IntArray) {
    private val calCount = if (n == 3) 6 else n
    private val attackCount = Array(61) { Array(61) { IntArray(61) { Int.MAX_VALUE } } }
    private val damage = arrayOf(
        intArrayOf(9, 3, 1),
        intArrayOf(3, 9, 1),
        intArrayOf(9, 1, 3),
        intArrayOf(3, 1, 9),
        intArrayOf(1, 3, 9),
        intArrayOf(1, 9, 3),
    )

    fun solution(): Int {
        val scvArray = IntArray(3) { 0 }
        for (i in scv.indices) {
            scvArray[i] = scv[i]
        }

        return calculateAttackCount(scvArray, 0)
    }

    private fun calculateAttackCount(scv: IntArray, count: Int): Int {
        if (scv[0] == 0 && scv[1] == 0 && scv[2] == 0) return count

        scv.sortDescending()
        if (attackCount[scv[0]][scv[1]][scv[2]] != Int.MAX_VALUE) return attackCount[scv[0]][scv[1]][scv[2]]

        for (i in 0 until calCount) {
            val next = IntArray(3) { 0 }
            next[0] = max(scv[0] - damage[i][0], 0)
            next[1] = max(scv[1] - damage[i][1], 0)
            next[2] = max(scv[2] - damage[i][2], 0)
            attackCount[scv[0]][scv[1]][scv[2]] =
                min(attackCount[scv[0]][scv[1]][scv[2]], calculateAttackCount(next, count + 1))
        }

        return attackCount[scv[0]][scv[1]][scv[2]]
    }
}

/* BFS 이용 - 메모리 초과 => Queue 때문으로 판단
    val queue = ArrayDeque<IntArray>()

    queue.addLast(scvArray)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()

        for (i in 0 until calCount) {
            val next = IntArray(3) { 0 }
            next[0] = if (current[0] - damage[i][0] < 0) 0 else current[0] - damage[i][0]
            next[1] = if (current[1] - damage[i][1] < 0) 0 else current[1] - damage[i][1]
            next[2] = if (current[2] - damage[i][2] < 0) 0 else current[2] - damage[i][2]

            if (attackCount[next[0]][next[1]][next[1]] != 0) continue

            if (next.sumOf { it } == 0) return attackCount[current[0]][current[1]][current[2]] + 1
            attackCount[next[0]][next[1]][next[2]] = attackCount[current[0]][current[1]][current[2]] + 1
            queue.addLast(next)
        }
    }

    return attackCount[0][0][0]
 */
