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

public class Salat {

    public static void salat(CallbackQuery callbackQuery, EditMessageText editMessageText) {
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
            case "olivye":
                values.setName("Olivye");
                values.setSum(17000);
                photo = "Siz tanladingiz: \uD83E\uDD57 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/3f467b7e-0ce3-4ffe-8823-45c2fed78b7c)";
                editMessageText.setText(photo);
                break;
            case "kapriz":
                values.setName("Mujskoy Kapriz");
                values.setSum(18500);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/fbe4cc2a-7e83-47da-bdbc-c36c627e972c)";
                editMessageText.setText(photo);
                break;
            case "sezar":
                values.setName("Sezar");
                values.setSum(18000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/5c7e9c76-6d1f-451e-b93c-915ef95c8f6d)";
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

    public static InlineKeyboardMarkup salatMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Olivye", "/salat/olivye"),
                        InlineKeyboardUtil.button("Mujskoy Kapriz", "/salat/kapriz")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Sezar", "/salat/sezar")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/salat/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
