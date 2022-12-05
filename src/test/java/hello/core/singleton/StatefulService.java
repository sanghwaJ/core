package hello.core.singleton;

public class StatefulService {
    /* 무상태로 설계하지 않은 예시
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기서 문제 발생
    }

    public int getPrice() {
        return price;
    }
    */

    /* 무상태로 설계 */
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
