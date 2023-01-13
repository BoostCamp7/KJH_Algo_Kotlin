package _2022._10월_17_to_21.BOJ2559_수열

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val list = readLine().split(" ").map { it.toInt() }

    var max = k * (-100)

    for (i in 0..(n - k)) {
        val sum = list.subList(i, i + k).sum()
        if (sum > max) max = sum
    }

    println(max)
}