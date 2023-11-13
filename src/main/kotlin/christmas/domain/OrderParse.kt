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

        for (menuOrder in menuOrders) {
            orderList.add(parseByHyphen(menuOrder))
        }
        return orderList
    }

    private fun isValidMenuFormat(menuOrder: String): Boolean {
        val parts = menuOrder.split(",")

        if (parts.size != 2) {
            throw IllegalArgumentException(ErrorMessage.ERROR_MENU_INPUT.getMessage() + "올바른 주문 형식이 아님")
        }
        return true
    }

    private fun parseByHyphen(menuOrder: String): Order {
        isValidMenuFormat(menuOrder)
        val (menuName, quantityStr) = menuOrder.split("-")
        if (menuName.isBlank() || quantityStr.isBlank()) {
            throw IllegalArgumentException(ErrorMessage.ERROR_MENU_INPUT.getMessage())
        }
        val quantity = quantityStr.toIntOrNull()
            ?: throw IllegalArgumentException(ErrorMessage.ERROR_MENU_INPUT.getMessage() + "주문 숫자가 int값이 아님")

        return Order(menuName.trim(), quantity)
    }

    private fun checkQuantity(menuName: String, quantityStr: String): MutableList<Order> {
        //숫자 입력이 맞는지
        val orderList = mutableListOf<Order>()
        try {
            val quantity = quantityStr.toInt()
            orderList.add(Order(menuName.trim(), quantity))
        } catch (e: NumberFormatException) {
            // 예외 처리: 수량이 숫자로 변환할 수 없는 경우
            println("수량을 올바르게 입력해주세요:")
        }
        return orderList
    }
}
