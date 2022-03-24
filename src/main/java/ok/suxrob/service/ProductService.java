package ok.suxrob.service;

import ok.suxrob.dto.CodeMessage;
import ok.suxrob.enums.CodeMessageType;
import ok.suxrob.lists.RepositoryList;
import ok.suxrob.orderConfirmation.Confirmation;
import ok.suxrob.product.*;
import ok.suxrob.utils.BackButtonUtil;
import ok.suxrob.utils.MessageUtil;
import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public class ProductService {

    public CodeMessage buyurtma(CallbackQuery callbackQuery) {
        EditMessageText editMessageText = new EditMessageText();
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage = new CodeMessage();
        User user = callbackQuery.getFrom();
        sendMessage.setChatId(user.getId().toString());
        editMessageText.setChatId(user.getId().toString());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());
        String data = callbackQuery.getData();
        LogService.log(user.getFirstName(), user.getLastName(), user.getId(), data);
        String[] text = data.split("/");

        tanlash(text, editMessageText, user, callbackQuery, sendMessage);

        if (text[1].equals("buyurtmanitasdiqlash") && !text[2].equals("ortga")) {
            codeMessage.setType(CodeMessageType.MESSAGE);
            codeMessage.setSendMessage(sendMessage);
        } else {
            codeMessage.setType(CodeMessageType.EDIT);
            codeMessage.setEditMessageText(editMessageText);
        }
        return codeMessage;
    }

    public void tanlash(String[] text, EditMessageText editMessageText, User user, CallbackQuery callbackQuery,
                        SendMessage sendMessage) {
        if (RepositoryList.userButton.containsKey(user.getId().toString())) {
            if (RepositoryList.userButton.get(user.getId().toString()).equals(text[1])) {
                if (text[1].equals("lavash")) {
                    Lavash.lavash(callbackQuery, editMessageText);
                } else if (text[1].equals("desert")) {
                    Desert.desert(callbackQuery, editMessageText);
                } else if (text[1].equals("set")) {
                    Set.set(callbackQuery, editMessageText);
                } else if (text[1].equals("xaggi")) {
                    Xaggi.xaggi(callbackQuery, editMessageText);
                } else if (text[1].equals("burger")) {
                    Burger.burger(callbackQuery, editMessageText);
                } else if (text[1].equals("pizza")) {
                    Pizza.pizza(callbackQuery, editMessageText);
                } else if (text[1].equals("sendvich")) {
                    Sendvich.sendvich(callbackQuery, editMessageText);
                } else if (text[1].equals("doner")) {
                    Doner.doner(callbackQuery, editMessageText);
                } else if (text[1].equals("xotdog")) {
                    XotDog.xotDog(callbackQuery, editMessageText);
                } else if (text[1].equals("fri")) {
                    Fri.fri(callbackQuery, editMessageText);
                } else if (text[1].equals("napitki")) {
                    Napitki.napitki(callbackQuery, editMessageText);
                } else if (text[1].equals("salat")) {
                    Salat.salat(callbackQuery, editMessageText);
                } else if (text[1].equals("non")) {
                    Non.non(callbackQuery, editMessageText);
                } else if (text[1].equals("sous")) {
                    Sous.sous(callbackQuery, editMessageText);
                } else if (text[1].equals("savatcha")) {
                    BuscetService.savat(callbackQuery, editMessageText);
                }
            } else if (text[1].equals("buyurtma")) {
                if (text[2].equals("savatchagajoylash")) {
                    OrderService.fv(callbackQuery, editMessageText);
                }
                if (text[2].equals("orqaga")) {
                    BackButtonUtil.orderBack(callbackQuery, editMessageText);
                } else if (!text[2].equals("savatchagajoylash")) {
                    OrderService.buyurtmaHisob(callbackQuery, editMessageText);
                }
            } else if (text[1].equals("buyurtmanitasdiqlash")) {
                if (text[2].equals("ortga")) {
                    BackButtonUtil.orderConformationBack(callbackQuery, editMessageText);
                } else {
                    Confirmation.confirmation(callbackQuery, editMessageText, sendMessage);
                    RepositoryList.userButtonReply.put(user.getId().toString(), "inlineButton");
                }
            }
        } else {
            switchTanlash(text, editMessageText, user);
        }
    }

    public CodeMessage replyMessage(Message message) {
        String text = message.getText();
        CodeMessage codeMessage = new CodeMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Joylashuvni noto'g'rimi?\n" +
                "Qayta jo'nating\uD83D\uDCCD");
        sendMessage.setReplyMarkup(Confirmation.enterMenu());
        codeMessage.setType(CodeMessageType.MESSAGE);
        codeMessage.setSendMessage(sendMessage);
        return codeMessage;
