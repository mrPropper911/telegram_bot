package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.AbstractCommandTest;
import by.vadim.community.telegrambot.command.Command;

import static by.vadim.community.telegrambot.command.CommandName.NO;
import static by.vadim.community.telegrambot.command.impl.NoCommand.NO_MESSAGE;

import static org.junit.jupiter.api.Assertions.*;

class NoCommandTest extends AbstractCommandTest{

    @Override
    public String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    public String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    protected Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}