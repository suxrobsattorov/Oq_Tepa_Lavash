package ok.suxrob.product;

import ok.suxrob.lists.RepositoryList;
import ok.suxrob.service.LogService;
import ok.suxrob.service.OrderService;
import ok.suxrob.utils.BackButtonUtil;
import ok.suxrob.utils.InlineKeyboardUtil;
import ok.suxrob.utils.MessageUtil;
import ok.suxrob.value.Values;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Sous {

    public static void sous(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        String data = callbackQuery.getData();
        String photo="";
        User user = callbackQuery.getFrom();
        editMessageText.setChatId(user.getId().toString());
        editMessageText.setReplyMarkup(OrderService.buyurtmaMenu(user.getId()));
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
        String[] text = data.split("/");
        Values values=new Values();
        values.setSurname(text[1]);

        switch (text[2]) {
            case "ketchup":
                values.setName("Ketchup");
                values.setSum(1000);
                photo="Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/eaac5a2f-b4cc-4de6-b009-fa92d464e4aa)";
                editMessageText.setText(photo);
                break;
            case "mayonez":
                values.setName("Mayonez");
                values.setSum(1000);
                photo="Siz tanladingiz: \uD83C\uDF45 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/6201a61f-31fd-4677-95bb-b4f289c65ea3)";
                editMessageText.setText(photo);
                break;
            case "sirli":
                values.setName("Sirli sous");
                values.setSum(2500);
                photo="Siz tanladingiz: \uD83E\uDDC0 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/bccfe623-b141-4c59-b850-97264b1935b8)";
                editMessageText.setText(photo);
                break;
            case "orqaga":
                BackButtonUtil.f(callbackQuery,editMessageText);
                RepositoryList.userButton.remove(user.getId().toString());
                break;
            default:
                MessageUtil.notFoundMessage(user.getId());
                break;
        }
        RepositoryList.userValues.put(String.valueOf(user.getId()), values);
        RepositoryList.phototext.put(user.getId().toString(),photo);
    }

    public static InlineKeyboardMarkup sousMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🌶 Ketchup", "/sous/ketchup"),
                        InlineKeyboardUtil.button("🍅 Mayonez", "/sous/mayonez")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🧀 Sirli Sous", "/sous/sirli")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/sous/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
