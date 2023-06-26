package _2023._6월_week4.BOJ2504_괄호의_값

/*
    https://www.acmicpc.net/problem/2504

    Stack
 */

fun main() {
    val str = readln()
    println(solution(str))
}

fun solution(str: String): Int {
    var answer = 0
    val stack = ArrayDeque<Char>()
    var temp = 1

    for (i in str.indices) {
        when (str[i]) {
            '(' -> {
                stack.addLast(str[i])
                temp *= 2
            }
            '[' -> {
                stack.addLast(str[i])
                temp *= 3
            }
            ')' -> {
                if (stack.isEmpty() || stack.removeLast() != '(') {
                    answer = 0
                    break
                }
                if (str[i - 1] == '(') {
                    answer += temp
                }
                temp /= 2
            }
            ']' -> {
                if (stack.isEmpty() || stack.removeLast() != '[') {
                    answer = 0
                    break
                }
                if (str[i - 1] == '[') {
                    answer += temp
                }
                temp /= 3
            }
            else -> {
                answer = 0
                break
            }
        }
    }

    if (stack.isNotEmpty()) {
        answer = 0
    }

    return answer
}
