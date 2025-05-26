package guru.springframework.springaiintro.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.GetCapitalResponse;
import guru.springframework.springaiintro.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class AIServiceImpl implements AIService {


    private final ChatModel chatModel;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;
    @Value("classpath:templates/get-capital-prompt-with-info.st")
    private Resource getCapitalPromptWithInfo;

    @Autowired
    ObjectMapper objectMapper;

    public AIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }


    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getContent());
    }

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();
        System.out.println(format);


        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);

        Prompt prompt = promptTemplate.create(Map.of(
                "stateOrCountry",getCapitalRequest.stateOrCountry(),
                "format",format));

        ChatResponse response = chatModel.call(prompt);

        System.out.println(response.getResult().getOutput().getContent());

        return converter.convert(response.getResult().getOutput().getContent());
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPromptWithInfo);

        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry",getCapitalRequest.stateOrCountry()));

        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getContent());
    }
}
