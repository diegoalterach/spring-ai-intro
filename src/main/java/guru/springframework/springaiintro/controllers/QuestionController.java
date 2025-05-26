package guru.springframework.springaiintro.controllers;


import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.Question;
import guru.springframework.springaiintro.services.AIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {


    private final AIService aiService;

    public QuestionController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest getCapitalRequest){
        return aiService.getCapital(getCapitalRequest);
    }

    @PostMapping("/capital-with-info")
    public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest){
        return aiService.getCapitalWithInfo(getCapitalRequest);
    }


    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question){
        return aiService.getAnswer(question);
    }

}
