package hello.core.lifecycle;

// import org.springframework.beans.factory.DisposableBean;
// import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/* 방법 1 : 인터페이스 (InitializingBean, DisposableBean) */
// public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient{
    public String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    /* 방법 1 : 인터페이스 (InitializingBean, DisposableBean)
    // 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    // 소멸전 콜백 : 빈이 소멸되기 직전에 호출
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
    */

    /* 방법 2 : 빈 등록 초기화, 소멸 메서드 지정
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
    */

    /* 방법 3 : @PostConstruct, @PreDestroy */
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
