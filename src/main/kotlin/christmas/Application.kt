package christmas

import christmas.domain.OrderParse
import christmas.view.InputView

fun main() {
//    dayOfWeek()
    val input = "해산물파스타-2,레드와인-1"
    val orders = OrderParse().parseOrder(input)

    for (order in orders) {
        println("주문 메뉴: ${order.menuName}, 수량: ${order.quantity}")
    }
}
