package ru.ceki.fgiski2.logbot;

import ru.ceki.fgiski2.logbot.dto.QueueElementImpl;

public interface LogBotLogic {
    void processElement(QueueElementImpl element);
}
