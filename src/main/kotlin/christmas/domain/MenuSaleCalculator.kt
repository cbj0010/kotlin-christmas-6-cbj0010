package christmas.domain

import christmas.data.Order
import christmas.util.Dessert
import christmas.util.MainMenu
import christmas.util.StoreMessageConstants.WEEKEND_DAY
import christmas.util.StoreMessageConstants.ZERO_NUM

class MenuSaleCalculator(private val day: String) {

    fun calculateDiscountRate(selectedMenus: List<Order>): Int {
        return when (day) {
            WEEKEND_DAY -> calculateWeekendDiscount(selectedMenus)
            else -> calculateWeekdayDiscount(selectedMenus)
        }
    }

    private fun calculateWeekendDiscount(selectedMenus: List<Order>): Int {
        var totalOrders = ZERO_NUM

        for (order in isMainMenu(selectedMenus)) {
            totalOrders += order.quantity
        }
        return totalOrders * MENU_DISCOUNT
    }

    private fun calculateWeekdayDiscount(selectedMenus: List<Order>): Int {
        var totalOrders = ZERO_NUM

        for (order in isDessertMenu(selectedMenus)) {
            totalOrders += order.quantity
        }
        return totalOrders * MENU_DISCOUNT
    }

    private fun isDessertMenu(selectedMenus: List<Order>): List<Order> {
        return selectedMenus.filter { order ->
            order.menuName in Dessert.entries.map { it.dessertName }
        }
    }

    private fun isMainMenu(selectedMenus: List<Order>): List<Order> {
        return selectedMenus.filter { order ->
            order.menuName in MainMenu.entries.map { it.mainMenuName }
        }
    }

    companion object {
        const val MENU_DISCOUNT = 2023
    }
}
