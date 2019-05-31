package DAO;

import Domain.UserGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDao extends BaseDao {

    private static final String CREATE_USER_GROUP_QUERY = "insert into user_group(name) VALUES (?);";
    private static final String READ_USER_GROUP_QUERY = "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_USER_GROUP_QUERY = "UPDATE user_group SET name = ? where id = ?";
    private static final String DELETE_USER_GROUP_QUERY = "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_USER_GROUP_QUERY = "SELECT * FROM user_group";

    public UserGroup create(UserGroup userGroup) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userGroup.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userGroup.setId(resultSet.getInt(1));
            }
            return userGroup;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserGroup read(int userGroupId) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_GROUP_QUERY);
            statement.setInt(1, userGroupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getInt("id"));
                userGroup.setName(resultSet.getString("name"));
                return userGroup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(UserGroup userGroup) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_GROUP_QUERY);
            statement.setString(1, userGroup.getName());
            statement.setInt(2, userGroup.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userGroupId) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_GROUP_QUERY);
            statement.setInt(1, userGroupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserGroup> findAll() {
        try (Connection conn = dbUtils.getConnection()) {
            List<UserGroup> userGroups = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USER_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getInt("id"));
                userGroup.setName(resultSet.getString("name"));
                userGroups.add(userGroup);
            }
            return userGroups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
