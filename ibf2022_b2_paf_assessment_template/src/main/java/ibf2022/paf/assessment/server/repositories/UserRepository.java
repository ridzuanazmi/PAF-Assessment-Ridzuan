package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jTemplate;

    private static final String SELECT_USER_BYUSERNAME_SQL = "select * from user where username = ?";
    private static final String INSERT_USER_SQL = "insert into user values (?, ?, ?)";

    // Method that finds user by username. Returns User
    public Optional<User> findUserByUsername(String username) {

        ResultSetExtractor<Optional<User>> resultSetExtractor = resultSet -> {
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getString("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setName(resultSet.getString("name"));
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        };

        return jTemplate.query(SELECT_USER_BYUSERNAME_SQL,
                resultSetExtractor, username);
    }

    // Method that creates a user. Returns the userId if successful,
    // null if not
    public String insertUser(User user) {

        String userId = user.getUserId();

        int isCreated = jTemplate.update(
                INSERT_USER_SQL, userId, user.getUsername(), user.getName());

        if (isCreated > 0) {
            return userId;
        } else {
            return null;
        }
    }
}
