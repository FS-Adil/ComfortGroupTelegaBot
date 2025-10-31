package com.example.comfortgrouptelegabot.telegram;

import com.example.comfortgrouptelegabot.config.BotProperties;

import com.example.comfortgrouptelegabot.telegram.commands.CommandsHandler;
import com.example.comfortgrouptelegabot.utils.Consts;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final String botUserName;

    private final CommandsHandler commandsHandler;

    public TelegramBot(
            BotProperties botProperties,
            CommandsHandler commandsHandler
    ) {
        super(botProperties.getToken());

        this.botUserName = botProperties.getUsername();
        this.commandsHandler = commandsHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            if (update.getMessage().getText().startsWith("/")) {
                sendMessage(commandsHandler.handleCommands(update));
            } else {
                sendMessage(new SendMessage(chatId, Consts.CANT_UNDERSTAND));
            }
        } else if (update.hasCallbackQuery()) {
            unknownCommand(update);
        } else {
            unknownCommand(update);
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    private void unknownCommand(Update update) {
        sendMessage(new SendMessage(String.valueOf(update.getMessage().getChatId()), Consts.UNKNOWN_COMMAND));
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
