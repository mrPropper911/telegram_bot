package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.Command;
import by.vadim.community.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command{

    private final SendBotMessageService sendBotMessageService;

    public StopCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public static final String STOP_MESSAGE = "Деактивировал все ваши подписки \uD83D\uDE1F.";

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}
