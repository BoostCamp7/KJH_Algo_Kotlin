package _2023._6월_week2.BOJ18119_단어_암기

/*
    https://www.acmicpc.net/problem/18119

    비트마스킹

    BooleanArray 사용 -> 6% 시간초과
    bit shift -> 성공
 */

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val words = Array(n) { readln() }
    val queries = Array(m) {
        val (num, alpha) = readln().trim().split(" ")
        Query(num.toInt(), alpha[0])
    }

    println(solution(n, m, words, queries).joinToString("\n"))
}

data class Query(
    val num: Int,
    val alpha: Char,
)

fun solution(n: Int, m: Int, words: Array<String>, queries: Array<Query>): IntArray {
    val answer = ArrayList<Int>()
    val binaryWords = IntArray(n) { 0 }
    val aCode = 'a'.code
    var remembered = (1 shl 26) - 1

    for (i in 0 until n) {
        for (alpha in words[i]) {
            binaryWords[i] = binaryWords[i] or (1 shl (alpha.code - aCode))
        }
    }

    for (query in queries) {
        when (query.num) {
            1 -> {
                remembered = remembered and (1 shl (query.alpha.code - aCode)).inv()
                answer.add(binaryWords.count { it and remembered == it })
            }
            2 -> {
                remembered = remembered or (1 shl (query.alpha.code - aCode))
                answer.add(binaryWords.count { it and remembered == it })
            }
        }
    }

    return answer.toIntArray()
}
