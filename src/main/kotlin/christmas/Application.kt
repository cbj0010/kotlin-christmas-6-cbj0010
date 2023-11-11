package christmas

fun main() {
//    dayOfWeek()
    val input = "해산물파스타-2,레드와인-1"
    val orders = OrderParse().parseOrder(input)

    for (order in orders) {
        println("주문 메뉴: ${order.menuName}, 수량: ${order.quantity}")
    }
}

//입력한 요일을 판단하는 함수

val userInput = InputView()

data class Order(val menuName: String, val quantity: Int)

class OrderParse(){
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


