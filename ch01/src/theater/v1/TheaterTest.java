package theater.v1;

// 티캣 판매 애플리케이션

// v1 버전은 상당히 문제가 많다. 일단 예상을 빗나간다.
// 당연하지만 관람객이 직접 자신의 가방에서 초대장을 꺼내 판매원에게 건내는 것이 맞다.
// 하지만 이 코드는 소극장이 관람객의 의사 상관없이 가방을 열어서 초대장, 티켓, 현금을 확인한다.

// 변경에도 취약하다. 객체간 결합도가 높기 때문에 한 객체가 바뀌면 다른 객체들도 다 바꿔줘야 한다.
// 관람객이 가방을 들고 있다는 과정이 바뀌었다고 치면, Audience 객체에서 Bag을 제거해야 할 뿐만 아니라
// Theater의 enter 메서드 역시 수정해야 한다.

// v2 버전에서는 관람객과 판매원을 자율적인 존재로 만들어서 자율성을 높이는 식으로 이 문제를 해결한다.
public class TheaterTest {

    public static void main(String[] args) {

        long fee = 1000;
        long amount = 10000;

        Ticket ticket1 = new Ticket(fee);
        Ticket ticket2 = new Ticket(fee);

        Bag bag = new Bag(amount);
        bag.setTicket(ticket1);

        Audience audience = new Audience(bag);

        TicketOffice ticketOffice = new TicketOffice(amount, ticket1, ticket2);

        TicketSeller ticketSeller = new TicketSeller(ticketOffice);

        Theater theater = new Theater(ticketSeller);

        System.out.println(audience.getBag().getAmount());
        System.out.println(ticketSeller.getTicketOffice().getAmount() + "\n");
        theater.enter(audience);
        System.out.println(audience.getBag().getAmount());
        System.out.println(ticketSeller.getTicketOffice().getAmount());
    }
}
