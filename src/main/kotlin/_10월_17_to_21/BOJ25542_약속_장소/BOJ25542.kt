package _10월_17_to_21.BOJ25542_약속_장소

/*
   반례 케이스로 어떤게 있을까???
*/

fun compare(name: String, other: String, l: Int): Int {
    var result = 0
    for (i in 0 until l) {
        if (name[i] != other[i]) result++
    }
    return result
}

fun main() = with(System.`in`.bufferedReader()) {
    val (n, l) = readLine().split(" ").map { it.toInt() }
    val list = ArrayList<String>()
    for (i in 0 until n) {
        list.add(readLine())
    }

    val result = Array(n) { IntArray(n) { 0 } }

    for (i in 0 until n) {
        for (k in 0 until n) {
            result[i][k] = compare(list[i], list[k], l)
        }
    }

    val answer = result.filter { it.all { num -> num <= 1 } }

    if (answer.isEmpty()) {
        print("CALL FRIEND")
    } else {
        print(list[result.indexOf(answer.last())])
    }
}