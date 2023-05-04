package com.example.outbox.outboxjpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PEOPLE")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String firstName;
    String lastName;

}
