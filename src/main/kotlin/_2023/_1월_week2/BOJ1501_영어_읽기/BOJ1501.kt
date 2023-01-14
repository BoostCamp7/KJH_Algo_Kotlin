package _2023._1월_week2.BOJ1501_영어_읽기

/*
    https://www.acmicpc.net/problem/1501

    Map 활용
    단어의 알파벳 개수를 Hash 로 만들어 저장
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val dict = List(n) { readLine().trim() }
    val m = readLine().toInt()
    val wordList = List(m) { readLine().trim() }

    Solution().solution(n, dict, m, wordList).forEach {
        println(it)
    }
}

class Solution {
    private val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun solution(n: Int, dict: List<String>, m: Int, wordList: List<String>): IntArray {
        val result = IntArray(m) { 0 }

        val dictMap = HashMap<String, Int>()

        for (i in 0 until n) {
            val hash = toHash(dict[i])
            dictMap[hash] = 1 + (dictMap[hash] ?: 0)
        }

        for (k in 0 until m) {
            wordList[k].split(" ").forEach { str ->
                val hash = toHash(str)
                if (result[k] == 0) {
                    result[k] = dictMap[hash] ?: 0
                } else {
                    result[k] *= dictMap[hash] ?: 1
                }
            }
        }

        return result
    }

    private fun toHash(str: String): String {
        val result = StringBuilder()

        val hashArray = IntArray(52) { 0 }

        result.append(str.first())
        if (str.length > 2) {
            str.substring(1, str.lastIndex).forEach { char ->
                val index = alphabet.indexOf(char)
                if (index != -1) hashArray[index]++
            }
            result.append(hashArray.joinToString(""))
        }
        if (str.length > 1) result.append(str.last())

        return result.toString()
    }
}
