package InteractionWithTelegram;

import InteractionWithTelegram.AuthorizationUser;
import constants.CommandConstants;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class BotConnection extends TelegramLongPollingBot{
    private static String outMessage;

    @Override
    public String getBotToken() {
        return "1419715545:AAF4mYFvnvbCTeR6J3-i3bqXWsFO5ifU07w";
    }
    @Override
    public String getBotUsername() {
        return "TestingBotLapi";
    }

    @Override
    public void onUpdateReceived(Update update){
        AuthorizationUser input = new AuthorizationUser();
        if (update.getMessage() != null && update.getMessage().hasText()){
            outMessage = input.processingUserInput(update.getMessage().getText());
        } else {
            outMessage = CommandConstants.MISTAKE_TYPE_MESSAGE_BOT;
        }
        String chat_id = update.getMessage().getChatId().toString();
        try {
            execute(new SendMessage(chat_id, outMessage));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public BotConnection() { }
}