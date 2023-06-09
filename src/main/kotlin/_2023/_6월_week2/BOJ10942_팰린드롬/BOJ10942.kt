package _2023._6월_week2.BOJ10942_팰린드롬

/*
    https://www.acmicpc.net/problem/10942

    DP

    s~e 가 팰린드롬이려면 num[s] == num[e] && isPalin[s+1][e-1] == true 여야 함
    맨 끝부터 팰린드롬이 되는지 확인하면서 isPalin 갱신하기
    isPalin[i][i] 는 항상 true

    4% 틀림 -> num[i] == num[i+1] 인지 확인해서 붙은 두 수가 팰린드롬인지도 확인해야 함 (ex: "1 1", "3 3")
 */

fun main() {
    val n = readln().toInt()
    val numbers = readln().trim().split(" ").map { it.toInt() }
    val m = readln().toInt()
    val questions = Array(m) {
        val (s, e) = readln().trim().split(" ").map { it.toInt() - 1 }
        Question(s, e)
    }

    println(solution(n, m, numbers, questions).joinToString("\n"))
}

data class Question(
    val s: Int,
    val e: Int
)

fun solution(n: Int, m: Int, numbers: List<Int>, questions: Array<Question>): IntArray {
    val answerArray = ArrayList<Int>()
    val dp = Array(n) { row -> BooleanArray(n) { col -> row == col } }

    for (i in 0 until n - 1) {
        if (numbers[i] == numbers[i + 1]) dp[i][i + 1] = true
    }

    for (s in n - 3 downTo 0) {
        for (e in s + 2 until n) {
            dp[s][e] = numbers[s] == numbers[e] && dp[s + 1][e - 1]
        }
    }

    for (q in questions) {
        answerArray.add(if (dp[q.s][q.e]) 1 else 0)
    }

    return answerArray.toIntArray()
}

/* 반례
6
1 1 3 3 6 6
1
3 4

ans : 1
 */
