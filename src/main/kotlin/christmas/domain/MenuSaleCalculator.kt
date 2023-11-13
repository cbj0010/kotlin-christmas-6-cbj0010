package christmas.domain

import christmas.data.Order
import christmas.util.Appetizer
import christmas.util.MainMenu

class MenuSaleCalculator(private val day: String) {

    //user가 입력한 값을 어떻게 들고 있을지 생각,
    // 그래야 그거 기반으로 주말, 평일의 메뉴 할인을 어캐 적용할건지 달라지니까


    //주말, 평일의 경우를 나눠서 할일율을 계산하는 함수
    fun calculateDiscountRate(selectedMenus: List<Order>) {
        when (day) {
            "주말" -> calculateWeekendDiscount(selectedMenus)
            "평일" -> calculateWeekdayDiscount(selectedMenus)
        }
    }

    //중복되는 코드 확장함수로 줄일 수 있지 않을까?
    private fun calculateWeekendDiscount(selectedMenus: List<Order>) {    //주말 - 메인 메뉴를 메뉴1개당 할인
        var totalOrders = 0

        for (order in isMainMenu(selectedMenus)) {
            println("메뉴: ${order.menuName}, 수량: ${order.quantity}")
            totalOrders += order.quantity
        }
        println("총 할인량 ${totalOrders * 2023}")
    }

    private fun calculateWeekdayDiscount(selectedMenus: List<Order>) {    //평일 평일에는 디저트 메뉴를 할인해준다
//필요로 되어지는 변수 - 메뉴 리스트
        //메뉴 리스트들 중에서 디저트 메뉴 판별
        //받은 디저트 메뉴의 총 개수 확인
        //반환 - 총 할인금액
        var totalOrders = 0

        for (order in isDessertMenu(selectedMenus)) {
            println("메뉴: ${order.menuName}, 수량: ${order.quantity}")
            totalOrders += order.quantity
        }
        println("총 할인량 ${totalOrders * 2023}")
    }

    private fun isDessertMenu(selectedMenus: List<Order>): List<Order> {
        return selectedMenus.filter { order ->
            order.menuName in Appetizer.values().map { it.appetizerName }
        }
    }
    //list= {Order(양송이스프,2),Order(티본,3)}

    private fun isMainMenu(selectedMenus: List<Order>): List<Order> {
        return selectedMenus.filter { order ->
            order.menuName in MainMenu.values().map { it.mainMenuName }
        }
    }
}
