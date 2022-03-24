package ok.suxrob.product;

import ok.suxrob.lists.RepositoryList;
import ok.suxrob.service.OrderService;
import ok.suxrob.utils.BackButtonUtil;
import ok.suxrob.utils.InlineKeyboardUtil;
import ok.suxrob.service.LogService;
import ok.suxrob.utils.MessageUtil;
import ok.suxrob.value.Values;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Lavash {

    public static void lavash(CallbackQuery callbackQuery, EditMessageText editMessageText) {
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
            case "lavashi":
                values.setName("Lavash");
                values.setSum(22000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D" +
                        " (https://cdn.delever.uz/delever/a4350a0c-cef4-408a-8433-3d462f4390f0)";
                editMessageText.setText(photo);
                break;
            case "lavashmini":
                values.setName("Lavash Mini");
                values.setSum(19000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/60d4d7be-fff0-48da-9a61-a3204cba41b4)";
                editMessageText.setText(photo);
                break;
            case "lavashsirli":
                values.setName("Lavash sirli");
                values.setSum(24000);
                photo = "Siz tanladingiz: " + values.getName() + " \n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/82b45aec-8e96-4b5e-af65-7d3073dd3b1c)";
                editMessageText.setText(photo);
                break;
            case "lavashsirlimini":
                values.setName("Lavash sirli Mini");
                values.setSum(21000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/60977dce-e07a-42ee-9c17-0cc1e2e8ed50)";
                editMessageText.setText(photo);
                break;
            case "tandirlavash":
                values.setName("Tandir Lavash");
                values.setSum(23000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/53cddbcb-a41e-46f3-94c8-6f0292db05b1)";
                editMessageText.setText(photo);
                break;
            case "xalapenyolavashmini":
                values.setName("Xalapenyo Lavash");
                values.setSum(23000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/e0e73302-f07a-4023-bfdd-e0d1bc5a2290)";
                editMessageText.setText(photo);
                break;
            case "tandirlavashsirli":
                values.setName("Tandir lavash sirli");
                values.setSum(25000);
                photo = "Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!" +
                        "(https://cdn.delever.uz/delever/53cddbcb-a41e-46f3-94c8-6f0292db05b1)";
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

    public static InlineKeyboardMarkup lavashMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Lavash", "/lavash/lavashi"),
                        InlineKeyboardUtil.button("Lavash Mini", "/lavash/lavashmini")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Lavash Tandir", "/lavash/lavashsirli"),
                        InlineKeyboardUtil.button("Lavash Sirli Mini", "/lavash/lavashsirlimini")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Tandir Lavash", "/lavash/tandirlavash"),
                        InlineKeyboardUtil.button("Xalapenyo Lavash", "/lavash/xalapenyolavashmini")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Tandir Lavash Sirli", "/lavash/tandirlavashsirli")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/lavash/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
