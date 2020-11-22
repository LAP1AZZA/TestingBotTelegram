package service;

import Configs.ConnectionDB;
import constants.TestConstants;
import entity.Test;
import java.util.ArrayList;

public class UserService {
    private static int questionNumberInTheTest;
    private static int numberOfCorrectAnswers;
    private static String questionMessage;
    private static int testQuestionIdSize;
    private static ArrayList testQuestionId = new ArrayList();
    private static ArrayList questionAnswerParts = new ArrayList();
    private static InputService input = new InputService();

    public String testPerformer(String userMessage) {
        String answer;
        ConnectionDB connectionDB = new ConnectionDB();
        if (questionNumberInTheTest == 0) {
            Test test = new Test(userMessage);
            for (String question : connectionDB.getTest(test).replace(TestConstants.REGULAR_EXPRESSION_QUESTION, "").split(",")) {
                testQuestionId.add(question);
                testQuestionIdSize++;

            }
        }
        String[] arrSplit;
        if (questionNumberInTheTest == 0) {
            questionMessage = connectionDB.getQuestion(testQuestionId.get(questionNumberInTheTest).toString());
            questionNumberInTheTest++;
            arrSplit = questionMessage.split(TestConstants.REGULAR_EXPRESSION_ANSWER);
            return arrSplit[0];
        } else {
            arrSplit = questionMessage.split(TestConstants.REGULAR_EXPRESSION_ANSWER);
            answer = arrSplit[1];
            checkAnswer(userMessage, answer);
            if (testQuestionIdSize == questionNumberInTheTest) {
                input.testingFlowStopper();
                testQuestionId = new ArrayList();
                questionAnswerParts = new ArrayList();
                questionNumberInTheTest = 0;
                int AnVar = numberOfCorrectAnswers;
                numberOfCorrectAnswers = 0;
                testQuestionIdSize = 0;
                return TestConstants.TEST_FINISH_MESSAGE_BOT +
                        TestConstants.NUMBER_CORRECT_ANSWERS +
                        AnVar;
            } else {
                questionMessage = connectionDB.getQuestion(testQuestionId.get(questionNumberInTheTest).toString());
                arrSplit = questionMessage.split(TestConstants.REGULAR_EXPRESSION_ANSWER);
                questionNumberInTheTest++;
                return arrSplit[0];
            }
        }
    }

    public void checkAnswer(String userAnswer, String answer) {
        if (userAnswer.equals(answer)) {
            numberOfCorrectAnswers++;
        }
    }
}