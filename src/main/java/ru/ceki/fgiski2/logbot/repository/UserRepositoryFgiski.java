package ru.ceki.fgiski2.logbot.repository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.ceki.fgiski2.logbot.model.User;

@Repository
public class UserRepositoryFgiski implements UserRepository {
	private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepositoryFgiski(
                  @Qualifier("commonDataSource") DataSource dataSource) {
    	this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    	JdbcOperations operations = jdbcTemplate.getJdbcOperations();
    	if (operations instanceof JdbcTemplate) {
    		((JdbcTemplate)operations).setFetchSize(1000);
    	}
	}

    @Override
    public Optional<User> findById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return this.jdbcTemplate.query(
          "SELECT users.id, users.first_name, "
               + "users.last_name, users.middle_name "
        + "FROM users WHERE id=:id", params,
           new BeanPropertyRowMapper<>(User.class)).stream().findFirst();
    }
}