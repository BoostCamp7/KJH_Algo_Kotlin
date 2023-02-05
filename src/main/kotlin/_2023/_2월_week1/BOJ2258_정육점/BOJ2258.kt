package _2023._2월_week1.BOJ2258_정육점

/*
    https://www.acmicpc.net/problem/2258

    고려해야 하는 것
    1. 가격이 같은 고기 여러개를 구매해야 하는 경우  1 2 2 2 3 3
    2. 가격이 같은 고기 여러개보다 더 비싼 고기를 더 적은 개수 사는게 더 싼 경우

<반례>
3 8
4 2
4 2
1 4
답 : 4  ( [4, 2] + [4, 2] )
-> 1번의 경우

3 2
1 1
1 1
2 3
답 : 2  ( [1, 1] + [1, 1] )
-> 1번의 경우

10 10
2 3
2 4
2 5
3 1
1 3
7 9
7 3
8 4
10 3
3 10
답 : 3


10 14
2 3
2 4
2 5
3 1
1 3
7 9
7 3
8 4
10 3
3 10
답 : 4


4 3
1 2
3 2
2 2
5 7
답 : 2


3 2
1 5
1 5
2 6
답 : 6
-> 2번의 경우

    40퍼 틀림 -> 결과가 Int.MAX_VALUE 인 경우가 있을 수 있음 -> Int.MAX_VALUE 대신 -1 등 다른 수로 변경
 */

fun main() {
    val (n, m) = readln().trim().split(" ").map { it.toInt() }
    val meatList = Array(n) {
        val (weight, price) = readln().trim().split(" ").map { it.toInt() }
        Meat(weight, price)
    }

    println(Solution().solution(n, m, meatList))
}

data class Meat(
    val weight: Int,
    val price: Int
) : Comparable<Meat> {

    override fun compareTo(other: Meat): Int {
        return if (this.price == other.price) {
            other.weight.compareTo(this.weight)
        } else {
            this.price.compareTo(other.price)
        }
    }
}

class Solution {
    fun solution(n: Int, m: Int, meatList: Array<Meat>): Int {
        var result = -1
        val sortedMeat = meatList.sorted()

        var weightSum = 0
        var priceSum = 0
        var priceBefore = -1

        for (meat in sortedMeat) {
            weightSum += meat.weight

            if (priceBefore == meat.price) {
                priceSum += meat.price
            } else {
                priceSum = meat.price
                priceBefore = meat.price
            }

            if (weightSum >= m && (result > priceSum || result == -1)) {
                result = priceSum
            }
        }

        return result
    }
}
