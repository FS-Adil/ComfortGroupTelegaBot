package com.example.comfortgrouptelegabot.telegram;

import com.example.comfortgrouptelegabot.config.BotProperties;

import com.example.comfortgrouptelegabot.telegram.commands.CommandsHandler;
import com.example.comfortgrouptelegabot.telegram.services.UserSessionService;
import com.example.comfortgrouptelegabot.utils.Consts;
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

    private final UserSessionService userSessionService;

    public TelegramBot(
            BotProperties botProperties,
            CommandsHandler commandsHandler,
            UserSessionService userSessionService
    ) {
        super(botProperties.getToken());

        this.botUserName = botProperties.getUsername();
        this.commandsHandler = commandsHandler;
        this.userSessionService = userSessionService;
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
            unknownCommand(update);
        } else {
            unknownCommand(update);
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    private void startMessage(Update update) {
        String userName = update.getMessage().getChat().getUserName();
        var text = """
                %s добро пожаловать в бот компании "Группа Комфорт"!
                
                Здесь Вы сможете узнать следующую информацию о производственных предприятиях:
                 - Остатки закрытых рулонов
                
                Для этого воспользуйтесь командой:
                /start - запуск программы
                
                Дополнительные команды:
                /help - получение справки
                """;
        var formattedText = String.format(text, userName);
        sendMessage(new SendMessage(update.getMessage().getChatId().toString(), formattedText));
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
