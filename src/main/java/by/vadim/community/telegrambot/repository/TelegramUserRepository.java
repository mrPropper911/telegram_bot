package by.vadim.community.telegrambot.repository;

import by.vadim.community.telegrambot.repository.entity.TelegramUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link Repository} for handling with {@link TelegramUser} entity.
 */
@Repository
public interface TelegramUserRepository extends CrudRepository<TelegramUser, String> {
    List<TelegramUser> findAllByActiveTrue();
}
