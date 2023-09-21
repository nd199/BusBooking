package com.naren.busbookingsystem;

import com.github.javafaker.Faker;
import com.naren.busbookingsystem.Entity.Gender;
import com.naren.busbookingsystem.Entity.Person;
import com.naren.busbookingsystem.Repository.BusRepository;
import com.naren.busbookingsystem.Repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusBookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusBookingSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                        BusRepository busRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                Faker faker = new Faker();
                String username = faker.name().username();
                String name = faker.name().name();
                Gender gender = Gender.getRandomGender();
                //encoder.encode
                var password = (faker.internet().password(8
                        , 16
                        , true
                        , true
                        , true));
                String email = faker.internet().safeEmailAddress();
                Person person = new Person(
                        username,
                        name,
                        email,
                        password,
                        faker.random().nextInt(18, 40),
                        gender
                );
                System.out.println("HI Naren" + person.getAuthorities());
                customerRepository.save(person);
            }
        };
    }
}
