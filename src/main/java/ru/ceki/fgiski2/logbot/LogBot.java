package ru.ceki.fgiski2.logbot;

import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.ceki.fgiski2.logbot.helper.ApplicationHelper;
import ru.ceki.fgiski2.logbot.dto.LogDto;

@Component
public class LogBot extends TelegramLongPollingBot {
    
    public void setQueue(Queue<QueueElement> consumer) {
        this.consumer = consumer;
    }

    @Override
    public String getBotUsername() {
        return ApplicationHelper.getPropertyLogBotBotUsername();
    }

    @Override
    public String getBotToken() {
        return ApplicationHelper.getPropertyLogBotBotToken();
    }
    
    @Override
    public void onUpdateReceived(Update update) {
        int putDebugPoinHere = -1;
    }

    private void feedback(List<LogDto> list) {
        if (list.isEmpty()) {
        } else {
        }
    }
}
