package com.example.comfortgrouptelegabot.telegram.command;

import com.example.comfortgrouptelegabot.onec.dto.ClosedRollValue;
import com.example.comfortgrouptelegabot.onec.service.ClosedRollService;
import com.example.comfortgrouptelegabot.util.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuCommand implements Command {
    private final ClosedRollService closedRollService;

    @Override
    public SendMessage apply(Update update) {

        String chatId = update.getMessage().getChatId().toString();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(Consts.MENU_MESSAGE);

        addInlineKeyboard(sendMessage);

        return sendMessage;
    }

    private void addInlineKeyboard(SendMessage sendMessage) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();

        // Кнопка с callback data
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Остатки на складе 'Закрытые рулоны'");
        button1.setCallbackData("closed_rolls");

        // Кнопка с URL
//        InlineKeyboardButton button2 = new InlineKeyboardButton();
//        button2.setText("Наш сайт");
//        button2.setUrl("https://example.com");

        // Кнопка для обратного вызова
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Помощь");
        button3.setCallbackData("help");

        rowInline1.add(button1);
//        rowInline1.add(button2);
        rowInline2.add(button3);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);
    }
}
