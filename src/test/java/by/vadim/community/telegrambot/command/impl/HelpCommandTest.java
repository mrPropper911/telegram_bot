package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.AbstractCommandTest;
import by.vadim.community.telegrambot.command.Command;
import org.junit.jupiter.api.DisplayName;

import static by.vadim.community.telegrambot.command.CommandName.HELP;
import static by.vadim.community.telegrambot.command.impl.HelpCommand.HELP_MESSAGE;

@DisplayName("Unit-level testing for HelpCommand")
class HelpCommandTest extends AbstractCommandTest {

    @Override
    public String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    public String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    public Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}