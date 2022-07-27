package by.vadim.community.telegrambot.command;

import by.vadim.community.telegrambot.command.impl.*;
import by.vadim.community.telegrambot.javarushclient.JavaRushGroupClient;
import by.vadim.community.telegrambot.service.GroupSubService;
import by.vadim.community.telegrambot.service.SendBotMessageService;
import by.vadim.community.telegrambot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

import static by.vadim.community.telegrambot.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final ImmutableMap<Object,Object> commandImmutableMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            TelegramUserService telegramUserService,
                            JavaRushGroupClient javaRushGroupClient,
                            GroupSubService groupSubService) {
        commandImmutableMap = ImmutableMap.<Object, Object>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService,telegramUserService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubsCommand(sendBotMessageService,javaRushGroupClient,groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
                .put(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubsCommand(telegramUserService, sendBotMessageService, groupSubService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier){
        return (Command) commandImmutableMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
