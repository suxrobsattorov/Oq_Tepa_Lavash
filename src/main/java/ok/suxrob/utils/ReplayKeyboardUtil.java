package ok.suxrob.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReplayKeyboardUtil {
    public static KeyboardButton button(String name) {
        return new KeyboardButton(name);
    }

    public static KeyboardRow row(KeyboardButton... buttons) {
        return new KeyboardRow(Arrays.asList(buttons));
    }

    public static List<KeyboardRow> rowlist(KeyboardRow... rows) {
        return new ArrayList<>(Arrays.asList(rows));
    }

    public static ReplyKeyboardMarkup keyboard(List<KeyboardRow> row) {
        return new ReplyKeyboardMarkup(row);
    }
}
