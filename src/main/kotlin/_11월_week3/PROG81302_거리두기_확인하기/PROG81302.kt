package _11월_week3.PROG81302_거리두기_확인하기

class Solution {

    // matrix 의 i, j 가 element 인지 검사. element 와 같으면 true
    private fun check(matrix: Array<String>, i: Int, j: Int, element: Char): Boolean {
        var result = false
        if (i in 0..4 && j in 0..4) {
            if (matrix[i][j] == element) {
                result = true
            }
        }
        return result
    }

    fun solution(places: Array<Array<String>>): IntArray {
        val answer = ArrayList<Int>()

        for (place in places) {
            var result = 1
            // place : 5X5 행렬, (i, j)로 거리두기 확인

            // 가로
            for (i in 0..4) {
                // 세로
                for (j in 0..4) {
                    // place[i][j] 가 사람인 경우
                    if (check(place, i, j, 'P')) {
                        // 상, 하, 좌, 우 중 하나라도 사람인 경우
                        if (check(place, i - 1, j, 'P') ||
                            check(place, i + 1, j, 'P') ||
                            check(place, i, j - 1, 'P') ||
                            check(place, i, j + 1, 'P')
                        ) {
                            result = 0
                            break
                        }
                        // 좌측이 빈 테이블일 경우
                        if (check(place, i - 1, j, 'O')) {
                            // 빈 테이블 상단, 하단, 좌측 검사
                            if (check(place, i - 1, j - 1, 'P') ||
                                check(place, i - 1, j + 1, 'P') ||
                                check(place, i - 2, j, 'P')
                            ) {
                                result = 0
                                break
                            }
                        }
                        // 우측이 빈 테이블일 경우
                        if (check(place, i + 1, j, 'O')) {
                            // 빈 테이블 상단, 하단, 우측 검사
                            if (check(place, i + 1, j - 1, 'P') ||
                                check(place, i + 1, j + 1, 'P') ||
                                check(place, i + 2, j, 'P')
                            ) {
                                result = 0
                                break
                            }
                        }
                        // 상단이 빈 테이블일 경우
                        if (check(place, i, j - 1, 'O')) {
                            // 빈 테이블 상단, 좌측, 우측 검사
                            if (check(place, i, j - 2, 'P') ||
                                check(place, i - 1, j - 1, 'P') ||
                                check(place, i + 1, j - 1, 'P')
                            ) {
                                result = 0
                                break
                            }
                        }
                        // 하단이 빈 테이블일 경우
                        if (check(place, i, j + 1, 'O')) {
                            // 빈 테이블 하단, 좌측, 우측 검사
                            if (check(place, i, j + 2, 'P') ||
                                check(place, i - 1, j + 1, 'P') ||
                                check(place, i + 1, j + 1, 'P')
                            ) {
                                result = 0
                                break
                            }
                        }
                    }
                }
                if (result == 0) break
            }
            answer.add(result)
        }

        return answer.toIntArray()
    }
}
