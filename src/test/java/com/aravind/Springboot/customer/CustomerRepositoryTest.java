package com.aravind.Springboot.customer;

import com.aravind.Springboot.Abstracttestcontainers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

import java.util.Optional;
import java.util.UUID;
import static com.aravind.Springboot.Abstracttestcontainers.faker;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest extends Abstracttestcontainers {

@Autowired
private CustomerRepository underTest;
@Autowired
private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        System.out.println(applicationContext.getBeanDefinitionCount());
    }

    @Test
    void existsCustomerByEmail() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.save(customer);

        //WHEN
        var actual = underTest.existsCustomerByEmail(email);
        //THEN
        assertThat(actual).isTrue();
    };


    @Test
    void existsCustomerByEmailFailsWhenEmailNotPresent() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();




        //WHEN
        var actual = underTest.existsCustomerByEmail(email);
        //THEN
        assertThat(actual).isFalse();
    };


    @Test
    void existsCustomerById() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.save(customer);
        Integer id = underTest.findAll().stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId).findFirst()
                .orElseThrow();
        //WHEN
        var actual = underTest.existsCustomerById(id);
        //THEN
        assertThat(actual).isTrue();
    }

    @Test
    void existsCustomerByIdWhenIdNotPresent() {
        //GIVEN
   int id = -1;

        //WHEN
        var actual = underTest.existsCustomerById(id);
        //THEN
        assertThat(actual).isFalse();
    }

    }
