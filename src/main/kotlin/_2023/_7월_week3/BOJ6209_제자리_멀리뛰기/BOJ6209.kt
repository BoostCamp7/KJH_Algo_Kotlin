package _2023._7월_week3.BOJ6209_제자리_멀리뛰기

/*
    https://www.acmicpc.net/problem/6209

    이분 탐색
    매개변수 탐색

    공유기 설치 문제와 유사
 */

fun main() {
    val (d, n, m) = readln().trim().split(" ").map { it.toInt() }
    val dist = Array(n) { readln().toInt() }.toIntArray()

    println(solution(d, n, m, dist))
}

fun solution(d: Int, n: Int, m: Int, dist: IntArray): Int {
    dist.sort()

    var answer = 0
    var left = 0
    var right = d

    while (left <= right) {
        val mid = (left + right) / 2 // 현재 위치와 다른 섬 사이의 거리
        var current = 0
        var count = 0

        for (i in 0 until n) {
            if (dist[i] - current < mid) { // 섬 제거
                count++
            } else {
                current = dist[i]
            }
        }

        if (count <= m) {
            left = mid + 1
            answer = mid
        } else {
            right = mid - 1
        }
    }

    return answer
}
