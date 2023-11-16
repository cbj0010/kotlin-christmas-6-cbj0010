package christmas.domain

import christmas.data.Order
import christmas.util.*
import christmas.util.StoreMessageConstants.FIRST_NUM
import christmas.util.StoreMessageConstants.ZERO_NUM

class MenuValidator(private val parsedMenu: List<Order>) {

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
        val isMainValid = MainMenu.entries.any { it.mainMenuName == menuNameToCheck }
        val isDessertValid = Dessert.entries.any { it.dessertName == menuNameToCheck }
        val isAppetizerValid = Appetizer.entries.any { it.appetizerName == menuNameToCheck }
        val isDrinkValid = Beverage.entries.any { it.drinkName == menuNameToCheck }

        return isMainValid || isDessertValid || isAppetizerValid || isDrinkValid
    }

    private fun checkMinimumOrderAmount() = parsedMenu.size > FIRST_NUM
    private fun checkMaximumOrderAmount(): Boolean {
        var totalOrderNum = ZERO_NUM
        parsedMenu.forEach { order ->
            totalOrderNum += order.quantity
        }
        return (totalOrderNum < MAXIMUM_ORDER)
    }

    private fun checkOnlyBeverage(): Boolean {
        var total = ZERO_NUM

        for (order in parsedMenu) {
            val menu = Beverage.entries.find { it.drinkName == order.menuName }

            if (menu != null) {
                total++
            }
        }
        return parsedMenu.size == total
    }

    private fun isDuplicateMenu() = parsedMenu.distinctBy { it.menuName }.size == parsedMenu.size

    companion object {
        private const val MAXIMUM_ORDER = 21
    }
}
