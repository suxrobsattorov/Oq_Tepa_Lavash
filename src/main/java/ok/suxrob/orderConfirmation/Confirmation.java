package ok.suxrob.orderConfirmation;

import ok.suxrob.dto.CodeMessage;
import ok.suxrob.enums.CodeMessageType;
import ok.suxrob.lists.RepositoryList;
import ok.suxrob.service.LogService;
import ok.suxrob.service.OrderService;
import ok.suxrob.utils.BackButtonUtil;
import ok.suxrob.utils.InlineKeyboardUtil;
import ok.suxrob.utils.MessageUtil;
import ok.suxrob.utils.ReplayKeyboardUtil;
import ok.suxrob.value.Values;
import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class Confirmation {

    public static void confirmation(CallbackQuery callbackQuery, EditMessageText editMessageText, SendMessage sendMessage) {
        String data = callbackQuery.getData();
        String photo = "";
        User user = callbackQuery.getFrom();
        sendMessage.setChatId(user.getId().toString());
        editMessageText.setChatId(user.getId().toString());
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
        String[] text = data.split("/");
        Values values = new Values();
        values.setSurname(text[1]);

        switch (text[2]) {
            case "yetkazibberish":
                values.setName(null);
                values.setSum(0);
                sendMessage.setText("Iltimos, “\uD83D\uDCCD Geolokatsiyani jo’natish” " +
                        "tugmasini bosish orqali geolokatsiyangizni yuboring. Bunda telefoningizda" +
                        " manzilni aniqlash funksiyasi yoqilgan bo’lishi lozim.");
                sendMessage.setReplyMarkup(locationButton());
                RepositoryList.userButton.remove(user.getId().toString());
                break;
            case "olibketish":
                values.setName(null);
                values.setSum(0);
                sendMessage.setText("Iltimos, “\uD83D\uDCCD Geolokatsiyani jo’natish”" +
                        " tugmasini bosish orqali geolokatsiyangizni yuboring. Bunda telefoningizda" +
                        " manzilni aniqlash funksiyasi yoqilgan bo’lishi lozim.");
                sendMessage.setReplyMarkup(locationButton());
                RepositoryList.userButton.remove(user.getId().toString());
                break;
            case "ortga":
                BackButtonUtil.orderConformationBack(callbackQuery, editMessageText);
                RepositoryList.userButton.remove(user.getId().toString());
                break;
            default:
                MessageUtil.notFoundMessage(user.getId());
                break;
        }
        RepositoryList.userValues.put(String.valueOf(user.getId()), values);
        RepositoryList.phototext.put(user.getId().toString(), photo);
    }

    public static CodeMessage location(Message message) {
        CodeMessage codeMessage = new CodeMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Iltimos, “\uD83D\uDCCD Geolokatsiyani jo’natish” " +
                "tugmasini bosish orqali geolokatsiyangizni yuboring. Bunda telefoningizda" +
                " manzilni aniqlash funksiyasi yoqilgan bo’lishi lozim.");
        sendMessage.setReplyMarkup(locationButton());
        codeMessage.setType(CodeMessageType.MESSAGE);
        codeMessage.setSendMessage(sendMessage);
        return codeMessage;
    }

    public static CodeMessage yetqazish(Message message){
        CodeMessage codeMessage=new CodeMessage();
        SendMessage sendMessage=new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Yetkazib berish usulini tanlang");
        sendMessage.setReplyMarkup(Confirmation.confirmationMenu());
        codeMessage.setType(CodeMessageType.MESSAGE);
        codeMessage.setSendMessage(sendMessage);
        return codeMessage;
    }

    public static InlineKeyboardMarkup confirmationMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🚖 Yetkazib berish", "/buyurtmanitasdiqlash/yetkazibberish"),
                        InlineKeyboardUtil.button("🏃‍♂️ Olib ketish", "/buyurtmanitasdiqlash/olibketish")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/buyurtmanitasdiqlash/ortga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }

    public static ReplyKeyboardMarkup locationButton() {
        KeyboardButton button = ReplayKeyboardUtil.button("📍 Geolokatsiyani jo'natish");
        button.setRequestLocation(true);

        List<KeyboardRow> keyboardRowList = ReplayKeyboardUtil.rowlist(
                ReplayKeyboardUtil.row(button),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("⬅ Orqaga"))
        );
        ReplyKeyboardMarkup markup = ReplayKeyboardUtil.keyboard(keyboardRowList);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }

    public static ReplyKeyboardMarkup enterMenu() {
        KeyboardButton button = ReplayKeyboardUtil.button("📍 Joylashuvni qayta yuborish");
        button.setRequestLocation(true);

        List<KeyboardRow> keyboardRowList = ReplayKeyboardUtil.rowlist(
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("✔ Tasdiqlash")),
                ReplayKeyboardUtil.row(button),
                ReplayKeyboardUtil.row(ReplayKeyboardUtil.button("⬅ Orqaga"))
        );
        ReplyKeyboardMarkup markup = ReplayKeyboardUtil.keyboard(keyboardRowList);
        markup.setOneTimeKeyboard(true);
        markup.setResizeKeyboard(true);
        markup.setSelective(true);
        return markup;
    }
}
