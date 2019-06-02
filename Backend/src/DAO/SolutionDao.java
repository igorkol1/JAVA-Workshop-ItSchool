package DAO;

import Domain.Homework;
import Domain.Solution;
import Domain.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao extends BaseDao {

    private static final String CREATE_SOLUTION_QUERY = "insert into solution(created, updated, description, homework_id, user_id) VALUES (?,?,?,?,?);";
    private static final String READ_SOLUTION_QUERY = "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solution SET created = ?, updated=?, description=?, homework_id=?, user_id=? where id = ?";
    private static final String DELETE_SOLUTION_QUERY = "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_FOR_USER_QUERY = "select * from solution where user_id = ?";
    private static final String FIND_SOLUTION_FOR_USER_AND_HOMEWORK_QUERY = "select * from solution where user_id=? and homework_id =?";

    public Solution create(Solution solution) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getCreated().toString());
            statement.setString(2, solution.getUpdated().toString());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getHomework_id());
            statement.setInt(5, solution.getUser_id());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int solutionId) {
        DateTimeFormatter formatter = getDateTimeFormatter();
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(LocalDateTime.parse(resultSet.getString("created"), formatter));
                solution.setUpdated(LocalDateTime.parse(resultSet.getString("updated"), formatter));
                solution.setDescription(resultSet.getString("description"));
                solution.setHomework_id(resultSet.getInt("homework_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, solution.getCreated().toString());
            statement.setString(2, solution.getUpdated().toString());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getHomework_id());
            statement.setInt(5, solution.getUser_id());
            statement.setInt(6, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId) {
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Solution> findAllForUser(User user) {
        try (Connection conn = dbUtils.getConnection()) {
            List<Solution> solutions = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_FOR_USER_QUERY);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(LocalDateTime.parse(resultSet.getString("created"), getDateTimeFormatter()));
                solution.setUpdated(LocalDateTime.parse(resultSet.getString("updated"), getDateTimeFormatter()));
                solution.setDescription(resultSet.getString("description"));
                solution.setHomework_id(resultSet.getInt("homework_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution findSolutionForHomeworkAndUser(User user,Homework homework){
        DateTimeFormatter formatter = getDateTimeFormatter();
        try (Connection conn = dbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_SOLUTION_FOR_USER_AND_HOMEWORK_QUERY);
            statement.setInt(1, user.getId());
            statement.setInt(2,homework.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(LocalDateTime.parse(resultSet.getString("created"), formatter));
                solution.setUpdated(LocalDateTime.parse(resultSet.getString("updated"), formatter));
                solution.setDescription(resultSet.getString("description"));
                solution.setHomework_id(resultSet.getInt("homework_id"));
                solution.setUser_id(resultSet.getInt("user_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
