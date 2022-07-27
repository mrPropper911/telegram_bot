package by.vadim.community.telegrambot.service;

import by.vadim.community.telegrambot.javarushclient.dto.GroupDiscussionInfo;
import by.vadim.community.telegrambot.repository.entity.GroupSub;

import java.util.Optional;

/**
 * Service for manipulating with {@link GroupSub}.
 */
public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
    GroupSub save(GroupSub groupSub);

    Optional<GroupSub> findById(Integer id);

}
