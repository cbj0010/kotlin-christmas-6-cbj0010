package christmas

import christmas.data.Order
import christmas.domain.MenuValidator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MenuValidatorTest {

    @Test
    fun `메뉴를 제대로 입력했을 때`() {
        val selectMenus = listOf(
            Order(menuName = "타파스", quantity = 1),
            Order(menuName = "제로콜라", quantity = 1)
        )
        assertThat(MenuValidator(selectMenus).isValidMenu()).isEqualTo(selectMenus)
    }

    @Test
    fun `메뉴판에 없는 메뉴를 입력 했을 때 예외처리`() {
        val selectMenus = listOf(
            Order(menuName = "꼼파뇨", quantity = 1),
            Order(menuName = "제로콜라", quantity = 1)
        )
        assertThrows<IllegalArgumentException> { MenuValidator(selectMenus).isValidMenu() }
    }

    @Test
    fun `메뉴주문 수가 20개가 넘을 때 예외처리`() {
        val selectMenus = listOf(
            Order(menuName = "티본스테이크", quantity = 20),
            Order(menuName = "제로콜라", quantity = 1)
        )
        assertThrows<IllegalArgumentException> { MenuValidator(selectMenus).isValidMenu() }
    }

    @Test
    fun `메뉴주문이 중복일때 예외처리`() {
        val selectMenus = listOf(
            Order(menuName = "티본스테이크", quantity = 1),
            Order(menuName = "티본스테이크", quantity = 2)
        )
        assertThrows<IllegalArgumentException> { MenuValidator(selectMenus).isValidMenu() }
    }
}
