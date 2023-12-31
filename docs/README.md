## 기존 요구사함

- [x]  함수는 15줄을 지킨다.
- [x]  리드미를 상세하게.
- [x]  기능목록은 계속해서 추가하면서 해라
- [x]  하드 코딩 하지 말아라
- [x]  변수 이름에 자료형 쓰지 말아라
- [x]  작은 단위의 테스트 부터 진행해라
---
## 공통 피드백

- [x]  예외 사항에 대해서 고민해라
- [x]  비즈니스 로직과 ui 로직을 분리해라
    - [x]  뷰에서 사용할 데이터라면 getter메서드를 통해서 데이터를 전달하라.
- [x]  연관성 있는 상수는 전역으로 선언하는 대신 enum 을 활용한다.
- [x]  val을 이용해서 다른 곳에서 값을 바꾸지 못하도록 한다.
- [x]  객체의 프로퍼티듸 접근 제어자는 private으로 구현한다.
---
# 이야기

안녕하세요~ 저 그 식당 예약하고 싶어서 전화했어요~<br>
아~ 네 그러면 오실 날짜랑 드실 메뉴를 미리 알려주시겠어요? 알려주시면 어떤 증정과 혜택 그리고 총 할인 금액 및 결제 금액을 미리 알려드릴게요!<br>
저 이거저거 2개씩 먹고싶어요!<br>네! 여기 할인 정보요!<br>
<주문 메뉴><br><할인 전 총주문 금액><br><증정 메뉴><br><혜택 내역><br><총 혜택 금액><br><할인 후 예상 결제 금액><br><12월 이벤트 배지><br>입니다!
-----
 내가 생각한 핵심기능 - 어떤 할인 혜택이 적용되는 날짜인지 판단할 수 있다.
------
# 기능명세

## 입력받는 곳

- [x]  방문 날짜를 입력받는다.
    - [x] 날짜는 숫자여야한다.
    - [x] 1~31 사이의 값
- [x]  어떤 메뉴와 몇개를 먹을 것인지 입력한다.
- [x]  입력을 잘못 받은 경우 잘못받은 부분 부터 다시 입력받도록한다.
    * 조건
      타파스-1,제로콜라-1

    - [x]  메뉴 형식에 맞게 입력해야한다(메뉴 - 수량)
        - [x] "-"를 기준으로 나눌 수 있다.
            - [x] 입력한 값이 공백일때 - 예와
            - [x] 입력한 값이 자료형에 맞지 않을 때 - 예외
    - [x]  메뉴판에 있는 메뉴여야 한다.
        - [x]  메뉴판에 있는 메뉴인지 판별할 수 있어야 한다.
            - [x] 입력한 값이 메뉴- 수량 순서에 맞지 않을 때 - 예외
    - [x]  메뉴는 1개 이상이여야한다.
        - [x]  음료만 주문시 주문할 수 없다.
    - [x]  메뉴는 최대 20개 까지 주문할 수 있다.
        - [x] 메뉴의 개수를 카운트 할 수 있다.
    - [x]  메뉴는 중복일 수 없다.
        - [x] 중복을 제거한 수와 입력받은 수가 같은지

- - ---

## 할인 혜택 계산하는 곳

### 총 주문 금액이 1000원 이상일 때

* 이벤트를 하기 위한 주의사항 검증

- [x] 총 주문 금액이 10000원 이상인지 판별한다.<br>
- [x] 총 주문 금액을 구할 수 있어야 한다.

* 요일을 알아야 적용할 수 있는 할인

  총주문 금액에서 할인
    * 크리스마스 D-day할인

    - [x]  1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    - [x]  이벤트 기한은 12.01 ~ 12.25이다.

    * 특별할인

    - [x]  일요일, 12월25일 인 경우에 1000원이 총액에서 할인된다.

  각메뉴마다 할인
    * 평일할인

    - [x]  디저트 메뉴당 2,023원이 할인된다.

    * 주말할인

    - [x]  메인 메뉴당 2,023원이 할인된다.

* 증정 이벤트

- [x] 할인 전 총 금액이 12만원 이상인 경우 증정 샴페인이 나간다.

### 총 주문 금액이 1000미만일 때

    - [x] 증정 메뉴 - 없음
    - [x] 혜택내역 - 없음
    - [x] 총혜택 금액 - 없음
    - [x] 12월 이벤트 배지 - 없음

## 출력하는 곳

- [x] 돈이 1,000 이런식으로 ","가 찍혀서 출력되어야됨

- ---

