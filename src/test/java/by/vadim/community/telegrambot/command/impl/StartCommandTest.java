package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.AbstractCommandTest;
import by.vadim.community.telegrambot.command.Command;

import static by.vadim.community.telegrambot.command.CommandName.START;
import static by.vadim.community.telegrambot.command.impl.StartCommand.START_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class StartCommandTest extends AbstractCommandTest{

    @Override
    public String getCommandName() {
        return START.getCommandName();
    }

    @Override
    public String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    protected Command getCommand() {
        return new StartCommand(sendBotMessageService);
    }
}