package _2023._8월_week1.BOJ1493_박스_채우기

/*
    Greedy
    분할정복

    https://www.acmicpc.net/problem/1493
 */

fun main() {
    val (length, width, height) = readln().trim().split(" ").map { it.toInt() }
    val n = readln().toInt()
    val cubes = IntArray(n)
    repeat(n) {
        val cubeCount = readln().trim().split(" ").map { it.toInt() }.toIntArray()
        cubes[cubeCount[0]] = cubeCount[1]
    }

    println(Solution(length, width, height, n, cubes).solution())
}

class Solution(
    private val length: Int,
    private val width: Int,
    private val height: Int,
    private val n: Int,
    private val cubes: IntArray,
) {
    private val powers = IntArray(n) { (1 shl it) }
    private var isPossible = true
    private var answer = 0

    fun solution(): Int {
        fillCube(length, width, height)

        return if (isPossible) answer else -1
    }

    private fun fillCube(l: Int, w: Int, h: Int) {
        if (l == 0 || w == 0 || h == 0) return
        if (isPossible.not()) return

        var flag = false

        for (i in n - 1 downTo 0) {
            if (cubes[i] == 0) continue
            if (powers[i] > l || powers[i] > w || powers[i] > h) continue

            answer++
            flag = true
            cubes[i]--

            fillCube(powers[i], powers[i], h - powers[i])
            fillCube(l - powers[i], w, h)
            fillCube(powers[i], w - powers[i], h)
            break
        }

        if (flag.not()) {
            isPossible = false
            return
        }
    }
}
