package _2022._11월_week4.BOJ1092_배

/*

< 반례 >
4
1 2 3 4
8
1 1 2 2 3 3 4 4

답 : 2 -> (1 2 3 4), (1 2 3 4)
오류 출력 : 4 -> (4), (4 3), (3 2 2 1), (1)

*/

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val weightLimits = readLine().trim().split(" ").map { it.toInt() }
    val m = readLine().toInt()
    val boxWeights = readLine().trim().split(" ").map { it.toInt() }

    print(Solution().solution(n, weightLimits, m, boxWeights))
}

class Solution {
    fun solution(n: Int, weightLimits: List<Int>, m: Int, boxWeights: List<Int>): Int {
        val sortedCranes = weightLimits.sortedByDescending { it }
        val sortedBox = ArrayList<Int>(m)
        boxWeights.sortedByDescending { it }.forEach {
            sortedBox.add(it)
        }

        if (sortedCranes[0] < sortedBox[0]) return -1

        var count = 0

        while (sortedBox.isNotEmpty()) {
            for (i in 0 until n) {
                if (sortedBox.isEmpty()) break

                for (k in 0..sortedBox.lastIndex) {
                    if (sortedCranes[i] >= sortedBox[k]) {
                        sortedBox.removeAt(k)
                        break
                    }
                }
            }
            count++
        }

        return count
    }
}
