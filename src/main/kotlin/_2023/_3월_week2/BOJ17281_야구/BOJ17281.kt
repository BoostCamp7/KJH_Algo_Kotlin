package _2023._3월_week2.BOJ17281_야구

/*
    https://www.acmicpc.net/problem/17281

    완탐
    구현
 */

fun main() {
    val n = readln().toInt()
    val innings = Array(n) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

    println(Solution(n, innings).solution())
}

class Solution(private val n: Int, private val innings: Array<IntArray>) {
    private val lineUp = IntArray(9) { 0 }
    private val isSelected = BooleanArray(9) { false }
    private var result = 0

    fun solution(): Int {
        lineUp[3] = 0
        isSelected[3] = true
        makeLineUp(1)

        return result
    }

    private fun makeLineUp(num: Int) {
        if (num == 9) {
            play()
            return
        }

        for (i in 0 until 9) {
            if (isSelected[i]) continue
            isSelected[i] = true
            lineUp[i] = num
            makeLineUp(num + 1)
            isSelected[i] = false
        }
    }

    private fun play() {
        val base = BooleanArray(3) { false }
        var index = 0
        var outCount = 0
        var score = 0

        for (inning in 0 until n) {
            while (outCount < 3) {
                when (innings[inning][lineUp[index]]) {
                    0 -> { // 아웃
                        outCount++
                    }
                    1 -> { // 안타
                        for (i in 2 downTo 0) {
                            if (base[i]) {
                                if (i == 2) {
                                    score++
                                    base[2] = false
                                } else {
                                    base[i + 1] = true
                                    base[i] = false
                                }
                            }
                        }
                        base[0] = true
                    }
                    2 -> { // 2루타
                        for (i in 2 downTo 0) {
                            if (base[i]) {
                                if (i == 0) {
                                    base[2] = true
                                    base[0] = false
                                } else {
                                    score++
                                    base[i] = false
                                }
                            }
                        }
                        base[1] = true
                    }
                    3 -> { // 3루타
                        score += base.count { it }
                        for (i in 0 until 2) base[i] = false
                        base[2] = true
                    }
                    4 -> { // 홈런
                        score += base.count { it } + 1
                        for (i in 0 until 3) base[i] = false
                    }
                }
                index = (index + 1) % 9
            }
            outCount = 0
            for (i in 0 until 3) base[i] = false
        }

        if (score > result) result = score
    }
}
