package ru.ceki.fgiski2.logbot.helper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;

@Component
public class ApplicationHelper {
    private static final Logger LOGGER =
                        LoggerFactory.getLogger(ApplicationHelper.class);
    private static final String NAME = "log-bot.properties";
    private static String logBotBotUsername;
    private static String logBotBotToken;
    private static String logBotChatId;
    private static int logBotDataSearchInterval = 1000;
    private static int logBotSendingInterval = 5000;
    private static Long logBotCurrentId;

    static {
    }

    public static void init() {
        Properties file = new Properties();
        try (FileInputStream in = new FileInputStream(NAME)) {
            file.load(in);
            logBotCurrentId =
                  Long.parseLong(file.getProperty("log-bot.current-id"));
        } catch (Throwable exception) {
            LOGGER.error(StringHelper.getStackTrace(exception));
            try (FileInputStream in = new FileInputStream(
                                     System.getProperty("java.io.tmpdir")
                                          + File.separatorChar + NAME)) {
                file.load(in);
                logBotCurrentId =
                  Long.parseLong(file.getProperty("log-bot.current-id"));
            } catch (Throwable exc) {
                LOGGER.error(StringHelper.getStackTrace(exc));
            }
        }
    }

    public static void init(ApplicationContext ctx) {
        init();
        setApplicationContext_internal(ctx);
    }

    private static
            void setApplicationContext_internal(ApplicationContext ctx) {
        logBotBotUsername = ctx.getEnvironment()
                          .getProperty("log-bot.bot-username");
        logBotBotToken = ctx.getEnvironment()
                             .getProperty("log-bot.bot-token");
        logBotChatId = ctx.getEnvironment()
                               .getProperty("log-bot.chat-id");
        try {
            logBotDataSearchInterval = Integer.parseInt(
                    ctx.getEnvironment()
                           .getProperty("log-bot.data-search-interval"));
        } catch (Exception exception) {
            LOGGER.error(StringHelper.getStackTrace(exception));
        }
        try {
            logBotSendingInterval = Integer.parseInt(
                    ctx.getEnvironment()
                               .getProperty("log-bot.sending-interval"));
        } catch (Exception exception) {
            LOGGER.error(StringHelper.getStackTrace(exception));
        }
        if (logBotCurrentId == null) {
            try {
                logBotCurrentId = Long.parseLong(ctx.getEnvironment()
                                 .getProperty("log-bot.current-id"));
            } catch (NumberFormatException exception) {
                LOGGER.error(StringHelper.getStackTrace(exception));
                logBotCurrentId = Long.MIN_VALUE;
            }
        }
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
