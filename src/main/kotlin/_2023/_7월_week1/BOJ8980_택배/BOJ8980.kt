package _2023._7월_week1.BOJ8980_택배

/*
    https://www.acmicpc.net/problem/8980

    Greedy
    정렬

    목적지 번호로 정렬
 */

import kotlin.math.max
import kotlin.math.min

fun main() {
    val (n, c) = readln().trim().split(" ").map { it.toInt() }
    val m = readln().toInt()
    val deliveries = Array(m) {
        val (start, dest, count) = readln().trim().split(" ").map { it.toInt() }
        Info(start, dest, count)
    }

    println(solution(n, c, m, deliveries))
}

data class Info(
    val start: Int,
    val dest: Int,
    val count: Int
) : Comparable<Info> {
    override fun compareTo(other: Info): Int {
        return if (dest != other.dest) dest.compareTo(other.dest)
        else other.count.compareTo(count)
    }
}

fun solution(n: Int, c: Int, m: Int, deliveries: Array<Info>): Int {
    var answer = 0
    val truck = IntArray(n + 1) { 0 }

    deliveries.sort()
    for (info in deliveries) {
        var maxCount = 0
        for (i in info.start until info.dest) {
            maxCount = max(maxCount, truck[i])
        }

        val boxCount = min(info.count, c - maxCount)
        for (i in info.start until info.dest) {
            truck[i] += boxCount
        }

        answer += boxCount
    }

    return answer
}
