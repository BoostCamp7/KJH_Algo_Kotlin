package _2022._10월_24_to_28.BOJ4485_녹색_옷_입은_애가_젤다지

/*
   최소비용, 최단경로 -> 다익스트라?

   지점과 도둑루피 값을 가지는 data class를 만들어 인접한 모든 경로를 도둑루피 값 순으로 정렬되는 우선순위 큐에 넣고 하나씩 꺼내서 탐색 (BFS)
*/

data class Node(
    val point: Pair<Int, Int>,
    val rupee: Int
) : Comparable<Int> {
    override fun compareTo(other: Int): Int {
        return rupee.compareTo(other)
    }
}
