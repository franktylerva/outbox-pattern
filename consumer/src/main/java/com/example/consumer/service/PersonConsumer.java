package com.example.consumer.service;

import com.example.consumer.domain.Person;
import com.example.consumer.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class PersonConsumer implements Consumer<Person> {

    private final PersonRepository personRepository;
    @Override
    public void accept(Person person) {
        personRepository.save(person);
    }
}
