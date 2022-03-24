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

public class Fri {

    public static void fri(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        String data = callbackQuery.getData();
        String photo = "";
        User user = callbackQuery.getFrom();
        editMessageText.setChatId(user.getId().toString());
        editMessageText.setReplyMarkup(OrderService.buyurtmaMenu(user.getId()));
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
        String[] text = data.split("/");
        Values values = new Values();
        values.setSurname(text[1]);

        switch (text[2]) {
            case "fri":
                values.setName("Fri");
                values.setSum(10000);
                photo = "Siz tanladingiz: \uD83C\uDF5F " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/a5e8bd47-1838-4a2e-827b-bb96e7c0f681)";
                editMessageText.setText(photo);
                break;
            case "kartofel":
                values.setName("Kartofel po derevenski");
                values.setSum(11000);
                photo = "Siz tanladingiz: \uD83C\uDF5F " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/d727f361-a58f-4280-8cc5-8c7d189eb4e1)";
                editMessageText.setText(photo);
                break;
            case "sir":
                values.setName("Sir");
                values.setSum(3000);
                photo = "Siz tanladingiz: \uD83E\uDDC0 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!";
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

    public static InlineKeyboardMarkup friMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🍟 Fri", "/fri/fri"),
                        InlineKeyboardUtil.button("🍟 Kartofel po derevenski", "/fri/kartofel")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🧀 Sir", "/fri/sir")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/fri/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
