package com.example.outbox.outboxjpa.service;

import com.example.outbox.outboxjpa.domain.Person;
import com.example.outbox.outboxjpa.domain.PersonOutbox;
import com.example.outbox.outboxjpa.repository.PersonOutboxRepository;
import com.example.outbox.outboxjpa.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.EmitterProcessor;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonOutboxRepository personOutboxRepository;
    private final TransactionTemplate transactionTemplate;
    private final StreamBridge streamBridge;

    public Person createPerson(Person person) {

        var savedPerson = transactionTemplate.execute(status -> {
            var p = personRepository.save(person);
            personOutboxRepository.save(PersonOutbox.builder()
                    .personId(p.getId())
                    .firstName(p.getFirstName())
                    .lastName(p.getLastName())
                    .build());
            return p;
        });

        try {
            streamBridge.send("person.create", savedPerson );

            transactionTemplate.executeWithoutResult(transactionStatus -> {
                personOutboxRepository.updateEventSent(Instant.now(), savedPerson.getId());
            });

        }
        catch(Throwable ex) {
            System.out.println(ex.getMessage());
        }

        return savedPerson;
    }

    public Iterable<Person> listPeople() {
        return personRepository.findAll();
    }

}
