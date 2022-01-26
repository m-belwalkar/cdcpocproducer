package com.azurepoc.cdc.eventhub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class EventProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);

    private static EventProducer instance = null;
    @Autowired
    private Sinks.Many<Message<String>> many;

    private EventProducer(){}

    public static EventProducer getInstance(){
        if(instance == null){
            instance = new EventProducer();
        }
        return instance;
    }

    public ResponseEntity<String> sendMessage(String message) {
        LOGGER.info("Producer: Going to add message {} to sendMessage.", message);
        many.emitNext(MessageBuilder.withPayload(message).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return ResponseEntity.ok(message);
    }
}
