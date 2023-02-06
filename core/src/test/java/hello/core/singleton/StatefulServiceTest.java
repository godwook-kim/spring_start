package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(StatefulService.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA
        statefulService1.order("userA", 10000);

        //ThreadB
        statefulService2.order("userA", 20000);

        //ThreadA : Aㅅㅏ용자 주문 금액 조회

        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);


    }
    static class TestConfig{
        @Bean
        public StatefulService service(){
            return new StatefulService();
        }
    }

}
