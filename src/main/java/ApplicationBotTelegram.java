import InteractionWithTelegram.BotConnection;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ApplicationBotTelegram {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botApi = new TelegramBotsApi();
        try {
            botApi.registerBot(new BotConnection());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

