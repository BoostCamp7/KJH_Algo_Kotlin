package _11월_week1.BOJ1541_잃어버린_괄호

fun main() = with(System.`in`.bufferedReader()) {
    val formula = readLine()

    print(Solution().solution(formula))
}

class Solution {
    fun solution(formula: String): Int {
        var result = 0

        formula.split("-").forEachIndexed { index, form ->
            if (index == 0) {
                if (form.isNotBlank()) result += form.split("+").sumOf { it.toInt() }
            } else {
                result -= form.split("+").sumOf { it.toInt() }
            }
        }

        return result
    }
}
