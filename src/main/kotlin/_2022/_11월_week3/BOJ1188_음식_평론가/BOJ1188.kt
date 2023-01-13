package _2022._11월_week3.BOJ1188_음식_평론가

/*
   최대공약수
   유클리드 호제법 => O(logN)
*/

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }

    print(Solution().solution(n, m))
}

class Solution {
    fun solution(n: Int, m: Int): Int = m - gcd(n, m)

    private fun gcd(n: Int, m: Int): Int {
        return if (n % m == 0) m else gcd(m, n % m)
    }
}
