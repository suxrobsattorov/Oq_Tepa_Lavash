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

public class Napitki {

    public static void napitki(CallbackQuery callbackQuery, EditMessageText editMessageText) {
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
            case "pepsi":
                values.setName("Pepsi 1.5L");
                values.setSum(14000);
                photo = "Siz tanladingiz: \uD83E\uDD64 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/5b178423-ce5e-4dd2-b6e7-8acb2963a2b9)";
                editMessageText.setText(photo);
                break;
            case "sok":
                values.setName("Sok \"Сочная Долина\"");
                values.setSum(12000);
                photo = "Siz tanladingiz: \uD83C\uDF79 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/c0072fd5-b127-4145-abe5-7156615c7712)";
                editMessageText.setText(photo);
                break;
            case "suv":
                values.setName("Gazsiz suv 0,5L");
                values.setSum(2000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/a65ca37a-db0d-4f92-b012-04f778cb2529)";
                editMessageText.setText(photo);
                break;
            case "kofe":
                values.setName("Kofe 3v1");
                values.setSum(7000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/2a74eeeb-843c-44a7-b102-8fb1f0e9468c)";
                editMessageText.setText(photo);
                break;
            case "choy":
                values.setName("Qora choy");
                values.setSum(2000);
                photo = "Siz tanladingiz: \uD83C\uDF75 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/4bcd1ad0-0540-4d22-a3f6-5c905e16adef)";
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

    public static InlineKeyboardMarkup napitkiMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Pepsi", "/napitki/pepsi"),
                        InlineKeyboardUtil.button("Sok", "/napitki/sok")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Suv", "/napitki/suv"),
                        InlineKeyboardUtil.button("Kofe", "/napitki/kofe")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Choy", "/napitki/choy")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/napitki/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
