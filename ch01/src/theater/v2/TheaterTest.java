package theater.v2;

// 객체들의 자율성을 높여보자
// Audience와 TicketSeller가 직접 Bag와 TicketOffice를 처리할 수 있도록 수정했다.
// 가장 크게 달라진 점은 Audience와 TicketSeller가 내부 구현을 외부에 노출하지 않고
// 자신의 문제를 스스로 책임지고 해결한다는 것이다.

// 제일 중요한 점은 Audience와 TicketSeller의 내부 구현을 변경하더라도 Theater를 함께
// 변경할 필요가 없어졌다는 것이다.

// 캡슐화와 응집도
// 핵심은 객체 내부의 상태를 캡슐화하고 객체 간에 오직 메시지를 통해서만 상호작용하도록 만드는 것이다.
// Thether는 Ticketeller의 내부에 대해서는 알지 못한다. 단지 TicketSeller가 sellTo 메시지를 이해하고
// 응답할 수 있다는 사실만 알고 있을 뿐이다. 마찬가지로 TicketSeller 역시 Audience의 내부에 대해 전혀 알지 못한다.

// 자신의 데이터를 스스로 처리하는 자율적인 객체를 만들면 결합도를 낮출 수 있을뿐더러 응집도를 높일 수 있다.
// 외부의 간섭을 배제하고 메시지를 통해서만 협력하는 자율적인 객체들의 공동체를 만드는 것이 훌륭한 객체지향
// 설계를 얻을 수 있는 지름길이다!

// 변경하기 쉬운 설계는 한 번에 하나의 클래스만 변경할 수 있는 설계다 -> 클린코드에도 똑같은 말이 있었다
// 수정한 Audience와 TicketSeller 같이 데이터와 프로세스가 동일한 모듈 내부에 위치하도록 프로그래밍하는
// 방식을 객체지향 프로그래밍이라고 한다 -> 이해가 확된다. 참 좋은 책인 것 같다.

// 객체 지향 설계에서는 독재자가 존재하지 않고고 각 객체에 책임이 적절하게 분배된다.
// 적절한 객체에 적절한 책임을 할당하면 이해하기 쉬운 구조와 읽기 쉬운 코드를 얻을 수 있다.

// 설계를 어렵게 만드는 것은 의존성이라는 것을 기억하자.
// 해결 방법은 불필요한 의존성을 제거함으로써 객체 사이의 결합도를 낮추는 것이다.
// 이 예제에서는 그 방법으로 캡슐화를 사용했다. 결과적으로 불필요한 내부사항을 객체 내부로 캡슐화하는 것은
// 객체의 자율성을 높이고 응집도 높은 객체들의 공동체를 창조할 수 있게 한다.
// 불필요한 세부사항을 캡슐화하는 자율적인 객체들이 낮은 결합도와 높은 응집도를 가지고 협력하도록 최소한의
// 의존성만을 남기는 것이 훌륭한 객체지향 설계이다.

// 그 후에 TicketOffice, Bag도 같은 방식으로 수정했다. 그런데 여기서 TicketOffice와
// Audience 사이에 의존성이 추가됐다. TicketOffice의 자율성은 높였지만 전체 설계의 관점에서는
// 결합도가 상승한 것이다. 이렇듯이 어떤 경우에도 모든 사람들을 만족시킬 수 있는 설계는 없다.
// 훌륭한 설계는 적절한 트레이드오프의 결과물이라는 사실을 명심하자.
public class TheaterTest {

    public static void main(String[] args) {

        long fee = 1000;
        long amount = 10000;

        Ticket ticket1 = new Ticket(fee);
        Ticket ticket2 = new Ticket(fee);

        Bag bag = new Bag(amount);

        Audience audience = new Audience(bag);

        TicketOffice ticketOffice = new TicketOffice(amount, ticket1, ticket2);

        TicketSeller ticketSeller = new TicketSeller(ticketOffice);

        Theater theater = new Theater(ticketSeller);

        System.out.println(ticketOffice.getAmount() + "\n");
        theater.enter(audience);
        System.out.println(ticketOffice.getAmount() + "\n");
    }
}
