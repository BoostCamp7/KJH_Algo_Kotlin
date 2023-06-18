package _2023._6월_week3.BOJ20166_문자열_지옥에_빠진_호석

/*
    https://www.acmicpc.net/problem/20166

    DFS
 */

fun main() {
    val (n, m, k) = readln().trim().split(" ").map { it.toInt() }
    val world = Array(n) { readln().trim().toCharArray() }
    val words = Array(k) { readln() }

    println(Solution(n, m, k, world, words).solution().joinToString("\n"))
}

class Solution(
    private val n: Int,
    private val m: Int,
    private val k: Int,
    private val world: Array<CharArray>,
    private val words: Array<String>,
) {
    private val dy = intArrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
    private val dx = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    private val maxLength = words.maxOf { it.length }
    private val answerMap = mutableMapOf<String, Int>()
    private val answer = IntArray(k) { 0 }

    fun solution(): IntArray {
        for (row in 0 until n) {
            for (col in 0 until m) {
                val wordQueue = ArrayDeque<Char>()
                wordQueue.addLast(world[row][col])
                dfs(row, col, wordQueue)
            }
        }

        for (i in 0 until k) {
            answer[i] = answerMap[words[i]] ?: 0
        }

        return answer
    }

    fun dfs(row: Int, col: Int, word: ArrayDeque<Char>) {
        if (word.size > maxLength) return

        if (answerMap[word.joinToString("")] == null) {
            answerMap[word.joinToString("")] = 1
        } else {
            answerMap[word.joinToString("")] = answerMap[word.joinToString("")]!! + 1
        }

        for (i in 0 until 8) {
            val nRow = (row + dy[i]).mod(n)
            val nCol = (col + dx[i]).mod(m)
            word.addLast(world[nRow][nCol])
            dfs(nRow, nCol, word)
            word.removeLast()
        }
    }
}
