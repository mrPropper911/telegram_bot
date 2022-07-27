package by.vadim.community.telegrambot.service;

import by.vadim.community.telegrambot.javarushclient.dto.GroupDiscussionInfo;
import by.vadim.community.telegrambot.repository.entity.GroupSub;

/**
 * Service for manipulating with {@link GroupSub}.
 */
public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}
