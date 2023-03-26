package _2023._3월_week2.BOJ14725_개미굴

fun main() {
    val n = readln().toInt()
    val foods = Array(n) { readln().trim().split(" ").toTypedArray() }
}

data class Node(
    val food: String,
    val child: ArrayList<String> = ArrayList(),
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.food.compareTo(other.food)
    }
}

class Solution {
    private val answer = StringBuilder()

    fun solution(): String {
        return answer.toString()
    }

    private fun dfs() {
    }
}
