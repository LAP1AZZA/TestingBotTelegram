package constants;

public class RegistrationConstants extends CommandConstants{
    public static final String ABSENCE_USER_MESSAGE_BOT = "пользователь не найден";
    public static final String REGISTER_LOGIN_MESSAGE_BOT = "ввидите логин";
    public static final String REGISTER_NAME_MESSAGE_BOT = "ввидите имя";
    public static final String REGISTER_PASSWORD_MESSAGE_BOT = "ввидите пороль";
    public static final String REGISTER_COMPLETED_MESSAGE_BOT = "новый пользователь успешно зарегестрирован";
    public static final String AUTHORIZATION_USER_MESSAGE_BOT = "вы вошли как пользователь\n" +
            "доступные команды:\n" + TAKE_TEST_USER + "  -пройти тест\n" + LOGOUT_USER + "  -перезайти";
    public static final String REGISTER_STATUS_MESSAGE_BOT = "ввидите код для одминистраторов" +
            " или 'not admin' если регистрируетесь как пользователь";
    public static final String START_MESSAGE_BOT = "введите одну из команд:\n" +
            REGISTER_USER + "  -заригестрироваться\n" + LOGIN_USER + "  -войти";

}