//        if (text.equals("📍 Geolokatsiyani jo'natish")) {
//            sendMessage.setText("Joylashuvni noto'g'rimi?\n" +
//                    "Qayta jo'nating\uD83D\uDCCD");
//            sendMessage.setReplyMarkup(Confirmation.enterMenu());
//        } else {
//
//        }
    }

    public void switchTanlash(String[] text, EditMessageText editMessageText, User user) {
        switch (text[2]) {
            case "lavash":
                editMessageText.setText("Bo'lim: \uD83C\uDF2F Lavash \u200D " +
                        "(https://cdn.delever.uz/delever/2e8af7cd-5edb-4403-beb9-456b9353e4f6)");
                editMessageText.setReplyMarkup(Lavash.lavashMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "desert":
                editMessageText.setText("Bo'lim: \uD83C\uDF70 Desert");
                editMessageText.setReplyMarkup(Desert.desertMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "set":
                editMessageText.setText("Bo'lim: \uD83C\uDF71 Set\u200D " +
                        "(https://cdn.delever.uz/delever/a3b990a8-2043-4042-855d-6c98932ab737)");
                editMessageText.setReplyMarkup(Set.setMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "xaggi":
                editMessageText.setText("Bo'lim: \uD83E\uDD59 Xaggi\u200D " +
                        "(https://cdn.delever.uz/delever/1bd4e141-2797-42fe-a808-35fadb1f24cd)");
                editMessageText.setReplyMarkup(Xaggi.xaggiMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "burger":
                editMessageText.setText("Bo'lim: \uD83C\uDF54 Burger\u200D " +
                        "(https://cdn.delever.uz/delever/3e207848-a4e3-4401-9294-a27f2a233bad)");
                editMessageText.setReplyMarkup(Burger.burgerMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "pizza":
                editMessageText.setText("Bo'lim: \uD83C\uDF55 Pizza\u200D " +
                        "(https://cdn.delever.uz/delever/56084c2b-dff7-470e-8f91-04f8f09f94a6)");
                editMessageText.setReplyMarkup(Pizza.pizzaMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "sendvich":
                editMessageText.setText("Bo'lim: \uD83E\uDD6A Sendvich\u200D " +
                        "(https://cdn.delever.uz/delever/fd140458-a05e-490e-be0a-fb2030cef2a5)");
                editMessageText.setReplyMarkup(Sendvich.sendvichMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "doner":
                editMessageText.setText("Bo'lim: \uD83E\uDD59 Doner\u200D " +
                        "(https://cdn.delever.uz/delever/12529b1f-b096-45c2-ad7d-d39c90d94811)");
                editMessageText.setReplyMarkup(Doner.donerMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "xotdog":
                editMessageText.setText("Bo'lim: \uD83C\uDF2D Xot-Dog\u200D " +
                        "(https://cdn.delever.uz/delever/9c513c81-4da7-4d20-b977-3a61675c8cda)");
                editMessageText.setReplyMarkup(XotDog.xotdogMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "fri":
                editMessageText.setText("Bo'lim: \uD83C\uDF5F Fri\u200D " +
                        "(https://cdn.delever.uz/delever/3fcf7ba3-7f74-4d66-9559-e2118057c516)");
                editMessageText.setReplyMarkup(Fri.friMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "napitki":
                editMessageText.setText("Bo'lim: \uD83C\uDF79 Napitki\u200D " +
                        "(https://cdn.delever.uz/delever/263afbf4-c00c-4df5-bdf2-1f724d17e5cc)");
                editMessageText.setReplyMarkup(Napitki.napitkiMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "salat":
                editMessageText.setText("Bo'lim: \uD83E\uDD57 Salat\u200D " +
                        "(https://cdn.delever.uz/delever/eefca14c-66f0-4d59-b544-4a5ad479b0d4)");
                editMessageText.setReplyMarkup(Salat.salatMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "non":
                editMessageText.setText("Bo'lim: \uD83C\uDF5E Non\u200D " +
                        "(https://cdn.delever.uz/delever/99aa339f-9438-4670-a8ad-31cb9d6d9689)");
                editMessageText.setReplyMarkup(Non.nonMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "sous":
                editMessageText.setText("Bo'lim: \uD83C\uDF45 Sous\u200D " +
                        "(https://cdn.delever.uz/delever/e016472e-a7f3-4475-b734-0ebc88eb169e)");
                editMessageText.setReplyMarkup(Sous.sousMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "buyurtmalartarixi":
                editMessageText.setText("Buyurtmalar Tarixi\n\n" +
                        OrderService.getZakazMap(user.getId()));
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                RepositoryList.userButton.remove(user.getId().toString());
                break;
            case "savatcha":
                editMessageText.setText("Sizning Buyurtmalaringiz\n\n" +
                        OrderService.getZakazMap(user.getId()));
                editMessageText.setReplyMarkup(BuscetService.savatMenu());
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                break;
            case "karyera":
                editMessageText.setText("OqTepa Lavash ish joylari uchun bot. Mavjud vakansiyalar " +
                        "to'g'risida bilib oling va @RabotaOqTepaBot -da anketani to'ldiring");
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                RepositoryList.userButton.remove(user.getId().toString());
                break;
            case "tilniozgartirish":
                editMessageText.setText("🤣🤣🤣🤣🤣🤣🤣🤣🤣🤣🤣🤣\n\nVaqtim bo'lganida qilaman");
                RepositoryList.userButton.put(user.getId().toString(), text[2]);
                RepositoryList.userButton.remove(user.getId().toString());
                break;
            default:
                MessageUtil.notFoundMessage(user.getId());
                break;
        }
    }

}
