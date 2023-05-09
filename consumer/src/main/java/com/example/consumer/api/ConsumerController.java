package com.example.consumer.api;

import com.example.consumer.domain.Person;
import com.example.consumer.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class ConsumerController {

    private final PersonRepository personRepository;

    @GetMapping
    public List<Person> people() {
        return personRepository.findAll();
    }


}
