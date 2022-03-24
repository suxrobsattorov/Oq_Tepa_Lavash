package ok.suxrob.service;

import ok.suxrob.controller.WelcomeController;
import ok.suxrob.dto.Zakaz;
import ok.suxrob.lists.RepositoryList;
import ok.suxrob.orderConfirmation.Confirmation;
import ok.suxrob.product.*;
import ok.suxrob.utils.BackButtonUtil;
import ok.suxrob.utils.InlineKeyboardUtil;
import ok.suxrob.utils.MessageUtil;
import ok.suxrob.value.Values;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;

public class OrderService {

    public static List<String> getZakazMap(Long userId) {
        List<String> s = new LinkedList<>();
        List<Zakaz> zakazList = RepositoryList.zakazMap.get(String.valueOf(userId));
        for (Zakaz zakaz : zakazList) {
            s.add(zakaz.getValue());
        }
        return s;
    }
    public static InlineKeyboardMarkup buyurtmaMenu(Long userId) {
        Integer count = RepositoryList.userSelectedProductCount.get(String.valueOf(userId));
        if (count == null) {
            count = 0;
        }
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Tanlangan : " + count, "/buyurtma/tanlangan")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("1", "/buyurtma/1"),
                        InlineKeyboardUtil.button("2", "/buyurtma/2"),
                        InlineKeyboardUtil.button("3", "/buyurtma/3")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("4", "/buyurtma/4"),
                        InlineKeyboardUtil.button("5", "/buyurtma/5"),
                        InlineKeyboardUtil.button("6", "/buyurtma/6")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("7", "/buyurtma/7"),
                        InlineKeyboardUtil.button("8", "/buyurtma/8"),
                        InlineKeyboardUtil.button("9", "/buyurtma/9")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("0", "/buyurtma/0"),
                        InlineKeyboardUtil.button("🗑 O'chirish", "/buyurtma/ochirish")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🛒 Savatchaga joylash", "/buyurtma/savatchagajoylash")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }

    public static void buyurtmaHisob(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        String data = callbackQuery.getData();
        User user = callbackQuery.getFrom();
        editMessageText.setChatId(user.getId().toString());
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
        String[] text = data.split("/");
        editMessageText.setText(RepositoryList.phototext.get(user.getId().toString()));

        Integer count = RepositoryList.userSelectedProductCount.get(String.valueOf(user.getId()));
        if (count == null) {
            count = 0;
        }

        switch (text[2]) {
            case "1":
                count = count * 10 + 1;
                break;
            case "2":
                count = count * 10 + 2;
                break;
            case "3":
                count = count * 10 + 3;
                break;
            case "4":
                count = count * 10 + 4;
                break;
            case "5":
                count = count * 10 + 5;
                break;
            case "6":
                count = count * 10 + 6;
                break;
            case "7":
                count = count * 10 + 7;
                break;
            case "8":
                count = count * 10 + 8;
                break;
            case "9":
                count = count * 10 + 9;
                break;
            case "0":
                count = count * 10;
                break;
            case "ochirish":
                count = count / 10;
                break;
            case "savatchagajoylash":
                fv(callbackQuery, editMessageText);
                break;
            case "orqaga":
                BackButtonUtil.orderBack(callbackQuery, editMessageText);
                break;
            default:
                MessageUtil.notFoundMessage(user.getId());
                break;
        }

        RepositoryList.userSelectedProductCount.put(String.valueOf(user.getId()), count);
        editMessageText.setReplyMarkup(buyurtmaMenu(user.getId()));
    }

    public static void fv(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        User user = callbackQuery.getFrom();

        Values values = RepositoryList.userValues.get(String.valueOf(user.getId()));
        Integer count = RepositoryList.userSelectedProductCount.get(String.valueOf(user.getId()));
        int sum = count * values.getSum();
        String s = values.getName() + " : " + count + " x " +
                values.getSum() + " = " + sum + "\n";
        RepositoryList.totalSum.put(user.getId().toString(), sum);
        if (RepositoryList.userSelectedProductCount.get(String.valueOf(user.getId())) != null) {
            RepositoryList.userSelectedProductCount.put(user.getId().toString(), 0);
        }

        List<Zakaz> zakazList = RepositoryList.zakazMap.get(String.valueOf(callbackQuery.getFrom().getId()));
        if (zakazList == null) {
            zakazList = new LinkedList<>();
        }
        Zakaz zakaz = new Zakaz();
        zakaz.setId(String.valueOf(callbackQuery.getFrom().getId()));
        zakaz.setValue(s);
        zakazList.add(zakaz);

        RepositoryList.zakazMap.put(String.valueOf(callbackQuery.getFrom().getId()), zakazList);

        editMessageText.setText("Buyurtmani birga joylashtiramizmi? 🤗");
        editMessageText.setReplyMarkup(WelcomeController.menuKeyboad());
        String data = callbackQuery.getData();
        editMessageText.setChatId(user.getId().toString());
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
        RepositoryList.userButton.remove(user.getId().toString());
    }
}
