package ucy.ece318.assignment3;



import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@RestController
public class MessageController { 
    @Autowired
    private MessageRepository repository;
    private String token = "add your token :) from OpenAI";
    CompletionRequest request;
    String model ="text-davinci-002";
    OpenAiService service;
    CompletionResult response;


    @GetMapping("/MessageList")
    public Iterable<Message> getMessages(){
        return repository.findAll();
    }
      // change return null to something more appropriate
    @GetMapping("/addMessageandResponse")
    public RedirectView addMessageandResponse(@RequestParam final String message) {
      /*
      1. You need to create a message object and save it into the repository
      2. Make an api request to OpenAI with the message’s text and receive the answer.
      3. Create a new message object with the AI response and save it to the  repository.

        Remember, that you need to set the type  of the message as “Question”
        for the message of the user and “Answer”

       */
        Message msg = new Message();
        msg.setMessageText(message);
        msg.setMessageDate(new Date().toString());
        msg.setMessageType("Question");
        repository.save(msg);
        String token = "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";
        service = new OpenAiService(token);
        request = new CompletionRequest();

        request.setModel(model);
        request.setPrompt(msg.getMessageText());

        response = service.createCompletion(request);


        List<CompletionChoice> Requests = response.getChoices();
        for(CompletionChoice c: Requests){
            Message answer = new Message();
            answer.setMessageText(c.getText());
            answer.setMessageType("Answer");
            answer.setMessageDate(new Date().toString());
            repository.save(answer);
        }

        return new RedirectView("");


    }
    @GetMapping("/deleteMessage")
    public RedirectView deleteMessage(@RequestParam Integer id) {

        repository.deleteById(id);

        // add code to delete message

        getMessages();
        return new RedirectView("");

    }
}