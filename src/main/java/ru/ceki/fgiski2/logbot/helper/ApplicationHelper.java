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

    static {
    }

    public static String getPropertyLogBotBotUsername() {
        return logBotBotUsername;
    }

    public static String getPropertyLogBotBotToken() {
        return logBotBotToken;
    }
}
