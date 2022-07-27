package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.Command;
import by.vadim.community.telegrambot.repository.entity.GroupSub;
import by.vadim.community.telegrambot.repository.entity.TelegramUser;
import by.vadim.community.telegrambot.service.GroupSubService;
import by.vadim.community.telegrambot.service.SendBotMessageService;
import by.vadim.community.telegrambot.service.TelegramUserService;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.vadim.community.telegrambot.command.CommandName.DELETE_GROUP_SUB;
import static by.vadim.community.telegrambot.command.CommandUtils.getChatId;
import static by.vadim.community.telegrambot.command.CommandUtils.getMessage;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class DeleteGroupSubsCommand implements Command {

    private final TelegramUserService telegramUserService;
    private final SendBotMessageService sendBotMessageService;
    private final GroupSubService groupSubService;

    String messageAfterDelete = "Группа %s успешно удалена";

    public DeleteGroupSubsCommand(TelegramUserService telegramUserService,
                                  SendBotMessageService sendBotMessageService,
                                  GroupSubService groupSubService) {
        this.telegramUserService = telegramUserService;
        this.sendBotMessageService = sendBotMessageService;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(DELETE_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update));
            return;
        }
        String groupId = update.getMessage().getText().split(SPACE)[1];
        String chatId = update.getMessage().getChatId().toString();

        if (isNumeric(groupId)){
            Optional<GroupSub> groupSubFromBD = groupSubService.findById(Integer.valueOf(groupId));
            if (groupSubFromBD.isPresent()){
                GroupSub groupSub = groupSubFromBD.get();
                TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
                groupSub.getUsers().remove(telegramUser);
                groupSubService.save(groupSub);
                sendBotMessageService.sendMessage(chatId, format("Удалил подписку на группу: %s", groupSub.getTitle()));
            } else {
                sendBotMessageService.sendMessage(chatId, "Не нашел такой группы =/");
            }
        } else {
            sendBotMessageService.sendMessage(chatId, "неправильный формат ID группы.\n " +
                    "ID должно быть целым положительным числом");
        }
    }

    private void sendGroupIdList(String chatId) {
        String message;
        List<GroupSub> groupSubs = telegramUserService.findByChatId(chatId)
                .orElseThrow(NotFoundException::new)
                .getGroupSubs();
        if (CollectionUtils.isEmpty(groupSubs)) {
            message = "Пока нет подписок на группы. Чтобы добавить подписку напиши /addGroupSub";
        } else {
            message = "Чтобы удалить подписку на группу - передай комадну вместе с ID группы. \n" +
                    "Например: /deleteGroupSub 16 \n\n" +
                    "я подготовил список всех групп, на которые ты подписан) \n\n" +
                    "имя группы - ID группы \n\n" +
                    "%s";

        }
        String userGroupSubData = groupSubs.stream()
                .map(group -> format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        sendBotMessageService.sendMessage(chatId, format(message, userGroupSubData));
    }
}
