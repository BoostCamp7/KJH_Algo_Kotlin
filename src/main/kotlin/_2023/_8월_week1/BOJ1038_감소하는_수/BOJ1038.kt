package _2023._8월_week1.BOJ1038_감소하는_수

/*
    https://www.acmicpc.net/problem/1038

    완전탐색

    0 ~ 1022 의 총 1023개의 경우의 수만 가능 (0 ~ 9876543210)
    n >= 1023일 경우 -1 출력
 */

fun main() {
    val n = readln().toInt()

    println(solution(n))
}

fun solution(n: Int): String {
    if (n >= 1023) return "-1"
    val answerArray = ArrayList<String>()
    var index = 1

    for (i in 0..9) {
        answerArray.add(i.toString())
    }

    while (answerArray.lastIndex < n) {
        val last = answerArray[index].last().digitToInt()

        for (i in 0 until last) {
            answerArray.add(answerArray[index] + i.toString())
        }

        index++
    }

    return answerArray[n]
}
