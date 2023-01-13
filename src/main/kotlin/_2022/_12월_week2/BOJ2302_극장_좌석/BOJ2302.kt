package _2022._12월_week2.BOJ2302_극장_좌석

/*
   <DP>
   vip는 고정
   vip로 둘러싸인 일반 좌석의 경우의 수들을 모두 곱하면 됨

   0 -> 1
   1 -> 1
   2 -> 2 (12, 21)
   3 -> 3 (123, 213, 132)
   4 -> 5 (1234, 2134, 1324, 1243, 2143)
   ...
   => dp[i] = dp[i - 1] + dp[i - 2]
   == 피보나치
*/

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val vipList = IntArray(m) { readLine().toInt() }

    print(Solution().solution(n, m, vipList))
}

class Solution {
    fun solution(n: Int, m: Int, vipList: IntArray): Int {
        val dp = IntArray(n + 1) { 1 }
        if (n > 1) {
            dp[2] = 2
        }
        if (n > 2) {
            for (i in 3..n) {
                dp[i] = dp[i - 1] + dp[i - 2]
            }
        }

        var result = 1
        var start = 0
        vipList.forEach { vip ->
            result *= dp[vip - start - 1]
            start = vip
        }
        return result * dp[n - start]
    }
}
