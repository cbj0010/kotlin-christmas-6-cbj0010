package christmas.domain

import christmas.DayOfWeek
import christmas.userInput
import christmas.util.Appetizer

class SaleCalculator {
    private fun dayOfWeek(): String {
        val dayOfWeek = mutableMapOf<DayOfWeek, Int>()
        val day = userInput.inputDay().toInt()
        return when (day % 7) {
            1, 2 -> "주말"
            else -> "평일"
        }
    }

    //주말, 평일의 경우를 나눠서 할일율을 계산하는 함수
    private fun checkWeekDay() {

    }

    private fun calculateWeekendDiscount() {    //주말

    }

    private fun calculateWeekdayDiscount(userInput: String) {    //평일 평일에는 디저트 메뉴를 할인해준다

        // 사용자 입력에 해당하는 Appetizer를 찾음
        val selectedMenu = Appetizer.values().find { it.appetizerName == userInput }


        // 선택된 메뉴에 따라 개수를 증가시킬 mutableMap 초기화
        val menuCountMap = mutableMapOf<Appetizer, Int>(
            Appetizer.MUSHROOM_SOUP to 0,
            Appetizer.TAPAS to 0,
            Appetizer.CAESAR_SALAD to 0
        )

        // 선택된 메뉴에 해당하는 count 증가
        menuCountMap[selectedMenu!!] = menuCountMap[selectedMenu]!! + 2023
    }
}