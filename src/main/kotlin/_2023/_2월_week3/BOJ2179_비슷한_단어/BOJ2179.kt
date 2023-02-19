package _2023._2월_week3.BOJ2179_비슷한_단어

/*
    https://www.acmicpc.net/problem/2179

    37퍼 틀림

    ** 단, 이 두 단어는 서로 달라야 한다. 즉, 가장 비슷한 두 단어를 구할 때 같은 단어는 제외하는 것이다. **
    -> 이 조건 때문에 틀린게 아닌가 싶음 -> 이 조건 때문은 아니었음...
    -> 같은 비슷한 정도를 가진 두 단어가 있는 경우 기존 단어 순서에서 제일 앞쪽에 있는 단어인 경우를 출력해야 한다!

<반례>

9
ab
is
lunch
for
most
waits
until
two
ac

answer = ab, ac


4
aa
bb
bc
aj

answer = aa, aj


3
aaa
aac
aab

answer = aaa, aac
-> 틀림 : 이 경우를 해결해야 할 듯

 */

fun main() {
    val n = readln().trim().toInt()
    val wordArray = Array(n) { readln().trim() }

    println(Solution().solution(n, wordArray).joinToString("\n"))
}

class Solution {
    fun solution(n: Int, words: Array<String>): Array<String> {
        val wordArray = words.sorted()

        var similarCount = -1
        var first = ""
        var second = ""

        for (i in 0 until n - 1) {
            if (wordArray[i] == wordArray[i + 1]) continue
            val maxLength = minOf(wordArray[i].length, wordArray[i + 1].length)
            for (k in 0..maxLength) {
                if (k == maxLength || wordArray[i][k] != wordArray[i + 1][k]) {
                    if (similarCount < k) {
                        similarCount = k
                        first = wordArray[i]
                        second = wordArray[i + 1]
                    } else if (similarCount == k) {
                    }
                    break
                }
            }
        }

        return if (similarCount == 0) arrayOf(words[0], words[1])
        else arrayOf(first, second).sortedBy { words.indexOf(it) }.toTypedArray()
    }
}
