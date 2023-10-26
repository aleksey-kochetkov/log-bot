package ru.ceki.fgiski2.logbot;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class LogBotApplication {
    @Autowired
    private LogBot logBot;
    @Autowired
    private LogService logService;

    public static void main(String[] args) {
        SpringApplication.run(LogBotApplication.class);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
          this.logBot.setQueue(this.logService.getQueue());
          TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
          this.logService.setBotSession(botsApi.registerBot(logBot));
          this.logService.start();
          this.logBot.initDataSearch();
        };
    }
}