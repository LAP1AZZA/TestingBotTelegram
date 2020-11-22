package Configs;

import constants.CommandConstants;
import service.InputService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotConnection extends TelegramLongPollingBot{
    private static String OutMessage;

    public String getBotToken() {
        return "1419715545:AAF4mYFvnvbCTeR6J3-i3bqXWsFO5ifU07w";
    }
    public String getBotUsername() {
        return "TestingBotLapi";
    }

    public void onUpdateReceived(Update update){
        InputService input = new InputService();
        if (update.getMessage() != null && update.getMessage().hasText()){
            OutMessage = input.processingUserInput(update.getMessage().getText());
        } else {
            OutMessage = CommandConstants.MISTAKE_TYPE_MESSAGE_BOT;
        }
        String chat_id = update.getMessage().getChatId().toString();
        try {
            execute(new SendMessage(chat_id, OutMessage));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public BotConnection() { }
}