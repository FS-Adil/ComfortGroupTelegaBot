package com.example.comfortgrouptelegabot.telegram.callback;

import com.example.comfortgrouptelegabot.telegram.command.Command;
import com.example.comfortgrouptelegabot.util.Consts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
@Slf4j
public class CallbacksHandler {
    private final Map<String, CallbackHandler> callbackMap;

    public CallbacksHandler(ClosedRollsCallback closedRollsCallback) {
        this.callbackMap = Map.of(
                "closed_rolls", closedRollsCallback
        );
    }

    public SendMessage handleCallbacks(Update update) {
        String callbackData = update.getCallbackQuery().getData();

        var commandsHandler = callbackMap.get(callbackData);

        if (commandsHandler != null) {
            return commandsHandler.apply(update);
        } else {
            return new SendMessage(update.getMessage().getChatId().toString(), Consts.UNKNOWN_COMMAND);
        }
    }
}
