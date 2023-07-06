package _2023._7월_week1.BOJ1148_단어_만들기

/*
    https://www.acmicpc.net/problem/1148

    구현
 */

fun main() {
    val words = ArrayList<String>()
    while (true) {
        val word = readln()
        if (word == "-") break
        words.add(word)
    }
    val puzzles = ArrayList<String>()
    while (true) {
        val puzzle = readln()
        if (puzzle == "#") break
        puzzles.add(puzzle)
    }

    solution(words, puzzles)
}

fun solution(words: ArrayList<String>, puzzles: ArrayList<String>) {
    val wordsAlpha = Array(words.size) { IntArray(26) { 0 } }

    for (i in words.indices) {
        for (char in words[i]) {
            wordsAlpha[i][char - 'A']++
        }
    }

    for (puzzle in puzzles) {
        val puzzleAlpha = IntArray(26) { 0 }
        val usedAlpha = IntArray(26) { 0 }
        val minAlphas = ArrayList<Char>()
        var minResult = Int.MAX_VALUE
        val maxAlphas = ArrayList<Char>()
        var maxResult = 0

        for (char in puzzle) {
            puzzleAlpha[char - 'A']++
        }

        for (wordAlpha in wordsAlpha) {
            var isPossible = true
            for (i in 0 until 26) {
                if (wordAlpha[i] > puzzleAlpha[i]) {
                    isPossible = false
                    break
                }
            }

            if (isPossible.not()) continue
            for (i in 0 until 26) {
                if (wordAlpha[i] > 0) usedAlpha[i]++
            }
        }

        for (char in puzzle.toSortedSet()) {
            val index: Int = char - 'A'

            if (usedAlpha[index] < minResult) {
                minResult = usedAlpha[index]
                minAlphas.clear()
                minAlphas.add('A' + index)
            } else if (usedAlpha[index] == minResult) {
                minAlphas.add('A' + index)
            }

            if (usedAlpha[index] > maxResult) {
                maxResult = usedAlpha[index]
                maxAlphas.clear()
                maxAlphas.add('A' + index)
            } else if (usedAlpha[index] == maxResult) {
                maxAlphas.add('A' + index)
            }
        }

        println("${minAlphas.joinToString("")} $minResult ${maxAlphas.joinToString("")} $maxResult")
    }
}
