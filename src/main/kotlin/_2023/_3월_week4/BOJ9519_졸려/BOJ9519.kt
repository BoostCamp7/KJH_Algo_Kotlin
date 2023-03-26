package _2023._3월_week4.BOJ9519_졸려

/*
    https://www.acmicpc.net/problem/9519

    구현

    사이클을 찾아 반복 횟수를 줄이는 것이 중요
 */

fun main() {
    val x = readln().toInt()
    val word = readln()

    println(Solution().solution(x, word))
}

class Solution {
    fun solution(x: Int, word: String): String {
        var answer = word

        val resultList = ArrayList<String>()

        repeat(x) {
            resultList.add(answer)

            val left = answer.filterIndexed { index, _ -> index % 2 == 0 }
            val right = answer.filterIndexed { index, _ -> index % 2 == 1 }.reversed()
            answer = left + right

            if (answer == resultList[0]) {
                return resultList[x % resultList.size]
            }
        }

        return answer
    }
}
