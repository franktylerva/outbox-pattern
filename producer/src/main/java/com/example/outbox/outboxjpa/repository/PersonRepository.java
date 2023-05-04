package com.example.outbox.outboxjpa.repository;

import com.example.outbox.outboxjpa.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {
}
