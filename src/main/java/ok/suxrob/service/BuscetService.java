package ok.suxrob.service;

import ok.suxrob.lists.RepositoryList;
import ok.suxrob.orderConfirmation.Confirmation;
import ok.suxrob.utils.BackButtonUtil;
import ok.suxrob.utils.InlineKeyboardUtil;
import ok.suxrob.utils.MessageUtil;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class BuscetService {

    public static void savat(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        String data = callbackQuery.getData();
        String photo="";
        User user = callbackQuery.getFrom();
        editMessageText.setChatId(user.getId().toString());
        editMessageText.setReplyMarkup(OrderService.buyurtmaMenu(user.getId()));
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
        String[] text = data.split("/");
        editMessageText.setText("Bo'lim: \uD83C\uDF70 Desert");

        switch (text[2]) {
            case "buyurtmanitasdiqlash":
                editMessageText.setText("Yetkazib berish usulini tanlang");
                editMessageText.setReplyMarkup(Confirmation.confirmationMenu());
                break;
            case "orqaga":
                BackButtonUtil.f(callbackQuery,editMessageText);
                RepositoryList.userButton.remove(user.getId().toString());
                break;
            default:
                MessageUtil.notFoundMessage(user.getId());
                break;
        }
        RepositoryList.phototext.put(user.getId().toString(),photo);
}

    public static InlineKeyboardMarkup savatMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Buyurtmani tasdiqlash", "/savatcha/buyurtmanitasdiqlash")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Yana buyurtma berish", "/savatcha/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
