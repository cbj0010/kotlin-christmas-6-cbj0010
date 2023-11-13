package christmas

import christmas.data.Order
import christmas.util.Appetizer
import christmas.util.Beverage
import christmas.util.Dessert
import christmas.util.MainMenu

class CalculatorTotalPrice(private val selectedMenus: List<Order>) {
    //조건에 성립되는 메뉴List만 받아서 최종금액을 보여주면 되는거아님?
    //다른 곳에서 조건검사 하고
    //음료만 주문해서 10000원을 넘기는 경우는 우짜지 ->음료만 주문시 주문할 수 없음을 보여줘야 됨
    fun checkSumOrderMoney(): Int {  //주문 금액의 합계를 확인하는 함수
        //필요로 되어지는 거 = 어떤것을 주문했는지의 list
        //여기서 list는 검증이 완료된 list여야함
        return sumMainMenuPrice() + sumAppetizerMenuPrice() + sumDesertMenuPrice() + sumBeverageMenuPrice()
    }

    private fun sumAppetizerMenuPrice(): Int {
        var total = 0
        for (order in selectedMenus) {
            val menu = Appetizer.values().find { it.appetizerName == order.menuName }

            if (menu != null) {
                // 주문한 메뉴가 에피타이저Menu에 있는 경우
                total += menu.appetizerMoney * order.quantity
            }
        }
        return total
    }

    private fun sumMainMenuPrice(): Int {
        var total = 0
        for (order in selectedMenus) {
            val menu = MainMenu.values().find { it.mainMenuName == order.menuName }

            if (menu != null) {
                // 주문한 메뉴가 MainMenu에 있는 경우
                total += menu.mainMenuMoney * order.quantity
            }
        }
        return total
    }

    private fun sumDesertMenuPrice(): Int {
        var total = 0
        for (order in selectedMenus) {
            val menu = Dessert.values().find { it.dessertName == order.menuName }

            if (menu != null) {
                // 주문한 메뉴가 디저트Menu에 있는 경우
                total += menu.dessertPrice * order.quantity
            }
        }
        return total
    }

    private fun sumBeverageMenuPrice(): Int {
        var total = 0
        for (order in selectedMenus) {
            val menu = Beverage.values().find { it.drinkName == order.menuName }

            if (menu != null) {
                // 주문한 메뉴가 음료Menu에 있는 경우
                total += menu.drinkPrice * order.quantity
            }
        }
        return total
    }

}
