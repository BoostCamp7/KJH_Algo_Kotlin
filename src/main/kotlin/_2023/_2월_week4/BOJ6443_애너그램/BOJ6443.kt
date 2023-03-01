package _2023._2월_week4.BOJ6443_애너그램

/*
    https://www.acmicpc.net/problem/6443

    DFS - 백트래킹
 */

fun main() {
    val n = readln().toInt()
    for (i in 0 until n) {
        // println(Solution1(readln()).solution().joinToString("\n"))
        println(Solution2(readln()).solution().joinToString("\n"))
    }
}

class Solution2(private val word: String) {
    private val result = HashSet<String>()
    private val buffer = CharArray(word.length)
    private val alphabetArray = IntArray(26) { 0 }

    fun solution(): List<String> {
        for (alpha in word) {
            alphabetArray[alpha - 'a']++
        }

        dfs(0)

        return result.sorted()
    }

    private fun dfs(index: Int) {
        if (index == word.length) result.add(buffer.joinToString(""))

        for (i in 0 until 26) {
            if (alphabetArray[i] > 0) {
                alphabetArray[i]--
                buffer[index] = 'a' + i
                dfs(index + 1)
                alphabetArray[i]++
            }
        }
    }
}

// 백트래킹 - 25퍼쯤 메모리 초과
// aaaaaaabbbbb 등 반복되는 알파벳이 많은 경우 메모리 초과가 발생하는 것으로 추정..?
// 이유는 모르겠음..
class Solution1(private val word: String) {
    private val result = HashSet<String>()

    fun solution(): List<String> {
        val length = word.length
        makeAnagram(length, ArrayList(), BooleanArray(length) { false })

        return result.sorted()
    }

    private fun makeAnagram(depth: Int, picked: ArrayList<Char>, visited: BooleanArray) {
        if (picked.size == depth) {
            result.add(picked.joinToString(""))
            return
        }

        for (i in 0 until depth) {
            if (visited[i].not()) {
                visited[i] = true
                picked.add(word[i])
                makeAnagram(depth, picked, visited)
                visited[i] = false
                picked.removeLast()
            }
        }
    }
}
