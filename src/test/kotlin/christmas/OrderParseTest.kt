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
    fun `입력한 값이 메뉴이름,숫자 에 따른 자료형에 맞지 않을 때 예외를 던짐`() {

    }
}

/*
 fun `3개가 동일 할때 5등이다`() {
        val lottoUser = listOf(1, 2, 3, 4, 5, 6)
        val bonus = 7
        val calculator = Calculator()
        calculator.compareNum(lottoUser, bonus, winningLottoNum)
        val compareResult = calculator.lottoResult
        val lottoResult = mapOf<MatchedCount, Int>(
            MatchedCount.FIFTH to 1,
            MatchedCount.FOURTH to 0,
            MatchedCount.THIRD to 0,
            MatchedCount.SECOND to 0,
            MatchedCount.FIRST to 0,
        )
        assertThat(compareResult).isEqualTo(lottoResult)
    }
 */