package service;

import constants.QuestionConstants;
import Configs.ConnectionDB;
import constants.RegistrationConstants;
import constants.TestConstants;
import entity.Question;
import entity.Test;

import java.sql.SQLException;

public class AdminService {
    private static int progressCounter = 0;
    private static InputService input = new InputService();
    private static Test test = new Test();
    private static Question question = new Question();

    public String createQuestion(String adminMessage) {

        if (progressCounter == 0) {
            progressCounter++;
            return QuestionConstants.QUESTION_CREATE_AUTHOR_MESSAGE_BOT;
        } else if (progressCounter == 1) {
            progressCounter++;
            question.setAuthorLogin(adminMessage);
            return QuestionConstants.QUESTION_CREATE_TYPE_MESSAGE_BOT;
        } else if (progressCounter == 2) {
            progressCounter++;
            question.setType(adminMessage);
            return QuestionConstants.QUESTION_CREATE_DIFFICULTY_MESSAGE_BOT;
        } else if (progressCounter == 3) {
            progressCounter++;
            question.setDifficulty(adminMessage);
            return QuestionConstants.QUESTION_CREATE_TEXT_MESSAGE_BOT;
        } else if (progressCounter == 4) {
            progressCounter++;
            question.setQuestionText(adminMessage);
            return QuestionConstants.QUESTION_CREATE_ANSWER_MESSAGE_BOT;
        } else if (progressCounter == 5) {
            progressCounter++;
            question.setAnswer(adminMessage);
            return QuestionConstants.QUESTION_CREATE_FINISH_MESSAGE_BOT;
        } else {
            ConnectionDB connectionDB = new ConnectionDB();
            try {
                connectionDB.addQuestion(question);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            question = new Question();
            progressCounter = 0;
            input.modesOff();
            return RegistrationConstants.START_MESSAGE_BOT;
        }
    }
    public String openQuestion(String adminMessage) {
        if (progressCounter == 0) {
            progressCounter++;
            return QuestionConstants.QUESTION_OPEN_MESSAGE_BOT;
        } else {
            ConnectionDB connectionDB = new ConnectionDB();
            progressCounter = 0;
            input.modesOff();
            return connectionDB.getQuestion(adminMessage);
        }
    }

    public String deleteQuestion(String adminMessage) {
        if (progressCounter == 0) {
            progressCounter++;
            return QuestionConstants.QUESTION_DELETE_MESSAGE_BOT;
        } else {
            ConnectionDB connectionDB = new ConnectionDB();
            connectionDB.eraseQuestion(adminMessage);
            progressCounter = 0;
            input.modesOff();
            return QuestionConstants.QUESTION_DELETE_СOMPLETED_MESSAGE_BOT;
        }
    }

    public String createTest(String adminMessage) {
        if (progressCounter == 0) {
            progressCounter++;
            return TestConstants.TEST_CREATE_NAME_MESSAGE_BOT;
        } else if (progressCounter == 1) {
            progressCounter++;
            test.setName(adminMessage);
            return TestConstants.TEST_CREATE_TEXT_MESSAGE_BOT;
        } else {
            progressCounter++;
            test.setQuestions_list(adminMessage);
            try {
                ConnectionDB connectionDB = new ConnectionDB();
                connectionDB.addTest(test);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            progressCounter = 0;
            input.modesOff();
            return TestConstants.TEST_CREATE_FINISH_MESSAGE_BOT;
        }
    }

    public String openTest(String adminMessage) {
        if (progressCounter == 0) {
            progressCounter++;
            return TestConstants.TEST_OPEN_NAME_MESSAGE_BOT;
        } else {
            ConnectionDB connectionDB = new ConnectionDB();
            progressCounter = 0;
            input.modesOff();
            test.setName(adminMessage);
            return TestConstants.REGULAR_EXPRESSION_QUESTION + connectionDB.getTest(test);
        }
    }

    public String deleteTest(String adminMessage) {
        if (progressCounter == 0) {
            progressCounter++;
            return TestConstants.TEST_DELETE_MESSAGE_BOT;
        } else {
            ConnectionDB connectionDB = new ConnectionDB();
            progressCounter = 0;
            input.modesOff();
            test.setName(adminMessage);
            connectionDB.eraseTest(test);
            return TestConstants.TEST_DELETE_COMPLETED_MESSAGE_BOT;
        }
    }
}