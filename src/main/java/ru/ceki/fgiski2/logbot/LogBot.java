package ru.ceki.fgiski2.logbot;

import java.util.Queue;
import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.ceki.fgiski2.logbot.helper.ApplicationHelper;
import ru.ceki.fgiski2.logbot.dto.QueueElement;
import ru.ceki.fgiski2.logbot.dto.ShutdownElement;
import ru.ceki.fgiski2.logbot.dto.QueueElementImpl;
import ru.ceki.fgiski2.logbot.dto.LogDto;

@Component
public class LogBot extends TelegramLongPollingBot {
    private Queue<QueueElement> consumer;

    public LogBot() {
        ((ShutdownElement)QueueElement.SHUTDOWN)
                                    .setRunnable(this::shutdownFeedback);
    }
    
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

    public void initDataSearch() {
        this.consumer.add(new QueueElementImpl(this::feedback));
    }

    private void feedback(List<LogDto> list) {
        if (list.isEmpty()) {
        } else {
        }
    }
}
