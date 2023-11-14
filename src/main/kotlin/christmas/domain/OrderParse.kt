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
        isValidMenuFormat(input)
        for (menuOrder in menuOrders) {
            orderList.add(parseByHyphen(menuOrder))
        }
        return orderList
    }

    private fun isValidMenuFormat(menuOrder: String): Boolean {
        val parts = menuOrder.split(",")
        println("분리했을 때 기대 값 $parts")
        val menuName = parts[0].trim()
        val quantityStr = parts[1].trim()

        require(menuName.isNotBlank() && quantityStr.isNotBlank()) { ErrorMessage.ERROR_MENU_INPUT.getMessage() }

        return true
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
