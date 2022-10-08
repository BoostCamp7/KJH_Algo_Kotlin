package _10월_3_to_7._2022_KAKAO_BLIND_k진수에서_소수_개수_구하기

import java.util.LinkedList
import kotlin.math.sqrt

class Solution {
    private fun isPrimeNumber(num: Long): Boolean {
        return if (num <= 1) false
        else (2..sqrt(num.toFloat()).toLong()).none{ num % it == 0L }
    }

    fun solution(n: Int, k: Int): Int {
        val number: String = if (k == 10) {
            n.toString()
        } else {
            val numList = LinkedList<Int>()
            var num = n
            var remainder = 0

            while (!(num == 0 && remainder == 0)) {
                remainder = num % k
                num /= k
                numList.addFirst(remainder)
            }

            numList.joinToString("")
        }

        var answer: Int = 0
        val numberList = number.split("0").filter { it.isNotBlank() }.map { it.toLong() }
        numberList.forEach { if (isPrimeNumber(it)) answer++ }

        return answer
    }
}