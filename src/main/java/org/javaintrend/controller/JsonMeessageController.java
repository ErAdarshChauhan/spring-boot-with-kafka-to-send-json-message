package org.javaintrend.controller;

import org.javaintrend.kafka.KafkaJsonConsumer;
import org.javaintrend.kafka.KafkaJsonProducer;
import org.javaintrend.payload.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMeessageController {

    private KafkaJsonProducer kafkaJsonProducer;
    private KafkaJsonConsumer kafkaJsonConsumer;

    public JsonMeessageController(KafkaJsonProducer kafkaJsonProducer, KafkaJsonConsumer kafkaJsonConsumer) {
        this.kafkaJsonProducer = kafkaJsonProducer;
        this.kafkaJsonConsumer = kafkaJsonConsumer;
    }

    @PostMapping("/producer")
    public ResponseEntity<String> jsonProducer(@RequestBody Employee employee){
        kafkaJsonProducer.sendJsonMessage(employee);
        return ResponseEntity.ok("Json Message sent to Kafka Topic");
    }

    @GetMapping("/consumer")
    public ResponseEntity<Employee> jsonConsumer(){
        kafkaJsonConsumer.consumedEmployee();
        return ResponseEntity.ok(kafkaJsonConsumer.consumedEmployee());
    }
}
