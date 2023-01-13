package _2022._11월_week2.BOJ1946_신입_사원

/*
    1 4  -> 합격 : min = 4
    2 5  -> 탈락
    3 6  -> 탈락
    4 2  -> 합격 : min = 2
    5 7  -> 탈락
    6 1  -> 합격 : min = 1
    7 3  -> 탈락
    => 3
*/

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()

    for (i in 0 until t) {
        val n = readLine().toInt()
        val scoreList = Array(n) {
            readLine().trim().split(" ").map { it.toInt() }.toIntArray()
        }
        println(Solution().solution(n, scoreList))
    }
}

class Solution {
    fun solution(n: Int, scoreList: Array<IntArray>): Int {
        scoreList.sortBy { it[0] }
        var min = scoreList[0][1]

        var result = 1

        for (i in 1 until n) {
            val interviewRank = scoreList[i][1]
            if (interviewRank < min) {
                min = interviewRank
                result++
            }
        }

        return result
    }
}
