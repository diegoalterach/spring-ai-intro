package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Question;

public interface AIService {

    public String getAnswer(String question);

    Answer getAnswer(Question question);
}
