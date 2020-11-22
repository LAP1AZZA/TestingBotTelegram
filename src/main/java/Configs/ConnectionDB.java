package Configs;

import constants.DateBaseConstants;
import constants.QuestionConstants;
//import Entity.Answer;
import entity.Question;
import entity.Test;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class ConnectionDB extends ConfigDateBase {
    Connection dbConnection;
    public Connection getDbConnection()
            throws ClassCastException, SQLException {
        String ConnectionString = "jdbc:h2:" + PathDB + "/" + NameDB;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbConnection = DriverManager.getConnection(ConnectionString, UserDB, PassDB);
        return dbConnection;
    }
    public ResultSet getUser(User user) {
        ResultSet rs = null;
        String select = "SELECT * FROM " + DateBaseConstants.USERS_TABLE + " WHERE " +
                DateBaseConstants.USERS_LOGIN + " = ? AND " + DateBaseConstants.USERS_PASSWORD +
                " = ? AND " + DateBaseConstants.USERS_ADMIN_STATUS + "= ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setBoolean(3, user.getAdmin_status());
            rs = prSt.executeQuery();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    public void registerUser(User user) throws SQLException {
        ResultSet rs = getDbConnection().createStatement().executeQuery(
                "SELECT COUNT(*) AS Qty FROM " + DateBaseConstants.USERS_TABLE);
        rs.next();
        int CounterID = rs.getInt(1) + 1;
        String insert = "INSERT INTO " + DateBaseConstants.USERS_TABLE + "(" +
                DateBaseConstants.USERS_ID + "," + DateBaseConstants.USERS_LOGIN + "," +
                DateBaseConstants.USERS_NAME + "," + DateBaseConstants.USERS_PASSWORD + "," +
                DateBaseConstants.USERS_ADMIN_STATUS + ")" +
                "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, CounterID);
            prSt.setString(2, user.getLogin());
            prSt.setString(3, user.getName());
            prSt.setString(4, user.getPassword());
            prSt.setBoolean(5, user.getAdmin_status());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addTest(Test test) throws SQLException {
        ResultSet rs = getDbConnection().createStatement().executeQuery(
                "SELECT COUNT(*) AS Qty FROM " + DateBaseConstants.TESTS_TABLE);
        rs.next();
        int CounterID = rs.getInt(1) + 1;
        String insert = "INSERT INTO " + DateBaseConstants.TESTS_TABLE + "(" +
                DateBaseConstants.TESTS_ID + "," + DateBaseConstants.TESTS_NAME + "," +
                DateBaseConstants.TESTS_QUESTIONS_LIST + ")" +
                "VALUES(?, ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, CounterID);
            prSt.setString(2, test.getName());
            prSt.setString(3, test.getQuestions_list());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String getTest(Test test) {
        ResultSet rs = null;
        String result = null;
        String select = "SELECT * FROM " + DateBaseConstants.TESTS_TABLE + " WHERE " +
                DateBaseConstants.TESTS_NAME + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, test.getName());
            rs = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs.next();
            result = rs.getString(3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public void eraseTest(Test test) {
        String searchSequence = getTest(test);
        String erase = "DELETE FROM " + DateBaseConstants.TESTS_TABLE + " WHERE " +
                DateBaseConstants.TESTS_QUESTIONS_LIST + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(erase);
            prSt.setString(1, searchSequence);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addQuestion(Question question) throws SQLException {
        ResultSet rs = getDbConnection().createStatement().executeQuery(
                "SELECT COUNT(*) AS Qty FROM " + DateBaseConstants.QUESTIONS_TABLE);
        rs.next();
        int CounterID = rs.getInt(1) + 1;
        String insert = "INSERT INTO " + DateBaseConstants.QUESTIONS_TABLE + "(" +
                DateBaseConstants.QUESTIONS_ID + "," + DateBaseConstants.QUESTIONS_AUTHOR +
                "," + DateBaseConstants.QUESTIONS_DIFFICULTY + "," + DateBaseConstants.QUESTIONS_TYPE +
                "," + DateBaseConstants.QUESTIONS_ANSWER + "," + DateBaseConstants.QUESTIONS_TEXT + ")" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, CounterID);
            prSt.setString(2, question.getAuthorLogin());
            prSt.setString(3, question.getDifficulty());
            prSt.setString(4, question.getType());
            prSt.setString(5, question.getAnswer());
            prSt.setString(6, question.getQuestionText());
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String getQuestion(String questionId) {
        ResultSet rs = null;
        String result = null;
        String select = "SELECT * FROM " + DateBaseConstants.QUESTIONS_TABLE + " WHERE " +
                DateBaseConstants.QUESTIONS_ID + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, questionId);
            rs = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs.next();
            result = QuestionConstants.QUESTION_DIFFICULTY + rs.getString(2) +
                    QuestionConstants.QUESTION_TYPE + rs.getString(3) +
                    QuestionConstants.QUESTION_AUTHOR + rs.getString(4) +
                    QuestionConstants.QUESTION_TEXT + rs.getString(5) +
                    QuestionConstants.QUESTION_TEXT_ANSWER + rs.getString(6);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public void eraseQuestion(String questionId) {
        String erase = "DELETE FROM " + DateBaseConstants.QUESTIONS_TABLE + " WHERE " +
                DateBaseConstants.QUESTIONS_ID + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(erase);
            prSt.setString(1, questionId);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
