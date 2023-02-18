package _2023._2월_week3.BOJ2179_비슷한_단어

/*
    https://www.acmicpc.net/problem/2179

    37퍼 틀림

<반례>

9
ab
is
lunch
for
most
waits
until
two
ac

answer = ab, ac


4
aa
bb
bc
aj

answer = aa, aj

 */

fun main() {
    val n = readln().trim().toInt()
    val wordArray = Array(n) { readln().trim() }

    println(Solution().solution(n, wordArray).joinToString("\n"))
}

class Solution {
    fun solution(n: Int, words: Array<String>): Array<String> {
        val wordArray = words.sorted()

        var similarCount = -1
        var first = ""
        var second = ""

        for (i in 0 until n - 1) {
            val maxLength = minOf(wordArray[i].length, wordArray[i + 1].length)
            for (k in 0..maxLength) {
                if (k == maxLength || wordArray[i][k] != wordArray[i + 1][k]) {
                    if (similarCount < k) {
                        similarCount = k
                        first = wordArray[i]
                        second = wordArray[i + 1]
                    }
                    break
                }
            }
        }

        return if (similarCount == 0) arrayOf(words[0], words[1])
        else arrayOf(first, second).sortedBy { words.indexOf(it) }.toTypedArray()
    }
}
