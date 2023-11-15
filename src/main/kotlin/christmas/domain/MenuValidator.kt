package christmas.domain

import christmas.data.Order
import christmas.util.*

class MenuValidator(private val parsedMenu: List<Order>) {
    //Order(1,1),Order(스테이크,1) 이런식으로 나눠준 애 중애서 메뉴가 메뉴판에 있는 메뉴인지 팔별
    //1,1이런식으로 나눠진거는 메뉴 형식에 맞게 입력하지 않은거니까 예외를 던짐

    fun isValidMenu(): List<Order> {
        parsedMenu.forEach { order ->
            require(checkMenu(order.menuName)) { ErrorMessage.ERROR_MENU_INPUT.getMessage() }
        }
        require(checkMinimumOrderAmount()) { ErrorMessage.ERROR_MENU_INPUT.getMessage() }
        require(!checkOnlyBeverage()) { ErrorMessage.ERROR_MENU_INPUT.getMessage() }
        require(checkMaximumOrderAmount()) { ErrorMessage.ERROR_MENU_INPUT.getMessage() }
        require(isDuplicateMenu()) { ErrorMessage.ERROR_MENU_INPUT.getMessage() }
        return parsedMenu
    }

    private fun checkMenu(menuNameToCheck: String): Boolean {
        //해당하는 메뉴가 없을 경우 ERROR_MENU_INPUT 던짐
        val isMainValid = MainMenu.entries.any { it.mainMenuName == menuNameToCheck }
        val isDessertValid = Dessert.entries.any { it.dessertName == menuNameToCheck }
        val isAppetizerValid = Appetizer.entries.any { it.appetizerName == menuNameToCheck }
        val isDrinkValid = Beverage.entries.any { it.drinkName == menuNameToCheck }

        // 하나라도 일치하는 메뉴가 있다면 true, 그렇지 않으면 false 반환
        return isMainValid || isDessertValid || isAppetizerValid || isDrinkValid
    }

    private fun checkMinimumOrderAmount() = parsedMenu.size > 1
    private fun checkMaximumOrderAmount(): Boolean {
        var totalOrderNum = 0
        parsedMenu.forEach { order ->
            totalOrderNum += order.quantity
        }
        return (totalOrderNum < 21)
    }

    private fun checkOnlyBeverage(): Boolean {
        //음료수만 입력한 경우 true
        var total = 0

        for (order in parsedMenu) {
            val menu = Beverage.entries.find { it.drinkName == order.menuName }

            if (menu != null) {
                // 주문한 메뉴가 음료Menu에 있는 경우
                total++
            }
        }
        return parsedMenu.size == total
    }

    private fun isDuplicateMenu() = parsedMenu.distinct().size == parsedMenu.size


}
