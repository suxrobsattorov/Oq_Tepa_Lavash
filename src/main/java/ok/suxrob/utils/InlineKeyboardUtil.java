package ok.suxrob.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InlineKeyboardUtil {
    public static InlineKeyboardButton button(String name, String callBackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(name);
        button.setCallbackData(callBackData);
        return button;
    }

    public static List<InlineKeyboardButton> row(InlineKeyboardButton... buttons) {
        return new ArrayList<InlineKeyboardButton>(Arrays.asList(buttons));
    }

    public static List<List<InlineKeyboardButton>> rowlist(List<InlineKeyboardButton>... rows) {
        return new ArrayList<List<InlineKeyboardButton>>(Arrays.asList(rows));
    }

    public static InlineKeyboardMarkup keyboard(List<List<InlineKeyboardButton>> rowList) {
        return new InlineKeyboardMarkup(rowList);
    }
}
