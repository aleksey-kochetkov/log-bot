package ru.ceki.fgiski2.logbot.helper;

import java.util.concurrent.ArrayBlockingQueue;
import ru.ceki.fgiski2.logbot.dto.QueueElement;

public class NormalizedBlockingQueue extends ArrayBlockingQueue<QueueElement> {

    public NormalizedBlockingQueue() {
        super(1);
    }

    @Override
    public boolean add(QueueElement element) {
        try {
            super.put(element);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        return true;
    }

    @Override
    public QueueElement remove() {
        try {
            return super.take();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }
}
