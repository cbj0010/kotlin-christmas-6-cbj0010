package christmas

import christmas.data.Order
import christmas.domain.MenuSaleCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MenuSaleCalculatorTest {
    @Test
    fun `주말에 메인메뉴를 먹었을 때의 총 할인 금액`() {
        val parseMenu: List<Order> = listOf(
            Order(menuName = "티본스테이크", quantity = 2),
            Order(menuName = "양송이수프", quantity = 1)
        )

        val saleMoney = MenuSaleCalculator("주말").calculateDiscountRate(parseMenu)
        assertThat(saleMoney).isEqualTo(4046)
    }
}