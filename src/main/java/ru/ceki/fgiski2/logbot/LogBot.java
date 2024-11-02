package ru.ceki.fgiski2.logbot;

import java.util.Queue;
import java.util.List;
import java.sql.Timestamp;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
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

    public void sendText(String chatId, String what) {
        SendMessage sm = SendMessage.builder()
                    .chatId(chatId) //Who are we sending a message to
                    .text(what).build();    //Message content
        try {
            this.execute(sm);
        } catch (TelegramApiException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void feedback(List<LogDto> list) {
        if (list.isEmpty()) {
        } else {
            for (LogDto l : list) {
            }
        }
    }

    private void shutdownFeedback() {
        this.sendText(ApplicationHelper.getPropertyLogBotChatId(),
         String.format("Дата: %s%nПользователь: %s%nОписание ошибки: %s",
                    new Timestamp(System.currentTimeMillis()).toString(),
                                                 "Log Bot", "SHUTDOWN"));
    }
}
