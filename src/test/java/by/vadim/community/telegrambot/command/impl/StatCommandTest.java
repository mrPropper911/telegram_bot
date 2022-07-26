package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.AbstractCommandTest;
import by.vadim.community.telegrambot.command.Command;

import static by.vadim.community.telegrambot.command.CommandName.STAT;
import static by.vadim.community.telegrambot.command.impl.StatCommand.STAT_MESSAGE;

class StatCommandTest extends AbstractCommandTest {

    @Override
    public String getCommandName() {
        return STAT.getCommandName();
    }

    @Override
    public String getCommandMessage() {
        return String.format(STAT_MESSAGE, 0) ;
    }

    @Override
    protected Command getCommand() {
        return new StatCommand(sendBotMessageService, telegramUserService);
    }
}