package _10월_17_to_21.BOJ11000_강의실_배정

import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val list = ArrayList<Pair<Int, Int>>()
    for (i in 0 until n) {
        val (start, end) = readLine().split(" ").map { it.toInt() }
        list.add(Pair(start, end))
    }
    list.sortBy { it.first }
    val queue = PriorityQueue<Int>()

    queue.add(list[0].second)

    for (i in 1 until n) {
        if (list[i].first >= queue.first()) {
            queue.remove()
        }
        queue.add(list[i].second)
    }

    print(queue.size)
}