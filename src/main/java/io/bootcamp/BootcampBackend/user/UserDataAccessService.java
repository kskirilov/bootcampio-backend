package io.bootcamp.BootcampBackend.user;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
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

    @Override
    public Optional<User> selectUserByEmail(String email) {
        String sql = """
                SELECT * FROM users WHERE email = ?
                """;

        List<User> users = jdbcTemplate.query(sql, getUserRowMapper(), email);
        if (users.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(users.get(0));
    }


    @Override
    public List<User> selectAllUser() {
        String sql = """
                SELECT * FROM users
                """;

        List<User> users = jdbcTemplate.query(sql, getUserRowMapper());

        return users;
    }

    @Override
    public int deleteUser(int id) {
        String sql = """
        DELETE FROM users WHERE id = ?
        """;

        int result = jdbcTemplate.update(sql, id);

        return result;
    }

    @Override
    public int insertUser(User user) {
        String sql = """
        INSERT INTO users(name, email, password) VALUES(?, ?, ?)
        """;

        int result = jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());

        return result;
    }

    @Override
    public int updateUser(User user) {
        String sql = """
        UPDATE users SET name = ?, email = ?, password = ? WHERE id = ? 
        """;

        int result = jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getId());

        return result;
    }

    @Override
    public int loginUser(int id) {
        String sql = """
                INSERT INTO sessions(user_id) VALUES(?)
                """;

        int result = jdbcTemplate.update(sql, id);
        return result;
    }

    @Override
    public int logoutUser(int id) {
        String sql = """
                DELETE FROM sessions WHERE user_id = ?
                """;

        int result = jdbcTemplate.update(sql, id);

        return result;
    }

    @Override
    public List<User> selectAllOnlineUser() {
        String sql = """
                SELECT users.id, users.name, users.email FROM users
                INNER JOIN sessions
                ON sessions.user_id = users.id
                """;

        List<User> onlineUsers = jdbcTemplate.query(sql, getOnlineUserRowMapper());
        return onlineUsers;
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


    private RowMapper<User> getOnlineUserRowMapper() {
        return (resultSet, i) -> {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"));
        };
    }

}
