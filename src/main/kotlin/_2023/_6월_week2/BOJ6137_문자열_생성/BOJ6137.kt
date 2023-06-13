package _2023._6월_week2.BOJ6137_문자열_생성

/*
    https://www.acmicpc.net/problem/6137

    투 포인터

    "80글자마다 새줄 문자를 출력해야 한다."

문자가 같을 경우 예외 :
8
C
B
B
B
B
B
C
A

ans : ACBBBBBC
 */

fun main() {
    val n = readln().toInt()
    val sb = StringBuilder()
    repeat(n) {
        sb.append(readln())
    }
    val S = sb.toString()

    val T = solution(n, S)
    for (i in 0 until n) {
        if (i != 0 && i % 80 == 0) println()
        print(T[i])
    }
}

fun solution(n: Int, S: String): String {
    val T = StringBuilder()
    var left = 0
    var right = n - 1

    while (left <= right) {
        if (S[left] == S[right]) {
            var isDiff = false
            var leftTemp = left + 1
            var rightTemp = right - 1
            while (leftTemp < rightTemp && rightTemp >= 0 && leftTemp < n) {
                if (S[leftTemp] < S[rightTemp]) {
                    T.append(S[left])
                    isDiff = true
                    left++
                    break
                } else if (S[leftTemp] > S[rightTemp]) {
                    T.append(S[right])
                    isDiff = true
                    right--
                    break
                } else {
                    leftTemp++
                    rightTemp--
                }
            }
            if (isDiff.not()) {
                for (i in left..right) {
                    T.append(S[i])
                }
                break
            }
        } else if (S[left] < S[right]) {
            T.append(S[left])
            left++
        } else {
            T.append(S[right])
            right--
        }
    }

    return T.toString()
}
