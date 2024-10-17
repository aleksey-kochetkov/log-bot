package ru.ceki.fgiski2.logbot.repository;

import java.util.Optional;
import ru.ceki.fgiski2.logbot.model.User;

public interface UserRepository {
    Optional<User> findById(Long id);
}
