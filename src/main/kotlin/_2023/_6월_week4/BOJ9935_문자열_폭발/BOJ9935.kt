package _2023._6월_week4.BOJ9935_문자열_폭발

/*
    https://www.acmicpc.net/problem/9935

    Stack
 */

fun main() {
    val str = readln()
    val bomb = readln()

    solution(str, bomb)
}

fun solution(str: String, bomb: String) {
    val stack = ArrayDeque<Char>()

    for (char in str) {
        stack.addLast(char)
        if (stack.size < bomb.length) continue

        var isBomb = true
        for (i in bomb.indices) {
            if (bomb[i] != stack[i + stack.size - bomb.length]) isBomb = false
        }
        if (isBomb) {
            repeat(bomb.length) {
                stack.removeLast()
            }
        }
    }

    if (stack.isEmpty()) {
        println("FRULA")
    } else {
        println(stack.joinToString(""))
    }
}
