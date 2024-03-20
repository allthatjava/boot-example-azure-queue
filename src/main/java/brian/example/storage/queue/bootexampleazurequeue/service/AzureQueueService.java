package brian.example.storage.queue.bootexampleazurequeue.service;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AzureQueueService {

    private final QueueClient queueClient;

    public AzureQueueService(@Value("${azure.storage.connection.string}") String connectionString,
                             @Value("${azure.storage.queue-name}") String queueName){
        this.queueClient = new QueueClientBuilder()
                .connectionString(connectionString)
                .queueName(queueName)
                .buildClient();
    }

    public void sendMessage(String message){
        queueClient.sendMessage(message);
    }

    public String receiveMessage(){
        return queueClient.receiveMessages(1).stream()
                .findFirst()
                .map(message -> {
                    queueClient.deleteMessage(message.getMessageId(), message.getPopReceipt());
                    return message.getMessageText();
                }).orElse(null);
    }
}
