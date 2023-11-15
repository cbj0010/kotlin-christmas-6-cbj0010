package christmas.domain

import christmas.data.Order
import christmas.util.Appetizer
import christmas.util.Beverage
import christmas.util.Dessert
import christmas.util.MainMenu
import christmas.util.StoreMessageConstants.ZERO_NUM

class CalculatorTotalPrice(private val selectedMenus: List<Order>) {
    fun checkSumOrderMoney(): Int {
        return sumMainMenuPrice() + sumAppetizerMenuPrice() + sumDesertMenuPrice() + sumBeverageMenuPrice()
    }

    private fun sumAppetizerMenuPrice(): Int {
        var total = ZERO_NUM
        for (order in selectedMenus) {
            val menu = Appetizer.entries.find { it.appetizerName == order.menuName }

            if (menu != null) {
                total += menu.appetizerMoney * order.quantity
            }
        }
        return total
    }

    private fun sumMainMenuPrice(): Int {
        var total = ZERO_NUM
        for (order in selectedMenus) {
            val menu = MainMenu.entries.find { it.mainMenuName == order.menuName }

            if (menu != null) {
                total += menu.mainMenuMoney * order.quantity
            }
        }
        return total
    }

    private fun sumDesertMenuPrice(): Int {
        var total = ZERO_NUM
        for (order in selectedMenus) {
            val menu = Dessert.entries.find { it.dessertName == order.menuName }

            if (menu != null) {
                total += menu.dessertPrice * order.quantity
            }
        }
        return total
    }

    private fun sumBeverageMenuPrice(): Int {
        var total = ZERO_NUM
        for (order in selectedMenus) {
            val menu = Beverage.entries.find { it.drinkName == order.menuName }

            if (menu != null) {
                total += menu.drinkPrice * order.quantity
            }
        }
        return total
    }

}
