package christmas

import christmas.data.Order
import christmas.domain.*
import christmas.view.InputView
import christmas.view.OutputView


class FoodStore() {
    private val outputView = OutputView()
    fun start() {
        outputView.startMessage()
        checkUserValidate()
    }

    private fun checkUserValidate() {
//수량이 0 개 일 때 ERROR_MENU_INPUT던짐
        try {
            val date = InputDayValidator(InputView().inputUser()).isValidDate()
            println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
            val parsedMenu: List<Order> = OrderParse().parseOrder(InputView().inputUser())
            val menu = MenuValidator(parsedMenu).isValidMenu()
            showAll(date, menu)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            checkUserValidate()
        }
    }

    private fun showAll(date: Int, menu: List<Order>) {
        val totalOrderPrice = CalculatorTotalPrice(menu).checkSumOrderMoney()
        val totalDiscountPrice = SaleController(date, menu)
        val christMasBadge =
            totalDiscountPrice.sumSaleMoney() + totalDiscountPrice.calculateGiftEventReward(totalOrderPrice)
        outputView.startMessage()

        outputView.displayMenu(menu)

        //할인 전 총 주문금액
        outputView.displayTotalPriceOrder(totalOrderPrice)
        //증정메뉴
        showGiftEvent(ValidateEventConditions(totalOrderPrice).checkValidGift())
        //혜택내역
        showDiscount(totalOrderPrice, totalDiscountPrice)
        //할인 후 예상 결제 금액
        outputView.displayFinalMoney(totalOrderPrice - totalDiscountPrice.sumSaleMoney())

        outputView.displayChristMasBadge(christMasBadge)
    }

    private fun showGiftEvent(isGift: Boolean) {
        val outputView = OutputView()
        when (isGift) {
            true -> outputView.displayGiftEvent()
            false -> outputView.displayGiftNoneEvent()
        }
    }

    private fun showDiscount(totalPrice: Int, totalDiscountPrice: SaleController) {
        //이게 true이면 sale이 들어감 false면 세일이 들어가지 않음을 보여줌
        val outputView = OutputView()
        val checkEvent = ValidateEventConditions(totalPrice)

        if (checkEvent.checkEventRequirements()) {
            totalDiscountPrice.saveDiscountInfo(totalPrice)
        } else {
            outputView.displayNoBenefits()
        }
    }
}
