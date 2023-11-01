package ru.ceki.fgiski2.logbot.helper;

import org.springframework.stereotype.Component;

@Component
public class ApplicationHelper {
    private static final Logger LOGGER =
                        LoggerFactory.getLogger(ApplicationHelper.class);
    private static final String NAME = "log-bot.properties";
}
