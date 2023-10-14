package _2023._10월_week2.BOJ12970_AB

/*
    https://www.acmicpc.net/problem/12970

    Greedy
    수학

    참고한 방법 : https://kyu9341.github.io/algorithm/2020/04/03/algorithm12970/
 */

fun main() {
    val (n, k) = readln().trim().split(" ").map { it.toInt() }
    println(solution(n, k))
}

fun solution(n: Int, k: Int): String {
    if ((n / 2) * ((n + 1) / 2) < k) {
        return "-1"
    }
    val answer = CharArray(n) { 'B' }

    if (k == 0) {
        answer[answer.lastIndex] = 'A'
        return answer.joinToString("")
    }

    var a = 1
    var b = n - 1

    while (a * b < k) {
        a++
        b--
    }

    for (i in 0 until a - 1) {
        answer[i] = 'A'
    }

    val index = (a - 1) + ((a * b) - k)
    answer[index] = 'A'

    return answer.joinToString("")
}
