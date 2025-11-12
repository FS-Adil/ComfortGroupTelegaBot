package com.example.comfortgrouptelegabot.telegram;

import com.example.comfortgrouptelegabot.config.BotProperties;

import com.example.comfortgrouptelegabot.telegram.callback.CallbacksHandler;
import com.example.comfortgrouptelegabot.telegram.command.CommandsHandler;
import com.example.comfortgrouptelegabot.telegram.service.UserSessionService;
import com.example.comfortgrouptelegabot.util.Consts;
import lombok.extern.slf4j.Slf4j;
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
    private final CallbacksHandler callbacksHandler;

    private final UserSessionService userSessionService;

    public TelegramBot(
            BotProperties botProperties,
            CommandsHandler commandsHandler,
            UserSessionService userSessionService,
            CallbacksHandler callbacksHandler
    ) {
        super(botProperties.getToken());

        this.botUserName = botProperties.getUsername();
        this.commandsHandler = commandsHandler;
        this.userSessionService = userSessionService;
        this.callbacksHandler = callbacksHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
//        Long userId = update.getMessage().getFrom().getId();

//        if (userSessionService.isFirstVisit(userId)) {
//            startMessage(update);
//            userSessionService.markAsVisited(userId);
//        }

        if (update.hasMessage() && update.getMessage().hasText()) {

            sendMessage(commandsHandler.handleCommands(update));

        } else if (update.hasCallbackQuery()) {
            sendMessage(callbacksHandler.handleCallbacks(update));
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
