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

public class Doner {

    public static void doner(CallbackQuery callbackQuery, EditMessageText editMessageText) {
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
            case "shaurma":
                values.setName("Shaurma");
                values.setSum(18000);
                photo = "Siz tanladingiz: \uD83C\uDF54 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/de31cd80-dc84-4b09-85d1-51abbf8f1c77)";
                editMessageText.setText(photo);
                break;
            case "bigdoner":
                values.setName("Big Doner");
                values.setSum(22000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/0b11afd2-e79d-402d-a150-14188ab584fe)";
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

    public static InlineKeyboardMarkup donerMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Shaurma", "/doner/shaurma"),
                        InlineKeyboardUtil.button("Big Doner", "/doner/bigdoner")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/doner/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
