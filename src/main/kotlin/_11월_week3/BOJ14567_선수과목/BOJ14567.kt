package _11월_week3.BOJ14567_선수과목

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val subjects = Array(m) {
        val (a, b) = readLine()
            .trim()
            .split(" ")
            .map { it.toInt() }
        Subject(a, b)
    }

    print(Solution().solution(n, m, subjects))
}

data class Subject(
    val a: Int,     // 선수과목
    val b: Int
)

class Solution {
    fun solution(n: Int, m: Int, subjects: Array<Subject>) {

    }
}
