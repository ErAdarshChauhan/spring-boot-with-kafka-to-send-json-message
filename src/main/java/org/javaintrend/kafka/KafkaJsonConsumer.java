package org.javaintrend.kafka;

import org.javaintrend.payload.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonConsumer.class);

    Employee employeeConsumed = new Employee();

    @KafkaListener(topics="javatrends",groupId = "group_id")
    public void consume(Employee employee){
        LOGGER.info(String.format("Json Message received %s",employee.toString()));
        employeeConsumed.setId(employee.getId());
        employeeConsumed.setFirstname(employee.getFirstname());
        employeeConsumed.setLastname(employee.getLastname());
    }

    public Employee consumedEmployee(){
        return employeeConsumed;
    }
}
