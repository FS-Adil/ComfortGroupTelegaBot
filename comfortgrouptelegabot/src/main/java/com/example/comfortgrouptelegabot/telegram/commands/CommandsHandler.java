package com.example.comfortgrouptelegabot.telegram.commands;

import com.example.comfortgrouptelegabot.utils.Consts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
@Slf4j
public class CommandsHandler {
    private final Map<String, Command> commandMap;

    public CommandsHandler(StartCommand startCommand) {
        this.commandMap = Map.of(
                "/start", startCommand
        );
    }

    public SendMessage handleCommands(Update update) {
        String command = update.getMessage().getText();

        var commandsHandler = commandMap.get(command);

        if (commandsHandler != null) {
            return commandsHandler.apply(update);
        } else {
            return new SendMessage(update.getMessage().getChatId().toString(), Consts.UNKNOWN_COMMAND);
        }
    }
}
