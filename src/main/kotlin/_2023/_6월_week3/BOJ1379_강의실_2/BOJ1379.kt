package _2023._6월_week3.BOJ1379_강의실_2

/*
    https://www.acmicpc.net/problem/1379

    정렬
    PriorityQueue
    Greedy

    Java의 PriorityQueue로는 정렬이 제대로 되지 않아 ArrayDeque를 사용하고 따로 sort() 했음
 */

import kotlin.math.max
import kotlin.math.min

fun main() {
    val n = readln().toInt()
    val lectures = Array(n) {
        val (num, start, end) = readln().trim().split(" ").map { it.toInt() }
        Lecture(num, start, end)
    }

    println(solution(n, lectures).joinToString("\n"))
}

data class Lecture(
    val num: Int,
    val start: Int,
    val end: Int
)

data class Data(
    val isStart: Boolean,
    val time: Int,
    val endAt: Int,
    val lectureNum: Int
) : Comparable<Data> {
    override fun compareTo(other: Data): Int {
        return if (time != other.time) time.compareTo(other.time)
        else if (isStart != other.isStart) isStart.compareTo(other.isStart)
        else endAt.compareTo(other.endAt)
    }
}

fun solution(n: Int, lectures: Array<Lecture>): IntArray {
    val answer = IntArray(n + 1) { 0 }
    val roomState = IntArray(n + 1) { 0 }
    val queue = ArrayDeque<Data>()
    var k = 1

    for (lecture in lectures) {
        queue.addLast(Data(true, lecture.start, lecture.end, lecture.num))
        queue.addLast(Data(false, lecture.end, lecture.end, lecture.num))
    }
    queue.sort()

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()

        if (current.isStart) {
            roomState[k] = current.lectureNum
            answer[current.lectureNum] = k
            answer[0] = max(answer[0], k)
            while (true) {
                k++
                if (k > n || roomState[k] == 0) break
            }
        } else {
            val index = roomState.indexOf(current.lectureNum)
            roomState[index] = 0
            k = min(k, index)
        }
    }

    return answer
}
