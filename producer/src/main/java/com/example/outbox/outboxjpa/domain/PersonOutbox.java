package com.example.outbox.outboxjpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PEOPLE_OUTBOX")
public class PersonOutbox {

    @Id
    @SequenceGenerator(name="PEOPLE_OUTBOX_SEQ",
            sequenceName="PEOPLE_OUTBOX_SEQ",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="PEOPLE_OUTBOX_SEQ")
    Long id;
    Instant sent;
    UUID personId;
    String firstName;
    String lastName;
}
