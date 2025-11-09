package com.example.comfortgrouptelegabot.telegram.command;

import com.example.comfortgrouptelegabot.onec.dto.ClosedRollValue;
import com.example.comfortgrouptelegabot.onec.service.ClosedRollService;
import com.example.comfortgrouptelegabot.util.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

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

        List<ClosedRollValue> closedRollValueList = closedRollService.findAllClosedRoll();

        String text;
        int size = closedRollValueList.size();
        if (size == 0) {
            text = "На складе 'Закрытые рулоны' на данный момент нет рулонов.";
        } else if (size == 1) {
            text = "На складе 'Закрытые рулоны' на данный момент 1 рулон.";
        } else if (size <= 4) {
            text = String.format("На складе 'Закрытые рулоны' на данный момент %s рулона.", size);
        } else {
            text = String.format("На складе 'Закрытые рулоны' на данный момент %s рулонов.", size);
        }

        sendMessage.setText(text);

        return sendMessage;
    }
}
