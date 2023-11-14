package christmas

import christmas.data.Order
import christmas.domain.MenuValidator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MenuValidatorTest {

    @Test
    fun `메뉴판에 없는 메뉴를 입력 했을 때 예외처리`() {
        val selectMenus = "꼼파뇨-1,제로콜라-1"
        assertThrows<IllegalArgumentException> { MenuValidator(selectMenus).isValidMenu() }
    }

    @Test
    fun `메뉴판 형식(메뉴 - 수량)에 안맞게 입력 했을 때 예외처리`() {
        val selectMenus = "3, 제로콜라-a,5"
        assertThrows<IllegalArgumentException> { MenuValidator(selectMenus).isValidMenu() }
    }

    @Test
    fun `메뉴주문 수가 20개가 넘을 때 예외처리`() {
        val selectMenus = "티본스테이크-20,제로콜라-1"
        assertThrows<IllegalArgumentException> { MenuValidator(selectMenus).isValidMenu() }
    }

    @Test
    fun `메뉴주문이 중복일때 예외처리`() {
        val selectMenus = "티본스테이크-2,티본스테이크-1,티본스테이크-3,티본스테이크-4"
        assertThrows<IllegalArgumentException> { MenuValidator(selectMenus).isValidMenu() }
    }

    @Test
    fun `제대로 된 메뉴를 입력한 경우`() {
        val selectMenus = "티본스테이크-19,제로콜라-1"
        val parseMenu: List<Order> = listOf(
            Order(menuName = "티본스테이크", quantity = 19),
            Order(menuName = "제로콜라", quantity = 1)
        )
        assertThat(MenuValidator(selectMenus).isValidMenu()).isEqualTo(parseMenu)
    }

}
