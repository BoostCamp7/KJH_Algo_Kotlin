package _2023._9월_week4.BOJ20311_화학_실험

/*
    https://www.acmicpc.net/problem/20311

    정렬
    Greedy

    counts[i] > (n + 1) / 2 이면 불가능
    가능한 경우 개수가 많은 색부터 번갈아 배치 -> 그냥 원래 순서대로 해도 가능

    40점 획득
 */

fun main() {
    val (n, k) = readln().trim().split(" ").map { it.toInt() }
    val counts = readln().trim().split(" ").map { it.toInt() }.toIntArray()

    println(solution(n, k, counts))
}

data class Color(
    val num: Int,
    var count: Int,
)

fun solution(n: Int, k: Int, counts: IntArray): String {
    val half = (n + 1) / 2
    for (count in counts) {
        if (count > half) return "-1"
    }

    val answer = IntArray(n) { 0 }
    val colors = Array(k) { Color(it + 1, counts[it]) }

    colors.sortByDescending { it.count }

    var index = 0

    for (i in 0 until k) {
        repeat(colors[i].count) {
            answer[index] = colors[i].num
            index += 2

            if (index >= n) {
                index = 1
            }
        }
    }

    return answer.joinToString(" ")
}

/* 정렬 없는 풀이 : 서브태스크2 런타임 오류
fun solution(n: Int, k: Int, counts: IntArray): String {
    val half = (n + 1) / 2
    for (count in counts) {
        if (count > half) return "-1"
    }

    val answer = IntArray(n) { 0 }

    var evenIdx = 0
    var oddIdx = 1

    for (i in 0 until k) {
        repeat(counts[i]) {
            if (i % 2 == 0 && evenIdx < n) {
                answer[evenIdx] = i + 1
                evenIdx += 2
            } else {
                answer[oddIdx] = i + 1
                oddIdx += 2
            }
        }
    }

    return answer.joinToString(" ")
}
 */
