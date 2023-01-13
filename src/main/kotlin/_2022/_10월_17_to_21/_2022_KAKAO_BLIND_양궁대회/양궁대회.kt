package _2022._10월_17_to_21._2022_KAKAO_BLIND_양궁대회

/*
   Greedy??? DP??? -> 완전탐색
   중복 조합
   DFS
*/

/*
   처음에 시도한 방법 :
   1. 중복 조합 모두 구하여 어피치를 이기는 경우만 점수와 함께 list에 저장
   2. 최고점인 경우 중 낮은 점수의 화살이 더 많은 경우를 출력 (못이기면 -1)
*/

/*
   -> DFS 이용하여 중복 조합 모두 순회
*/

class Solution {

    private var apeachInfo = IntArray(11)
    private val ryanInfo = IntArray(11) { 0 }
    private var maxDeltaScore = 0
    private val resultList = ArrayList<IntArray>()
    private var result = IntArray(11)

    fun solution(n: Int, info: IntArray): IntArray {
        apeachInfo = info

        dfs(n, 0, 0)

        return if (resultList.isEmpty()) {
            intArrayOf(-1)
        } else {
            sortResult()
            result
        }
    }

    private fun dfs(n: Int, depth: Int, pos: Int) {
        if (depth == n) {
            var apeachScore = 0
            var ryanScore = 0
            for (i in 0 until 10) {
                when {
                    apeachInfo[i] == 0 && ryanInfo[i] == 0 -> {}
                    apeachInfo[i] >= ryanInfo[i] -> { apeachScore += (10 - i) }
                    else -> { ryanScore += (10 - i) }
                }
            }
            if (ryanScore > apeachScore) {
                val deltaScore = ryanScore - apeachScore
                if (deltaScore > maxDeltaScore) {
                    maxDeltaScore = deltaScore
                    resultList.clear()
                    resultList.add(ryanInfo.clone())
                } else if (deltaScore == maxDeltaScore) {
                    resultList.add(ryanInfo.clone())
                }
            }
            return
        }

        for (i in pos..10) {
            ryanInfo[i]++
            dfs(n, depth + 1, i)
            ryanInfo[i]--
        }
    }

    private fun sortResult() {
        result = resultList.first()
        for (i in 1..resultList.lastIndex) {
            for (k in 10 downTo 0) {
                if (resultList[i][k] == result[k]) {
                    continue
                } else if (resultList[i][k] > result[k]) {
                    result = resultList[i]
                    break
                } else {
                    break
                }
            }
        }
    }
}