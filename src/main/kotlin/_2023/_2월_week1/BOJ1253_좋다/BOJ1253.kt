package _2023._2월_week1.BOJ1253_좋다

/*
    https://www.acmicpc.net/problem/1253

    정렬
    투포인터

    동일한 수여도 index가 다르면 다른 수로 간주 (ex: [0, 3, 3]인 경우 1번 인덱스의 3과 2번 인덱스의 3은 다른 수)
    + 자기 자신을 이용하여 더한 수는 결과에서 제외 (ex: [0, 3, 1] 에서 0과 3을 더하면 1번 인덱스 3과 같은 수가 되지만 자기 자신을 이용한 경우이므로 인정하지 않음)
    -> "어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면" 이라는 조건
 */

fun main() {
    val n = readln().toInt()
    val numArray = readln().trim().split(" ").map { it.toInt() }.toIntArray()

    println(Solution().solution(n, numArray))
}

class Solution {
    fun solution(n: Int, numArray: IntArray): Int {
        var result = 0
        val sortedNum = numArray.sorted()

        for (i in 0 until n) {
            val current = sortedNum[i]
            var left = 0
            var right = n - 1

            while (left < right) {
                val sum = sortedNum[left] + sortedNum[right]

                when {
                    current == sum -> {
                        when {
                            right == i -> {
                                right--
                            }
                            left == i -> {
                                left++
                            }
                            else -> {
                                result++
                                break
                            }
                        }
                    }
                    current < sum -> {
                        right--
                    }
                    current > sum -> {
                        left++
                    }
                }
            }
        }

        return result
    }
}
