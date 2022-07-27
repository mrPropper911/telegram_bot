package by.vadim.community.telegrambot.service.impl;

import by.vadim.community.telegrambot.javarushclient.dto.GroupDiscussionInfo;
import by.vadim.community.telegrambot.repository.GroupSubsRepository;
import by.vadim.community.telegrambot.repository.entity.GroupSub;
import by.vadim.community.telegrambot.repository.entity.TelegramUser;
import by.vadim.community.telegrambot.service.GroupSubService;
import by.vadim.community.telegrambot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service
public class GroupSubServiceImpl implements GroupSubService {

    private final GroupSubsRepository groupSubsRepository;
    private final TelegramUserService telegramUserService;

    @Autowired
    public GroupSubServiceImpl(GroupSubsRepository groupSubsRepository,
                               TelegramUserService telegramUserService) {
        this.groupSubsRepository = groupSubsRepository;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {
        TelegramUser telegramUser = telegramUserService
                .findByChatId(chatId)
                .orElseThrow(NotFoundException::new);
        GroupSub groupSub;
        Optional<GroupSub> groupSubFromBD =
                groupSubsRepository.findById(groupDiscussionInfo.getId());
        if (groupSubFromBD.isPresent()){
            groupSub = groupSubFromBD.get();
            Optional<TelegramUser> first = groupSub.getUsers().stream()
                    .filter(it-> it.getChatId().equalsIgnoreCase(chatId))
                    .findFirst();
            if (first.isEmpty()){
                groupSub.addUser(telegramUser);
            }
        } else {
            groupSub = new GroupSub();
            groupSub.addUser(telegramUser);
            groupSub.setId(groupDiscussionInfo.getId());
            groupSub.setTitle(groupDiscussionInfo.getTitle());
        }
        return groupSubsRepository.save(groupSub);
    }

    @Override
    public GroupSub save(GroupSub groupSub) {
        return groupSubsRepository.save(groupSub);
    }

    @Override
    public Optional<GroupSub> findById(Integer id) {
        return groupSubsRepository.findById(id);
    }

}
