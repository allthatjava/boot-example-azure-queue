package brian.example.storage.queue.bootexampleazurequeue.controller;

import brian.example.storage.queue.bootexampleazurequeue.service.AzureQueueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    private final AzureQueueService service;
    public QueueController(AzureQueueService service){
        this.service = service;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message){
        service.sendMessage(message);
        return "Message send to queue";
    }

    @GetMapping("/receive")
    public String receiveMessage(){
        return service.receiveMessage();
    }
}
