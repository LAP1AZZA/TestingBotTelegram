package InteractionWithTelegram;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import service.QuestionService;
import service.TestService;
import constants.TestConstants;
import entity.Test;

import java.util.ArrayList;

@Component
public class PassingTheTest {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    AuthorizationUser authorizationUser = context.getBean("authorizationUser", AuthorizationUser.class);
    QuestionService questionService = context.getBean("questionService", QuestionService.class);
    TestService testService = context.getBean("testService", TestService.class);
    private static int questionNumberInTheTest;
    private static int numberOfCorrectAnswers;
    private static String questionMessage;
    private static int testQuestionIdSize;
    private static ArrayList testQuestionId = new ArrayList();
    private static ArrayList questionAnswerParts = new ArrayList();

    public String testPerformer(String userMessage) {
        String answer;
        if (questionNumberInTheTest == 0) {
            Test test = new Test(userMessage);
            for (String question : String.valueOf(testService.getTest(test.getId())).replace(TestConstants.REGULAR_EXPRESSION_QUESTION, "").split(",")) {
                testQuestionId.add(question);
                testQuestionIdSize++;

            }
        }
        String[] arrSplit;
        if (questionNumberInTheTest == 0) {
            questionMessage = String.valueOf(questionService.getQuestion(Integer.valueOf(testQuestionId.get(questionNumberInTheTest).toString())));
            questionNumberInTheTest++;
            arrSplit = questionMessage.split(TestConstants.REGULAR_EXPRESSION_ANSWER);
            return arrSplit[0];
        } else {
            arrSplit = questionMessage.split(TestConstants.REGULAR_EXPRESSION_ANSWER);
            answer = arrSplit[1];
            checkAnswer(userMessage, answer);
            if (testQuestionIdSize == questionNumberInTheTest) {
                authorizationUser.testingFlowStopper();
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
                questionMessage = String.valueOf(questionService.getQuestion(Integer.valueOf(testQuestionId.get(questionNumberInTheTest).toString())));
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