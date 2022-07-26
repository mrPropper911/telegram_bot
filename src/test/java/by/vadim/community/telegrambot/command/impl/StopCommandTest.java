package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.AbstractCommandTest;
import by.vadim.community.telegrambot.command.Command;

import static by.vadim.community.telegrambot.command.CommandName.STOP;
import static by.vadim.community.telegrambot.command.impl.StopCommand.STOP_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class StopCommandTest extends AbstractCommandTest {

    @Override
    public String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    public String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    protected Command getCommand() {
        return new StopCommand(sendBotMessageService, telegramUserService);
    }
}