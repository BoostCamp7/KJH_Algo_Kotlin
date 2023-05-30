package _2023._5월_week5.BOJ1720_타일_코드

/*
    https://www.acmicpc.net/problem/1720

    dp

    참고 : https://beginthread.tistory.com/145
    가운데 기준 좌우 대칭인 경우를 찾아야 함
    좌우 대칭이 아닌 경우 중복 = 2X
    좌우 대칭인 경우는 중복되지 않음 = Y
    DP[n] = 2X + Y 이므로, 대칭인 경우의 수를 찾아 (2X + Y + Y) / 2 로 만들어야 함
 */

fun main() {
    val n = readln().toInt()
    println(Solution().solution(n))
}

class Solution() {
    fun solution(n: Int): Int {
        if (n == 1) return 1

        val dp = IntArray(n + 1) { 0 }

        dp[1] = 1 // (2 x 1) -> 1가지 경우
        dp[2] = 3 // (1 x 2) * 2, (2 x 1) * 2, (2 x 2) -> 3가지 경우

        for (i in 3..n) {
            // 세로 (2 x 1) -> 한가지 경우의 수 + 나머지(n-1)
            // or  (2 x 2) & (1 x 2) * 2 -> 두가지 경우의 수 + 나머지(n-2)
            dp[i] = dp[i - 1] + (dp[i - 2] * 2)
        }

        val symmetryCount = if (n % 2 == 1) {
            dp[n / 2]
        } else {
            dp[(n / 2) + 1]
        }

        return (dp[n] + symmetryCount) / 2
    }
}
