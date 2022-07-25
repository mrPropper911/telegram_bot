package by.vadim.community.telegrambot.command;

import by.vadim.community.telegrambot.command.impl.*;
import by.vadim.community.telegrambot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import static by.vadim.community.telegrambot.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final ImmutableMap<Object,Object> commandImmutableMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandImmutableMap = ImmutableMap.<Object, Object>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier){
        return (Command) commandImmutableMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
