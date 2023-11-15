package christmas

import christmas.data.Order
import christmas.domain.OrderParse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderParseTest {
    private val orderParse = OrderParse()

    @Test
    fun `메뉴판 형식(메뉴 - 수량)에 안맞게 입력 했을 때 예외처리`() {
        val selectMenus = "3, 제로콜라-a,5"
        assertThrows<IllegalArgumentException> { orderParse.parseOrder(selectMenus) }
    }

    @Test
    fun `입력한 값중에 공백이 들어 있을 때 예외를 던짐`() {
        val selectMenus = "-1,제로콜라-3"

        assertThrows<IllegalArgumentException> {
            orderParse.parseOrder(selectMenus)
        }
    }

    @Test
    fun `입력한 값중 개수가 int가 아닌 경우 예외를 던짐`() {
        val selectMenus = "1-제로콜라,제로콜라-1"

        assertThrows<IllegalArgumentException> {
            orderParse.parseOrder(selectMenus)
        }
    }

    @Test
    fun `공백이 들어가있는 경우 예외던짐`() {
        val selectMenus = "-제로콜라,제로콜라-"

        assertThrows<IllegalArgumentException> {
            orderParse.parseOrder(selectMenus)
        }
    }

    @Test
    fun `제대로 된 메뉴를 입력한 경우`() {
        val selectMenus = "티본스테이크-19,제로콜라-1"
        val parseMenu: List<Order> = listOf(
            Order(menuName = "티본스테이크", quantity = 19),
            Order(menuName = "제로콜라", quantity = 1)
        )
        Assertions.assertThat(OrderParse().parseOrder(selectMenus)).isEqualTo(parseMenu)
    }

}
