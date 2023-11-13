package christmas

import christmas.data.Order
import christmas.domain.OrderParse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderParseTest {
    @Test
    fun `-를 기준으로 값을 잘 나누는지 검사`() {
        val selectMenus = "타파스-1,제로콜라-1"
        val parseMenu: List<Order> = listOf(
            Order(menuName = "타파스", quantity = 1),
            Order(menuName = "제로콜라", quantity = 1)
        )
        assertThat(OrderParse().parseOrder(selectMenus)).isEqualTo(parseMenu)
    }

    @Test
    fun `입력한 값중에 공백이 들어 있을 때 예외를 던짐`() {
        val selectMenus = "-1,제로콜라-"

        assertThrows<IllegalArgumentException> {
            OrderParse().parseOrder(selectMenus)
        }
    }

    @Test
    fun `입력한 값중 개수가 int가 아닌 경우 예외를 던짐`() {
        val selectMenus = "1-제로콜라,제로콜라-1"

        assertThrows<IllegalArgumentException> {
            OrderParse().parseOrder(selectMenus)
        }
    }
}
