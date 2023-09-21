package com.naren.busbookingsystem.Utils;

import com.naren.busbookingsystem.Entity.Gender;
import com.naren.busbookingsystem.Entity.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Person(
                rs.getLong("id"),
                rs.getString("user_name"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("age"),
                Gender.valueOf(rs.getString("gender"))
        );
    }
}
