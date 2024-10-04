package ru.ceki.fgiski2.logbot.dto;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class QueueElementImpl implements QueueElement {
    private final Consumer<List<LogDto>> consumer;

    public QueueElementImpl(Consumer<List<LogDto>> consumer) {
        this.consumer = consumer;
    }

    public void onProcessed(List<LogDto> list) {
        this.consumer.accept(list);
    }

    public void onProcessed(LogDto logDto) {
        this.onProcessed(Arrays.asList(logDto));
    }
}
