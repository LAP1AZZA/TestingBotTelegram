package InteractionWithTelegram;

import Connection.ConnectionDB;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import service.UserService;
import constants.CommandConstants;
import constants.TestConstants;
import constants.RegistrationConstants;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorizationUser {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private static boolean startModOn;
    private static boolean createQuestionModOn;
    private static boolean openQuestionModOn;
    private static boolean deleteQuestionModOn;
    private static boolean createTestModOn;
    private static boolean openTestModOn;
    private static boolean deleteTestModOn;
    private static boolean testingModOn;
    private static boolean registrationFlow;
    private static boolean authorizationFlow;
    private static boolean userFlow;
    private static boolean administrationFlow;
    private static int flowStep = 0;
    private static User user = new User();

    public String processingUserInput(String userInput) {
        EditTestAdmin adminService = context.getBean("editTestAdmin", EditTestAdmin.class);
        PassingTheTest userTest = context.getBean("passingTheTest", PassingTheTest.class);
        if (registrationFlow) {
            return registerNewUserProcess(userInput);
        } else if (authorizationFlow) {
            return authorizationUserProcess(userInput);
        } else if (userFlow) {
            if (testingModOn) {
                return userTest.testPerformer(userInput);
            } else {
                return userProcess(userInput); }
        } else if (administrationFlow) {
            administrationProcess(userInput);
            if (createQuestionModOn) {
                return (adminService.createQuestion(userInput));
            } else if (openQuestionModOn) {
                return (adminService.getQuestion(userInput));
            } else if (deleteQuestionModOn) {
                return adminService.deleteQuestion(userInput);
            } else if (createTestModOn) {
                return adminService.createTest(userInput);
            } else if (openTestModOn) {
                return adminService.openTest(userInput);
            } else if (deleteTestModOn) {
                return adminService.deleteTest(userInput);
            } else if (!administrationFlow) {
                return RegistrationConstants.START_MESSAGE_BOT;
            }
        } else {
            switch (userInput) {
                case CommandConstants.START_USER:
                    startModOn = true;
                    return RegistrationConstants.START_MESSAGE_BOT;
                case CommandConstants.LOGIN_USER:
                    if (startModOn) {
                        authorizationFlow = true;
                        return authorizationUserProcess(userInput);
                    } else {
                        return CommandConstants.MISTAKE_START_MESSAGE_BOT;
                    }
                case CommandConstants.REGISTER_USER:
                    if (startModOn) {
                        registrationFlow = true;
                        return registerNewUserProcess(userInput);
                    } else {
                        return CommandConstants.MISTAKE_START_MESSAGE_BOT;
                    }
                default:
                    return CommandConstants.MISTAKE_MESSAGE_BOT;
            }
        }
        return RegistrationConstants.START_MESSAGE_BOT;
    }

    private String registerNewUserProcess(String currentMessage) {
        UserService userService = context.getBean("userService", UserService.class);
        switch (flowStep) {
            case 0:
                flowStep++;
                return (RegistrationConstants.REGISTER_LOGIN_MESSAGE_BOT);
            case 1:
                user.setLogin(currentMessage);
                flowStep++;
                return (RegistrationConstants.REGISTER_NAME_MESSAGE_BOT);
            case 2:
                user.setName(currentMessage);
                flowStep++;
                return (RegistrationConstants.REGISTER_PASSWORD_MESSAGE_BOT);
            case 3:
                user.setPassword(currentMessage);
                flowStep++;
                return (RegistrationConstants.REGISTER_STATUS_MESSAGE_BOT);
            default:
                if (currentMessage.equals(CommandConstants.ADMIN_PASSWORD)) {
                    user.setAdmin_status(true);
                } else if (currentMessage.equals(CommandConstants.NOT_ADMIN_MESSAGE)) {
                    user.setAdmin_status(false);
                } else {
                    return (CommandConstants.MISTAKE_MESSAGE_BOT);
                }
                userService.addUser(user);
                user = new User();
                flowStopper();
                return (RegistrationConstants.REGISTER_COMPLETED_MESSAGE_BOT +
                        "\n\n" + RegistrationConstants.START_MESSAGE_BOT);
        }
    }

    public ResultSet getUser(User user) { //Костыль - доделать
        ConnectionDB connectionDB = new ConnectionDB();
        ResultSet rs = null;
        String select = "SELECT * FROM USER WHERE " +
                "LOGIN" + " = ? AND " + "PASSWORD" +
                " = ? AND " + "ADMIN_STATUS" + "= ?";
        try {
            PreparedStatement prSt = connectionDB.getDataBaseConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setBoolean(3, user.getAdmin_status());
            rs = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    private String authorizationUserProcess(String currentMessage){
        ConnectionDB connectionDB = new ConnectionDB();
        switch (flowStep) {
            case 0:
                flowStep++;
                return (RegistrationConstants.REGISTER_LOGIN_MESSAGE_BOT);
            case 1:
                user.setLogin(currentMessage);
                flowStep++;
                return (RegistrationConstants.REGISTER_PASSWORD_MESSAGE_BOT);
            default:
                user.setPassword(currentMessage);
                user.setAdmin_status(false);
                ResultSet rs = getUser(user);
                boolean occurrence = false;
                try {
                    while (rs.next()) {
                        occurrence = true;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (!occurrence) {
                    user.setAdmin_status(true);
                    rs = getUser(user);
                    boolean adminOccurrence = false;
                    try {
                        while (rs.next()) {
                            adminOccurrence = true;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (adminOccurrence) {
                        flowStopper();
                        administrationFlow = true;
                        return TestConstants.AUTHORIZATION_ADMIN_MESSAGE_BOT;
                    } else {
                        flowStopper();
                        modesOff();
                        return RegistrationConstants.ABSENCE_USER_MESSAGE_BOT;
                    }
                }
                flowStopper();
                userFlow = true;
                return RegistrationConstants.AUTHORIZATION_USER_MESSAGE_BOT;
        }
    }

    private void administrationProcess (String currentMessage) {
        if (currentMessage.equals(CommandConstants.CREATE_TEST_USER)) {
            createTestModOn = true;
        } else if (currentMessage.equals(CommandConstants.OPEN_TEST_USER)) {
            openTestModOn  = true;
        } else if (currentMessage.equals(CommandConstants.DELETE_TEST_USER)) {
            deleteTestModOn = true;
        } else if (currentMessage.equals(CommandConstants.CREATE_QUESTION_USER)) {
            createQuestionModOn = true;
        } else if (currentMessage.equals(CommandConstants.OPEN_QUESTION_USER)) {
            openQuestionModOn  = true;
        } else if (currentMessage.equals(CommandConstants.DELETE_QUESTION_USER)) {
            deleteQuestionModOn = true;
        } else if (currentMessage.equals(CommandConstants.LOGOUT_USER)) {
            logout();
        }
    }

    private String userProcess (String currentMessage) {
        switch (currentMessage) {
            case CommandConstants.TAKE_TEST_USER:
                testingModOn = true;
                return TestConstants.TESTING_START_MESSAGE_BOT;
            case CommandConstants.LOGOUT_USER:
                logout();
                return RegistrationConstants.START_MESSAGE_BOT;
            default:
                return CommandConstants.MISTAKE_MESSAGE_BOT;
        }
    }

    private void logout() {
        authorizationFlow = false;
        administrationFlow = false;
        userFlow = false;
    }

    private void flowStopper () {
        administrationFlow = false;
        authorizationFlow = false;
        registrationFlow = false;
        userFlow = false;
        flowStep = 0;
    }

    public void testingFlowStopper () {testingModOn = false;}

    public void modesOff() {
        createQuestionModOn = false;
        openQuestionModOn = false;
        deleteQuestionModOn = false;
        createTestModOn = false;
        openTestModOn = false;
        deleteTestModOn = false;
    }
}
