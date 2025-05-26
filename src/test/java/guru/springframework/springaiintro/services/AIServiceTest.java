package guru.springframework.springaiintro.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class AIServiceTest {

   // @Autowired
    AIService service;

  //  @Test
    void getAnswer(){
        String answer = service.getAnswer("tell me a dad joke");
        System.out.println(answer);

    }
}
