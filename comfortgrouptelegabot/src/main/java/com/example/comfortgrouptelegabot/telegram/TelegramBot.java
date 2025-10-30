package com.example.comfortgrouptelegabot.telegram;

import com.example.comfortgrouptelegabot.config.BotProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final String botUserName;

    public TelegramBot(BotProperties botProperties) {
        super(botProperties.getToken());
        this.botUserName = botProperties.getUsername();
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }
}
