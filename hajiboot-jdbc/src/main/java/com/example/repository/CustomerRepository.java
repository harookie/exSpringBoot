package com.example.repository;

import com.example.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.util.List;

/**
 * <ul>
 * <li>업무 그룹명 : tango-inventory-dr-controller-daemon</li>
 * <li>서브 업무명 : example</li>
 * <li>설  명 : CustomerRepository</li>
 * <li>작성일 : 2017. 11. 17.</li>
 * <li>작성자 : 하종현(Mobigen co.)</li>
 * </ul>
 */
@Repository
@Transactional
public class CustomerRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    SimpleJdbcInsert insert;

    @PostConstruct
    public void init() {
        insert = new SimpleJdbcInsert(
                (JdbcTemplate) jdbcTemplate.getJdbcOperations())
                .withTableName("customers")
                .usingGeneratedKeyColumns("id");
    }

    private static final RowMapper<Customer> customerRowMapper = (ResultSet rs, int i) -> {
        Integer id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        return new Customer(id, firstName, lastName);
    };

    public List<Customer> findAll() {
        List<Customer> customers = jdbcTemplate.query(
                "SELECT ID, FIRST_NAME, LAST_NAME FROM CUSTOMERS ORDER BY ID",
                customerRowMapper);

        return customers;
    }

    public Customer findOne(Integer customerId) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
        return jdbcTemplate.queryForObject(
                "SELECT ID, FIRST_NAME, LAST_NAME FROM CUSTOMERS WHERE ID = :id"
                , param
                , customerRowMapper);
    }

    public Customer save(Customer customer) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
        if (customer.getId() == null) {
            Number key = insert.executeAndReturnKey(param);
            customer.setId(key.intValue());
        } else {
            jdbcTemplate.update("UPDATE CUSTOMERS SET FIRST_NAME = :firstName, LAST_NAME = :lastName WHERE ID = :id", param);
        }

        return customer;
    }

    public void delete(Integer customerId) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
        jdbcTemplate.update("DELETE FROM CUSTOMERS WHERE ID = :id"
                , param);
    }
}