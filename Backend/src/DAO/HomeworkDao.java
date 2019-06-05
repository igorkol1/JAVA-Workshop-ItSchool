package DAO;

import Domain.Homework;
import Domain.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HomeworkDao extends BaseDao {

    private static final String CREATE_HOMEWORK_QUERY = "insert into homework(group_id, exercise_id, is_active, created) VALUES (?,?,?,?)";
    private static final String READ_HOMEWORK_QUERY = "SELECT * FROM homework where id = ?";
    private static final String UPDATE_HOMEWORK_QUERY = "UPDATE homework SET group_id = ?, exercise_id = ?, is_active = ?, created = ? where id = ?";
    private static final String DELETE_HOMEWORK_QUERY = "DELETE FROM homework WHERE id = ?";
    private static final String FIND_ALL_HOMEWORKS_QUERY = "SELECT * FROM homework";
    private static final String FIND_ALL_HOMEWORK_FOR_USER_QUERY = "select * from homework join user_group ug on homework.group_id = ug.id where ug.id = (select group_id from users where id = ?)";
    private static final String FIND_UNSOLVED_HOMEWORK_FOR_USER_QUERY = "select h.id,h.group_id,h.exercise_id,h.is_active,h.created from homework h left join solution s on h.id=s.homework_id where s.user_id!=? and h.group_id=(select group_id from users where id = ?) and h.is_active=true";

    public Homework create(Homework homework) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_HOMEWORK_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, homework.getGroupId());
            statement.setInt(2, homework.getExerciseId());
            statement.setBoolean(3, homework.isActive());
            statement.setString(4, homework.getCreated().toString());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                homework.setId(resultSet.getInt(1));
            }
            return homework;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Homework read(int homeworkId) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_HOMEWORK_QUERY);
            statement.setInt(1, homeworkId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToHomework(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Homework homework) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_HOMEWORK_QUERY);
            statement.setInt(1, homework.getGroupId());
            statement.setInt(2, homework.getExerciseId());
            statement.setBoolean(3, homework.isActive());
            statement.setString(4, homework.getCreated().toString());
            statement.setInt(5, homework.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int homeworkId) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_HOMEWORK_QUERY);
            statement.setInt(1, homeworkId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Homework> findAll() {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_HOMEWORKS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            return mapResultSetToHomeworkList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Homework> findAllForUser(User user) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_HOMEWORK_FOR_USER_QUERY);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            return mapResultSetToHomeworkList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Homework> findAllUnsolvedForUser(User user) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_UNSOLVED_HOMEWORK_FOR_USER_QUERY);
            statement.setInt(1, user.getId());
            statement.setInt(2, user.getId());
            ResultSet resultSet = statement.executeQuery();
            return mapResultSetToHomeworkList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Homework mapResultSetToHomework(ResultSet resultSet) throws SQLException {
        Homework homework = new Homework();
        homework.setId(resultSet.getInt("id"));
        homework.setGroupId(resultSet.getInt("group_id"));
        homework.setExerciseId(resultSet.getInt("exercise_id"));
        homework.setActive(resultSet.getBoolean("is_active"));
        homework.setCreated(LocalDateTime.parse(resultSet.getString("created"), getDateTimeFormatter()));
        return homework;
    }

    private List<Homework> mapResultSetToHomeworkList(ResultSet resultSet) throws SQLException {
        List<Homework> homeworkList = new ArrayList<>();
        while (resultSet.next()) {
            homeworkList.add(mapResultSetToHomework(resultSet));
        }
        return homeworkList;
    }
}
