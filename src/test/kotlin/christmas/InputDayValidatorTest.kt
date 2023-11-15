package christmas

import christmas.domain.InputDayValidator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputDayValidatorTest {


    @Test
    fun `날짜입력에 숫자가 아닌 값을 입력했을 때 예외`() {
        assertThrows<IllegalArgumentException> { InputDayValidator("a").isValidDate() }
    }


    @Test
    fun `1~31사이의 날짜를 입력하지 않은 경우 예외`() {
        assertThrows<IllegalArgumentException> { InputDayValidator("33").isValidDate() }
    }

    @Test
    fun `날짜를 제대로 입력한 경우 입력한 값 반환`() {
        assertThat(InputDayValidator("3").isValidDate()).isEqualTo(3)
    }

}