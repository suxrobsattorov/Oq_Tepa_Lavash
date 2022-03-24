package ok.suxrob.utils;

import ok.suxrob.controller.WelcomeController;
import ok.suxrob.lists.RepositoryList;
import ok.suxrob.orderConfirmation.Confirmation;
import ok.suxrob.product.*;
import ok.suxrob.service.BuscetService;
import ok.suxrob.service.LogService;
import ok.suxrob.service.OrderService;
import ok.suxrob.value.Values;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;

public class BackButtonUtil {

    public static void f(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        editMessageText.setText("Buyurtmani birga joylashtiramizmi? 🤗");
        editMessageText.setReplyMarkup(WelcomeController.menuKeyboad());
        String data = callbackQuery.getData();
        User user = callbackQuery.getFrom();
        editMessageText.setChatId(user.getId().toString());
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
    }

    public static void orderConformationBack(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        String data = callbackQuery.getData();
        User user = callbackQuery.getFrom();
        String[] text = data.split("/");
        editMessageText.setChatId(user.getId().toString());
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);

        if (text[1].equals("buyurtmanitasdiqlash")) {
            editMessageText.setText("Sizning Buyurtmalaringiz\n\n" +
                    OrderService.getZakazMap(user.getId()));
            editMessageText.setReplyMarkup(BuscetService.savatMenu());
        }else {
            editMessageText.setText("Buyurtmani birga joylashtiramizmi? 🤗");
            editMessageText.setReplyMarkup(WelcomeController.menuKeyboad());
        }
    }

    public static void orderBack(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        String data = callbackQuery.getData();
        User user = callbackQuery.getFrom();
        editMessageText.setChatId(user.getId().toString());
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
        String[] text = data.split("/");
        Values values = RepositoryList.userValues.get(String.valueOf(user.getId()));

        editMessageText.setText(RepositoryList.phototext.get(user.getId().toString()));

        if (values.getSurname().equals("lavash")) {
            editMessageText.setReplyMarkup(Lavash.lavashMenu());
        } else if (values.getSurname().equals("desert")) {
            editMessageText.setReplyMarkup(Desert.desertMenu());
        } else if (values.getSurname().equals("set")) {
            editMessageText.setReplyMarkup(Set.setMenu());
        } else if (values.getSurname().equals("xaggi")) {
            editMessageText.setReplyMarkup(Xaggi.xaggiMenu());
        } else if (values.getSurname().equals("burger")) {
            editMessageText.setReplyMarkup(Burger.burgerMenu());
        } else if (values.getSurname().equals("pizza")) {
            editMessageText.setReplyMarkup(Pizza.pizzaMenu());
        } else if (values.getSurname().equals("sendvich")) {
            editMessageText.setReplyMarkup(Sendvich.sendvichMenu());
        } else if (values.getSurname().equals("doner")) {
            editMessageText.setReplyMarkup(Doner.donerMenu());
        } else if (values.getSurname().equals("xotdog")) {
            editMessageText.setReplyMarkup(XotDog.xotdogMenu());
        } else if (values.getSurname().equals("fri")) {
            editMessageText.setReplyMarkup(Fri.friMenu());
        } else if (values.getSurname().equals("napitki")) {
            editMessageText.setReplyMarkup(Napitki.napitkiMenu());
        } else if (values.getSurname().equals("salat")) {
            editMessageText.setReplyMarkup(Salat.salatMenu());
        } else if (values.getSurname().equals("non")) {
            editMessageText.setReplyMarkup(Non.nonMenu());
        } else if (values.getSurname().equals("sous")) {
            editMessageText.setReplyMarkup(Sous.sousMenu());
        }
    }
}
