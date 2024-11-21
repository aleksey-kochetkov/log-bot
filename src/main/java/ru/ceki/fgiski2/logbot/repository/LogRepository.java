package ru.ceki.fgiski2.logbot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ceki.fgiski2.logbot.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByIdGreaterThan(Long id);
}
