package _2023._2월_week3.BOJ2251_물통

/*
    https://www.acmicpc.net/problem/2251

    BFS

    물을 옮기는 모든 경우의 수를 BFS를 이용해 확인 -> A의 물 양이 0인 경우의 C의 물 양을 Set에 추가
 */

fun main() {
    val (a, b, c) = readln().trim().split(" ").map { it.toInt() }

    println(Solution().solution(a, b, c).joinToString(" "))
}

data class Water(
    val a: Int,
    val b: Int,
    val c: Int
)

class Solution {
    fun solution(a: Int, b: Int, c: Int): IntArray {
        val answerSet = HashSet<Int>()

        val visited = Array(a + 1) { Array(b + 1) { BooleanArray(c + 1) { false } } }
        val queue = ArrayDeque<Water>()
        queue.addLast(Water(0, 0, c))

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            if (visited[current.a][current.b][current.c]) continue
            visited[current.a][current.b][current.c] = true

            if (current.a == 0) answerSet.add(current.c)

            // a -> b
            if (current.a + current.b > b) queue.addLast(Water(current.a + current.b - b, b, current.c))
            else queue.addLast(Water(0, current.a + current.b, current.c))

            // a -> c
            if (current.a + current.c > c) queue.addLast(Water(current.a + current.c - c, current.b, c))
            else queue.addLast(Water(0, current.b, current.a + current.c))

            // b -> a
            if (current.b + current.a > a) queue.addLast(Water(a, current.b + current.a - a, current.c))
            else queue.addLast(Water(current.b + current.a, 0, current.c))

            // b -> c
            if (current.b + current.c > c) queue.addLast(Water(current.a, current.b + current.c - c, c))
            else queue.addLast(Water(current.a, 0, current.b + current.c))

            // c -> a
            if (current.c + current.a > a) queue.addLast(Water(a, current.b, current.c + current.a - a))
            else queue.addLast(Water(current.c + current.a, current.b, 0))

            // c -> b
            if (current.c + current.b > b) queue.addLast(Water(current.a, b, current.c + current.b - b))
            else queue.addLast(Water(current.a, current.c + current.b, 0))
        }

        return answerSet.sorted().toIntArray()
    }
}
