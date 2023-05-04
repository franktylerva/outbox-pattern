package com.example.outbox.outboxjpa.repository;

import com.example.outbox.outboxjpa.domain.PersonOutbox;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.UUID;

public interface PersonOutboxRepository extends CrudRepository<PersonOutbox, Long> {

    @Modifying
    @Query("update PersonOutbox po set po.sent = :sent where po.personId = :personId")
    int updateEventSent(@Param("sent")Instant sent, @Param("personId") UUID personId);

}
