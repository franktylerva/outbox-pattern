package com.example.consumer;

import com.example.consumer.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public Consumer<Person> personConsumer() {
		return person -> System.out.println(person.getFirstName());
	}

}
