package com.aravind.Springboot.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository("jdbc")
public class CustomerJDBCDataAccessService implements CustomerDao{
    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;
    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate,
                                         CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> selectAllCusotmers() {
        var sql = """
                SELECT id,name,email,age,gender
                FROM customer1
                """;
        RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
            Customer customer = new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age"),
                    Gender.MALE);
            return customer;
        };
        return jdbcTemplate.query(sql, customerRowMapper);

    }

    @Override
    public Optional<Customer> selectCusotmerById(Integer id) {
        var sql = """
                SELECT id,name,email,age,gender
                FROM customer1 WHERE id = ?
                """;
        return jdbcTemplate.query(sql, customerRowMapper,id)
                .stream().findFirst();
    }

    @Override
    public void insertcustomer(Customer customer) {
            var sql = """
                    INSERT INTO customer1(name,email,age,gender) 
                    VALUES (?,?,?,?)
                    """;
            int result = jdbcTemplate.update(
                    sql,
                    customer.getName(),
                    customer.getEmail(),
                    customer.getAge(),
                    customer.getGender().name()
                    );
        System.out.println("jdbcTemplate.update = "+result);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        var sql = """
                SELECT COUNT(id)
                FROM customer1
                WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,email);
        return count != null && count>0;
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        var sql = """
                SELECT count(id)
                FROM customer1
                WHERE id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,id);
        return count != null && count > 0;
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        var sql = """
                  DELETE FROM customer1
                  WHERE id = ?
                """;
        int result = jdbcTemplate.update(sql,customerId);
        System.out.println("deleteCustomerById result = "+result);

    }

    @Override
    public void updateCustomer(Customer update) {
      if(update.getName() != null){
          String sql = "UPDATE customer1 SET name = ? WHERE id = ? ";
          int result = jdbcTemplate.update(
                  sql,
                  update.getName(),
                  update.getId()
          );
          System.out.println("update customer1 name result ="+result);
      }
      if(update.getAge()!= null){
          String sql = "UPDATE customer1 SET age = ? WHERE id = ?";
          int result = jdbcTemplate.update(
                  sql,
                  update.getAge(),
                  update.getId()
          );
          System.out.println("update customer1 age result = "+result);
      }
      if(update.getEmail()!= null){
          String sql = "UPDATE customer1 SET email = ? WHERE id =?";
          int result = jdbcTemplate.update(
                  sql,
                  update.getEmail(),
                  update.getId()
          );
          System.out.println("update customer1 email result = "+result);
      }
    }
}
