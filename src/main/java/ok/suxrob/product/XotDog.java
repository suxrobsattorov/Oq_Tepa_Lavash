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

public class XotDog {

    public static void xotDog(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        String data = callbackQuery.getData();
        String photo = "";
        User user = callbackQuery.getFrom();
        editMessageText.setChatId(user.getId().toString());
        editMessageText.setReplyMarkup(OrderService.buyurtmaMenu(user.getId()));
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
        String[] text = data.split("/");
        Values values=new Values();
        values.setSurname(text[1]);

        switch (text[2]) {
            case "xalapenyo":
                values.setName("Xalapenyo Xot-dog");
                values.setSum(13000);
                photo = "Siz tanladingiz: \uD83C\uDF2D " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/49ab078c-2903-47b0-8a7a-8be1ed2ee18d)";
                editMessageText.setText(photo);
                break;
            case "chizdog":
                values.setName("Chiz-Dog");
                values.setSum(11000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/8195d858-51b6-4dd2-ae24-47c864531a6c)";
                editMessageText.setText(photo);
                break;
            case "xotdog":
                values.setName("Xot-Dog");
                values.setSum(10000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/300aa91f-cd5a-4ed4-b1b4-264c560dd60d)";
                editMessageText.setText(photo);
                break;
            case "kingdog":
                values.setName("King-Dog");
                values.setSum(17000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/1f452f7b-8c8b-44e2-8b0a-47c20b964d2f)";
                editMessageText.setText(photo);
                break;
            case "orqaga":
                BackButtonUtil.f(callbackQuery, editMessageText);
                RepositoryList.userButton.remove(user.getId().toString());
                break;
            default:
                MessageUtil.notFoundMessage(user.getId());
                break;
        }
        RepositoryList.userValues.put(String.valueOf(user.getId()), values);
        RepositoryList.phototext.put(user.getId().toString(), photo);
    }

    public static InlineKeyboardMarkup xotdogMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Xalapenyo Xot-Dog", "/xotdog/xalapenyo"),
                        InlineKeyboardUtil.button("Chiz-Dog", "/xotdog/chizdog")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Xot-Dog", "/xotdog/xotdog"),
                        InlineKeyboardUtil.button("King-Dog", "/xotdog/kingdog")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/xotdog/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
