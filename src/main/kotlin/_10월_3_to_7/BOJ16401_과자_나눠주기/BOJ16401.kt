package _10월_3_to_7.BOJ16401_과자_나눠주기

fun main() = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map { it.toInt() }
    val cookies = readLine().split(" ").map { it.toInt() }

    var length = 0
    var left = 0
    var right = cookies.maxOf { it }

    while (left <= right) {
        val mid = (left + right) / 2
        var count = 0
        if (mid == 0) break
        cookies.forEach { count += it / mid }

        if (count >= m) {
            length = mid
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    print(length)
}