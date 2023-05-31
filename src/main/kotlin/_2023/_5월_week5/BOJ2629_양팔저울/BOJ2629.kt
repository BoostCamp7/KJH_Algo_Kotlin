package _2023._5월_week5.BOJ2629_양팔저울

/*
    DP
    배낭 문제

    모든 추의 무게를 합했을 때 최대 무게인 30 * 500 크기의 BooleanArray로 모든 추를 사용하였을 때 가능한 모든 무게 인덱스에 true 저장
    -> "구슬의 무게는 40,000보다 작거나 같은 자연수이다."
        = 배열 크기를 40000으로 하고 DP 테이블을 만들 때 40000이 넘어갈 때의 예외처리가 필요함

    dp[weightN][marbleWeight] == true 이면 'Y', false 이면 'N'

    추를 사용하는 경우의 수 :
    1. 해당 추를 사용하지 않음
    2. 해당 추를 구슬의 반대편에 추가
    3. 해당 추를 구슬과 같은 곳에 추가
 */

import kotlin.math.abs

fun main() {
    val weightN = readln().toInt()
    val weightArray = readln().trim().split(" ").map { it.toInt() }.toIntArray()
    val marbleN = readln().toInt()
    val marbleArray = readln().trim().split(" ").map { it.toInt() }.toIntArray()

    println(Solution(weightN, weightArray, marbleN, marbleArray).solution())
}

class Solution(
    private val weightN: Int,
    private val weightArray: IntArray,
    private val marbleN: Int,
    private val marbleArray: IntArray,
) {

    private val dp = Array(weightN + 1) { BooleanArray(40001) { false } }

    fun solution(): String {
        buildDpTable(0, 0)

        return marbleArray.map { if (dp[weightN][it]) 'Y' else 'N' }.joinToString(" ")
    }

    private fun buildDpTable(index: Int, currentWeight: Int) {
        if (currentWeight > 40000 || dp[index][currentWeight]) return
        dp[index][currentWeight] = true

        if (index >= weightN) return

        buildDpTable(index + 1, currentWeight)
        buildDpTable(index + 1, currentWeight + weightArray[index])
        buildDpTable(index + 1, abs(currentWeight - weightArray[index]))
    }
}
