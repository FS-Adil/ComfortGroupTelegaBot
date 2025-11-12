package com.example.comfortgrouptelegabot.telegram.command;

import com.example.comfortgrouptelegabot.util.Consts;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartCommand implements Command{
    @Override
    public SendMessage apply(Update update) {
        String userName = update.getMessage().getChat().getUserName();
        String chatId = update.getMessage().getChatId().toString();
        var formattedText = String.format(Consts.START_MESSAGE, userName);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(formattedText);

        addReplyKeyboard(sendMessage);

        return sendMessage;
    }

    private void addReplyKeyboard(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true); // Автоматическое изменение размера
        keyboardMarkup.setOneTimeKeyboard(false); // Клавиатура остается открытой

        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первый ряд кнопок
        KeyboardRow row1 = new KeyboardRow();
        row1.add("📋 Меню");
        row1.add("ℹ️ О нас");

        // Второй ряд кнопок
        KeyboardRow row2 = new KeyboardRow();
        row2.add("📞 Контакты");
        row2.add("⚙️ Настройки");

        keyboard.add(row1);
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);
    }
}
