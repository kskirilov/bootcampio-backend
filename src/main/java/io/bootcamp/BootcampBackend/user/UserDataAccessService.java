package io.bootcamp.BootcampBackend.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDataAccessService implements UserDAO {
    private JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> selectUserById(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> selectAllUser() {
        return null;
    }

    @Override
    public int deletePerson(int id) {
        return 0;
    }

    @Override
    public int insertUser(User user) {
        String insertUserSql = """
        INSERT INTO users(name, email, password) VALUES(?, ?, ?)
        """;

        int result = jdbcTemplate.update(insertUserSql, user.getName(), user.getEmail(), user.getPassword());

        return result;
    }
}
