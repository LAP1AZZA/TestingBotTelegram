import CRUD.QuestionService;
import CRUD.UserService;
import entity.Question;
import entity.User;
import org.junit.jupiter.api.Test;

import javax.management.Query;

public class QuestionServiceTest {
    UserService service = new UserService();

    @Test
    public void userAddRecord() throws Exception {
        User user1 = new User();
        user1.setName("Boby");
        user1.setLogin("Body");
        user1.setAdmin_status(true);
        user1.setPassword("12345");

        User user = service.addUser(user1);
/**
        User userFromDB = service.getUser(user.getId());
        System.out.println(userFromDB);
*/














        /**Question question2 = new Question();
        question2.setAuthorLogin("qwersdfty");
        question2.setAnswer("bsdfduka");
        question2.setQuestionText("bysdfska");
        question2.setDifficulty("HARD");
        question2.setType("OPEN");

        //Question question = service.addQuestion(question1);
        Question question = service.addQuestion(question2);

        Question questionFromDB = service.getQuestion(question.getId());
        System.out.println(questionFromDB);*/
        
    }



}
