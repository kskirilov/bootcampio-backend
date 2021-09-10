package io.bootcamp.BootcampBackend.user;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository("postgres")
public class UserDataAccessService implements UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> selectUserById(int id) {
        String sql = """
                SELECT * FROM users WHERE id = ?
                """;

        List<User> users = jdbcTemplate.query(sql, getUserRowMapper(), id);
        if (users.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(users.get(0));
    }

    private RowMapper<User> getUserRowMapper() {
        return (resultSet, i) -> {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"));
        };
    }

    @Override
    public List<User> selectAllUser() {
        String selectUserSql = """
                SELECT * FROM users
                """;

        List<User> users = jdbcTemplate.query(selectUserSql, getUserRowMapper());
        System.out.println(users);
        return users;
    }

    @Override
    public int deleteUser(int id) {
        String deleteUserSql = """
        DELETE FROM users WHERE id = ?
        """;

        int result = jdbcTemplate.update(deleteUserSql, id);

        return result;
    }

    @Override
    public int insertUser(User user) {
        String insertUserSql = """
        INSERT INTO users(name, email, password) VALUES(?, ?, ?)
        """;

        int result = jdbcTemplate.update(insertUserSql, user.getName(), user.getEmail(), user.getPassword());

        return result;
    }

    @Override
    public int updateUser(User user) {
        String insertUserSql = """
        UPDATE users SET name = ?, email = ?, password = ? WHERE id = ? 
        """;

        int result = jdbcTemplate.update(insertUserSql, user.getName(), user.getEmail(), user.getPassword(), user.getId());

        return result;
    }


}
