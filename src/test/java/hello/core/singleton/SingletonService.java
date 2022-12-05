package hello.core.singleton;

public class SingletonService {
    // static으로 선언하여, 클래스 level에 올라기기 때문에 딱 하나만 생성되게 됨
    private static final SingletonService instance = new SingletonService();

    // 새로 인스턴스를 생성하지 못하며, 인스턴스 접근은 오직 아래의 메소드를 통해서만 가능
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
        
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
    
}
