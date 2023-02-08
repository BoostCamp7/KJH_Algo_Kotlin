package _2023._2월_week2.BOJ2608_로마_숫자

/*
    https://www.acmicpc.net/problem/2608
 */

fun main() {
    val roma1 = readln().trim()
    val roma2 = readln().trim()

    val result = Solution().solution(roma1, roma2)
    println(result.first)
    println(result.second)
}

class Solution {
    private val romaMap = hashMapOf<Char, Int>(
        'M' to 1000,
        'D' to 500,
        'C' to 100,
        'L' to 50,
        'X' to 10,
        'V' to 5,
        'I' to 1
    )

    fun solution(roma1: String, roma2: String): Pair<Int, String> {
        val sum = romaToInt(roma1) + romaToInt(roma2)

        val romaSum = intToRoma(sum)
        return Pair(sum, romaSum)
    }

    private fun romaToInt(roma: String): Int {
        var result = 0

        for (i in roma.indices) {
            val romaInt = romaMap[roma[i]] ?: 0
            result += romaInt
            if (i > 0 && (romaMap[roma[i - 1]] ?: 0) < romaInt) {
                result -= (romaMap[roma[i - 1]] ?: 0) * 2
            }
        }

        return result
    }

    private fun intToRoma(num: Int): String {
        val result = StringBuilder()

        val thousand = num / 1000
        var remain = num % 1000
        for (i in 0 until thousand) {
            result.append('M')
        }

        var hundred = remain / 100
        remain %= 100
        when (hundred) {
            9 -> { result.append("CM") }
            4 -> { result.append("CD") }
            else -> {
                if (hundred >= 5) {
                    result.append('D')
                    hundred -= 5
                }
                for (i in 0 until hundred) {
                    result.append('C')
                }
            }
        }

        var ten = remain / 10
        remain %= 10
        when (ten) {
            9 -> { result.append("XC") }
            4 -> { result.append("XL") }
            else -> {
                if (ten >= 5) {
                    result.append('L')
                    ten -= 5
                }
                for (i in 0 until ten) {
                    result.append('X')
                }
            }
        }

        when (remain) {
            9 -> { result.append("IX") }
            4 -> { result.append("IV") }
            else -> {
                if (remain >= 5) {
                    result.append('V')
                    remain -= 5
                }
                for (i in 0 until remain) {
                    result.append('I')
                }
            }
        }

        return result.toString()
    }
}
