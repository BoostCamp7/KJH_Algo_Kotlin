package _2023._5월_week1.BOJ15486_퇴사2

fun main() {
    val n = readln().toInt()
    val tpArray = Array(n) {
        readln().trim().split(" ").map { it.toInt() }.toIntArray()
    }

    println(Solution(n, tpArray).solution())
}

class Solution(private val n: Int, private val tpArray: Array<IntArray>) {
    private val dp = Array<IntArray>(n) { IntArray(n) { 0 } }

    fun solution() {

    }
}
