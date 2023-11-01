package ru.ceki.fgiski2.logbot;

import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import ru.ceki.fgiski2.logbot.dto.LogDto;

@Component
public class LogBot extends TelegramLongPollingBot {
    
    @Override
    public String getBotToken() {
        return ApplicationHelper.getPropertyLogBotBotToken();
    }

    private void feedback(List<LogDto> list) {
        if (list.isEmpty()) {
        } else {
        }
    }
}
