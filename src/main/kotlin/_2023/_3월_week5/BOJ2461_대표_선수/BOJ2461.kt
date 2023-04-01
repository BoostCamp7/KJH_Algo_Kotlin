package _2023._3월_week5.BOJ2461_대표_선수

/*
    https://www.acmicpc.net/problem/2461

    우선순위 큐

    포인터를 이용한 풀이는 시간초과로 실패
 */

import java.util.PriorityQueue
import kotlin.math.min

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val classes = Array(n) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

    println(Solution().solution(n, m, classes))
}

data class Player(
    val classNum: Int,
    val playerIndex: Int,
    val ability: Int
) : Comparable<Player> {
    override fun compareTo(other: Player): Int {
        return ability.compareTo(other.ability)
    }
}

class Solution {
    fun solution(n: Int, m: Int, classes: Array<IntArray>): Int {
        var answer = Int.MAX_VALUE

        val playerQueue = PriorityQueue<Player>()

        for (i in 0 until n) {
            classes[i].sort()
            playerQueue.add(Player(i, 0, classes[i][0]))
        }

        while (true) {
            val minPlayer = playerQueue.poll()

            answer = min(playerQueue.maxOf { it.ability } - minPlayer.ability, answer)

            if (minPlayer.playerIndex == m - 1) break

            val nIndex = minPlayer.playerIndex + 1
            playerQueue.add(Player(minPlayer.classNum, nIndex, classes[minPlayer.classNum][nIndex]))
        }

        return answer
    }
}

// class 별 pointer 사용 => 1% 이후 바로 시간초과
/*
class Solution2 {
    fun solution(n: Int, m: Int, classes: Array<IntArray>): Int {
        var answer = Int.MAX_VALUE
        val pointers = IntArray(n) { 0 }

        for (i in 0 until n) classes[i].sort()

        while (true) {
            var max = 0
            var min = Int.MAX_VALUE
            var minClass = 0

            for (i in 0 until n) {
                if (min > classes[i][pointers[i]]) {
                    min = classes[i][pointers[i]]
                    minClass = i
                }
                max = max(classes[i][pointers[i]], max)
            }

            answer = min(max - min, answer)
            pointers[minClass]++

            if (pointers[minClass] == m) break
        }

        return answer
    }
}
 */
