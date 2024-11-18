package ru.ceki.fgiski2.logbot;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import ru.ceki.fgiski2.logbot.helper.ApplicationHelper;
import ru.ceki.fgiski2.logbot.dto.QueueElementImpl;
import ru.ceki.fgiski2.logbot.dto.LogDto;

@Service
public class LogBotLogicImpl implements LogBotLogic {
    @Override
    public synchronized void processElement(QueueElementImpl element) {
        Long currentId = ApplicationHelper.getPropertyLogBotCurrentId();
        List<LogDto> list = new ArrayList<>();
        for (Log l : this.logRepository.findByIdGreaterThan(currentId)) {
            list.add(ObjectHelper.newLogDto(l));
            if (currentId < l.getId()) {
                currentId = l.getId();
            }
        }
        element.onProcessed(list);
    }
}