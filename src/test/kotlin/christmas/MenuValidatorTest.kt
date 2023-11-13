package christmas

import christmas.domain.MenuValidator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MenuValidatorTest {

    @Test
    fun `메뉴판에 없는 메뉴를 입력 했을 때 예외처리`() {
        val selectMenus = "꼼파뇨-1,제로콜라-1"
        assertThrows<IllegalArgumentException> { MenuValidator(selectMenus) }
    }
}