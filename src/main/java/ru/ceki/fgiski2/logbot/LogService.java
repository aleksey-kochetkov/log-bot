package ru.ceki.fgiski2.logbot;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.generics.BotSession;
import ru.ceki.fgiski2.logbot.helper.StringHelper;
import ru.ceki.fgiski2.logbot.helper.ApplicationHelper;
import ru.ceki.fgiski2.logbot.helper.NormalizedBlockingQueue;
import ru.ceki.fgiski2.logbot.dto.QueueElement;
import ru.ceki.fgiski2.logbot.dto.QueueElementImpl;
import ru.ceki.fgiski2.logbot.dto.ShutdownElement;

@Service
public class LogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);
    private BotSession botSession;
    private CountDownLatch running;
    private final Queue<QueueElement> queue = new NormalizedBlockingQueue();
    private final Executor executor = Executors.newCachedThreadPool();
// отключение сообщений о собственных ошибках    
    private boolean suppressLogBotSelfReport = false;

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::waitToShutdown));
        this.running = new CountDownLatch(1);
        new Thread(this::run).start();
    }

    public void run() {
        try {
            while (this.running.getCount() == 1) {
                try {
                    QueueElement element;
                    element = this.queue.remove();
                    if (element == QueueElement.SHUTDOWN) {
                        this.running.countDown();
                        this.processShutdown((ShutdownElement)element);
                    } else if (element instanceof QueueElementImpl) {
                        this.processElement((QueueElementImpl)element);
                    }
                } catch (Throwable throwable) {
                    LOGGER.error(StringHelper.getStackTrace(throwable));
                    ApplicationHelper.sleep(3000);
                }
            }
        } catch (Exception exception) {
            LOGGER.error(StringHelper.getStackTrace(exception));
        } finally {
            this.stopBotSession();
            this.running.countDown();
        }
    }

    public void waitToShutdown() {
        this.suppressLogBotSelfReport = true;
        try {
            this.stopBotSession();
            this.queue.add(QueueElement.SHUTDOWN);
            if (this.executor instanceof ThreadPoolExecutor) {
                ((ThreadPoolExecutor)this.executor).shutdown();
            }
            this.running.await(5, TimeUnit.SECONDS);
            LOGGER.info("queue.size():{}", this.queue.size());
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void processElement(QueueElementImpl element) {
        if (this.elementException) {
            ApplicationHelper.sleep(3000); // we don't want exceptions flood
            elementException = false;
        }
        this.executor.execute(() -> {
                try {
                    logic.processElement(element);}
                catch (Throwable throwable) {
                    elementException = true;
                    LOGGER.error(StringHelper.getStackTrace(throwable));
                    if (!this.suppressLogBotSelfReport) {
                        element.onProcessed(ObjectHelper.newLogDto(throwable));
                    }}});
    }

    private void processShutdown(ShutdownElement element) {
        this.executor.execute(() -> {
                try {
                    element.onProcessed();}
                catch (Throwable throwable) {
                    LOGGER.error(StringHelper.getStackTrace(throwable));}});
    }

    private void stopBotSession() {
        if (this.botSession.isRunning()) {
            this.botSession.stop();
        }
    }

    public void setBotSession(BotSession botSession) {
        this.botSession = botSession;
    }

    public Queue<QueueElement> getQueue() {
        return this.queue;
    }
}