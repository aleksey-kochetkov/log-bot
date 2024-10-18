package ru.ceki.fgiski2.logbot.helper;

import org.springframework.stereotype.Component;
import ru.ceki.fgiski2.logbot.dto.LogDto;
import ru.ceki.fgiski2.logbot.model.Log;
import ru.ceki.fgiski2.logbot.repository.UserRepository;

@Component
public class ObjectHelper {
    private static final String STR = "Пользователь не определён";
    private static UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepositoryJpa) {
        ObjectHelper.userRepository = userRepositoryJpa;
    }

    public static LogDto newLogDto(Log log) {
        LogDto result = new LogDto();
        result.setId(log.getId());
        result.setCreatedAt(log.getCreatedAt());
        result.setUserStr(log.getUserId() == null ? STR
                 : String.format("%s (%s)", calcUserStr(log.getUserId()),
                                                       log.getUserId()));
        result.setDescription(log.getDescription());
        return result;
    }

    private static String calcUserStr(Long userId) {
        return userRepository.findById(userId).map(user
          -> user.getLastName() + ' ' + user.getFirstName()
                               + ' ' + user.getMiddleName()
                                             ).orElse(STR);
    }

    public static LogDto newLogDto(Throwable throwable) {
        LogDto result = new LogDto();
        result.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        result.setUserStr("Log Bot");
        result.setDescription(StringHelper.getStackTrace(throwable));
        return result;
    }
}
