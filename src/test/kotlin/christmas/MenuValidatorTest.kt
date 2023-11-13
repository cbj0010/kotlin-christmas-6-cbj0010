package christmas

import christmas.domain.MenuValidator
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
        val selectMenus = "3, 제로콜라-a"
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
}