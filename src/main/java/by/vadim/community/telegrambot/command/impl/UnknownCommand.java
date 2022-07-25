package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.Command;
import by.vadim.community.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Unknown {@link Command}.
 */
public class UnknownCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public static final String UNKNOWN_MESSAGE = "Не понимаю вас \uD83D\uDE1F, напишите /help чтобы узнать что я понимаю.";

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),UNKNOWN_MESSAGE);
    }
}
