package _2023._10월_week2.BOJ25381_ABBC

/*
    https://www.acmicpc.net/problem/25381

    1. A와 그 뒤에 있는 B를 지운다.
    2. B와 그 뒤에 있는 C를 지운다.

    2번 경우를 최대한 많이 해 B를 최대한 많이 지울 수 있도록 하는 게 중요
    2번 우선으로 판단, 남은 B로 1번 판단
 */

fun main() {
    val str = readln()
    println(solution(str))
}

fun solution(str: String): Int {
    var answer = 0
    val aIndexes = ArrayDeque<Int>()
    val bIndexes = ArrayDeque<Int>()

    for (i in str.indices) {
        when (str[i]) {
            'A' -> {
                aIndexes.addLast(i)
            }
            'B' -> {
                bIndexes.addLast(i)
            }
            'C' -> {
                if (bIndexes.isNotEmpty()) {
                    bIndexes.removeFirst()
                    answer++
                }
            }
        }
    }

    while (aIndexes.isNotEmpty() && bIndexes.isNotEmpty()) {
        if (aIndexes.first() < bIndexes.first()) {
            answer++
            aIndexes.removeFirst()
        }
        bIndexes.removeFirst()
    }

    return answer
}
