package by.vadim.community.telegrambot.repository;

import by.vadim.community.telegrambot.repository.entity.GroupSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupSubsRepository extends JpaRepository<GroupSub, Integer> {
}
