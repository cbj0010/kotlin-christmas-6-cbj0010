package christmas.domain

import christmas.util.*

class MenuValidator(private val selectedMenu: String) {
    //Order(1,1),Order(스테이크,1) 이런식으로 나눠준 애 중애서 메뉴가 메뉴판에 있는 메뉴인지 팔별
    //1,1이런식으로 나눠진거는 메뉴 형식에 맞게 입력하지 않은거니까 예외를 던짐

    //수량이 0 개 일 때 ERROR_MENU_INPUT던짐
    init {
        isValidMenu()
    }


    private fun isValidMenu() {
        val parsedMenu = OrderParse().parseOrder(selectedMenu)
        parsedMenu.forEach { order ->
            require(!checkMenu(order.menuName)) { ErrorMessage.ERROR_MENU_INPUT.getMessage() }
        }
    }

    private fun checkMenu(menuNameToCheck: String): Boolean {
        //해당하는 메뉴가 없을 경우 ERROR_MENU_INPUT 던짐
        val isMainValid = MainMenu.entries.any { it.mainMenuName == menuNameToCheck }
        val isDessertValid = Dessert.entries.any { it.dessertName == menuNameToCheck }
        val isAppetizerValid = Appetizer.entries.any { it.appetizerName == menuNameToCheck }
        val isDrinkValid = Beverage.entries.any { it.drinkName == menuNameToCheck }

        // 하나라도 일치하는 메뉴가 있다면 true, 그렇지 않으면 false 반환
        return isMainValid || isDessertValid || isAppetizerValid || isDrinkValid

    }
}
