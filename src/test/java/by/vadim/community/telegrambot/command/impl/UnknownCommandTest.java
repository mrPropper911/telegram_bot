package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.AbstractCommandTest;
import by.vadim.community.telegrambot.command.Command;

import static by.vadim.community.telegrambot.command.CommandName.NO;
import static by.vadim.community.telegrambot.command.impl.UnknownCommand.UNKNOWN_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class UnknownCommandTest extends AbstractCommandTest{

    @Override
    public String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    public String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    protected Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}