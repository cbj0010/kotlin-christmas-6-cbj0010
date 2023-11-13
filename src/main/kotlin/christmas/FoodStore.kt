package christmas

import christmas.data.Order
import christmas.domain.CalculatorTotalPrice

class FoodStore() {
    fun start() {
        val parseMenu: List<Order> = listOf(
            Order(menuName = "티본스테이크", quantity = 1),
            Order(menuName = "바비큐립", quantity = 1),
            Order(menuName = "초코케이크", quantity = 2),
            Order(menuName = "제로콜라", quantity = 1)
        )
        val date = "3"
        val totalOrderPrice = CalculatorTotalPrice(parseMenu).checkSumOrderMoney()
//할인 전 총 주문금액
        println("할인 전 총 주문 금액 : $totalOrderPrice")
        //혜택
        println("혜택 내역")
        val totalDiscountPrice = SaleController(date.toInt(), parseMenu).sumSaleMoney()
        println("총혜택 금액")
        println(totalDiscountPrice)
        println("할인 후 예상 결제 금약")
        println(totalOrderPrice - totalDiscountPrice)
    }
}