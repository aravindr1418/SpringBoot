package com.aravind.Springboot.customer;

import com.aravind.Springboot.Abstracttestcontainers;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertWith;
import static org.junit.jupiter.api.Assertions.*;

class CustomerJDBCDataAccessServiceTest extends Abstracttestcontainers {
      private CustomerJDBCDataAccessService underTest;
      private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();
    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDataAccessService(
               getJdbcTemplate(),
                customerRowMapper
        );
    }

    @Test
    void selectAllCusotmers() {
        //GIVEN
         Customer customer = new Customer(
                 faker.name().fullName(),
                 faker.internet().safeEmailAddress() +"_"+ UUID.randomUUID(),
                 20
         );
         underTest.insertcustomer(customer);
        //WHEN
        List<Customer> actual = underTest.selectAllCusotmers();
        //THEN
        assertThat(actual).isNotEmpty();
    }

    @Test
    void selectCusotmerById() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.insertcustomer(customer);
        Integer id = underTest.selectAllCusotmers().stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId).findFirst()
                .orElseThrow();
        //WHEN
        Optional<Customer> actual = underTest.selectCusotmerById(id);
        //THEN
        assertThat(actual).isPresent().hasValueSatisfying(c->{
             assertThat(c.getId()).isEqualTo(id);
             assertThat(c.getName()).isEqualTo(customer.getName());
             assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void willReturnEmptyWhenSelectCustomerById() {
        //GIVEN
           int id = -1;

        //WHEN
            var actual = underTest.selectCusotmerById(id);
        //THEN
        assertThat(actual).isEmpty();
    }

    @Test
    void insertcustomer() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void existsPersonWithEmail() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        String name = faker.name().fullName();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.insertcustomer(customer);
        //WHEN
          boolean actual = underTest.existsPersonWithEmail(email);
        //THEN
        assertThat(actual).isTrue();
    }

    @Test
    void existsPersonWithEmailReturnFalseWhenDoesNotExists() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        //WHEN
        boolean actual = underTest.existsPersonWithEmail(email);
        //THEN
        assertThat(actual).isFalse();
    }

    @Test
    void existsCustomerWithId() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        String name = faker.name().fullName();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.insertcustomer(customer);

        int id = underTest.selectAllCusotmers().stream()
                .filter(c->c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst().orElseThrow();
        //WHEN
        var actual = underTest.existsPersonWithId(id);
        //THEN
        assertThat(actual).isTrue();
    }

    @Test
    void existsPersonWithIdWillReturnFalseWhenIdNotPresent() {
        //GIVEN
         int id = -1;
        //WHEN
         var actual = underTest.existsPersonWithId(id);
        //THEN
        assertThat(actual).isFalse();
    }

    @Test
    void deleteCustomerById() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.insertcustomer(customer);
        int id = underTest.selectAllCusotmers().stream()
                .filter(c->c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst().orElseThrow();
        //WHEN
         underTest.deleteCustomerById(id);
        //THEN
        Optional<Customer> actual = underTest.selectCusotmerById(id);
        assertThat(actual).isNotPresent();
    }

    @Test
    void updateCustomer() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.insertcustomer(customer);
        int id = underTest.selectAllCusotmers().stream()
                .filter(c->c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst().orElseThrow();

        var newName = "foo";
        //When age is name
        Customer update = new Customer();
        update.setId(id);
        update.setName(newName);
        underTest.updateCustomer(update);

        //THEN
        Optional<Customer> actual = underTest.selectCusotmerById(id);
        assertThat(actual).isPresent().hasValueSatisfying(c->{
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(newName);
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void updateCustomerEmail() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.insertcustomer(customer);
        int id = underTest.selectAllCusotmers().stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst().orElseThrow();

        var newEmail = faker.internet().safeEmailAddress() + " " + UUID.randomUUID();
        //WHEN Email is changed

        Customer update = new Customer();
        update.setId(id);
        update.setEmail(newEmail);

        underTest.updateCustomer(update);

        //THEN
        Optional<Customer> actual = underTest.selectCusotmerById(id);
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getEmail()).isEqualTo(newEmail);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void updateCustomerAge() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.insertcustomer(customer);
        int id = underTest.selectAllCusotmers().stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst().orElseThrow();

        var newAge = 100;

        //WHEN age is changed
        Customer update = new Customer();
        update.setId(id);
        update.setAge(newAge);
        underTest.updateCustomer(update);

        //THEN
        Optional<Customer> actual = underTest.selectCusotmerById(id);
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getAge()).isEqualTo(newAge);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
        });
    }

    @Test
    void willUpdateAllPropertiesCustomer() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.insertcustomer(customer);
        int id = underTest.selectAllCusotmers().stream()
                .filter(c->c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst().orElseThrow();


        //WHEN update new name , age ,email

        Customer update = new Customer();
        update.setId(id);
        update.setName("foo");
        update.setEmail(UUID.randomUUID().toString());
        update.setAge(22);

        underTest.updateCustomer(update);

        //THEN
        Optional<Customer> actual = underTest.selectCusotmerById(id);
        assertThat(actual).isPresent().hasValue(update);
    }

    @Test
    void willNotUpdateWhenNothingToUpdate() {
        //GIVEN
        String email = faker.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        Customer customer = new Customer(
                faker.name().fullName(),
                email,
                20
        );
        underTest.insertcustomer(customer);
        int id = underTest.selectAllCusotmers().stream()
                .filter(c->c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst().orElseThrow();


        //WHEN update without no changes
        Customer update = new Customer();
        update.setId(id);
        underTest.updateCustomer(update);
        //THEN
        Optional<Customer> actual = underTest.selectCusotmerById(id);
        assertThat(actual).isPresent().hasValueSatisfying(c->{
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });

    }
}