package _2023._3월_week4.BOJ5052_전화번호_목록

/*
    https://www.acmicpc.net/problem/5052

    정렬
    Set

    문자열 길이가 작은 것 부터 입력된다는 보장 없음 -> 정렬
 */

fun main() {
    val t = readln().toInt()

    repeat(t) {
        val n = readln().toInt()
        val numbers = Array(n) { readln() }

        println(Solution().solution(n, numbers))
    }
}

class Solution {
    fun solution(n: Int, numbers: Array<String>): String {
        numbers.sort()

        val numberSet = HashSet<String>()
        numberSet.add(numbers[0])

        for (i in 1 until n) {
            for (j in 1 until numbers[i].length) {
                if (numberSet.contains(numbers[i].substring(0, j))) return "NO"
            }
            numberSet.add(numbers[i])
        }

        return "YES"
    }
}

/*
시간초과 풀이

class Solution {
    fun solution(n: Int, numbers: Array<String>): String {
        numbers.sort()

        for (i in 1 until n) {
            for (j in 0 until i) {
                if (numbers[i].startsWith(numbers[j])) return "NO"
            }
        }

        return "YES"
    }
}
 */
