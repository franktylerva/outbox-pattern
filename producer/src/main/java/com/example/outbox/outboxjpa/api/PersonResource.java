package com.example.outbox.outboxjpa.api;

import com.example.outbox.outboxjpa.domain.Person;
import com.example.outbox.outboxjpa.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonResource {

    private final PersonService personService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Person> people() {
        return personService.listPeople();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person user) {
        return personService.createPerson(user.toBuilder()
                .build());
    }

}
