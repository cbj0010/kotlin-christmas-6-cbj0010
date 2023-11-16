package christmas.domain

import christmas.data.Order
import christmas.util.ErrorMessage

class OrderParse() {

    fun parseOrder(input: String): List<Order> {
        val orderMenus = mutableListOf<Order>()
        val menuOrders = input.split(COMMA)
        isValidOrder(input)
        for (menuOrder in menuOrders) {
            orderMenus.add(parseByHyphen(menuOrder))
        }
        return orderMenus
    }

    private fun isValidOrder(input: String) {
        val hyphenCount = input.count { it == HYPHEN }
        val commaCount = input.count { it == COMMA }

        require(hyphenCount == commaCount + 1) { ErrorMessage.ERROR_MENU_INPUT.getMessage() }
    }

    private fun parseByHyphen(menuOrder: String): Order {

        val (menuName, quantityStr) = menuOrder.split(HYPHEN)
        if (menuName.isBlank() || quantityStr.isBlank()) {
            throw IllegalArgumentException(ErrorMessage.ERROR_MENU_INPUT.getMessage())
        }
        val quantity = quantityStr.toIntOrNull()
            ?: throw IllegalArgumentException(ErrorMessage.ERROR_MENU_INPUT.getMessage())

        return Order(menuName.trim(), quantity)
    }

    companion object {
        private const val COMMA = ','
        private const val HYPHEN = '-'
    }
}
