package _2023._7월_week3.BOJ1082_방_번호

/*
    https://www.acmicpc.net/problem/1082

    그리디
 */

fun main() {
    val n = readln().toInt()
    val p = readln().trim().split(" ").map { it.toInt() }.toIntArray()
    val m = readln().toInt()

    println(solution(n, p, m))
}

fun solution(n: Int, p: IntArray, m: Int): String {
    var minCost = Int.MAX_VALUE
    var minIndex = 0
    for (i in p.indices) {
        if (p[i] <= minCost) {
            minCost = p[i]
            minIndex = i
        }
    }

    val size = m / minCost
    var remain = m % minCost
    val roomNumber = IntArray(size) { minIndex }
    var startIndex = 0

    for (i in 0 until size) {
        for (j in n - 1 downTo 0) {
            if (p[j] <= remain + minCost) {
                roomNumber[i] = j
                remain += minCost - p[j]
                break
            }
        }

        if (roomNumber[startIndex] == 0) {
            startIndex++
            remain += minCost
        }
    }

    return if (startIndex == size) "0" else roomNumber.joinToString("").substring(startIndex)
}
