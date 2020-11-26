package InteractionWithTelegram;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import service.QuestionService;
import service.TestService;
import constants.QuestionConstants;
import constants.RegistrationConstants;
import constants.TestConstants;
import entity.Question;
import entity.Test;

@Component
public class EditTestAdmin {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    AuthorizationUser authorizationUser = context.getBean("authorizationUser", AuthorizationUser.class);
    QuestionService questionService = context.getBean("questionService", QuestionService.class);
    TestService testService = context.getBean("testService", TestService.class);
    private static int progressCounter = 0;
    private static Test test = new Test();

    public String createQuestion(String adminMessage) {
        Question question = context.getBean("question", Question.class);
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
            Question question1 = questionService.addQuestion(question);
            progressCounter = 0;
            authorizationUser.modesOff();
            return RegistrationConstants.START_MESSAGE_BOT;
        }
    }


    public String getQuestion(String adminMessage) {
        if (progressCounter == 0) {
            progressCounter++;
            return QuestionConstants.QUESTION_OPEN_MESSAGE_BOT;
        } else {
            progressCounter = 0;
            authorizationUser.modesOff();
            return String.valueOf(questionService.getQuestion(Integer.valueOf(adminMessage)));
        }
    }

    public String deleteQuestion(String adminMessage) {
        if (progressCounter == 0) {
            progressCounter++;
            return QuestionConstants.QUESTION_DELETE_MESSAGE_BOT;
        } else {
            questionService.deleteQuestion(Integer.valueOf(adminMessage));
            progressCounter = 0;
            authorizationUser.modesOff();
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
            Test test1 = testService.addTest(test);
            progressCounter = 0;
            authorizationUser.modesOff();
            return TestConstants.TEST_CREATE_FINISH_MESSAGE_BOT;
        }
    }

    public String openTest(String adminMessage) {
        if (progressCounter == 0) {
            progressCounter++;
            return TestConstants.TEST_OPEN_NAME_MESSAGE_BOT;
        } else {
            progressCounter = 0;
            authorizationUser.modesOff();
            test.setName(adminMessage);
            return TestConstants.REGULAR_EXPRESSION_QUESTION + testService.getTest(test.getId());
        }
    }
    public String deleteTest(String adminMessage) {
        if (progressCounter == 0) {
            progressCounter++;
            return TestConstants.TEST_DELETE_MESSAGE_BOT;
        } else {
            progressCounter = 0;
            authorizationUser.modesOff();
            test.setName(adminMessage);
            testService.deleteTest(test.getId());
            return TestConstants.TEST_DELETE_COMPLETED_MESSAGE_BOT;
        }
    }
}