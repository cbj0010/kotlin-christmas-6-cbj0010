package christmas.domain

import christmas.data.Order
import christmas.util.ErrorMessage

class OrderParse() {
    /*
  사용자가 입력한 값을 splite로 나눠서 저장하는 곳
  티본스테이크 1개
  바비큐립 1개
  이런 형식

  ,와 -를 기준으로 나눌 때 이뤄져야 하는 예외처리
   */

    fun parseOrder(input: String): List<Order> {
        val orderList = mutableListOf<Order>()
        val menuOrders = input.split(",")
        isValidOrder(input)
        for (menuOrder in menuOrders) {
            orderList.add(parseByHyphen(menuOrder))
        }
        return orderList
    }

    private fun isValidOrder(input: String) {
        val hyphenCount = input.count { it == '-' }
        val commaCount = input.count { it == ',' }

        require(hyphenCount == commaCount + 1) { ErrorMessage.ERROR_MENU_INPUT.getMessage() }
    }

    private fun parseByHyphen(menuOrder: String): Order {

        val (menuName, quantityStr) = menuOrder.split("-")
        if (menuName.isBlank() || quantityStr.isBlank()) {
            throw IllegalArgumentException(ErrorMessage.ERROR_MENU_INPUT.getMessage())
        }
        val quantity = quantityStr.toIntOrNull()
            ?: throw IllegalArgumentException(ErrorMessage.ERROR_MENU_INPUT.getMessage() + "주문 숫자가 int값이 아님")

        return Order(menuName.trim(), quantity)
    }
}
