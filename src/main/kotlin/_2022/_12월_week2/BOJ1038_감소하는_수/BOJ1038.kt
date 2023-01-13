package _2022._12월_week2.BOJ1038_감소하는_수

/*
    완탐
    모든 경우의 수를 구해두고 해당 위치의 숫자 출력
*/

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    print(Solution().solution(n))
}

class Solution {

    fun solution(n: Int): Int {
        val resultList = ArrayList<Int>()

        // 감소하는 숫자 구하기
        for (i in 0..9) {
        }
        resultList.sort()

        return if (resultList.lastIndex < n) {
            -1
        } else {
            resultList[n]
        }
    }
}
