package ru.ceki.fgiski2.logbot;

import java.util.Queue;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.generics.BotSession;
import ru.ceki.fgiski2.logbot.helper.NormalizedBlockingQueue;
import ru.ceki.fgiski2.logbot.dto.QueueElement;

@Service
public class LogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);
    private final Queue<QueueElement> queue = new NormalizedBlockingQueue();

    public void setBotSession(BotSession botSession) {
        this.botSession = botSession;
    }

    public Queue<QueueElement> getQueue() {
        return this.queue;
    }
}