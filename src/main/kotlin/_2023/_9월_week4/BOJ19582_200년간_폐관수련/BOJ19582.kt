package _2023._9월_week4.BOJ19582_200년간_폐관수련

/*
    https://www.acmicpc.net/problem/19582

    Greedy
    DP -> 포기 (15퍼 틀림)

    dp[대회 순서(index)][대회 미참여 카운트(0, 1)] = 상금 합계
    상금 합계 = -1 이면 불가능하다는 뜻

    dp[i][1] = min(dp[i-1][1] + comp[i].p, dp[i-1][0] + 0)
    dp[i][0] = dp[i-1][0] + comp[i].p
 */

import kotlin.math.max

fun main() {
    val n = readln().toInt()
    val competitions = Array(n) {
        val (x, p) = readln().trim().split(" ").map { it.toInt() }
        Competition(x, p)
    }

    println(solution(n, competitions))
}

data class Competition(
    val x: Int,
    val p: Int,
)

fun solution(n: Int, competitions: Array<Competition>): String {
    var answer = true
    var flag = true
    var sum = 0L
    var max = 0

    for (i in 0 until n) {
        if (sum > competitions[i].x) {
            if (flag.not()) {
                answer = false
                break
            }

            if (max > competitions[i].p) {
                if (sum - max <= competitions[i].x) {
                    sum += competitions[i].p - max
                }
            }

            flag = false
            continue
        }

        sum += competitions[i].p
        if (flag) max = max(max, competitions[i].p)
    }

    return if (answer) "Kkeo-eok" else "Zzz"
}

/* DP 풀이
fun solution(n: Int, competitions: Array<Competition>): String {
    val dp = Array(n) { LongArray(2) { -1L } }

    dp[0][0] = competitions[0].p.toLong()
    dp[0][1] = 0L

    for (i in 1 until n) {
        if (dp[i - 1][1] <= competitions[i].x && dp[i - 1][1] != -1L) {
            dp[i][1] = dp[i - 1][1] + competitions[i].p
        } else {
            dp[i][1] = -1L
        }

        if (dp[i - 1][0] != -1L && dp[i][1] != -1L) {
            dp[i][1] = min(dp[i - 1][0], dp[i][1])
        }

        if (dp[i - 1][0] <= competitions[i].x && dp[i - 1][0] != -1L) {
            dp[i][0] = dp[i - 1][0] + competitions[i].p
        } else {
            dp[i][0] = -1
        }
    }

    return if (dp[n - 1][0] == -1L && dp[n - 1][1] == -1L) {
        "Zzz"
    } else {
        "Kkeo-eok"
    }
}

 */

/*
9
1 1
2 2
4 3
7 4
11 5
14 27
16 1
17 1
18 1

ans : Kkeo-eok

6
1 3
4 5
9 5
7 4
13 2
15 3

 */
