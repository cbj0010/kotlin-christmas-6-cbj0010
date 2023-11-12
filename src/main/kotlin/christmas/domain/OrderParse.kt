package christmas.domain

import christmas.data.Order

class OrderParse() {
    fun parseOrder(input: String): List<Order> {
        val orderList = mutableListOf<Order>()

        val menuOrders = input.split(",")

        for (menuOrder in menuOrders) {
            val (menuName, quantityStr) = menuOrder.split("-")

            try {
                val quantity = quantityStr.toInt()
                orderList.add(Order(menuName.trim(), quantity))
            } catch (e: NumberFormatException) {
                // 예외 처리: 수량이 숫자로 변환할 수 없는 경우
                println("수량을 올바르게 입력해주세요: $menuOrder")
            }
        }

        return orderList
    }

}