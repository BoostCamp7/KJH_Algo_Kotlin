package _2023._9월_week1.BOJ2310_어드벤처_게임

/*
    https://www.acmicpc.net/problem/2310

    DFS
 */

fun main() {
    while (true) {
        val n = readln().toInt()
        if (n == 0) break

        val rooms = Array(n) {
            val room = readln().trim().split(" ")

            val doors = ArrayList<Int>()
            for (i in 2 until room.lastIndex) {
                doors.add(room[i].toInt())
            }

            Room(room[0], room[1].toInt(), doors)
        }

        println(Solution(n, rooms).solution())
    }
}

data class Room(
    val type: String,
    val money: Int,
    val doors: ArrayList<Int>
)

class Solution(private val n: Int, private val rooms: Array<Room>) {
    private var answer = false
    private val visited = BooleanArray(n) { false }

    fun solution(): String {
        dfs(0, 0)

        return if (answer) "Yes" else "No"
    }

    private fun dfs(index: Int, money: Int) {
        if (answer) return

        var nMoney = money
        val room = rooms[index]

        when (room.type) {
            "L" -> {
                if (nMoney < room.money) nMoney = room.money
            }
            "T" -> {
                nMoney -= room.money
                if (nMoney < 0) return
            }
        }

        if (index + 1 == n) {
            answer = true
            return
        }

        visited[index] = true

        for (i in room.doors) {
            if (visited[i - 1].not()) dfs(i - 1, nMoney)
        }

        visited[index] = false
    }
}
