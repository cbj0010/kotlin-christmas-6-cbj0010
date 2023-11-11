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




class Calculator() {
    private fun dayOfWeek(): String {
        val dayOfWeek = mutableMapOf<DayOfWeek, Int>()
        val day = userInput.inputDay().toInt()
        return when (day % 7) {
            1, 2 -> "주말"
            else -> "평일"
        }
    }

    //주말, 평일의 경우를 나눠서 할일율을 계산하는 함수
    private fun checkWeekDay() {

    }

    private fun calculateWeekendDiscount() {    //주말

    }

    private fun calculateWeekdayDiscount(userInput: String) {    //평일 평일에는 디저트 메뉴를 할인해준다

        // 사용자 입력에 해당하는 Appetizer를 찾음
        val selectedMenu = Appetizer.values().find { it.appetizerName == userInput }


        // 선택된 메뉴에 따라 개수를 증가시킬 mutableMap 초기화
        val menuCountMap = mutableMapOf<Appetizer, Int>(
            Appetizer.MUSHROOM_SOUP to 0,
            Appetizer.TAPAS to 0,
            Appetizer.CAESAR_SALAD to 0
        )

        // 선택된 메뉴에 해당하는 count 증가
        menuCountMap[selectedMenu!!] = menuCountMap[selectedMenu]!! + 2023
    }
}


enum class DayOfWeek(val dayNumber: Int, val dayName: String) {
    FRIDAY(1, "금요일"),
    SATURDAY(2, "토요일"),
    SUNDAY(3, "일요일"),
    MONDAY(4, "월요일"),
    TUESDAY(5, "화요일"),
    WEDNESDAY(6, "수요일"),
    THURSDAY(7, "목요일");

}

enum class Appetizer(val appetizerName: String, val appetizerMoney: Int) {
    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000);
}

enum class MainMenu(val mainMenuName: String, val mainMenuMoney: Int) {
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIB("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000);
}

enum class Dessert(val dessertName: String, val dessertPrice: Int) {
    CHOCO_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);
}

enum class Beverage(val drinkName: String, val drinkPrice: Int) {
    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);
}
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


