package _2023._5월_week4.BOJ3107_IPv6

/*
    https://www.acmicpc.net/problem/3107

    구현

    1:2:3:4:5:6:7:: -> ":" 으로 split 했을 때 9개의 공간이 생기는 경우 예외처리 필요
 */

import java.util.LinkedList

fun main() {
    val ip = readln()

    println(Solution().solution(ip))
}

class Solution {
    fun solution(ip: String): String {
        val linkedList = LinkedList<String>()
        linkedList.addAll(ip.split(":"))

        if (linkedList.contains("")) {
            val emptyIndex = linkedList.indexOf("")
            while (linkedList.size > 8) {
                linkedList.removeAt(emptyIndex)
            }
            while (linkedList.size < 8) {
                linkedList.add(emptyIndex, "")
            }
        }

        for (i in 0 until 8) {
            linkedList[i] = linkedList[i].padStart(4, '0') // makeString(linkedList[i])
        }

        return linkedList.joinToString(":")
    }

    private fun makeString(str: String): String { // 대신 padStart 함수 활용 가능
        val result = StringBuilder()
        for (i in 0 until (4 - str.length)) {
            result.append('0')
        }
        return result.append(str).toString()
    }
}
