package christmas

import christmas.data.Order
import christmas.util.Beverage

class ValidateEventConditions(private val selectedMenus: List<Order>) {
    //order 클래스로 변환한 애를 가지고 10000원 이상 할인을 받을 수 있는 조건을 검증
    //검증한 값이 다 true인 경우에 전체 총 합을 구하기 위해서 검증이 완료 된건지 아닌지 전달

    fun checkEventRequirements(): Boolean {
        return true
    }

    private fun isOrderOnlyBeverage(): Boolean {
        //음료만 주문한 경우 함수명에 맞게 true반환
        var menuCount = 0
        for (order in selectedMenus) {
            val menu = Beverage.entries.find { it.drinkName == order.menuName }

            if (menu != null) {
                // 주문한 메뉴가 에피타이저Menu에 있는 경우
                menuCount++
            }
        }
        return (selectedMenus.size == menuCount)
    }
    //이게 false여야지만 1000원이상 주문시 할인 혜택을 적용할 수 있음

}