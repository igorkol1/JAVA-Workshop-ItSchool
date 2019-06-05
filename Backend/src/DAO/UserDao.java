package DAO;

import Domain.User;
import Domain.UserGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? where id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String FIND_ALL_USERS_WITH_EMPTY_GROUP_QUERY = "SELECT * FROM users WHERE group_id IS NULL";
    private static final String FIND_ALL_USERS_IN_GROUP_QUERY = "SELECT * FROM users WHERE group_id=?";
    private static final String ASSIGN_USER_GROUP_QUERY = "UPDATE users SET group_id=? WHERE id=?";
    private static final String RESIGN_USER_GROUP_QUERY = "UPDATE users SET group_id=NULL WHERE id=?";
    private static final String FIND_USER_BY_EMAIL_QUERY = "select * from users where email=?";

    public User create(User user) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<User> findAll(String query) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return mapResultSetToUserList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findAll() {
        return findAll(FIND_ALL_USERS_QUERY);
    }

    public List<User> findAllWithEmptyGroup() {
        return findAll(FIND_ALL_USERS_WITH_EMPTY_GROUP_QUERY);
    }

    public List<User> findAllInGroup(UserGroup userGroup) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_IN_GROUP_QUERY);
            statement.setInt(1, userGroup.getId());
            ResultSet resultSet = statement.executeQuery();
            return mapResultSetToUserList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void asignGroup(User user, UserGroup userGroup) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(ASSIGN_USER_GROUP_QUERY);
            statement.setInt(1, userGroup.getId());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resignGroup(User user) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(RESIGN_USER_GROUP_QUERY);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User findByEmail(String email) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_USER_BY_EMAIL_QUERY);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUserName(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    private List<User> mapResultSetToUserList(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            userList.add(mapResultSetToUser(resultSet));
        }
        return userList;
    }
}
