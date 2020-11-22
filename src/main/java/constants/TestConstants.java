package constants;

import static constants.CommandConstants.*;

public class TestConstants{
    public static final String TESTING_START_MESSAGE_BOT = "введите название теста";
    public static final String TEST_CREATE_NAME_MESSAGE_BOT = "введите название теста";
    public static final String TEST_OPEN_NAME_MESSAGE_BOT = "введите название открываемого теста";
    public static final String TEST_DELETE_MESSAGE_BOT = "введите название удаляемого теста";
    public static final String TEST_DELETE_COMPLETED_MESSAGE_BOT = "тест успешно удалён";
    public static final String TEST_CREATE_TEXT_MESSAGE_BOT = "введите id вопросов через запятую";
    public static final String TEST_CREATE_FINISH_MESSAGE_BOT = "тест успешно создан";
    public static final String TEST_FINISH_MESSAGE_BOT = "тест завершен";
    public static final String NUMBER_CORRECT_ANSWERS = "\nколличество правильных ответов: ";
    public static final String REGULAR_EXPRESSION_ANSWER = "\n\nтекст ответа:\n";
    public static final String REGULAR_EXPRESSION_QUESTION = "вопросы теста:\n";
    public static final String AUTHORIZATION_ADMIN_MESSAGE_BOT = "вы вошли как администратор\n" +
            "доступные команды:\n" + CREATE_TEST_USER + "  -создать тест\n" + OPEN_TEST_USER +
            "  -открыть тест\n" + DELETE_TEST_USER + "  -удалить тест\n" + CREATE_QUESTION_USER +
            "  -создать вопрос\n" + OPEN_QUESTION_USER + "  -открыть вопрос\n" + DELETE_QUESTION_USER +
            "  -удалить вопрос\n" + LOGOUT_USER+ "  -перезайти";
}