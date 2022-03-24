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

public class Set {

    public static void set(CallbackQuery callbackQuery, EditMessageText editMessageText) {
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
            case "setklassika":
                values.setName("Set Klassika");
                values.setSum(31000);
                photo="Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/9adc94ea-7ebc-4188-b47c-ea66c940a64c)";
                editMessageText.setText(photo);
                break;
            case "setgurman":
                values.setName("Set Gurman");
                values.setSum(31000);
                photo="Siz tanladingiz: \uD83C\uDF71 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/6631145f-8b8d-4a6a-a64c-8f4db2873e57)";
                editMessageText.setText(photo);
                break;
            case "setskromnyaga":
                values.setName("Set Skromnyaga");
                values.setSum(31000);
                photo="Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/ebc6c3ad-d28b-4d25-b42e-704e1c87fb7f)";
                editMessageText.setText(photo);
                break;
            case "setlyubov":
                values.setName("Set Lyubov");
                values.setSum(39000);
                photo="Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/69d4cf5b-95f8-4c21-a406-688f47807263)";
                editMessageText.setText(photo);
                break;
            case "setdrujba":
                values.setName("Set Drujba");
                values.setSum(87000);
                photo="Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/0e3d58cf-0e3e-4f3e-adb8-7663a420045f)";
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

    public static InlineKeyboardMarkup setMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Set Klassika", "/set/setklassika"),
                        InlineKeyboardUtil.button("Set Gurman", "/set/setgurman")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Set Skromnyaga", "/set/setskromnyaga"),
                        InlineKeyboardUtil.button("Set Lyubov", "/set/setlyubov")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Set Drujba", "/set/setdrujba")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/set/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
