package ok.suxrob.controller;

import ok.suxrob.dto.CodeMessage;
import ok.suxrob.enums.CodeMessageType;
import ok.suxrob.utils.InlineKeyboardUtil;
import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class WelcomeController {
    public CodeMessage start(Message message) {
        SendMessage sendMessage = new SendMessage();
        CodeMessage codeMessage=new CodeMessage();
        sendMessage.setChatId(message.getChatId().toString());

        String responseText = "Buyurtmani birga joylashtiramizmi? 🤗";
        sendMessage.setReplyMarkup(menuKeyboad());
        sendMessage.setText(responseText);
        codeMessage.setSendMessage(sendMessage);
        codeMessage.setType(CodeMessageType.MESSAGE);
        return codeMessage;
    }

    public static InlineKeyboardMarkup menuKeyboad() {
        List<List<InlineKeyboardButton>> rowList = InlineKeyboardUtil.rowlist(
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🌯 Lavash", "/oqtepa/lavash"),
                        InlineKeyboardUtil.button("🍰 Desert", "/oqtepa/desert")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🍱 Set", "/oqtepa/set"),
                        InlineKeyboardUtil.button("🥙 Xaggi", "/oqtepa/xaggi")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🍔 Burger", "/oqtepa/burger"),
                        InlineKeyboardUtil.button("🍕 Pizza", "/oqtepa/pizza")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🥪 Sendvich", "/oqtepa/sendvich"),
                        InlineKeyboardUtil.button("🥙 Doner", "/oqtepa/doner")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🌭 Xot-Dog", "/oqtepa/xotdog"),
                        InlineKeyboardUtil.button("🍟 Fri", "/oqtepa/fri")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🍹 Napitki", "/oqtepa/napitki"),
                        InlineKeyboardUtil.button("🍛 Salat", "/oqtepa/salat")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🍞 Non", "/oqtepa/non"),
                        InlineKeyboardUtil.button("🍅 Sous", "/oqtepa/sous")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("📝 Buyurtmalar tarixi", "/oqtepa/buyurtmalartarixi")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("🛒 Savatchaga o'tish", "/oqtepa/savatcha")
                ),
                InlineKeyboardUtil.row(
                        InlineKeyboardUtil.button("Karyera", "/oqtepa/karyera"),
                        InlineKeyboardUtil.button("Tilni o'zgartirish", "/oqtepa/tilniozgartirish")
                )
        );
        return InlineKeyboardUtil.keyboard(rowList);
    }
}
