package ru.ceki.fgiski2.logbot.helper;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHelper {
    private static final Logger LOGGER =
                        LoggerFactory.getLogger(ApplicationHelper.class);
    private static final String NAME = "log-bot.properties";
    private static String logBotBotUsername;
    private static String logBotBotToken;
    private static String logBotChatId;
    private static Long logBotCurrentId;

    static {
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            LOGGER.error(StringHelper.getStackTrace(exception));
        }
    }

    public static String getPropertyLogBotBotUsername() {
        return logBotBotUsername;
    }

    public static String getPropertyLogBotBotToken() {
        return logBotBotToken;
    }

    public static String getPropertyLogBotChatId() {
        return logBotChatId;
    }

    public static long getPropertyLogBotCurrentId() {
        return logBotCurrentId;
    }
}
