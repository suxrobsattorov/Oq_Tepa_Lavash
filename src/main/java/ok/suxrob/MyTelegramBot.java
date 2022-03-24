package ok.suxrob;

import ok.suxrob.controller.WelcomeController;
import ok.suxrob.dto.CodeMessage;
import ok.suxrob.lists.RepositoryList;
import ok.suxrob.orderConfirmation.Confirmation;
import ok.suxrob.service.ProductService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyTelegramBot extends TelegramLongPollingBot {
    WelcomeController welcomeController = new WelcomeController();

    ProductService productService = new ProductService();

    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String text = update.getMessage().getText();
            User user = update.getMessage().getFrom();
            log(user.getFirstName(), user.getLastName(), user.getId(), text);
            if (text != null) {
                if (text.equals("/start")) {
                    try {
                        updatephoto(user, new File("src/main/resources/rasm/22.jpg"),
                                "Yetkazib berish bo'limi Toshkent shaxrida soat 10:00 dan 3:00 gacha ishlaydi.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sendMsg(welcomeController.start(update.getMessage()));
                } else if (text.equals("/buy") || text.equals("/help") || text.equals("/terms") || text.equals("/support")) {
                    sendMsg(welcomeController.start(update.getMessage()));
                } else if (text.equals("⬅ Orqaga")) {
                    if (RepositoryList.userButtonReply.containsKey(update.getMessage().getChatId().toString())) {
                        String s = RepositoryList.userButtonReply.get(update.getMessage().getChatId().toString());
                        if (s.equals("sendloc")) {
                            RepositoryList.userButtonReply.remove(update.getMessage().getChatId().toString());
                            sendMsg(Confirmation.location(update.getMessage()));
                        } else if (s.equals("inlineButton")) {
                            RepositoryList.userButtonReply.remove(update.getMessage().getChatId().toString());
                            RepositoryList.userButton.remove(update.getMessage().getChatId().toString());
                            sendMsg(Confirmation.yetqazish(update.getMessage()));
                        }
                    }
                }
            } else {
                if (update.getMessage().hasLocation()) {
                    RepositoryList.userButtonReply.put(update.getMessage().getChatId().toString(), "sendloc");
                    sendMsg(productService.replyMessage(update.getMessage()));
                }
            }
        } else if (update.hasCallbackQuery()) {
            sendMsg(productService.buyurtma(update.getCallbackQuery()));
        }
    }

    public void updatephoto(User user, File file, String caption) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(user.getId().toString());
        InputFile inputFile = new InputFile(file);
        sendPhoto.setPhoto(inputFile);
        sendPhoto.setCaption(caption);
        sendphoto(sendPhoto);
    }

    public void updatephoto(CallbackQuery callbackQuery, File file) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(callbackQuery.getMessage().getChatId().toString());
        InputFile inputFile = new InputFile(file);
        sendPhoto.setPhoto(inputFile);
        sendphoto(sendPhoto);
    }

    public void sendMsg(CodeMessage codeMessage) {
        try {
            switch (codeMessage.getType()) {
                case MESSAGE:
                    execute(codeMessage.getSendMessage());
                    break;
                case EDIT:
                    execute(codeMessage.getEditMessageText());
                    break;
                case EDITANDSEND:
                    execute(codeMessage.getEditMessageText());
                    execute(codeMessage.getSendMessage());
                    break;
                default:
                    break;
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendphoto(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void log(String first_name, String last_name, Long user_id, String txt) {
        try {
            System.out.println("\n --------------------------------------------------------");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            System.out.println(dateFormat.format(LocalDateTime.now()));
            System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getBotUsername() {
        return "";
    }

    public String getBotToken() {
        return "";
    }
}
