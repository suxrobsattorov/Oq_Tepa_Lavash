package ok.suxrob;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new MyTelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
