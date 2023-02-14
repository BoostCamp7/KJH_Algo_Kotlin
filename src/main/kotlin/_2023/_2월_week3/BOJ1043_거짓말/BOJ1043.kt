package _2023._2월_week3.BOJ1043_거짓말

/*
    https://www.acmicpc.net/problem/1043

    Graph
    Union-Find

    진실을 알고 있는 사람과 같은 파티에 한 번이라도 참여한 사람은 모두 진실을 알게 된다.
 */

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val truth = readln().trim().split(" ").map { it.toInt() }.toIntArray()
    val parties = Array(m) { readln().trim().split(" ").map { it.toInt() }.toIntArray() }

    println(Solution(n, m, truth, parties).solution())
}

class Solution(
    private val n: Int,
    private val m: Int,
    private val truth: IntArray,
    private val parties: Array<IntArray>
) {

    private val parentArray = IntArray(n + 1) { i -> i }
    private val parentOfTruthSet = HashSet<Int>()

    fun solution(): Int {
        var answer = m

        for (party in parties) {
            for (i in 2..party[0]) {
                union(party[i - 1], party[i])
            }
        }

        if (truth[0] > 0) {
            for (i in 1..truth[0]) {
                parentOfTruthSet.add(find(truth[i]))
            }
        }

        for (party in parties) {
            for (i in 1..party[0]) {
                if (parentOfTruthSet.contains(find(party[i]))) {
                    answer--
                    break
                }
            }
        }

        return answer
    }

    private fun union(parent: Int, child: Int) {
        val nParent = find(parent)
        val nChild = find(child)
        parentArray[nChild] = nParent
    }

    private fun find(index: Int): Int {
        if (parentArray[index] == index) return index
        return find(parentArray[index])
    }
}
