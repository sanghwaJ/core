package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    // ObjectProvider 사용
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    // 프록시 적용을 위해 기존 코드로 수정
    private final MyLogger myLogger;

    public void logic(String id) {
        // ObjectProvider 사용
        // MyLogger myLogger = myLoggerProvider.getObject();

        myLogger.log("service id = " + id);
    }
}
