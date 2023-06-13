package _2023._6월_week2.BOJ17298_오큰수

/*
    https://www.acmicpc.net/problem/17298

    Stack

    N이 크기때문에 O(N^2) 으론 불가능
 */

fun main() {
    val n = readln().toInt()
    val A = readln().trim().split(" ").map { it.toInt() }.toIntArray()

    solution(n, A)
    println(A.joinToString(" "))
}

fun solution(n: Int, A: IntArray) {
    val stack = ArrayDeque<Int>()

    for (i in 0 until n) {
        while (stack.isNotEmpty()) {
            if (A[i] > A[stack.last()]) {
                A[stack.removeLast()] = A[i]
            } else {
                break
            }
        }
        stack.addLast(i)
    }

    while (stack.isNotEmpty()) {
        A[stack.removeLast()] = -1
    }
}
