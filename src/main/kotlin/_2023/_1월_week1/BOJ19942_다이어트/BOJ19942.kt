package _2023._1월_week1.BOJ19942_다이어트
/*
    https://www.acmicpc.net/problem/19942

    백트래킹
    DFS

    N의 범위 : 3 <= N <= 15 -> 최대 개수가 크지 않음 -> 완탐?
    한 번 확인한 조합은 다시 확인하지 않도록 하는것이 중요할 듯
    visited 배열 이용? -> 작은 index 부터 순차적으로 확인
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val (mp, mf, ms, mv) = readLine().trim().split(" ").map { it.toInt() }
    val nutrients = List(n) {
        val nutrientList = readLine().trim().split(" ").map { it.toInt() }
        Nutrient(
            nutrientList[0],
            nutrientList[1],
            nutrientList[2],
            nutrientList[3],
            nutrientList[4]
        )
    }

    println(Solution().solution(n, mp, mf, ms, mv, nutrients))
}

data class Nutrient(
    val p: Int,
    val f: Int,
    val s: Int,
    val v: Int,
    val cost: Int
)

data class Result(
    val minCost: Int,
    val ingredients: List<Int>
) {
    override fun toString(): String {
        val min = if (minCost == Int.MAX_VALUE) -1 else minCost
        return "$min\n${ingredients.sorted().map { it + 1 }.joinToString(" ")}"
    }
}

class Solution {
    private var n: Int = 0
    private var mp: Int = 0
    private var mf: Int = 0
    private var ms: Int = 0
    private var mv: Int = 0
    private lateinit var nutrients: List<Nutrient>

    private var result = Result(Int.MAX_VALUE, emptyList())

    fun solution(n: Int, mp: Int, mf: Int, ms: Int, mv: Int, nutrients: List<Nutrient>): Result {
        this.n = n
        this.mp = mp
        this.mf = mf
        this.ms = ms
        this.mv = mv
        this.nutrients = nutrients

        val selected = ArrayDeque<Int>()
        dfs(0, selected)

        return result
    }

    private fun dfs(index: Int, selected: ArrayDeque<Int>) {
        val sum = selected.sumOf { nutrients[it].cost }
        if (checkMinimum(selected) && result.minCost > sum) {
            result = Result(sum, selected.toList())
        }

        if (index == n) return

        selected.addLast(index)
        dfs(index + 1, selected)

        selected.removeLast()
        dfs(index + 1, selected)
    }

    private fun checkMinimum(selected: ArrayDeque<Int>): Boolean {
        return mp <= selected.sumOf { nutrients[it].p } &&
            mf <= selected.sumOf { nutrients[it].f } &&
            ms <= selected.sumOf { nutrients[it].s } &&
            mv <= selected.sumOf { nutrients[it].v }
    }
}
