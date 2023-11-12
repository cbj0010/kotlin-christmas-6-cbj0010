package christmas.domain

import christmas.data.Order
import christmas.util.Appetizer
import christmas.util.MainMenu

class MenuSaleCalculator(private val day: String) {

    //user가 입력한 값을 어떻게 들고 있을지 생각,
    // 그래야 그거 기반으로 주말, 평일의 메뉴 할인을 어캐 적용할건지 달라지니까


    //주말, 평일의 경우를 나눠서 할일율을 계산하는 함수
    fun calculateDiscountRate(menuList: List<Order>) {
        when (day) {
            "주말" -> calculateWeekendDiscount(menuList)
            "평일" -> calculateWeekdayDiscount(menuList)
        }
    }

    //중복되는 코드 확장함수로 줄일 수 있지 않을까?
    private fun calculateWeekendDiscount(menuList: List<Order>) {    //주말 - 메인 메뉴를 메뉴1개당 할인
        var totalOrders = 0

        for (order in isMainMenu(menuList)) {
            println("메뉴: ${order.menuName}, 수량: ${order.quantity}")
            totalOrders += order.quantity
        }
        println("총 할인량 ${totalOrders * 2023}")
    }

    private fun calculateWeekdayDiscount(menuList: List<Order>) {    //평일 평일에는 디저트 메뉴를 할인해준다
//필요로 되어지는 변수 - 메뉴 리스트
        //메뉴 리스트들 중에서 디저트 메뉴 판별
        //받은 디저트 메뉴의 총 개수 확인
        //반환 - 총 할인금액
        var totalOrders = 0

        for (order in isDessertMenu(menuList)) {
            println("메뉴: ${order.menuName}, 수량: ${order.quantity}")
            totalOrders += order.quantity
        }
        println("총 할인량 ${totalOrders * 2023}")
    }

    private fun isDessertMenu(menuList: List<Order>): List<Order> {
        return menuList.filter { order ->
            order.menuName in Appetizer.values().map { it.appetizerName }
        }
    }

    private fun isMainMenu(menuList: List<Order>): List<Order> {
        return menuList.filter { order ->
            order.menuName in MainMenu.values().map { it.mainMenuName }
        }
    }

    /*
    private fun dfd(userInput: String) {
        // 사용자 입력에 해당하는 Appetizer를 찾음
        //원래는 입력받은 값을 이름 기준으로 {스프,스프,스프,타파스,타파스}
        //이런식으로 알고리즘 이용해서 반복문 돌려서 해당하는 매뉴만큼 ++하려고 했는데
        //Order data 클래스를 이용하니까 이제 필요가 없어진 듯
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
    */

}