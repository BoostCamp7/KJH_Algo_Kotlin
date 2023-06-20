package _2023._6월_week3.BOJ2141_우체국

/*
    https://www.acmicpc.net/problem/2141

    정렬

    위치 번호순으로 정렬하고 인구수를 카운트하며 전체 인구수 / 2 를 넘어가는 지점을 선택
    데이터 크기 확인하고 Int, Long 무엇을 사용해야 할지 잘 보기
 */

fun main() {
    val n = readln().toInt()
    val towns = Array(n) {
        val (loc, pop) = readln().trim().split(" ").map { it.toInt() }
        Town(loc, pop)
    }

    println(solution(n, towns))
}

data class Town(
    val loc: Int,
    val pop: Int
) : Comparable<Town> {
    override fun compareTo(other: Town): Int = loc.compareTo(other.loc)
}

fun solution(n: Int, towns: Array<Town>): Int {
    towns.sort()

    val half = (towns.sumOf { it.pop.toLong() } + 1) / 2
    var popCount = 0L

    for (town in towns) {
        popCount += town.pop
        if (popCount >= half) return town.loc
    }

    return towns.minOf { it.loc }
}
