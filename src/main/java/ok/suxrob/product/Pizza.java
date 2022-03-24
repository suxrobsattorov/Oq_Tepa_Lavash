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

public class Pizza {

    public static void pizza(CallbackQuery callbackQuery, EditMessageText editMessageText) {
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
            case "kombo":
                values.setName("Kombo");
                values.setSum(69000);
                photo="Siz tanladingiz: \uD83C\uDF55 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/61d58ddb-ed8a-4862-8ebf-5e335d23a5d9)";
                editMessageText.setText(photo);
                break;
            case "margarita":
                values.setName("Margarita");
                values.setSum(55000);
                photo="Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/2195e78c-b8ec-4592-97ad-32751a7e02e7)";
                editMessageText.setText(photo);
                break;
            case "goshtli":
                values.setName("Go'shtli");
                values.setSum(70000);
                photo="Siz tanladingiz: \uD83C\uDF55 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/afe27e2b-15e2-41ed-a1ac-6fdf1658303e)";
                editMessageText.setText(photo);
                break;
            case "qazili":
                values.setName("Qazi");
                values.setSum(70000);
                photo="Siz tanladingiz: " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/dbbbe57b-c3b4-44b8-b0b3-a4145f8328f3)";
                editMessageText.setText(photo);
                break;
            case "pepperone":
                values.setName("Pepperoni");
                values.setSum(58000);
                photo="Siz tanladingiz: \uD83C\uDF55 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/10b00132-129f-4ad1-b81a-a491e7281816)";
                editMessageText.setText(photo);
                break;
            case "qoziqorinle":
                values.setName("Qo’ziqorinli");
                values.setSum(58000);
                photo="Siz tanladingiz: \uD83C\uDF55 " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/b1dfaa50-bb25-4b8f-b2f2-f05681a1f97c)";
                editMessageText.setText(photo);
                break;
            case "tovuqli":
                values.setName("Tovuqli");
                values.setSum(61000);
                photo="Siz tanladingiz:  " + values.getName() + "\n" +
                        "Narx: " + values.getSum() + " so'm\n" +
                        "-----\n" +
                        "Iltimos, kerakli bo’lgan miqdorni kiriting!\u200D " +
                        "(https://cdn.delever.uz/delever/6221a1f5-2f83-4fe1-b459-e28c5318c44c)";
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

    public static InlineKeyboardMarkup pizzaMenu() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🍕 Kombo", "/pizza/kombo"),
                        InlineKeyboardUtil.button("🍕 Margarita", "/pizza/margarita")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🍕 Go'shtli", "/pizza/goshtli"),
                        InlineKeyboardUtil.button("🍕 Qazili", "/pizza/qazili")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🍕 Pepperone", "/pizza/pepperone"),
                        InlineKeyboardUtil.button("🍕 Qo'ziqorinli", "/pizza/qoziqorinle")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🍕 Tovuqli", "/pizza/tovuqli")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("⬅ Orqaga", "/pizza/orqaga")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
